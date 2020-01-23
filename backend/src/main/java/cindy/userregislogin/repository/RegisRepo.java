package cindy.userregislogin.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import cindy.userregislogin.model.Regis;

@Singleton
public class RegisRepo implements RegisInterface {
    @PersistenceContext
    private EntityManager entityManager;

    public RegisRepo(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Regis> findAll(int page, int limit) {
        TypedQuery<Regis> query = entityManager.createQuery("from Regis", Regis.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0).setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Regis save(@NotNull Regis regis) {
        entityManager.persist(regis);
        return regis;
    }

    @Transactional(readOnly = true)
    @Override
    public Long size() {
        return entityManager.createQuery("select count(*) from Regis", Long.class).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public Regis findById(@NotNull Long id) {
        return entityManager.find(Regis.class, id);
    }

    
}