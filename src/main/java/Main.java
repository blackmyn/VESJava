
import ecosystem.EcoSystem;

import java.util.Scanner;

class App {
   public static void main(String[] args) {
        EcoSystem ecosystem = new EcoSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ecosystem simulator!");
        System.out.println("1. Create new simulation.");
        System.out.println("2. Load existing simulation.");

        int choice = scanner.nextInt();
        if (choice == 1) {
            //ecosystem.createNewSimulation();
        } else {
            System.out.println("Select the simulation file to load:");
        }

    }
}
