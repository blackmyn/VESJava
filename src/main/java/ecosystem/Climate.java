package ecosystem;

public class Climate {
    private double temperature;
    private double humidity;

    public Climate(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void changeClimate() {
        this.temperature += (Math.random() * 2 - 1);
        this.humidity += (Math.random() * 5 - 2.5);
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + ", Humidity: " + humidity;
    }
}
