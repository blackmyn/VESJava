package ecosystem;

import utils.EcosystemRepository;
import utils.PredictionEngine;

import java.io.IOException;
import java.io.Serializable;

public class Simulation implements Serializable {
    private EcoSystem ecosystem;
    private final EcosystemRepository repository;
    private final PredictionEngine predictionEngine;
    private int simulationDay = 0;

    public PredictionEngine getPredictionEngine() {
        return predictionEngine;
    }

    public Simulation(String dataDirectory) {
        this.ecosystem = new EcoSystem();
        this.repository = new EcosystemRepository(dataDirectory);
        this.predictionEngine = new PredictionEngine();
    }

    public void createNewSimulation() throws IOException {
        ecosystem.createNewSimulation();
    }

    public void loadSimulation(String filename) throws IOException, ClassNotFoundException {
        this.ecosystem = repository.loadSimulation(filename);
        System.out.println("Simulation loaded successfully.");
    }

    public void saveSimulation() throws IOException {
        repository.saveSimulation(ecosystem);
        System.out.println("Simulation saved.");
    }

    public void simulateOneDay() {
        ecosystem.simulateOneDay();
        simulationDay++;
        System.out.println("Day " + simulationDay + " of simulation completed.");
    }

    public void showEcosystem() {
        ecosystem.showEcosystem();
    }

    public EcoSystem getEcosystem() {
        return ecosystem;
    }

    public EcosystemRepository getRepository() {
        return repository;
    }

}