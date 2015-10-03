// Design pattern: decorator pattern

// Decorator pattern attaches the additional responsibilities to an object dynamically.
// Provide a flexible alternative to extend functionality.

// Each decorator could has a component, add its own behavior before/after delegating the object it decorate the rest jobs.

import javax.swing.*;

public class DesignPatternDecoratorPattern{
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initialGUI();
            }
        });
        
    }
    
    private static void initialGUI() {
        //Create and set up the window.
        CoffeeShopUI starBuzz = new CoffeeShopUI();
        
        // Order a coffee
        Beverage b = new Espresso();
        
        b = new Milk(b);
        b = new Caramel(b);
        

        // Show coffee description with cost
        starBuzz.addLabel(b.getDescription() + ":" + b.cost());

        //Display the window.
        //starBuzz.pack();
        starBuzz.setVisible(true);
    }
    
}


// GUI class

class CoffeeShopUI extends JFrame{
    public CoffeeShopUI() {
        init();
    }
    
    private void init() {
        setTitle("StarBuzz Coffee");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void addLabel(String label) {
        add( new JLabel(label));
    }
    
    //TODO: add functionalities
}


// Beverage

abstract class Beverage{
   protected String description;
   protected String size;
   
   
   public abstract double cost();
   public String getDescription(){
        return "("+getSize() + ")"+description;
   }
   
   public void setSize(String size) {
        this.size = size;
   }
   
   public String getSize() {
        return size;
   }
}

// Concrete classes

class HouseBlend extends Beverage{
    public HouseBlend() {
        description = "House Blend Coffee";
        size = "Grande";
    }
    
    public double cost() {
        switch(getSize()) {
            case "Tall":
                return 0.56;
            case "Grande":
                return 0.89;
            case "Venti":
                return 1.56;
            default:
                return 0.89;
        }
    }
}

class DarkRoast extends Beverage{
    public DarkRoast() {
        description = "Dark Roast Coffee";
        size = "Grande";
    }
    public double cost() {
        switch(getSize()) {
            case "Tall":
                return 0.80;
            case "Grande":
                return 1.49;
            case "Venti":
                return 1.88;
            default:
                return 1.49;
        }
    }
}

class Espresso extends Beverage{
    public Espresso() {
        description = "Espresso Coffee";
        size = "Grande";
    }
    public double cost() {
        switch(getSize()) {
            case "Tall":
                return 0.56;
            case "Grande":
                return 1.89;
            case "Venti":
                return 2.23;
            default:
                return 1.89;
        }
    }
}


// Decorator
abstract class Decorator extends Beverage{
    protected Beverage beverage;
    
    public abstract String getDescription();
    
}

// Condiments decorator

class Mocha extends Decorator{

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    
    public String getDescription() {
        return this.beverage.getDescription() + ",Mocha";
    }
    
    public double cost() {
        return this.beverage.cost() + 1.0;
    }
}

class Whip extends Decorator{

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }
    
    public String getDescription() {
        return this.beverage.getDescription() + ",Whip";
    }
    
    public double cost() {
        return this.beverage.cost() + 0.6;
    }
}

class Caramel extends Decorator{

    public Caramel(Beverage beverage) {
        this.beverage = beverage;
    }
    
    public String getDescription() {
        return this.beverage.getDescription() + ",Caramel";
    }
    
    public double cost() {
        return this.beverage.cost() + 0.88;
    }
}

class Milk extends Decorator{

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }
    
    public String getDescription() {
        return this.beverage.getDescription() + ",Milk";
    }
    
    public double cost() {
        return this.beverage.cost() + 1.25;
    }
}
