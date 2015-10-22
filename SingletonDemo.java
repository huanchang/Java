// Singleton pattern:only holds one instance and get a global point of access



public class SingletonDemo{
    public static void main(String[] args) {
        MultithreadDemo thread1 = new MultithreadDemo("Thread1");
        MultithreadDemo thread2 = new MultithreadDemo("Thread2");
        
        thread2.start();
        thread1.start();
        thread2.start();
    }
}


class Singleton{
    private static Singleton singleton; // only object
    
    private Singleton() {
        // private constructor
        System.out.println("Constructor of Singleton.");
    }
    
    public void display() {
        System.out.println("Display singleton");
    }
    
    
    public static synchronized Singleton getInstance() {
        // Multithreading: synchronized is force every thread to wait for its turn before it can enter the method
    
        // uniquely hold only one instance
        if ( singleton == null ) {
            singleton = new Singleton();
        }
        
        return singleton;
    }
}

// Implemented a runnable multi-thread class, need to implemented start() and run()


class MultithreadDemo implements Runnable{

    private Thread thread;
    private String threadName;
    
    public MultithreadDemo(String name) {
        threadName = name;
        System.out.println("Creating thread:"+threadName);
    }
    
    public void start() {
        System.out.println("Starting thread:"+threadName);
        
        if ( thread == null ) {
            thread = new Thread(this,threadName);
            thread.start();
        }
        
    }
    
    public void run() {
        System.out.println("Running thread:"+threadName);
        
        try{
            Singleton singleton = Singleton.getInstance();
            singleton.display();
        
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            System.out.println("Thread:"+threadName+" interrupted.");
        }
        
        System.out.println("Exiting thread:"+threadName);
    }
    
}


