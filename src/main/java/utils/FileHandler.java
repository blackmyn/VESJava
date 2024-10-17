package utils;

import ecosystem.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileHandler {

    public static List<Animal> loadAnimals(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Animal> animals = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            animals.add(new Animal(parts[0], Integer.parseInt(parts[1])));
        }
        reader.close();
        return animals;
    }

    public static List<Plant> loadPlants(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Plant> plants = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            plants.add(new Plant(parts[0], Integer.parseInt(parts[1])));
        }
        reader.close();
        return plants;
    }

    public static List<Resource> loadResources(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Resource> resources = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            resources.add(new Resource(parts[0], Double.parseDouble(parts[1])));
        }
        reader.close();
        return resources;
    }

    public static Climate loadClimate(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        double temperature = Double.parseDouble(line.split(" ")[1]);
        line = reader.readLine();
        double humidity = Double.parseDouble(line.split(" ")[1]);
        reader.close();
        return new Climate(temperature, humidity);
    }

    public static void saveToFile(String filename, List<String> data) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        for (String line : data) {
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }

    public static void saveSimulationToFile(String simulationData) throws IOException {
        File directory = new File("data/simulations");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String currentDateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String fileName = "simulation_" + currentDateTime + ".txt";
        File file = new File(directory, fileName);

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(simulationData);
        fileWriter.close();

        System.out.println("Simulation saved to: " + file.getAbsolutePath());
    }

    public static EcoSystem loadSimulationFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/simulations/" + filename));
        EcoSystem ecosystem = new EcoSystem();

        String line;
        List<Animal> animals = new ArrayList<>();
        List<Plant> plants = new ArrayList<>();
        List<Resource> resources = new ArrayList<>();
        Climate climate = null;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Animals: ")) {
                // Parse animals
                String[] animalData = line.replace("Animals: [", "").replace("]", "").split(", Animal: ");
                for (String data : animalData) {
                    if (!data.trim().isEmpty()) {
                        String[] parts = data.split(", population: ");
                        String animalName = parts[0].trim();
                        int population = Integer.parseInt(parts[1].replace(",", "").trim());  // Удаляем запятую
                        animals.add(new Animal(animalName, population));
                    }
                }
            } else if (line.startsWith("Plants: ")) {
                // Parse plants
                String[] plantData = line.replace("Plants: [", "").replace("]", "").split(", Plant: ");
                for (String data : plantData) {
                    if (!data.trim().isEmpty()) {
                        String[] parts = data.split(", quantity: ");
                        String plantName = parts[0].trim();
                        int quantity = Integer.parseInt(parts[1].replace(",", "").trim());  // Удаляем запятую
                        plants.add(new Plant(plantName, quantity));
                    }
                }
            } else if (line.startsWith("Resources: ")) {
                // Parse resources
                String[] resourceData = line.replace("Resources: [", "").replace("]", "").split(", Resource: ");
                for (String data : resourceData) {
                    if (!data.trim().isEmpty()) {
                        String[] parts = data.split(", amount: ");
                        String resourceType = parts[0].trim();
                        double amount = Double.parseDouble(parts[1].replace(",", "").trim());  // Удаляем запятую
                        resources.add(new Resource(resourceType, amount));
                    }
                }
            } else if (line.startsWith("Climate: ")) {
                // Parse climate
                String[] climateData = line.replace("Climate: ", "").split(", ");
                double temperature = Double.parseDouble(climateData[0].split(":")[1].trim());
                double humidity = Double.parseDouble(climateData[1].split(":")[1].trim());
                climate = new Climate(temperature, humidity);
            }
        }

        reader.close();

        ecosystem.setAnimals(animals);
        ecosystem.setPlants(plants);
        ecosystem.setResources(resources);
        ecosystem.setClimate(climate);

        return ecosystem;
    }

    public static List<String> listSimulationFiles() {
        File directory = new File("data/simulations");
        if (!directory.exists()) {
            System.out.println("No simulations found.");
            return new ArrayList<>();
        }

        File[] files = directory.listFiles((dir, name) -> name.startsWith("simulation") && name.endsWith(".txt"));
        List<String> fileNames = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }
}