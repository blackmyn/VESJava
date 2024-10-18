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
            Animal.AnimalType type = parts[1].equalsIgnoreCase("HERBIVORE") ? Animal.AnimalType.HERBIVORE : Animal.AnimalType.CARNIVORE;
            animals.add(new Animal(parts[0], Integer.parseInt(parts[2]), type, 0.1, 0.05));
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
            plants.add(new Plant(parts[0], Integer.parseInt(parts[1]), 0.15, 0.5, 0.3));
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
            Resource.ResourceType type = parts[0].equalsIgnoreCase("WATER") ?
                    Resource.ResourceType.WATER :
                    (parts[0].equalsIgnoreCase("MINERALS") ?
                            Resource.ResourceType.MINERALS :
                            Resource.ResourceType.SUNLIGHT);
            resources.add(new Resource(type, Double.parseDouble(parts[1])));
        }
        reader.close();
        return resources;
    }
}