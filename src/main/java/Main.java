import ecosystem.Simulation;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulation simulation = new Simulation("data/simulations");

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
                        simulation.createNewSimulation();
                        simulation.handleSimulationInput(scanner);
                    } catch (IOException e) {
                        System.out.println("Failed to create simulation: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter simulation filename:");
                        String filename = scanner.nextLine();
                        simulation.loadSimulation(filename);
                        simulation.handleSimulationInput(scanner);
                    } catch (IOException e) {
                        System.out.println("Failed to load simulation: " + e.getMessage());
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}