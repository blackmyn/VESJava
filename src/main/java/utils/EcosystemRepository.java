package utils;

import ecosystem.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EcosystemRepository {
    private String dataDirectory;

    public EcosystemRepository(String dataDirectory) {
        this.dataDirectory = dataDirectory;
        createDataDirectoryIfNotExists();
    }

    private void createDataDirectoryIfNotExists() {
        File directory = new File(dataDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void saveSimulation(EcoSystem ecosystem) throws IOException {
        String simulationData = ecosystem.toString();
        String fileName = generateSimulationFileName();
        String filePath = dataDirectory + "/" + fileName;

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(simulationData);
        }

        System.out.println("Simulation saved to: " + filePath);
    }

    public EcoSystem loadSimulation(String filename) throws IOException {
        String filePath = dataDirectory + "/" + filename;
        EcoSystem loadedEcosystem = new EcoSystem();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Animal> animals = new ArrayList<>();
            List<Plant> plants = new ArrayList<>();
            List<Resource> resources = new ArrayList<>();
            Climate climate = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Animals: ")) {
                    animals = parseAnimals(line);
                } else if (line.startsWith("Plants: ")) {
                    plants = parsePlants(line);
                } else if (line.startsWith("Resources: ")) {
                    resources = parseResources(line);
                } else if (line.startsWith("Climate: ")) {
                    climate = parseClimate(line);
                }
            }

            loadedEcosystem.setAnimals(animals);
            loadedEcosystem.setPlants(plants);
            loadedEcosystem.setResources(resources);
            loadedEcosystem.setClimate(climate);
        }

        return loadedEcosystem;
    }

    private List<Animal> parseAnimals(String line) {
        List<Animal> animals = new ArrayList<>();
        String[] animalData = line.replace("Animals: [", "").replace("]", "").split(", Animal: ");
        for (String data : animalData) {
            if (!data.trim().isEmpty()) {
                String[] parts = data.split(", population: ");
                String animalName = parts[0].trim();
                int population = Integer.parseInt(parts[1].replace(",", "").trim());
                AnimalType type = parts[2].trim().equalsIgnoreCase("HERBIVORE") ? AnimalType.HERBIVORE : AnimalType.CARNIVORE;
                animals.add(new Animal(animalName, population, type));
            }
        }
        return animals;
    }

    private List<Plant> parsePlants(String line) {
        List<Plant> plants = new ArrayList<>();
        String[] plantData = line.replace("Plants: [", "").replace("]", "").split(", Plant: ");
        for (String data : plantData) {
            if (!data.trim().isEmpty()) {
                String[] parts = data.split(", quantity: ");
                String plantName = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].replace(",", "").trim());
                plants.add(new Plant(plantName, quantity));
            }
        }
        return plants;
    }

    private List<Resource> parseResources(String line) {
        List<Resource> resources = new ArrayList<>();
        String[] resourceData = line.replace("Resources: [", "").replace("]", "").split(", Resource: ");
        for (String data : resourceData) {
            if (!data.trim().isEmpty()) {
                String[] parts = data.split(", amount: ");
                String resourceType = parts[0].trim();
                double amount = Double.parseDouble(parts[1].replace(",", "").trim());
                resources.add(new Resource(resourceType, amount));
            }
        }
        return resources;
    }

    private Climate parseClimate(String line) {
        String[] climateData = line.replace("Climate: ", "").split(", ");
        double temperature = Double.parseDouble(climateData[0].split(":")[1].trim());
        double humidity = Double.parseDouble(climateData[1].split(":")[1].trim());
        return new Climate(temperature, humidity);
    }

    private String generateSimulationFileName() {
        String currentDateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        return "simulation_" + currentDateTime + ".txt";
    }
}