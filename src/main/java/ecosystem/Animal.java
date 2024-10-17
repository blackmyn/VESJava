package ecosystem;

public class Animal {
    private String name;
    private int population;
    private AnimalType type;

    public Animal(String name, int population, AnimalType type) {
        this.name = name;
        this.population = population;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Animal: " + name + ", population: " + population + ", type: " + type;
    }
}
