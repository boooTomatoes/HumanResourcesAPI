package persistence.repository;

import jakarta.persistence.EntityManager;

import java.util.List;

public class GenericRepository<T,ID>{
    private final Class<T> entityClass;

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(ID id, EntityManager entityManager){
        try {
            return entityManager.find(entityClass,id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public T findReferenceById(ID id,EntityManager entityManager){
        //return entityManager.getReference(entityClass,id);
        return null;
    }
    public List<T> findAll(EntityManager entityManager){
        try {
            String query = String.format("SELECT t FROM %s t", entityClass.getSimpleName());
            return entityManager.createQuery(query,entityClass).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean save(T entity,EntityManager entityManager){
        try {
            entityManager.persist(entity);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean update(T entity ,EntityManager entityManager){
        try {
            entityManager.merge(entity);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean delete(T entity,EntityManager entityManager){
        try {
            entityManager.remove(entity);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
