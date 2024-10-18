package ecosystem;

import java.io.Serializable;

public class Resource implements Serializable {
    private ResourceType type;
    private double amount;

    public enum ResourceType {
        WATER, MINERALS, SUNLIGHT
    }

    public Resource(ResourceType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public ResourceType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Resource: " + type + ", amount: " + amount;
    }
}