package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ContinentDAO continentDAO = new ContinentDAO();
            CountryDAO countryDAO = new CountryDAO();

            continentDAO.create("Europe");
            continentDAO.create("Asia");

            Integer europeId = continentDAO.findByName("Europe");
            Integer asiaId = continentDAO.findByName("Asia");

            System.out.println("ID Europe: " + europeId);
            System.out.println("ID Asia: " + asiaId);

            countryDAO.create("Romania", "RO", europeId);
            countryDAO.create("China", "CN", asiaId);

            Integer romaniaId = countryDAO.findByName("Romania");
            Integer chinaId = countryDAO.findByName("China");

            System.out.println("ID Romania: " + romaniaId);
            System.out.println("ID China: " + chinaId);

            System.out.println("Continentul cu ID " + europeId + " este " + continentDAO.findById(europeId));
            System.out.println("Continentul cu ID " + asiaId + " este " + continentDAO.findById(asiaId));

            System.out.println("Țara cu ID " + romaniaId + " este " + countryDAO.findById(romaniaId));
            System.out.println("Țara cu ID " + chinaId + " este " + countryDAO.findById(chinaId));

            Database.getConnection().commit();

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            Database.rollback();
        }
    }
}
