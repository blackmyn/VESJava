package ecosystem;
import java.util.ArrayList;
import java.util.List;

public class EcoSystem {
    private List<Animal> animals;
    private List<Plant> plants;
    private List<Resource> resources;

    public EcoSystem(){
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public void createNewSimulation(){
        System.out.println("Creating new simulation...");
        //Todo: implement creation of new simulation
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public void addPlant(Plant plant){
        plants.add(plant);
    }

    public void addResources(Resource resource){
        resources.add(resource);
    }
    public void showEcosystem() {
        System.out.println("Current animals: ");
        for (Animal animal : animals) {
            System.out.println(animal);
        }

        System.out.println("Current plants: ");
        for (Plant plant : plants) {
            System.out.println(plant);
        }

        System.out.println("Current resources: ");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
    }
}
