package com.github.beguy.module6.client;

import com.github.beguy.module6.core.Dao.RequestFilterToHqlCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class ClientDao {
    @PersistenceContext
    private EntityManager em;

    public long save(Client client) {
        em.persist(client);
        return client.getId();
    }

    public Client find(long id) {
        return em.find(Client.class, id);
    }

    public void delete(Client client) {
        em.remove(client);
    }

    public void delete(long id) {
        em.remove(em.find(Client.class, id));
    }

    public void update(Client client) {
        em.merge(client);
    }

    public Client findById(long id) {
        return em.find(Client.class, id);
    }

    public List<Client> findAll() {
        Query query = em.createQuery(
                "from Client c");
        return query.getResultList();
    }

    public List<Client> findAll(Map<String, String> filters) {
        // Learning crunch, see: MapSqlParameterSource
        StringBuilder queryString = new StringBuilder("select c from Client c where ");
        // Available filters
        Map<String, RequestFilterToHqlCriteria> requestToCriteria = Stream.of(new String[][]{
                        {"accountType", "c.accountType.name=:accountType"},
                        {"accountTypeNameContains", "c.accountType.name like '%'||:accountTypeNameContains||'%'"},
                        {"accountTypeNameStartWith", "c.accountType.name like :accountTypeNameStartWith||'%'"},
                        {"accountTypeNameEndWith", "c.accountType.name like '%'||:accountTypeNameEndWith"},
                        {"accountDate", "c.accountDate=:accountDate"}
        }).collect(Collectors.toMap(data -> data[0], data -> new RequestFilterToHqlCriteria(data[0], data[1])));

        // Build special filters.
        SimpleDateFormat dateFormatter = new SimpleDateFormat("y-M-d");
        RequestFilterToHqlCriteria accountDate = requestToCriteria.get("accountDate");
        accountDate.setJpqlCriteriaStringGenerator((thiz, requestParameter) -> {
            if (requestParameter.equals("today")) {
                return "c.accountDate=current_date()";
            } else {
                return thiz.getGeneratedJpqlCriteriaString();
            }
        });
        accountDate.setQueryParameterSetter((thiz, query) -> {
            if (thiz.getRequestParameter().equals("today")) {
                // skip, criteria without params
            } else {
                try {
                    query.setParameter(thiz.getRequestParameterName(), dateFormatter.parse(thiz.getRequestParameter()), TemporalType.DATE);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        List<RequestFilterToHqlCriteria> handledFilters = filters.entrySet()
                .stream()
                .filter(entry -> requestToCriteria.containsKey(entry.getKey()))
                .map(entry -> {
                    RequestFilterToHqlCriteria tmpCriteria = (RequestFilterToHqlCriteria) requestToCriteria.get(entry.getKey()).clone();
                    tmpCriteria.setRequestParameter(entry.getValue());
                    return tmpCriteria;
                })
                .collect(Collectors.toList());

        queryString.append(
                handledFilters
                        .stream()
                        .map(criteria -> criteria.getGeneratedJpqlCriteriaString())
                        .collect(Collectors.joining(" and "))
        );
        Query query = em.createQuery(queryString.toString());
        handledFilters.stream().forEach(criteria -> criteria.setQueryParameter(query));
        return query.getResultList();
    }
}
