package com.example.blogapp.repository.repositoriesImpl;

import com.example.blogapp.repository.repositories.BaseRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseRepositoryImpl<T, ID> implements BaseRepository<T, ID> {

    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    @Override
    public T save(T entity) {
        Object pk = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        if (pk == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    public void deleteById(ID id) {
        T entity = em.find(entityClass, id);
        if (entity != null) {
            em.remove(entity);
        }
    }
}
