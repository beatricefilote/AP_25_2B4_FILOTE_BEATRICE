package org.example;

import jakarta.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = EntityManagerFactorySingleton.getEntityManagerFactory().createEntityManager();

            ContinentRepository continentRepo = new ContinentRepository(em);
            CountryRepository countryRepo = new CountryRepository(em);

            System.out.println("\nCreating continents...");
            Continent southAmerica = continentRepo.create(new Continent("South America"));
            Continent africa = continentRepo.create(new Continent("Africa"));
            Continent oceania = continentRepo.create(new Continent("Oceania"));

            System.out.println("\nListing all continents:");
            List<Continent> continents = continentRepo.findByName("");
            for (Continent c : continents) {
                System.out.println("ID: " + c.getId() + ", Name: " + c.getName());
            }

            System.out.println("\nCreating countries...");
            try {
                Country brazil = new Country("Brazil", "BR", southAmerica);
                Country southAfrica = new Country("South Africa", "ZA", africa);
                Country australia = new Country("Australia", "AU", oceania);

                countryRepo.create(brazil);
                countryRepo.create(southAfrica);
                countryRepo.create(australia);
            } catch (Exception e) {
                System.err.println("Error creating countries: " + e.getMessage());
                throw e;
            }

            System.out.println("\nTesting country lookups:");
            List<Country> countries = countryRepo.findByName("Brazil");
            if (!countries.isEmpty()) {
                Country foundCountry = countries.get(0);
                System.out.println("Found country: " + foundCountry.getName() +
                        " in continent: " + foundCountry.getContinent().getName());
            }

            countries = countryRepo.findByName("South Africa");
            if (!countries.isEmpty()) {
                Country foundCountry = countries.get(0);
                System.out.println("Found country: " + foundCountry.getName() +
                        " in continent: " + foundCountry.getContinent().getName());
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}