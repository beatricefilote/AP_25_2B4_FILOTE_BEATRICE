package org.example;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = EntityManagerFactorySingleton.getEntityManagerFactory().createEntityManager();
            logger.info("EntityManager created successfully");

            ContinentRepository continentRepo = new ContinentRepository(em);
            CountryRepository countryRepo = new CountryRepository(em);

            // Create continents
            createContinents(continentRepo);

            // Create countries
            createCountries(countryRepo, continentRepo);

            // Test lookups
            testCountryLookups(countryRepo);

        } catch (Exception e) {
            logger.severe("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                logger.info("EntityManager closed successfully");
            }
        }
    }

    private static void createContinents(ContinentRepository continentRepo) {
        logger.info("Creating continents...");
        continentRepo.create(new Continent("North America"));
        continentRepo.create(new Continent("Europe"));
        continentRepo.create(new Continent("Asia"));

        logger.info("Listing all continents:");
        continentRepo.findAll().forEach(c -> logger.info("ID: " + c.getId() + ", Name: " + c.getName()));
    }

    private static void createCountries(CountryRepository countryRepo, ContinentRepository continentRepo) {
        logger.info("Creating countries...");
        try {
            // North America
            createCountry(countryRepo, "United States", "USA", "North America", continentRepo);
            createCountry(countryRepo, "Canada", "CAN", "North America", continentRepo);
            createCountry(countryRepo, "Mexico", "MEX", "North America", continentRepo);

            // Europe
            createCountry(countryRepo, "France", "FRA", "Europe", continentRepo);
            createCountry(countryRepo, "Germany", "DEU", "Europe", continentRepo);
            createCountry(countryRepo, "Italy", "ITA", "Europe", continentRepo);

            // Asia
            createCountry(countryRepo, "Japan", "JPN", "Asia", continentRepo);
            createCountry(countryRepo, "China", "CHN", "Asia", continentRepo);
            createCountry(countryRepo, "India", "IND", "Asia", continentRepo);
        } catch (Exception e) {
            logger.severe("Error creating countries: " + e.getMessage());
            throw e;
        }
    }

    private static void createCountry(CountryRepository countryRepo, String name, String code,
            String continentName, ContinentRepository continentRepo) {
        Continent continent = continentRepo.findByNameExact(continentName);
        if (continent != null) {
            countryRepo.create(new Country(name, code, continent));
        }
    }

    private static void testCountryLookups(CountryRepository countryRepo) {
        logger.info("Testing country lookups:");
        testCountryLookup(countryRepo, "United States");
        testCountryLookup(countryRepo, "France");
        testCountryLookup(countryRepo, "Japan");
    }

    private static void testCountryLookup(CountryRepository countryRepo, String countryName) {
        List<Country> countries = countryRepo.findByName(countryName);
        if (!countries.isEmpty()) {
            Country foundCountry = countries.get(0);
            logger.info("Found country: " + foundCountry.getName() +
                    " in continent: " + foundCountry.getContinent().getName());
        }
    }
}