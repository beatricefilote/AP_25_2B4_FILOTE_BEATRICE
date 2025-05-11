package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public abstract class AbstractRepository<T> {
    protected final EntityManager em;
    protected final Class<T> entityClass;
    protected final Logger logger;
    protected final FileHandler fileHandler;

    protected AbstractRepository(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
        this.logger = Logger.getLogger(entityClass.getName());

        try {
            this.fileHandler = new FileHandler("logs/" + entityClass.getSimpleName() + ".log", true);
            this.fileHandler.setFormatter(new SimpleFormatter());
            this.logger.addHandler(fileHandler);
            this.logger.setLevel(Level.ALL);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize logger", e);
        }
    }

    public T create(T entity) {
        long startTime = System.currentTimeMillis();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
            long endTime = System.currentTimeMillis();
            logger.info("Created " + entityClass.getSimpleName() + " in " + (endTime - startTime) + "ms");
            return entity;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.severe("Error creating " + entityClass.getSimpleName() + ": " + e.getMessage());
            throw e;
        }
    }

    public Optional<T> findById(int id) {
        long startTime = System.currentTimeMillis();
        try {
            T entity = em.find(entityClass, id);
            long endTime = System.currentTimeMillis();
            logger.info("Found " + entityClass.getSimpleName() + " by ID in " + (endTime - startTime) + "ms");
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            logger.severe("Error finding " + entityClass.getSimpleName() + " by ID: " + e.getMessage());
            throw e;
        }
    }

    public List<T> findAll() {
        long startTime = System.currentTimeMillis();
        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
            List<T> results = query.getResultList();
            long endTime = System.currentTimeMillis();
            logger.info("Found all " + entityClass.getSimpleName() + "s in " + (endTime - startTime) + "ms");
            return results;
        } catch (Exception e) {
            logger.severe("Error finding all " + entityClass.getSimpleName() + "s: " + e.getMessage());
            throw e;
        }
    }

    public T update(T entity) {
        long startTime = System.currentTimeMillis();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            T updatedEntity = em.merge(entity);
            transaction.commit();
            long endTime = System.currentTimeMillis();
            logger.info("Updated " + entityClass.getSimpleName() + " in " + (endTime - startTime) + "ms");
            return updatedEntity;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.severe("Error updating " + entityClass.getSimpleName() + ": " + e.getMessage());
            throw e;
        }
    }

    public void delete(int id) {
        long startTime = System.currentTimeMillis();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            transaction.commit();
            long endTime = System.currentTimeMillis();
            logger.info("Deleted " + entityClass.getSimpleName() + " in " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.severe("Error deleting " + entityClass.getSimpleName() + ": " + e.getMessage());
            throw e;
        }
    }
}