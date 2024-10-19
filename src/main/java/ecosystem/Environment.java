package ecosystem;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Environment implements Serializable {
    private static final double OPTIMAL_WATER = 5000.0;
    private static final double OPTIMAL_MINERALS = 5000.0;
    private static final double OPTIMAL_SUNLIGHT = 5000.0;
    private static final double OPTIMAL_HUMIDITY = 50.0;
    private static final double TEMPERATURE_STRESS_LOW = 5.0;
    private static final double TEMPERATURE_STRESS_HIGH = 35.0;

    private double temperature;
    private double humidity;
    private double water;
    private double minerals;
    private double sunlight;
    private final Random random = new Random();
    private int dayOfYear;

    public Environment() {
        this.temperature = 25;
        this.humidity = 60;
        this.water = 800;
        this.minerals = 600;
        this.sunlight = calculateSunlight();
        this.dayOfYear = 1;
    }

    public void update(List<Animal> animals, List<Plant> plants) {
        consumeResources(plants);
        applyClimateChange();
        if (random.nextDouble() < 0.01) {
            applyRainEvent();
        }
        sunlight = calculateSunlight();
        dayOfYear = (dayOfYear % 365) + 1;
    }
    private void applyRainEvent() {
        System.out.println("Rain event!");
        water += 500;
        minerals += 500;
        water = Math.min(water, 50000);
        minerals = Math.min(minerals, 50000);
    }
    public void consumeResources(List<Plant> plants) {
        for (Plant plant : plants) {
            water = Math.max(0, water - (plant.getWaterNeed() * plant.getQuantity()));
            minerals = Math.max(0, minerals - (plant.getMineralsNeed() * plant.getQuantity()));
        }
    }

    private double calculateSunlight() {
        double dayAngle = (dayOfYear / 365.0) * 2 * Math.PI;
        return 50 + (50 * Math.sin(dayAngle));
    }

    public void growPlants(List<Plant> plants) {
        for (Plant plant : plants) {
            double growthFactor = calculateGrowthFactor(this, plant);

            int newQuantity = (int) Math.round(plant.getQuantity() * growthFactor);
            newQuantity -= (int) Math.round(newQuantity * 0.002); //  Естественная  смертность  растений
            plant.setQuantity(Math.max(0, newQuantity));
        }

        consumeResources(plants);
    }

    public double calculateGrowthFactor(Environment environment, Plant plant) {
        double waterFactor = calculateFactor(water, OPTIMAL_WATER);
        double mineralsFactor = calculateFactor(minerals, OPTIMAL_MINERALS);
        double sunlightFactor = calculateFactor(sunlight, OPTIMAL_SUNLIGHT);
        double temperatureFactor = calculateTemperatureFactor(temperature);
        double humidityFactor = calculateFactor(humidity, OPTIMAL_HUMIDITY);

        double growthFactor = (waterFactor + mineralsFactor + sunlightFactor + temperatureFactor + humidityFactor) / 5;
        growthFactor = Math.min(growthFactor, 1.2);

        if (water < OPTIMAL_WATER / 2 || minerals < OPTIMAL_MINERALS / 2) {
            growthFactor = Math.max(growthFactor, 0.2); //  Минимальный  рост  при  нехватке  ресурсов
        }

        return growthFactor;
    }

    private double calculateTemperatureFactor(double currentTemperature) {
        if (currentTemperature >= TEMPERATURE_STRESS_LOW && currentTemperature <= TEMPERATURE_STRESS_HIGH) {
            return 1.0;
        }
        if (currentTemperature < TEMPERATURE_STRESS_LOW) {
            return Math.max(0.1, 0.5 * (currentTemperature / TEMPERATURE_STRESS_LOW));
        } else {
            double excess = (currentTemperature - TEMPERATURE_STRESS_HIGH) / TEMPERATURE_STRESS_HIGH;
            return Math.max(0.1, 1 - excess * 0.7);
        }
    }

    public void replenishResources() {
        water += random.nextDouble() * 250;
        minerals += random.nextDouble() * 250;

        water = Math.min(water, 50000);
        minerals = Math.min(minerals, 50000);
    }

    public void applyClimateChange() {
        double seasonalTemperatureChange = 10 * Math.sin((dayOfYear / 365.0) * 2 * Math.PI);
        double seasonalHumidityChange = 20 * Math.cos((dayOfYear / 365.0) * 2 * Math.PI);

        temperature += seasonalTemperatureChange + (random.nextDouble() - 0.5) * 2;
        humidity += seasonalHumidityChange + (random.nextDouble() - 0.5) * 5;

        temperature = Math.min(temperature, 35);
        humidity = Math.max(0, Math.min(humidity, 80));
    }

    public void setResourcesFromList(List<Resource> resources) {
        for (Resource resource : resources) {
            switch (resource.getType()) {
                case WATER:
                    this.water = resource.getAmount();
                    break;
                case MINERALS:
                    this.minerals = resource.getAmount();
                    break;
                case SUNLIGHT:
                    this.sunlight = resource.getAmount();
                    break;
            }
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

    @Override
    public String toString() {
        return  "Temperature: "  +  String.format("%.2f", temperature) +  "°C\n"  +
                "Humidity: "  +  String.format("%.2f", humidity) +  "%\n"  +
                "Water: "  +  String.format("%.2f", water) +  "\n"  +
                "Minerals: "  +  String.format("%.2f", minerals) +  "\n"  +
                "Sunlight: "  +  String.format("%.2f", sunlight);
    }

    public double getHumidity() {
        return humidity;
    }

    public void setMinerals(double minerals) {
        this.minerals = minerals;
    }

    public void setSunlight(double sunlight) {
        this.sunlight = sunlight;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getSunlight() {
        return sunlight;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getMinerals() {
        return minerals;
    }

    public double getWater() {
        return water;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }
}