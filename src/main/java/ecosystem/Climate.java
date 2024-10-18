package ecosystem;

import java.io.Serializable;

public class Climate implements Serializable {
    private double temperature;
    private double humidity;

    public Climate(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + ", Humidity: " + humidity;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemperature() {
        return temperature;
    }
}