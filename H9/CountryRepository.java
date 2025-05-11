package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.List;

public class CountryRepository extends AbstractRepository<Country> {

    public CountryRepository(EntityManager em) {
        super(em, Country.class);
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

    public List<Country> findByName(String name) {
        long startTime = System.currentTimeMillis();
        try {
            List<Country> results = em.createNamedQuery("Country.findByName", Country.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.info("Found countries by name in " + (endTime - startTime) + "ms");
            return results;
        } catch (Exception e) {
            logger.severe("Error finding countries by name: " + e.getMessage());
            throw e;
        }
    }

    public Country findByNameExact(String name) {
        long startTime = System.currentTimeMillis();
        try {
            Country result = em.createQuery("SELECT c FROM Country c WHERE c.name = :name", Country.class)
                    .setParameter("name", name)
                    .getSingleResult();
            long endTime = System.currentTimeMillis();
            logger.info("Found country by exact name in " + (endTime - startTime) + "ms");
            return result;
        } catch (NoResultException e) {
            logger.info("No country found with exact name: " + name);
            return null;
        } catch (Exception e) {
            logger.severe("Error finding country by exact name: " + e.getMessage());
            throw e;
        }
    }

    public List<Country> findByContinentId(int continentId) {
        long startTime = System.currentTimeMillis();
        try {
            List<Country> results = em
                    .createQuery("SELECT c FROM Country c WHERE c.continent.id = :continentId", Country.class)
                    .setParameter("continentId", continentId)
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.info("Found countries by continent ID in " + (endTime - startTime) + "ms");
            return results;
        } catch (Exception e) {
            logger.severe("Error finding countries by continent ID: " + e.getMessage());
            throw e;
        }
    }
}