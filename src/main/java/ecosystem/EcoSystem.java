package ecosystem;

import utils.FileHandler;
import utils.Logger;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EcoSystem implements Serializable {
    private List<Animal> animals;
    private List<Plant> plants;
    private List<Resource> resources;
    private Climate climate;
    private Environment environment;

    public EcoSystem() {
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.environment = new Environment();
    }

    public void createNewSimulation() throws IOException {
        System.out.println("Creating new simulation...");
        Random random = new Random();

        List<Animal> availableAnimals = FileHandler.loadAnimals("data/animals.txt");
        List<Plant> availablePlants = FileHandler.loadPlants("data/plants.txt");
        List<Resource> availableResources = FileHandler.loadResources("data/resources.txt");

        int numberOfAnimals = random.nextInt(5) + 1;
        for (int i = 0; i < numberOfAnimals; i++) {
            Animal randomAnimal = availableAnimals.get(random.nextInt(availableAnimals.size()));
            int randomPopulation = random.nextInt(100) + 1;
            animals.add(new Animal(randomAnimal.getName(), randomPopulation, randomAnimal.getType(), 0.1, 0.05));
        }

        int numberOfPlants = random.nextInt(10) + 1;
        for (int i = 0; i < numberOfPlants; i++) {
            Plant randomPlant = availablePlants.get(random.nextInt(availablePlants.size()));
            int randomQuantity = random.nextInt(1000) + 1; //  Увеличено  начальное  количество  растений
            plants.add(new Plant(randomPlant.getName(), randomQuantity, 0.15, 0.5, 0.3));
        }

        for (Resource templateResource : availableResources) {
            double randomResourceAmount = Math.round(random.nextDouble() * 1000 * 10.0) / 10.0;
            resources.add(new Resource(templateResource.getType(), randomResourceAmount));
        }

        double randomTemperature = Math.round(random.nextDouble() * 40 * 10.0) / 10.0;
        double randomHumidity = Math.round(random.nextDouble() * 100 * 10.0) / 10.0;
        climate = new Climate(randomTemperature, randomHumidity);
        environment.setResourcesFromList(resources);

        System.out.println("New simulation created!");
        showEcosystem();
    }

    public void setCustomConditions(double temperature, double humidity) {
        this.climate = new Climate(
                Math.round(temperature * 10.0) / 10.0,
                Math.round(humidity * 10.0) / 10.0
        );
    }

    public void simulateOneDay() {
        environment.growPlants(plants);

        for (Animal animal : animals) {
            animal.seekFood(this);
        }

        environment.update(animals, plants);

        applyNaturalMortality();

        Logger logger = new Logger("simulation.log");
        logger.logAnimals(animals);
        logger.logPlants(plants);
        logger.logEnvironment(environment);
    }

    private void applyNaturalMortality() {
        for (Animal animal : animals) {
            animal.applyNaturalMortality(0.01);
        }
        for (Plant plant : plants) {
            plant.applyNaturalMortality(0.005);
        }
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

    public void removeResource(Resource.ResourceType resourceType) {
        resources.removeIf(resource -> resource.getType() == resourceType);
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

        System.out.println("\nCurrent Environment: ");
        System.out.println(environment.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Your EcoSystem: \n");

        sb.append("Animals: [");
        for (int i = 0; i < animals.size(); i++) {
            sb.append(animals.get(i).toString());
            if (i < animals.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("],\n");

        sb.append("Plants: [");
        for (int i = 0; i < plants.size(); i++) {
            sb.append(plants.get(i).toString());
            if (i < plants.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("],\n");

        sb.append("Resources: [");
        for (int i = 0; i < resources.size(); i++) {
            sb.append(resources.get(i).toString());
            if (i < resources.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("],\n");

        sb.append("Climate: ").append(climate.toString()).append("\n");
        sb.append("Environment: ").append(environment.toString()).append("\n");

        return sb.toString();
    }

    public Climate getClimate() {
        return climate;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public List<Resource> getResources() {
        return resources;
    }
}