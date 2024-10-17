package ecosystem;

import utils.EcosystemRepository;
import utils.PredictionEngine;

import java.io.IOException;
import java.util.Scanner;

public class Simulation {
    private EcoSystem ecosystem;
    private EcosystemRepository repository;
    private boolean isRunning;
    private Interaction interaction;
    private PredictionEngine predictionEngine;

    public Simulation(String dataDirectory) {
        this.ecosystem = new EcoSystem();
        this.repository = new EcosystemRepository(dataDirectory);
        this.isRunning = false;
        this.interaction = new Interaction();
        this.predictionEngine = new PredictionEngine();
    }

    public void start() {
        isRunning = true;
        System.out.println("Simulation started.");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Simulation stopped.");
    }

    public void createNewSimulation() throws IOException {
        ecosystem.createNewSimulation();
    }

    public void loadSimulation(String filename) throws IOException {
        this.ecosystem = repository.loadSimulation(filename);
        System.out.println("Simulation loaded successfully.");
    }

    public void saveSimulation() throws IOException {
        repository.saveSimulation(ecosystem);
        System.out.println("Simulation saved.");
    }

    public void showEcosystem() {
        ecosystem.showEcosystem();
    }
    public void simulateOneDay() {
        interaction.simulateOneDayForPlants(ecosystem);
        interaction.simulateOneDayForAnimals(ecosystem);
        System.out.println("One day of simulation completed.");
    }
    public void handleSimulationInput(Scanner scanner) throws IOException {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\nSimulation Menu:");
            System.out.println("1. View current simulation.");
            System.out.println("2. Add new animal.");
            System.out.println("3. Add new plant.");
            System.out.println("4. Add new resource.");
            System.out.println("5. Remove animal.");
            System.out.println("6. Remove plant.");
            System.out.println("7. Remove resource.");
            System.out.println("8. Set custom climate conditions.");
            System.out.println("9. Save simulation.");
            System.out.println("10. Create new simulation (clear current).");
            System.out.println("11. Simulate 1 day");
            System.out.println("0. Back to main menu.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showEcosystem();
                    break;
                case 2:
                    System.out.println("Enter animal name:");
                    String animalName = scanner.nextLine();
                    System.out.println("Enter population:");
                    int population = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter animal type {CARNIVORE or HERBIVORE}:");
                    String typeStr = scanner.nextLine();
                    AnimalType animalType = AnimalType.valueOf(typeStr.toUpperCase());
                    ecosystem.addAnimal(new Animal(animalName, population, animalType));
                    break;
                case 3:
                    System.out.println("Enter plant name:");
                    String plantName = scanner.nextLine();
                    System.out.println("Enter quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    ecosystem.addPlant(new Plant(plantName, quantity));
                    break;
                case 4:
                    System.out.println("Enter resource type:");
                    String resourceType = scanner.nextLine();
                    System.out.println("Enter amount:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    ecosystem.addResource(new Resource(resourceType, amount));
                    break;
                case 5:
                    System.out.println("Enter the name of the animal to remove:");
                    String removeAnimal = scanner.nextLine();
                    ecosystem.removeAnimal(removeAnimal);
                    break;
                case 6:
                    System.out.println("Enter the name of the plant to remove:");
                    String removePlant = scanner.nextLine();
                    ecosystem.removePlant(removePlant);
                    break;
                case 7:
                    System.out.println("Enter the type of resource to remove:");
                    String removeResource = scanner.nextLine();
                    ecosystem.removeResource(removeResource);
                    break;
                case 8:
                    System.out.println("Enter custom temperature (°C):");
                    double temperature = scanner.nextDouble();
                    System.out.println("Enter custom humidity (%):");
                    double humidity = scanner.nextDouble();
                    scanner.nextLine();
                    ecosystem.setCustomConditions(temperature, humidity);
                    break;
                case 9:
                    saveSimulation();
                    break;
                case 10:
                    ecosystem.clearSimulation();
                    createNewSimulation();
                    handleSimulationInput(scanner);
                    break;
                case 11:
                    simulateOneDay();
                    break;
                case 12:
                    if (ecosystem != null) {
                        String prediction = predictionEngine.predictPopulationChanges(ecosystem);
                        System.out.println(prediction);
                    } else {
                        System.out.println("No simulation loaded.");
                    }
                    break;
                case 0:
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public EcoSystem getEcosystem() {
        return ecosystem;
    }

    public EcosystemRepository getRepository() {
        return repository;
    }

    public boolean isRunning() {
        return isRunning;
    }
}