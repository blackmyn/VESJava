package ecosystem;

import java.io.Serializable;
import java.util.List;

public class Animal implements Serializable {
    private String name;
    private int population;
    private AnimalType type;
    private double foodRequirement;
    private double reproductionRate;

    public enum AnimalType {
        HERBIVORE, CARNIVORE
    }

    public Animal(String name, int population, AnimalType type, double foodRequirement, double reproductionRate) {
        this.name = name;
        this.population = population;
        this.type = type;
        this.foodRequirement = foodRequirement;
        this.reproductionRate = reproductionRate;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public void setFoodRequirement(double foodRequirement) {
        this.foodRequirement = foodRequirement;
    }

    public void setReproductionRate(double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public void seekFood(EcoSystem ecosystem) {
        if (type == AnimalType.HERBIVORE) {
            eatPlants(ecosystem);
        } else if (type == AnimalType.CARNIVORE) {
            huntAnimals(ecosystem);
        }
    }

    private void eatPlants(EcoSystem ecosystem) {
        List<Plant> plants = ecosystem.getPlants();
        int foodEaten = 0;
        int totalFoodNeeded = (int) (population * foodRequirement);

        for (Plant plant : plants) {
            int foodAvailable = plant.getQuantity();
            int foodToEat = Math.min(totalFoodNeeded - foodEaten, foodAvailable);
            plant.setQuantity(plant.getQuantity() - foodToEat);
            foodEaten += foodToEat;

            if (foodEaten >= totalFoodNeeded) {
                break;
            }
        }

        adjustPopulation(foodEaten, totalFoodNeeded);
    }

    private void huntAnimals(EcoSystem ecosystem) {
        List<Animal> preyAnimals = ecosystem.getAnimals().stream()
                .filter(animal -> animal.getType() == AnimalType.HERBIVORE)
                .toList();

        int foodEaten = 0;
        int totalFoodNeeded = (int) (population * foodRequirement);

        for (Animal prey : preyAnimals) {
            int preyAvailable = prey.getPopulation();
            int foodToEat = Math.min(totalFoodNeeded - foodEaten, preyAvailable);
            prey.setPopulation(prey.getPopulation() - foodToEat);
            foodEaten += foodToEat;

            if (foodEaten >= totalFoodNeeded) {
                break;
            }
        }

        adjustPopulation(foodEaten, totalFoodNeeded);
    }

    private void adjustPopulation(int foodEaten, int totalFoodNeeded) {
        if (foodEaten < totalFoodNeeded) {
            double starvationRate = (double) (totalFoodNeeded - foodEaten) / totalFoodNeeded;
            applyNaturalMortality(starvationRate);
        } else {
            int newAnimals = (int) (population * reproductionRate * ((double) foodEaten / totalFoodNeeded));
            population += newAnimals;
        }
    }

    public void applyNaturalMortality(double deathRate) {
        population = Math.max(0, (int) (population - (population * deathRate)));
    }

    @Override
    public String toString() {
        return "Animal: " + name +
                ", population: " + population +
                ", type: " + type;
    }
}