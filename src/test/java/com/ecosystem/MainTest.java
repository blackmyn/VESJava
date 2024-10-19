package com.ecosystem;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import ecosystem.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTest {

    @Mock
    private Simulation mockSimulation;

    @Mock
    private EcoSystem mockEcosystem;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockSimulation.getEcosystem()).thenReturn(mockEcosystem);
    }

    @Test
    public void testGetValidIntInput() {
        String input = "abc\n5\n";
        Scanner scanner = new Scanner(input);
        int result = Main.getValidIntInput(scanner);
        assertEquals(5, result);
    }

    @Test
    public void testGetValidDoubleInput() {
        String input = "abc\n4.5\n";
        Scanner scanner = new Scanner(input);
        double result = Main.getValidDoubleInput(scanner);
        assertEquals(4.5, result, 0.01);
    }

    @Test
    public void testAddAnimal() {
        String input = "Lion\n50\nCARNIVORE\n5.0\n0.8\n";
        Scanner scanner = new Scanner(input);

        Main.addAnimal(scanner, mockEcosystem);

        verify(mockEcosystem).addAnimal(any(Animal.class));
    }

    @Test
    public void testRemoveAnimal() {
        String input = "Lion\n";
        Scanner scanner = new Scanner(input);

        Main.removeAnimal(scanner, mockEcosystem);

        verify(mockEcosystem).removeAnimal("Lion");
    }

    @Test
    public void testUpdateAnimal() {
        String input = "Lion\n1\n60\n";
        Scanner scanner = new Scanner(input);
        Animal lion = new Animal("Lion", 50, Animal.AnimalType.CARNIVORE, 5.0, 0.8);

        List<Animal> animals = new ArrayList<>();
        animals.add(lion);
        when(mockEcosystem.getAnimals()).thenReturn(animals);

        Main.updateAnimal(scanner, mockEcosystem);

        assertEquals(60, lion.getPopulation());
    }

    @Test
    public void testAddPlant() {
        String input = "Rose\n100\n2.5\n1.0\n0.5\n";
        Scanner scanner = new Scanner(input);

        Main.addPlant(scanner, mockEcosystem);

        verify(mockEcosystem).addPlant(any(Plant.class));
    }

    @Test
    public void testRemovePlant() {
        String input = "Rose\n";
        Scanner scanner = new Scanner(input);

        Main.removePlant(scanner, mockEcosystem);

        verify(mockEcosystem).removePlant("Rose");
    }

    @Test
    public void testUpdatePlant() {
        String input = "Rose\n1\n120\n";
        Scanner scanner = new Scanner(input);
        Plant rose = new Plant("Rose", 100, 2.5, 1.0, 0.5);

        List<Plant> plants = new ArrayList<>();
        plants.add(rose);
        when(mockEcosystem.getPlants()).thenReturn(plants);

        Main.updatePlant(scanner, mockEcosystem);

        assertEquals(120, rose.getQuantity());
    }

    @Test
    public void testAddResource() {
        String input = "WATER\n100.0\n";
        Scanner scanner = new Scanner(input);

        Main.addResource(scanner, mockEcosystem);

        verify(mockEcosystem).addResource(any(Resource.class));
    }

    @Test
    public void testRemoveResource() {
        String input = "WATER\n";
        Scanner scanner = new Scanner(input);

        Main.removeResource(scanner, mockEcosystem);

        verify(mockEcosystem).removeResource(Resource.ResourceType.WATER);
    }

    @Test
    public void testUpdateResource() {
        String input = "WATER\n150.0\n";
        Scanner scanner = new Scanner(input);
        Resource water = new Resource(Resource.ResourceType.WATER, 100.0);

        List<Resource> resources = new ArrayList<>();
        resources.add(water);
        when(mockEcosystem.getResources()).thenReturn(resources);

        Main.updateResource(scanner, mockEcosystem);

        assertEquals(150.0, water.getAmount(), 0.01);
    }
}
