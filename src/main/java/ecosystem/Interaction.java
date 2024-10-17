package ecosystem;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Interaction {

    private static final double OPTIMAL_WATER = 500.0;
    private static final double OPTIMAL_MINERALS = 500.0;
    private static final double OPTIMAL_SUNLIGHT = 500.0;
    private static final double OPTIMAL_HUMIDITY = 50.0;
    private static final double TEMPERATURE_STRESS_LOW = 5.0;
    private static final double TEMPERATURE_STRESS_HIGH = 35.0;

    public void simulateOneDayForPlants(EcoSystem ecosystem) {
        Random random = new Random();

        double growthFactor = calculateGrowthFactor(ecosystem);

        int totalPlants = ecosystem.getPlants().stream().mapToInt(Plant::getQuantity).sum();
        if (totalPlants < 10) {
            growthFactor = Math.max(growthFactor, 1.1); // Увеличиваем рост, если растений мало.
        }

        for (Plant plant : ecosystem.getPlants()) {
            int newQuantity = (int) Math.round((double) plant.getQuantity() * growthFactor);
            newQuantity -= (int) Math.round((newQuantity * 0.02)); // Учитываем смертность
            plant.setQuantity(Math.max(0, newQuantity));
        }

        updateResourcesAfterPlantGrowth(ecosystem);
        applyClimateChange(ecosystem, random);
        replenishWater(ecosystem);
        replenishMinerals(ecosystem);
    }

    private double calculateGrowthFactor(EcoSystem ecosystem) {
        double waterFactor = calculateFactor(ecosystem.getResources().get(0).getAmount(), OPTIMAL_WATER);
        double mineralsFactor = calculateFactor(ecosystem.getResources().get(1).getAmount(), OPTIMAL_MINERALS);
        double sunlightFactor = calculateFactor(ecosystem.getResources().get(2).getAmount(), OPTIMAL_SUNLIGHT);
        double temperatureFactor = calculateTemperatureFactor(ecosystem.getClimate().getTemperature());
        double humidityFactor = calculateFactor(ecosystem.getClimate().getHumidity(), OPTIMAL_HUMIDITY);

        if (ecosystem.getResources().get(0).getAmount() == 0 || ecosystem.getResources().get(1).getAmount() == 0) {
            return 0.5;
        }

        return waterFactor * mineralsFactor * sunlightFactor * temperatureFactor * humidityFactor;
    }

    private double calculateTemperatureFactor(double currentTemperature) {
        if (currentTemperature >= TEMPERATURE_STRESS_LOW && currentTemperature <= TEMPERATURE_STRESS_HIGH) {
            return 1.0;
        }
        if (currentTemperature < TEMPERATURE_STRESS_LOW) {
            return Math.max(0.1, 0.5 * (currentTemperature / TEMPERATURE_STRESS_LOW));
        } else {
            double excess = (currentTemperature - TEMPERATURE_STRESS_HIGH) / TEMPERATURE_STRESS_HIGH;
            return Math.max(0.1, 1 - excess * 0.7); // Чем выше температура, тем сильнее штраф
        }
    }

    private void replenishWater(EcoSystem ecosystem) {
        double humidity = ecosystem.getClimate().getHumidity();
        double waterAdded = (humidity / 100) * 100;
        double maxWaterAmount = 1000;
        ecosystem.getResources().get(0).setAmount(
                Math.min(ecosystem.getResources().get(0).getAmount() + Math.min(waterAdded, maxWaterAmount), 10000)
        );
    }

    private void replenishMinerals(EcoSystem ecosystem) {
        double mineralsAdded = 20; // Добавляем небольшое количество минералов
        ecosystem.getResources().get(1).setAmount(
                Math.min(ecosystem.getResources().get(1).getAmount() + mineralsAdded, 5000)
        );
    }

    private void updateResourcesAfterPlantGrowth(EcoSystem ecosystem) {
        int totalPlants = ecosystem.getPlants().stream().mapToInt(Plant::getQuantity).sum();
        double waterConsumption = totalPlants * 0.02;
        double mineralsConsumption = totalPlants * 0.02;

        ecosystem.getResources().get(0).setAmount(Math.max(0, ecosystem.getResources().get(0).getAmount() - waterConsumption));
        ecosystem.getResources().get(1).setAmount(Math.max(0, ecosystem.getResources().get(1).getAmount() - mineralsConsumption));
    }

    private void applyClimateChange(EcoSystem ecosystem, Random random) {
        double temperatureChange = (random.nextDouble() * 2) - 1;
        double humidityChange = (random.nextDouble() * 4) - 2;

        ecosystem.getClimate().setTemperature(
                Math.round((ecosystem.getClimate().getTemperature() + temperatureChange) * 10.0) / 10.0
        );
        ecosystem.getClimate().setHumidity(
                Math.round((ecosystem.getClimate().getHumidity() + humidityChange) * 10.0) / 10.0
        );
    }

    public void simulateOneDayForAnimals(EcoSystem ecosystem) {
        Random random = new Random();

        for (Animal animal : ecosystem.getAnimals()) {
            simulateAnimalLife(animal, ecosystem, random);
        }

        updateResourcesAfterAnimalConsumption(ecosystem);
    }

    private void simulateAnimalLife(Animal animal, EcoSystem ecosystem, Random random) {
        boolean hasFood = checkFoodAvailability(animal, ecosystem);
        int baseDeathRate = 1 + (int) Math.round(random.nextDouble() * 0.75); // 1-1.75% смертности

        if (hasFood) {
            int newPopulation = (int) Math.round(animal.getPopulation() * (1 + (random.nextDouble() * 0.10)));
            animal.setPopulation(Math.max(0, newPopulation));
        } else {
            int deathRate = (int) Math.round(baseDeathRate * 1.2); // Увеличиваем смертность, если нет пищи
            int deadAnimals = (int) Math.round((double) animal.getPopulation() * deathRate / 100.0);
            animal.setPopulation(Math.max(0, animal.getPopulation() - deadAnimals));
        }
    }

    private boolean checkFoodAvailability(Animal animal, EcoSystem ecosystem) {
        if (animal.getType() == AnimalType.HERBIVORE) {
            return ecosystem.getPlants().stream().anyMatch(p -> p.getQuantity() > 0);
        } else if (animal.getType() == AnimalType.CARNIVORE) {
            return ecosystem.getAnimals().stream().anyMatch(a -> a.getType() == AnimalType.HERBIVORE && a.getPopulation() > 0);
        }
        return false;
    }

    private void updateResourcesAfterAnimalConsumption(EcoSystem ecosystem) {
        int totalHerbivorePopulation = ecosystem.getAnimals().stream()
                .filter(a -> a.getType() == AnimalType.HERBIVORE)
                .mapToInt(Animal::getPopulation)
                .sum();

        double totalPlantConsumptionRate = 0.001; // 0.1% потребление
        int totalPlantConsumption = (int) Math.round(totalHerbivorePopulation * totalPlantConsumptionRate);

        int plantsConsumed = Math.min(totalPlantConsumption, ecosystem.getPlants().stream().mapToInt(Plant::getQuantity).sum());

        if (plantsConsumed > 0) {
            ecosystem.getPlants().forEach(p -> p.setQuantity(
                    Math.max(0, p.getQuantity() - plantsConsumed / ecosystem.getPlants().size())
            ));
        }
    }

    private double calculateFactor(double currentValue, double optimalValue) {
        double ratio = currentValue / optimalValue;

        if (ratio >= 0.8 && ratio <= 1.2) {
            return 1.0;
        } else if (ratio > 1.2) {
            return 1 + (ratio - 1.0) * 0.5;
        } else {
            return Math.max(0.1, 0.5 * ratio);
        }
    }
}
