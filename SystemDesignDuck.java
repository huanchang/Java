// Design pattern practice: Duck

// There are multiple types of ducks. Some duck could quack while some cannot. Some duck can fly but others cannot.

public class SystemDesignDuck{
    public static void main(String[] args) {
        // Duck system design demo
        
        Duck duck1 = new RedHeadDuck();
        Duck duck2 = new RubberDuck();
        
        duck1.performFly();
        duck2.performFly();
        
        duck1.performQuack();
        duck2.performQuack();
        
        // Test dynamic setting behavior
        duck1.setQuackBehavior(new SQuack());
        
        duck1.performQuack();
        duck2.performQuack();
    }
}


abstract class Duck{

    // Separate the changing behaviors from constant parts
    protected QuackBehavior quackBehavior;
    protected FlyBehavior flyBehavior;
    
    public Duck() {
        // Make Duck a abstract class, implementations need to extend this class
    }
    
    public void display() {
    }
    
    public void performQuack() {
        quackBehavior.quack();
    }
    
    public void performFly() {
        flyBehavior.fly();
    }
    
    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }
    
    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }
    
    public void swim() {
        
    }
    
}

class RedHeadDuck extends Duck{
    public RedHeadDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
    
    @Override
    public void display() {
        System.out.println("This is a red head duck.");
    }
    
}

class RubberDuck extends Duck{
    public RubberDuck() {
        quackBehavior = new SQuack();
        flyBehavior = new FlyNoWay();
    }
    
    @Override
    public void display() {
        System.out.println("This is a rubber duck.");
    }
}


// Encapsulate fly behaviors

abstract class FlyBehavior{
    // Abstract class defines different fly behavior
    public FlyBehavior(){
    }
    public abstract void fly(); // abstract class doesn't have body
}

class FlyWithWings extends FlyBehavior{
    public FlyWithWings(){
    }
    
    @Override
    public void fly(){
        System.out.println("Flying~~~");
    }
}

class FlyNoWay extends FlyBehavior{
    public FlyNoWay(){
    }
    
    @Override
    public void fly(){
        System.out.println("On ground.");
    }
}


interface QuackBehavior{
    // Interface defines different quack behavior
    
    public void quack();    // Methods in interface don't have body
}

class Quack implements QuackBehavior{
    public Quack() {
    }
    
    public void quack() {
        System.out.println("Lalala~~~");
    }
}

class SQuack implements QuackBehavior{
    public SQuack() {
    }
    
    public void quack() {
        System.out.println("...");
    }
}
