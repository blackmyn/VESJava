package com.ecosystem;

import ecosystem.Animal;
import ecosystem.Plant;
import ecosystem.Resource;
import org.junit.Before;
import org.junit.Test;
import utils.FileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class FileHandlerTest {

    private File testFile;

    @Before
    public void setUp() throws IOException {
        testFile = File.createTempFile("testfile", ".txt");
    }

    @Test
    public void testLoadAnimals() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("Lion CARNIVORE 50\n");
            writer.write("Elephant HERBIVORE 100\n");
        }

        List<Animal> animals = FileHandler.loadAnimals(testFile.getAbsolutePath());

        assertEquals(2, animals.size());
        assertEquals("Lion", animals.get(0).getName());
        assertEquals(50, animals.get(0).getPopulation());
        assertEquals(Animal.AnimalType.CARNIVORE, animals.get(0).getType());

        assertEquals("Elephant", animals.get(1).getName());
        assertEquals(100, animals.get(1).getPopulation());
        assertEquals(Animal.AnimalType.HERBIVORE, animals.get(1).getType());
    }

    @Test
    public void testLoadPlants() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("Rose 100\n");
            writer.write("Cactus 50\n");
        }

        List<Plant> plants = FileHandler.loadPlants(testFile.getAbsolutePath());

        assertEquals(2, plants.size());
        assertEquals("Rose", plants.get(0).getName());
        assertEquals(100, plants.get(0).getQuantity());

        assertEquals("Cactus", plants.get(1).getName());
        assertEquals(50, plants.get(1).getQuantity());
    }

    @Test
    public void testLoadResources() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("WATER 1000.0\n");
            writer.write("MINERALS 500.0\n");
            writer.write("SUNLIGHT 300.0\n");
        }

        List<Resource> resources = FileHandler.loadResources(testFile.getAbsolutePath());

        assertEquals(3, resources.size());
        assertEquals(Resource.ResourceType.WATER, resources.get(0).getType());
        assertEquals(1000.0, resources.get(0).getAmount(), 0.01);

        assertEquals(Resource.ResourceType.MINERALS, resources.get(1).getType());
        assertEquals(500.0, resources.get(1).getAmount(), 0.01);

        assertEquals(Resource.ResourceType.SUNLIGHT, resources.get(2).getType());
        assertEquals(300.0, resources.get(2).getAmount(), 0.01);
    }

    @Test(expected = IOException.class)
    public void testLoadAnimalsFileNotFound() throws IOException {
        FileHandler.loadAnimals("non_existing_file.txt");
    }

    @Test(expected = IOException.class)
    public void testLoadPlantsFileNotFound() throws IOException {
        FileHandler.loadPlants("non_existing_file.txt");
    }

    @Test(expected = IOException.class)
    public void testLoadResourcesFileNotFound() throws IOException {
        FileHandler.loadResources("non_existing_file.txt");
    }

    @org.junit.After
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }
}
