package utils;

import ecosystem.Animal;
import ecosystem.EcoSystem;
import ecosystem.Environment;
import ecosystem.Plant;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Logger {
    private final String logFile;

    public Logger(String logFile) {
        this.logFile = logFile;
    }

    public void logDay(int day) {
        log("Day " + day + ":");
    }

    private void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    public void logAnimals(List<Animal> animals) {
        log("Animals:");
        for (Animal animal : animals) {
            log(" - " + animal.toString());
        }
    }

    public void logPlants(List<Plant> plants) {
        log("Plants:");
        for (Plant plant : plants) {
            log(" - " + plant.toString());
        }
    }

    public void logEnvironment(Environment environment) {
        log("Environment:");
        log(" - " + environment.toString());
    }
}