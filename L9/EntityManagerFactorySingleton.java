package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");

    private EntityManagerFactorySingleton() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void close() {
        emf.close();
    }
}