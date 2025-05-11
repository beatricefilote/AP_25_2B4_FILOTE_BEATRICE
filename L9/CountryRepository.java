package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.List;

public class CountryRepository {
    private final EntityManager em;

    public CountryRepository(EntityManager em) {
        this.em = em;
    }

    public Country create(Country country) {
        List<Country> existing = findByName(country.getName());
        if (!existing.isEmpty()) {
            System.out
                    .println("Country '" + country.getName() + "' already exists with ID: " + existing.get(0).getId());
            return existing.get(0);
        }

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(country);
            transaction.commit();
            System.out.println("Created new country: " + country.getName() + " with ID: " + country.getId());
            return country;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Country findById(int id) {
        return em.find(Country.class, id);
    }

    public List<Country> findByName(String name) {
        return em.createNamedQuery("Country.findByName", Country.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public Country findByNameExact(String name) {
        try {
            return em.createQuery("SELECT c FROM Country c WHERE c.name = :name", Country.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}