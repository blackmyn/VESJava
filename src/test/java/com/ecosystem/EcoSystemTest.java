package com.ecosystem;

import ecosystem.Animal;
import ecosystem.EcoSystem;
import ecosystem.Plant;
import ecosystem.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class EcoSystemTest {

    private EcoSystem ecoSystem;

    @Before
    public void setUp() {
        ecoSystem = new EcoSystem();
    }

    @Test
    public void testCreateNewSimulation() throws IOException {
        ecoSystem.createNewSimulation();

        List<Animal> animals = ecoSystem.getAnimals();
        List<Plant> plants = ecoSystem.getPlants();
        List<Resource> resources = ecoSystem.getResources();

        assertFalse("Expected some animals to be added", animals.isEmpty());
        assertFalse("Expected some plants to be added", plants.isEmpty());
        assertFalse("Expected some resources to be added", resources.isEmpty());

        assertNotNull("Climate should be set", ecoSystem.getClimate());
    }

    @Test
    public void testSimulateOneDay() throws IOException {
        ecoSystem.createNewSimulation();

        // Initial state
        int initialAnimalCount = ecoSystem.getAnimals().size();
        int initialPlantCount = ecoSystem.getPlants().size();

        ecoSystem.simulateOneDay();

        assertEquals("Animal count should decrease due to natural mortality", initialAnimalCount - 1, ecoSystem.getAnimals().size(), 1);
        assertEquals("Plant count should decrease due to natural mortality", initialPlantCount - 1, ecoSystem.getPlants().size(), 1);
    }

    @Test
    public void testAddAndRemoveAnimal() {
        Animal animal = new Animal("Lion", 10, Animal.AnimalType.CARNIVORE, 0.1, 0.15);
        ecoSystem.addAnimal(animal);

        assertEquals("Animal count should be 1 after adding", 1, ecoSystem.getAnimals().size());

        ecoSystem.removeAnimal("Lion");

        assertEquals("Animal count should be 0 after removing", 0, ecoSystem.getAnimals().size());
    }

    @Test
    public void testAddAndRemovePlant() {
        Plant plant = new Plant("Oak", 100, 0.15, 0.05, 0.03);
        ecoSystem.addPlant(plant);

        assertEquals("Plant count should be 1 after adding", 1, ecoSystem.getPlants().size());

        ecoSystem.removePlant("Oak");

        assertEquals("Plant count should be 0 after removing", 0, ecoSystem.getPlants().size());
    }

    @Test
    public void testClearSimulation() throws IOException {
        ecoSystem.createNewSimulation();
        ecoSystem.clearSimulation();

        assertTrue("Animal list should be empty after clearing", ecoSystem.getAnimals().isEmpty());
        assertTrue("Plant list should be empty after clearing", ecoSystem.getPlants().isEmpty());
        assertTrue("Resource list should be empty after clearing", ecoSystem.getResources().isEmpty());
        assertNull("Climate should be null after clearing", ecoSystem.getClimate());
    }
}
