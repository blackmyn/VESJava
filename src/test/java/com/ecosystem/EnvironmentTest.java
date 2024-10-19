package com.ecosystem;

import ecosystem.Environment;
import ecosystem.Plant;
import ecosystem.Animal;
import ecosystem.Resource;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EnvironmentTest {

    private Environment environment;
    private List<Animal> animals;
    private List<Plant> plants;

    @Before
    public void setUp() {
        environment = new Environment();
        animals = new ArrayList<>();
        plants = new ArrayList<>();

        // Добавление тестовых растений
        plants.add(new Plant("Rose", 100, 0.2, 0.1, 0.3));
        plants.add(new Plant("Cactus", 50, 0.15, 0.05, 0.2));
    }

    @Test
    public void testInitialConditions() {
        assertEquals(25.0, environment.getTemperature(), 0.01);
        assertEquals(60.0, environment.getHumidity(), 0.01);
        assertEquals(800.0, environment.getWater(), 0.01);
        assertEquals(600.0, environment.getMinerals(), 0.01);
        assertEquals(1, environment.getDayOfYear());
    }

    @Test
    public void testUpdateResources() {
        double initialWater = environment.getWater();
        double initialMinerals = environment.getMinerals();

        plants.get(0).setWaterNeed(1.5);
        plants.get(0).setMineralsNeed(1.5);

        environment.update(animals, plants);

        assertTrue(environment.getWater() < initialWater);
        assertTrue(environment.getMinerals() < initialMinerals);
    }


    @Test
    public void testGrowPlants() {
        environment.growPlants(plants);
        assertTrue(plants.get(0).getQuantity() >= 0);
        assertTrue(plants.get(1).getQuantity() >= 0);
    }

    @Test
    public void testConsumeResources() {
        double initialWater = environment.getWater();
        double initialMinerals = environment.getMinerals();

        environment.consumeResources(plants);

        assertTrue(environment.getWater() < initialWater);
        assertTrue(environment.getMinerals() < initialMinerals);
    }

    @Test
    public void testSetResourcesFromList() {
        List<Resource> resources = new ArrayList<>();
        resources.add(new Resource(Resource.ResourceType.WATER, 10000));
        resources.add(new Resource(Resource.ResourceType.MINERALS, 20000));
        resources.add(new Resource(Resource.ResourceType.SUNLIGHT, 30000));

        environment.setResourcesFromList(resources);
        assertEquals(10000.0, environment.getWater(), 0.01);
        assertEquals(20000.0, environment.getMinerals(), 0.01);
        assertEquals(30000.0, environment.getSunlight(), 0.01);
    }

    @Test
    public void testClimateChange() {
        double initialTemperature = environment.getTemperature();
        double initialHumidity = environment.getHumidity();

        environment.applyClimateChange();

        assertNotEquals(initialTemperature, environment.getTemperature());
        assertNotEquals(initialHumidity, environment.getHumidity());
    }

    @Test
    public void testReplenishResources() {
        double initialWater = environment.getWater();
        double initialMinerals = environment.getMinerals();

        environment.replenishResources();

        assertTrue(environment.getWater() > initialWater);
        assertTrue(environment.getMinerals() > initialMinerals);
    }

    @Test
    public void testCalculateGrowthFactor() {
        double growthFactor = environment.calculateGrowthFactor(environment, plants.get(0));
        assertTrue(growthFactor >= 0 && growthFactor <= 1.2);
    }

    @Test
    public void testToString() {
        String representation = environment.toString();
        assertTrue(representation.contains("Temperature:"));
        assertTrue(representation.contains("Humidity:"));
        assertTrue(representation.contains("Water:"));
        assertTrue(representation.contains("Minerals:"));
        assertTrue(representation.contains("Sunlight:"));
    }
}
