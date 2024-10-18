package ecosystem;

import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private int quantity;
    private double growthRate;
    private double waterNeed;
    private double mineralsNeed;

    public Plant(String name, int quantity, double growthRate, double waterNeed, double mineralsNeed) {
        this.name = name;
        this.quantity = quantity;
        this.growthRate = growthRate;
        this.waterNeed = waterNeed;
        this.mineralsNeed = mineralsNeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    public double getWaterNeed() {
        return waterNeed;
    }

    public void setWaterNeed(double waterNeed) {
        this.waterNeed = waterNeed;
    }

    public double getMineralsNeed() {
        return mineralsNeed;
    }

    public void setMineralsNeed(double mineralsNeed) {
        this.mineralsNeed = mineralsNeed;
    }

    public void grow(double waterFactor, double mineralFactor, double sunlightFactor) {
        double growthFactor = growthRate * waterFactor * mineralFactor * sunlightFactor;
        int newPlants = (int) (quantity * growthFactor);
        quantity += newPlants;
    }

    public void applyNaturalMortality(double deathRate) {
        int deadPlants = (int) (quantity * deathRate);
        quantity = Math.max(0, quantity - deadPlants);
    }

    @Override
    public String toString() {
        return "Plant: " + name +
                ", quantity: " + quantity;
    }
}