public static void main(String[] args) {
    List<Aircraft> aircraftList = new ArrayList<>();

    aircraftList.add(new Airliner(416, 0, "Boeing", "747-400", "N12345", 68.4));
    aircraftList.add(new Airliner(180, 0, "Airbus", "A320", "F-GKXY", 35.8));
    aircraftList.add(new Airliner(850, 0, "Airbus", "A380", "9V-SKQ", 79.8));
    aircraftList.add(new Airliner(162, 0, "Boeing", "737-800", "D-ATUM", 35.8));
    aircraftList.add(new Airliner(293, 0, "Boeing", "777-300ER", "N2251U", 64.8));
    aircraftList.add(new Drone(5.0, 45, "DJI Matrice 300", "M300 RTK", "DJI123", 1.2));
    aircraftList.add(new Drone(15.0, 120, "Wingcopter", "198", "WC-198", 2.8));
    aircraftList.add(new Drone(2.5, 30, "Parrot", "Anafi USA", "PA-001", 0.7));
    aircraftList.add(new Drone(10.0, 90, "Amazon Prime Air", "MK27-2", "AP-2001", 4.2));
    aircraftList.add(new Freighter(0, 150000, "Antonov", "An-124", "UR-82027", 73.3));
    aircraftList.add(new Freighter(0, 112760, "Boeing", "747-8F", "N852GT", 68.4));
    aircraftList.add(new Freighter(0, 76500, "McDonnell Douglas", "MD-11F", "N526FE", 51.8));
    aircraftList.add(new Freighter(0, 103000, "Boeing", "777F", "N890FD", 64.8));

    Collections.sort(aircraftList);

    System.out.println("Aeronave:");
    for (Aircraft aircraft : aircraftList) {
        System.out.println(aircraft.toString());
    }
}

