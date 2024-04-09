package persistence.repository;

import controllers.rest.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityManager;

import java.util.Collections;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import persistence.entities.BaseEntity;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Slf4j
public class GenericRepository<T extends BaseEntity, ID> {
    private final Class<T> entityClass;

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(ID id, EntityManager entityManager) {

        return Optional.ofNullable(entityManager.find(entityClass, id))
                .orElseThrow(()->new ResourceNotFoundException("Entity not found with id: " + id));

    }

    public T findReferenceById(ID id, EntityManager entityManager) {
        return entityManager.getReference(entityClass,id);
    }

    public List<T> findAll(EntityManager entityManager) {
        try {
            String query = String.format("SELECT t FROM %s t", entityClass.getSimpleName());
            return entityManager.createQuery(query, entityClass).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean save(T entity, EntityManager entityManager) {
        try {
            entityManager.persist(entity);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean update(T entity, EntityManager entityManager) {
        try {
            entityManager.merge(entity);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean delete(T entity, EntityManager entityManager) {
        try {
            entityManager.remove(entity);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public List<T> findAllWithPagination(int pageNumber, int pageSize, EntityManager entityManager) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> rootEntry = cq.from(entityClass);
            CriteriaQuery<T> all = cq.select(rootEntry);

            TypedQuery<T> allQuery = entityManager.createQuery(all);
            allQuery.setFirstResult((pageNumber - 1) * pageSize);
            allQuery.setMaxResults(pageSize);

            return allQuery.getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
