/*
    Dining Philosopher problem solution:
    A bunch of philosophers are sitting around a circular table with one chopstick between each of them.
    A philosopher need both chopsticks to eat and always pick up the left chopstick before the right one.
    A deadlock may occur if all the philosophers pick up left chopstick at the same time.
    
    
*/
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;

import java.util.Random;


public class DiningPhilosopher{
   
    // Number of philosopher to test with
    public static final int NO_OF_PHILOSOPHER = 12;
    // Test time in milliseconds
    public static final int STIMILATION_DURATION_MILLIS = 1000 * 10;
    
    
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = null;
        
        Philosopher[] philosophers = null;
        
        try{
            philosophers = new Philosopher[NO_OF_PHILOSOPHER];
            
            
            Chopstick[] chopsticks = new Chopstick[NO_OF_PHILOSOPHER];
            
            // create chopstick instances
            for ( int i = 0; i < NO_OF_PHILOSOPHER; ++i) {
                chopsticks[i] = new Chopstick(i);
            }
            
            // Limit the number of threads            
            executorService = Executors.newFixedThreadPool(NO_OF_PHILOSOPHER);
            
            // create philosopher instances
            for( int i = 0; i < NO_OF_PHILOSOPHER; ++i) {
                philosophers[i] = new Philosopher(i,chopsticks[i],chopsticks[(i+1) % NO_OF_PHILOSOPHER]);
                executorService.execute(philosophers[i]);
            }
            
            
            Thread.sleep(STIMILATION_DURATION_MILLIS);    // wait for stimulation time
            
            // Stop all philosophers.
            for (Philosopher philosopher : philosophers) {
                philosopher.isTummyFull = true;
            }

            
        } finally {
            // Close everything down.
            executorService.shutdown();

            // Wait for all thread to finish
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }
            
            // Time for check
            for (Philosopher philosopher : philosophers) {
                System.out.println(philosopher + " => No of Turns to Eat =" + philosopher.getNumOfEat());
            }
      
        }
        
    }

    public static class Chopstick{
        private final int id;   // unique id
        private Lock up = new ReentrantLock();  // Make sure the chopstick can only be taken by one philosopher at the same time
        
        public Chopstick(final int id) {
            this.id = id;
        }
        
        public boolean pickUp(Philosopher who, String where)  throws InterruptedException{
        
            if (up.tryLock(10, TimeUnit.MILLISECONDS)) {   
             
                //Acquires the lock if it is not held by another thread within the given waiting time 
                // and the current thread has not been interrupted.
                System.out.println(who + " picked up " + where + " " + this);
                return true;
            } else {
                return false;
            }
        }
        
        public void putDown(Philosopher who, String where) {
            up.unlock();    // release the lock
            System.out.println(who + " put down " + where + " " + this);
        }
        
        @Override
        public String toString() {
            return "Chopstick-"+id;
        }
    }
    
    public static class Philosopher implements Runnable{
        private final int id;
        private final Chopstick leftChopstick;
        private final Chopstick rightChopstick;
        
        volatile boolean isTummyFull = false;
        
        private Random randomGenerator = new Random();
         
        private int noOfEat = 0;
        
        public Philosopher(final int id, final Chopstick l, final Chopstick r) {
            this.id = id;
            this.leftChopstick = l;
            this.rightChopstick = r;
        }
        
        
        @Override
        public void run() {
            try {
                while (!isTummyFull) {
                  // Think for a bit.
                  think();
                  // Make the mechanism obvious.
                  if (leftChopstick.pickUp(this, "left")) {
                        // First try to get left chopstick the right chopstick
                        if (rightChopstick.pickUp(this, "right")) {
                            //Got both chopsticks and eat
                            eat();
                            // Finished.
                            rightChopstick.putDown(this, "right");
                        }
                        // Finished.
                    leftChopstick.putDown(this, "left");
                  }
                }
              } catch (Exception e) {
                // Catch the exception outside the loop.
                e.printStackTrace();
              }
        }
        
        
        private void think()  throws InterruptedException{
            // Think for a random time between 1-1000 milliseconds
            System.out.println(this+" is thinking.");
            Thread.sleep(randomGenerator.nextInt(1000));
        }
        
        private void eat()  throws InterruptedException{
            noOfEat++;
            System.out.println(this+" is eating.");
            Thread.sleep(randomGenerator.nextInt(1000));
        }
        
        public int getNumOfEat() {
            return noOfEat;
        }
        
        @Override
        public String toString() {
            return "Philosopher-"+id;
        }
    }
    
    
}