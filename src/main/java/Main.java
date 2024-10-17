
import ecosystem.EcoSystem;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EcoSystem ecosystem = new EcoSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в симулятор экосистемы!");
        System.out.println("1. Создать новую симуляцию");
        System.out.println("2. Продолжить существующую симуляцию");

        int choice = scanner.nextInt();
        if (choice == 1) {
            //ecosystem.createNewSimulation();
        } else {
            System.out.println("Выберите файл симуляции для загрузки:");
            // Добавьте логику для загрузки симуляции
        }

        // Логика для управления экосистемой в цикле
    }
}
