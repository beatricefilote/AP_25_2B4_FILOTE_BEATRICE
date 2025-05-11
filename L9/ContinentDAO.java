package org.example;

import jakarta.persistence.EntityManager;
import java.util.List;

public class ContinentDAO {
    private EntityManager em;

    public ContinentDAO(EntityManager em) {
        this.em = em;
    }
}