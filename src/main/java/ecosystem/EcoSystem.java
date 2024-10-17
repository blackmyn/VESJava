package ecosystem;

import utils.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EcoSystem {
    private List<Animal> animals;
    private List<Plant> plants;
    private List<Resource> resources;
    private Climate climate;

    public EcoSystem() {
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public void createNewSimulation() throws IOException {
        System.out.println("Creating new simulation...");
        Random random = new Random();

        List<Animal> availableAnimals = FileHandler.loadAnimals("data/animals.txt");
        List<Plant> availablePlants = FileHandler.loadPlants("data/plants.txt");
        List<Resource> availableResources = FileHandler.loadResources("data/resources.txt");

        int numberOfAnimals = random.nextInt(4) + 1;
        for (int i = 0; i < numberOfAnimals; i++) {
            Animal randomAnimal = availableAnimals.get(random.nextInt(availableAnimals.size()));
            int randomPopulation = random.nextInt(50) + 1;
            animals.add(new Animal(randomAnimal.getName(), randomPopulation));
        }

        int numberOfPlants = random.nextInt(3) + 1;
        for (int i = 0; i < numberOfPlants; i++) {
            Plant randomPlant = availablePlants.get(random.nextInt(availablePlants.size()));
            int randomQuantity = random.nextInt(100) + 1;
            plants.add(new Plant(randomPlant.getName(), randomQuantity));
        }

        Resource randomResource = availableResources.get(random.nextInt(availableResources.size()));
        double randomResourceAmount = Math.round(random.nextDouble() * 1000 * 10.0) / 10.0;
        resources.add(new Resource(randomResource.getType(), randomResourceAmount));

        double randomTemperature = Math.round(random.nextDouble() * 40 * 10.0) / 10.0;
        double randomHumidity = Math.round(random.nextDouble() * 100 * 10.0) / 10.0;
        climate = new Climate(randomTemperature, randomHumidity);

        System.out.println("New simulation created!");
        showEcosystem();
    }

    public void setCustomConditions(double temperature, double humidity) {
        this.climate = new Climate(
                Math.round(temperature * 10.0) / 10.0,
                Math.round(humidity * 10.0) / 10.0
        );
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(String animalName) {
        animals.removeIf(animal -> animal.getName().equalsIgnoreCase(animalName));
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(String plantName) {
        plants.removeIf(plant -> plant.getName().equalsIgnoreCase(plantName));
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void removeResource(String resourceType) {
        resources.removeIf(resource -> resource.getType().equalsIgnoreCase(resourceType));
    }

    public void clearSimulation() {
        animals.clear();
        plants.clear();
        resources.clear();
        climate = null;
        System.out.println("Simulation cleared.");
    }

    public void showEcosystem() {
        System.out.println("Current animals: ");
        for (Animal animal : animals) {
            System.out.println(animal);
        }

        System.out.println("Current plants: ");
        for (Plant plant : plants) {
            System.out.println(plant);
        }

        System.out.println("Current resources: ");
        for (Resource resource : resources) {
            System.out.println(resource);
        }

        System.out.println("Current climate: " + climate);
    }

    @Override
    public String toString() {
        return "Your EcoSystem: " + "\n" +
                "Animals: " + animals + ",\n" +
                "Plants: " + plants + ",\n" +
                "Resources: " + resources + ",\n" +
                "Climate: " + climate;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }
}