import ecosystem.Animal;
import ecosystem.EcoSystem;
import ecosystem.Plant;
import ecosystem.Resource;
import utils.FileHandler;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        EcoSystem ecosystem = new EcoSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the ecosystem simulator!");
            System.out.println("1. Create new simulation.");
            System.out.println("2. Continue with existing simulation.");
            System.out.println("0. Exit.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        ecosystem.createNewSimulation();
                        handleSimulationMenu(scanner, ecosystem);
                    } catch (IOException e) {
                        System.out.println("Failed to create simulation: " + e.getMessage());
                    }
                    break;
                case 2:
                    List<String> simulationFiles = FileHandler.listSimulationFiles();
                    if (simulationFiles.isEmpty()) {
                        System.out.println("No saved simulations found. Please create a new one.");
                        break;
                    }
                    System.out.println("Available simulations:");
                    for (int i = 0; i < simulationFiles.size(); i++) {
                        System.out.println((i + 1) + ". " + simulationFiles.get(i));
                    }
                    System.out.println("Enter the number of the simulation to load:");
                    int fileChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (fileChoice > 0 && fileChoice <= simulationFiles.size()) {
                        String selectedFile = simulationFiles.get(fileChoice - 1);
                        try {
                            ecosystem = FileHandler.loadSimulationFromFile(selectedFile);
                            System.out.println("Simulation loaded successfully.");
                            handleSimulationMenu(scanner, ecosystem);
                        } catch (IOException e) {
                            System.out.println("Failed to load simulation: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleSimulationMenu(Scanner scanner, EcoSystem ecosystem) throws IOException {
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
            System.out.println("0. Back to main menu.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ecosystem.showEcosystem();
                    break;
                case 2:
                    System.out.println("Enter animal name:");
                    String animalName = scanner.nextLine();
                    System.out.println("Enter population:");
                    int population = scanner.nextInt();
                    scanner.nextLine();
                    ecosystem.addAnimal(new Animal(animalName, population));
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
                    FileHandler.saveSimulationToFile(ecosystem.toString());
                    System.out.println("Simulation saved.");
                    break;
                case 10:
                    ecosystem.clearSimulation();
                    ecosystem.createNewSimulation();
                    handleSimulationMenu(scanner, ecosystem);
                    break;
                case 0:
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}