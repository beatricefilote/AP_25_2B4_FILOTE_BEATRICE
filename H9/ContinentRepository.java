package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.logging.Logger;

public class ContinentRepository extends AbstractRepository<Continent> {
    private static final Logger logger = Logger.getLogger(ContinentRepository.class.getName());

    public ContinentRepository(EntityManager em) {
        super(em, Continent.class);
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

    public List<Continent> findByName(String name) {
        long startTime = System.currentTimeMillis();
        try {
            List<Continent> results = em.createNamedQuery("Continent.findByName", Continent.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.info("Found continents by name in " + (endTime - startTime) + "ms");
            return results;
        } catch (Exception e) {
            logger.severe("Error finding continents by name: " + e.getMessage());
            throw e;
        }
    }

    public Continent findByNameExact(String name) {
        long startTime = System.currentTimeMillis();
        try {
            Continent result = em.createQuery("SELECT c FROM Continent c WHERE c.name = :name", Continent.class)
                    .setParameter("name", name)
                    .getSingleResult();
            long endTime = System.currentTimeMillis();
            logger.info("Found continent by exact name in " + (endTime - startTime) + "ms");
            return result;
        } catch (NoResultException e) {
            logger.info("No continent found with exact name: " + name);
            return null;
        } catch (Exception e) {
            logger.severe("Error finding continent by exact name: " + e.getMessage());
            throw e;
        }
    }
}