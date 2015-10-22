// Design pattern: factory pattern


public class DesignPatternPizzaFactory{
    public static void main(String[] args) {
        PizzaStore store1 = new CollegeStationPizzaStore();
        PizzaStore store2 = new BryanPizzaStore();
        
        store1.orderPizza("mushroom");
        store1.orderPizza("ahi");
        store2.orderPizza("chicken");
        store2.orderPizza("vege");
    }
}


// Abstract class for pizza store
abstract class PizzaStore{

    abstract protected Pizza createPizza(String type);
    
    public void orderPizza(String type) {
        if (type == null) {
            System.out.println("Invalid input. Please input a pizza.");
        }
        Pizza pizza;
        pizza = createPizza(type);
        
        if (pizza == null) {
            System.out.println("Sorry, we don't have " +type);
            return;
        }
        
        pizza.prepare();
        pizza.bake();
        pizza.pack();
        
    }
    
}

// Concrete class for pizza store locating in different places
class CollegeStationPizzaStore extends PizzaStore{
    public CollegeStationPizzaStore() {
        System.out.println("Welcome to College Station PizzaHap.");
    }
    
    @Override
    public Pizza createPizza(String type) {
        if (type == null) {
            return null;
        }
        Pizza pizza;
        
        switch(type) {
            case "chicken":
                pizza = new ChickenPizza();
                break;
            case "vege":
                pizza = new VegePizza();
                break;
            case "mushroom":
                pizza = new MushroomPizza();
                break;
            default:
                pizza = null;
        }
        
        return pizza;
    }
}

// Concrete class for pizza store locating in different places
class BryanPizzaStore extends PizzaStore{
    public BryanPizzaStore() {
        System.out.println("Welcome to Bryan PizzaHap.");
    }
    
    @Override
    public Pizza createPizza(String type) {
        if (type == null) {
            return null;
        }
        Pizza pizza;
        
        switch(type) {
            case "sausage":
                pizza = new SausagePizza();
                break;
            case "vege":
                pizza = new VegePizza();
                break;
            default:
                pizza = null;
        }
        
        return pizza;
    }
}

//Abstract class for pizza

abstract class Pizza{
    protected abstract String getDescription();

    public void prepare(){
        System.out.println("Preparing " + getDescription());
    }
    
    public void bake(){
        System.out.println("Baking " + getDescription());
    }
    public void pack(){
        System.out.println("Packing " + getDescription());
    }
}

class SausagePizza extends Pizza{
    @Override
    public String getDescription() {
        return "Sausage pizza";
    }
}

class VegePizza extends Pizza{
    @Override
    public String getDescription() {
        return "Vege pizza";
    }
}

class ChickenPizza extends Pizza{
    @Override
    public String getDescription() {
        return "Chicken pizza";
    }
}

class MushroomPizza extends Pizza{
    @Override
    public String getDescription() {
        return "Mushroom pizza";
    }
}
