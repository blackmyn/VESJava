package utils;

import ecosystem.EcoSystem;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EcosystemRepository {
    private final String dataDirectory;

    public EcosystemRepository(String dataDirectory) {
        this.dataDirectory = dataDirectory;
        createDataDirectoryIfNotExists();
    }

    private void createDataDirectoryIfNotExists() {
        File directory = new File(dataDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void saveSimulation(EcoSystem ecosystem) throws IOException {
        String fileName = generateSimulationFileName();
        String filePath = dataDirectory + "/" + fileName;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(ecosystem);
            System.out.println("Simulation saved to: " + filePath);
        }
    }

    public EcoSystem loadSimulation(String filename) throws IOException, ClassNotFoundException {
        String filePath = dataDirectory + "/" + filename;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (EcoSystem) inputStream.readObject();
        }
    }

    public String getDataDirectory() {
        return dataDirectory;
    }

    private String generateSimulationFileName() {
        String currentDateTime = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        return "simulation_" + currentDateTime + ".txt";
    }
}