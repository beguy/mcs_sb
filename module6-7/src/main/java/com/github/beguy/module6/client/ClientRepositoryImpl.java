//package com.github.beguy.module6.client;
//
//import com.github.beguy.module6.core.entity.JpaCrudRepositoryImpl;
//import com.github.beguy.module6.core.repository.RequestFilterToHqlCriteria;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TemporalType;
//import javax.transaction.Transactional;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Repository
//@Transactional
//public class ClientRepositoryImpl extends JpaCrudRepositoryImpl<Client, Long> implements ClientRepository {
//    @Override
//    @PersistenceContext
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public List<Client> findAll(Map<String, String> filters) {
//        // Learning crunch, see: JpaCriteriaAPI ||org.springframework.data.domain.Example;
//        StringBuilder queryString = new StringBuilder("select c from Client c where ");
//        // Available filters
//        Map<String, RequestFilterToHqlCriteria> filterRequestToCriteriaMap = Stream.of(new String[][]{
//                {"accountTypeName", "c.accountType.name=:accountTypeName"},
//                {"accountTypeNameContains", "c.accountType.name like '%'||:accountTypeNameContains||'%'"},
//                {"accountTypeNameStartsWith", "c.accountType.name like :accountTypeNameStartsWith||'%'"},
//                {"accountTypeNameEndsWith", "c.accountType.name like '%'||:accountTypeNameEndsWith"},
//                {"accountDate", "c.accountDate=:accountDate"}
//        }).collect(Collectors.toMap(data -> data[0], data -> new RequestFilterToHqlCriteria(data[0], data[1])));
//
//        // Build special filters.
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("y-M-d");
//        RequestFilterToHqlCriteria accountDate = filterRequestToCriteriaMap.get("accountDate");
//        accountDate.setJpqlCriteriaStringGenerator((thiz, requestParameter) -> {
//            if (requestParameter.equals("today")) {
//                return "c.accountDate=current_date()";
//            } else {
//                return thiz.getGeneratedJpqlCriteriaString();
//            }
//        });
//        accountDate.setQueryParameterSetter((thiz, query) -> {
//            if (thiz.getRequestParameter().equals("today")) {
//                // skip, criteria without params
//            } else {
//                try {
//                    query.setParameter(thiz.getRequestParameterName(), dateFormatter.parse(thiz.getRequestParameter()), TemporalType.DATE);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        List<RequestFilterToHqlCriteria> handledFilters = filters.entrySet()
//                .stream()
//                .filter(entry -> filterRequestToCriteriaMap.containsKey(entry.getKey()))
//                .map(entry -> {
//                    RequestFilterToHqlCriteria tmpCriteria = (RequestFilterToHqlCriteria) filterRequestToCriteriaMap.get(entry.getKey()).clone();
//                    tmpCriteria.setRequestParameter(entry.getValue());
//                    return tmpCriteria;
//                })
//                .collect(Collectors.toList());
//
//        queryString.append(
//                handledFilters
//                        .stream()
//                        .map(criteria -> criteria.getGeneratedJpqlCriteriaString())
//                        .collect(Collectors.joining(" and "))
//        );
//        Query query = entityManager.createQuery(queryString.toString());
//
//        handledFilters.stream().forEach(criteria -> criteria.setQueryParameter(query));
//        return query.getResultList();
//    }
//}
