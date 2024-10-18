import ecosystem.*;
import utils.Logger;
import utils.PredictionEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulation simulation = new Simulation("data/simulations");

        while (true) {
            showMainMenu(scanner, simulation);
        }
    }

    private static void showMainMenu(Scanner scanner, Simulation simulation) {
        System.out.println("\nWelcome to the ecosystem simulator!");
        System.out.println("1. Start new simulation");
        System.out.println("2. Load existing simulation");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                handleNewSimulation(scanner, simulation);
                break;
            case 2:
                handleLoadSimulation(scanner, simulation);
                break;
            case 0:
                System.out.println("Exiting simulation. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleNewSimulation(Scanner scanner, Simulation simulation) {
        while (true) {
            System.out.println("\nNew Simulation:");
            System.out.println("1. Create random ecosystem");
            System.out.println("2. Set custom parameters");
            System.out.println("0. Back to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        simulation.createNewSimulation();
                        handleSimulationMenu(scanner, simulation);
                        return;
                    } catch (IOException e) {
                        System.out.println("Failed to create simulation: " + e.getMessage());
                    }
                    break;
                case 2:
                    handleCustomParameters(scanner, simulation);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleCustomParameters(Scanner scanner, Simulation simulation) {
        EcoSystem ecosystem = simulation.getEcosystem();

        while (true) {
            System.out.println("\nSet Custom Parameters:");
            System.out.println("1. Set climate conditions");
            System.out.println("2. Add animal");
            System.out.println("3. Add plant");
            System.out.println("4. Add resource");
            System.out.println("0. Back to new simulation menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter temperature (°C): ");
                    double temperature = getValidDoubleInput(scanner);
                    System.out.print("Enter humidity (%): ");
                    double humidity = getValidDoubleInput(scanner);
                    ecosystem.setCustomConditions(temperature, humidity);
                    break;
                case 2:
                    addAnimal(scanner, ecosystem);
                    break;
                case 3:
                    addPlant(scanner, ecosystem);
                    break;
                case 4:
                    addResource(scanner, ecosystem);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleLoadSimulation(Scanner scanner, Simulation simulation) {
        File simulationsDir = new File(simulation.getRepository().getDataDirectory());
        File[] simulationFiles = simulationsDir.listFiles((dir, name) -> name.endsWith(".txt"));

        if (simulationFiles == null || simulationFiles.length == 0) {
            System.out.println("No simulation files found in the directory.");
            return;
        }

        System.out.println("\nAvailable simulations:");
        List<String> simulationNames = new ArrayList<>();
        for (int i = 0; i < simulationFiles.length; i++) {
            String filename = simulationFiles[i].getName();
            simulationNames.add(filename);
            System.out.println((i + 1) + ". " + filename);
        }

        System.out.print("Choose a simulation to load (enter number): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= simulationNames.size()) {
            String filename = simulationNames.get(choice - 1);
            try {
                simulation.loadSimulation(filename);
                handleSimulationMenu(scanner, simulation);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to load simulation: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private static void handleSimulationMenu(Scanner scanner, Simulation simulation) {
        PredictionEngine predictionEngine = simulation.getPredictionEngine();
        while (true) {

            System.out.println("\nSimulation running...");
            System.out.println("1. Simulate one day");
            System.out.println("2. View ecosystem state");
            System.out.println("3. Manage ecosystem");
            System.out.println("4. Save simulation");
            System.out.println("5. Create prediction state");
            System.out.println("6. Run automatic simulation");
            System.out.println("0. Back to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    simulation.simulateOneDay();
                    break;
                case 2:
                    simulation.showEcosystem();
                    break;
                case 3:
                    handleEcosystemManagement(scanner, simulation);
                    break;
                case 4:
                    try {
                        simulation.saveSimulation();
                    } catch (IOException e) {
                        System.out.println("Failed to save simulation: " + e.getMessage());
                    }
                    break;
                case 5:
                    String prediction = predictionEngine.predictPopulationChanges(simulation.getEcosystem());
                    System.out.println(prediction);
                    break;
                case 6:
                    runAutomaticSimulation(simulation);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void runAutomaticSimulation(Simulation simulation) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of days to simulate: ");
        int daysToSimulate = scanner.nextInt();

        Logger logger = new Logger("simulation.log");

        System.out.println("Starting automatic simulation...");
        for (int day = 1; day <= daysToSimulate; day++) {
            simulation.simulateOneDay();
            logger.logDay(day);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Simulation interrupted.");
                return;
            }
        }
        System.out.println("Automatic simulation completed.");
    }

    private static void handleEcosystemManagement(Scanner scanner, Simulation simulation) {
        EcoSystem ecosystem = simulation.getEcosystem();

        while (true) {
            System.out.println("\nManage Ecosystem:");
            System.out.println("1. Add animal");
            System.out.println("2. Remove animal");
            System.out.println("3. Update animal");
            System.out.println("4. Add plant");
            System.out.println("5. Remove plant");
            System.out.println("6. Update plant");
            System.out.println("7. Add resource");
            System.out.println("8. Remove resource");
            System.out.println("9. Update resource");
            System.out.println("0. Back to simulation menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAnimal(scanner, ecosystem);
                    break;
                case 2:
                    removeAnimal(scanner, ecosystem);
                    break;
                case 3:
                    updateAnimal(scanner, ecosystem);
                    break;
                case 4:
                    addPlant(scanner, ecosystem);
                    break;
                case 5:
                    removePlant(scanner, ecosystem);
                    break;
                case 6:
                    updatePlant(scanner, ecosystem);
                    break;
                case 7:
                    addResource(scanner, ecosystem);
                    break;
                case 8:
                    removeResource(scanner, ecosystem);
                    break;
                case 9:
                    updateResource(scanner, ecosystem);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addAnimal(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter animal name: ");
        String animalName = scanner.nextLine();
        System.out.print("Enter population: ");
        int population = getValidIntInput(scanner);
        System.out.print("Enter animal type (CARNIVORE/HERBIVORE): ");
        String typeStr = scanner.nextLine();
        Animal.AnimalType animalType = Animal.AnimalType.valueOf(typeStr.toUpperCase());
        System.out.print("Enter food requirement: ");
        double foodRequirement = getValidDoubleInput(scanner);
        System.out.print("Enter reproduction rate: ");
        double reproductionRate = getValidDoubleInput(scanner);

        ecosystem.addAnimal(new Animal(animalName, population, animalType, foodRequirement, reproductionRate));
    }

    private static void removeAnimal(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter the name of the animal to remove: ");
        String animalToRemove = scanner.nextLine();
        ecosystem.removeAnimal(animalToRemove);
    }

    private static void updateAnimal(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter the name of the animal to update: ");
        String animalName = scanner.nextLine();

        for (Animal animal : ecosystem.getAnimals()) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                System.out.println("What do you want to update?");
                System.out.println("1. Population");
                System.out.println("2. Type (CARNIVORE/HERBIVORE)");
                System.out.println("3. Food Requirement");
                System.out.println("4. Reproduction Rate");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter new population: ");
                        int newPopulation = getValidIntInput(scanner);
                        animal.setPopulation(newPopulation);
                        break;
                    case 2:
                        System.out.print("Enter new type (CARNIVORE/HERBIVORE): ");
                        String newTypeStr = scanner.nextLine();
                        Animal.AnimalType newType = Animal.AnimalType.valueOf(newTypeStr.toUpperCase());
                        animal.setType(newType);
                        break;
                    case 3:
                        System.out.print("Enter new Food Requirement: ");
                        double newFoodRequirement = getValidDoubleInput(scanner);
                        animal.setFoodRequirement(newFoodRequirement);
                        break;
                    case 4:
                        System.out.print("Enter new Reproduction Rate: ");
                        double newReproductionRate = getValidDoubleInput(scanner);
                        animal.setReproductionRate(newReproductionRate);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Animal not found: " + animalName);
    }

    private static void addPlant(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter plant name: ");
        String plantName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = getValidIntInput(scanner);
        System.out.print("Enter growth rate: ");
        double growthRate = getValidDoubleInput(scanner);
        System.out.print("Enter water need: ");
        double waterNeed = getValidDoubleInput(scanner);
        System.out.print("Enter minerals need: ");
        double mineralsNeed = getValidDoubleInput(scanner);

        ecosystem.addPlant(new Plant(plantName, quantity, growthRate, waterNeed, mineralsNeed));
    }

    private static void removePlant(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter the name of the plant to remove: ");
        String plantToRemove = scanner.nextLine();
        ecosystem.removePlant(plantToRemove);
    }

    private static void updatePlant(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter the name of the plant to update: ");
        String plantName = scanner.nextLine();

        for (Plant plant : ecosystem.getPlants()) {
            if (plant.getName().equalsIgnoreCase(plantName)) {
                System.out.println("What do you want to update?");
                System.out.println("1. Quantity");
                System.out.println("2. Growth rate");
                System.out.println("3. Water need");
                System.out.println("4. Minerals need");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter new quantity: ");
                        int newQuantity = getValidIntInput(scanner);
                        plant.setQuantity(newQuantity);
                        break;
                    case 2:
                        System.out.print("Enter new growth rate: ");
                        double newGrowthRate = getValidDoubleInput(scanner);
                        plant.setGrowthRate(newGrowthRate);
                        break;
                    case 3:
                        System.out.print("Enter new water need: ");
                        double newWaterNeed = getValidDoubleInput(scanner);
                        plant.setWaterNeed(newWaterNeed);
                        break;
                    case 4:
                        System.out.print("Enter new minerals need: ");
                        double newMineralsNeed = getValidDoubleInput(scanner);
                        plant.setMineralsNeed(newMineralsNeed);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Plant not found: " + plantName);
    }

    private static void addResource(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter resource type (WATER/MINERALS/SUNLIGHT): ");
        String typeStr = scanner.nextLine();

        try {
            Resource.ResourceType resourceType = Resource.ResourceType.valueOf(typeStr.toUpperCase());
            System.out.print("Enter amount: ");
            double amount = getValidDoubleInput(scanner);
            ecosystem.addResource(new Resource(resourceType, amount));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid resource type. Please enter WATER, MINERALS, or SUNLIGHT.");
        }
    }

    private static void removeResource(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter the type of the resource to remove (WATER/MINERALS/SUNLIGHT): ");
        String typeStr = scanner.nextLine();

        try {
            Resource.ResourceType resourceType = Resource.ResourceType.valueOf(typeStr.toUpperCase());
            ecosystem.removeResource(resourceType);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid resource type. Please enter WATER, MINERALS, or SUNLIGHT.");
        }
    }

    private static void updateResource(Scanner scanner, EcoSystem ecosystem) {
        System.out.print("Enter the type of the resource to update (WATER/MINERALS/SUNLIGHT): ");
        String typeStr = scanner.nextLine();

        try {
            Resource.ResourceType resourceType = Resource.ResourceType.valueOf(typeStr.toUpperCase());
            for (Resource resource : ecosystem.getResources()) {
                if (resource.getType() == resourceType) {
                    System.out.print("Enter new amount: ");
                    double newAmount = getValidDoubleInput(scanner);
                    resource.setAmount(newAmount);
                    return;
                }
            }
            System.out.println("Resource not found: " + typeStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid resource type. Please enter WATER, MINERALS, or SUNLIGHT.");
        }
    }

    private static int getValidIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}