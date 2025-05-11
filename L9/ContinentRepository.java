package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.List;

public class ContinentRepository {
    private final EntityManager em;

    public ContinentRepository(EntityManager em) {
        this.em = em;
    }

    public Continent create(Continent continent) {
        List<Continent> existing = findByName(continent.getName());
        if (!existing.isEmpty()) {
            System.out.println(
                    "Continent '" + continent.getName() + "' already exists with ID: " + existing.get(0).getId());
            return existing.get(0);
        }

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(continent);
            transaction.commit();
            System.out.println("Created new continent: " + continent.getName() + " with ID: " + continent.getId());
            return continent;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Continent findById(int id) {
        return em.find(Continent.class, id);
    }

    public List<Continent> findByName(String name) {
        return em.createNamedQuery("Continent.findByName", Continent.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public Continent findByNameExact(String name) {
        try {
            return em.createQuery("SELECT c FROM Continent c WHERE c.name = :name", Continent.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}