package utils;

import ecosystem.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PredictionEngine {

    public String predictPopulationChanges(EcoSystem ecosystem) {
        StringBuilder prediction = new StringBuilder("Prediction of population change:\n");

        for (Animal animal : ecosystem.getAnimals()) {
            String predictionForAnimal = predictAnimalPopulation(animal, ecosystem);
            prediction.append("- ").append(animal.getName()).append(": ").append(predictionForAnimal).append("\n");
        }

        for (Plant plant : ecosystem.getPlants()) {
            String predictionForPlant = predictPlantGrowth(plant, ecosystem);
            prediction.append("- ").append(plant.getName()).append(": ").append(predictionForPlant).append("\n");
        }

        prediction.append(predictClimateTrends(ecosystem));
        return prediction.toString();
    }

    private String predictAnimalPopulation(Animal animal, EcoSystem ecosystem) {
        boolean hasFood = checkFoodAvailability(animal, ecosystem);
        double temperature = ecosystem.getClimate().getTemperature();
        double humidity = ecosystem.getClimate().getHumidity();

        if (hasFood) {
            if (animal.getPopulation() < 10) {
                return "Population will increase (sufficient food and favorable conditions)";
            } else {
                if (temperature > 35 || temperature < 0) {
                    return "Population might decline slightly (extreme temperature)";
                }
                return "Population will remain stable (food is available)";
            }
        } else {
            if (temperature > 35 || humidity < 30) {
                return "Population will dwindle significantly (no food, extreme weather)";
            }
            return "Population will dwindle (no food)";
        }
    }

    private String predictPlantGrowth(Plant plant, EcoSystem ecosystem) {
        double waterFactor = calculatePlantGrowthFactor(plant, ecosystem.getResources().get(0), 500);
        double mineralsFactor = calculatePlantGrowthFactor(plant, ecosystem.getResources().get(1), 700);
        double sunlightFactor = calculatePlantGrowthFactor(plant, ecosystem.getResources().get(2), 300);
        double temperature = ecosystem.getClimate().getTemperature();
        double humidity = ecosystem.getClimate().getHumidity();

        double growthFactor = waterFactor * mineralsFactor * sunlightFactor;

        if (temperature < 0 || temperature > 40) {
            return "Quantity will decrease (extreme temperature)";
        }

        if (growthFactor > 0.9) {
            return "Quantity will increase (sufficient resources and favorable conditions)";
        } else if (growthFactor < 0.6) {
            return "Quantity will decrease (insufficient resources or unfavorable conditions)";
        } else {
            return "Quantity will remain stable (average conditions)";
        }
    }

    private String predictClimateTrends(EcoSystem ecosystem) {
        StringBuilder climatePrediction = new StringBuilder("\nClimate Predictions:\n");
        double currentTemp = ecosystem.getClimate().getTemperature();
        double currentHumidity = ecosystem.getClimate().getHumidity();

        if (currentTemp > 30) {
            climatePrediction.append("- Temperature may cause heat stress to some species.\n");
        } else if (currentTemp < 5) {
            climatePrediction.append("- Cold temperatures may hinder plant growth.\n");
        }

        if (currentHumidity < 30) {
            climatePrediction.append("- Low humidity may reduce water replenishment, affecting plants.\n");
        } else if (currentHumidity > 70) {
            climatePrediction.append("- High humidity may lead to increased water availability.\n");
        }

        return climatePrediction.toString();
    }

    private boolean checkFoodAvailability(Animal animal, EcoSystem ecosystem) {
        if (animal.getType() == Animal.AnimalType.HERBIVORE) {
            return ecosystem.getPlants().stream().anyMatch(p -> p.getQuantity() > 0);
        } else if (animal.getType() == Animal.AnimalType.CARNIVORE) {
            List<Animal> herbivores = ecosystem.getAnimals().stream()
                    .filter(a -> a.getType() == Animal.AnimalType.HERBIVORE && a.getPopulation() > 0)
                    .collect(java.util.stream.Collectors.toList());
            return !herbivores.isEmpty();
        }
        return false;
    }

    private double calculatePlantGrowthFactor(Plant plant, Resource resource, double optimalValue) {
        double difference = Math.abs(resource.getAmount() - optimalValue);

        if (difference == 0) return 1.0;
        if (difference <= optimalValue * 0.2) return 0.9;
        if (difference <= optimalValue * 0.5) return 0.7;
        return 0.5;
    }
}
