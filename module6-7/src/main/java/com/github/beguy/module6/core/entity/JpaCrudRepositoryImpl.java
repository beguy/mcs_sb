//package com.github.beguy.module6.core.entity;
//
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.transaction.annotation.Transactional;
//import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Transactional(readOnly = true)
//public abstract class JpaCrudRepositoryImpl<T extends Object, ID extends Number> implements CrudRepository<T, ID> {
//    protected EntityManager entityManager;
//
//    abstract public void setEntityManager(EntityManager entityManager);
//
//    private Class<T> tClass;
//    private String jpaTableName;
//
//    public JpaCrudRepositoryImpl() {
//        this.tClass = (Class<T>) ((ParameterizedTypeImpl) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        jpaTableName = tClass.getTypeName()
//                + " entity";
//    }
//
//    @Override
//    @Transactional
//    public <S extends T> S save(S entity) {
//        entityManager.persist(entity);
//        return entity;
//    }
//
//    @Override
//    @Transactional
//    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
//        entities.forEach(entityManager::persist);
//        return entities;
//    }
//
//    @Override
//    public Optional<T> findById(ID id) {
//        return Optional.ofNullable(entityManager.find(tClass, id));
//    }
//
//    @Override
//    public boolean existsById(ID id) {
//        TypedQuery<Boolean> query = entityManager.createQuery(
//                "SELECT " +
//                        "CASE " +
//                        "WHEN COUNT(entity) > 0 THEN true ELSE false " +
//                        "END" +
//                        "FROM " + jpaTableName + " WHERE entity.id = :id",
//                Boolean.class
//        )
//                .setParameter("id", id);
//        return query.getSingleResult();
//    }
//
//    @Override
//    public List<T> findAll() {
//        return entityManager.createQuery("from " + jpaTableName).getResultList();
//    }
//
//    @Override
//    public Iterable<T> findAllById(Iterable<ID> ids) {
//        List<T> list = new ArrayList<>();
//        for (ID id : ids) {
//            T t = entityManager.find(tClass, id);
//            if (t != null) {
//                list.add(t);
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public long count() {
//        return entityManager.createQuery("select count(entity) from " + jpaTableName, Long.class).getSingleResult();
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(ID id) {
//        entityManager.remove(entityManager.find(tClass, id));
//    }
//
//    @Override
//    @Transactional
//    public void delete(T entity) {
//        entityManager.remove(entity);
//    }
//
//    @Override
//    @Transactional
//    public void deleteAll(Iterable<? extends T> entities) {
//        entities.forEach(entityManager::remove);
//    }
//
//    @Override
//    @Transactional
//    public void deleteAll() {
//        entityManager.createQuery("delete " + jpaTableName).executeUpdate();
//    }
//
//    @Transactional
//    public T update(T entity) {
//        return entityManager.merge(entity);
//    }
//}
