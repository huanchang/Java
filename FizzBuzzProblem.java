/*
    Multithread version of FizzBuzz problem. 
    Print "Fizz" if number can be divided by 3, "Buzz" if number can be divided by 5, "FizzBuzz" if both are satisfied.
    Otherwise print the number
    Four threads: on thread prints "Fizz", one prints "Buzz", one prints "FizzBuzz" and the last one prints current number
    
    P.S. Synchronized vs ReentrantLock
    synchronized: block synchronization, 
    Reentrantlock: need to have try/finally block
                    provides that synchronized doesn't, like timed lock waits, interruptible lock waits, 
                    non-block-structured locks, multiple condition variables, or lock polling.
*/


public class FizzBuzzProblem{
    public static void main(String[] args) {
        // Create one thread for each situation
        Thread[] threads = { new FizzBuzzMultiThread(true, false, 100, "Fizz"),
                            new FizzBuzzMultiThread(false, true, 100, "Buzz"),
                            new FizzBuzzMultiThread(true, true, 100, "FizzBuzz"),
                            new NumberFizzBuzz(false, false, 100)};
                            
        for (Thread thread:threads) {
            // Start each thread
            thread.start();
        }
    }
    
    public static class FizzBuzzMultiThread extends Thread{
        private boolean div3;
        private boolean div5;
        
        private String message;
        
        protected static Object lock = new Object();
        
        protected static int counter = 1;   // All objects share the same counter
        protected static int max = 1000;    // Maximum number of iterations
        
        public FizzBuzzMultiThread(boolean div3, boolean div5, int max, String s) {
            this.div3 = div3;
            this.div5 = div5;
            this.max = max;
            this.message = s;
        }
        
        public void print() {
            System.out.println(message+"-");
        }
        
        @Override
        public void run() {
            System.out.println("Starting thread");
            while(true) {
            
                // Synchronized block, threads share the same class cannot access lock at the same time
                synchronized(lock) {  
                
                    // check iteration 
                    if (counter>max) {
                        return; // out of range
                    } else {
                        // divided into different cases
                        if ( ((counter%3)==0)==div3 && ((counter%5)==0)==div5) {
                            print();    //call relative printer
                            counter++;  // increase the counter after correct run() was called
                        }
                    }
                    
                }
            }
        }
    }
    
    // Thread used to print out current number
    public static class NumberFizzBuzz extends FizzBuzzMultiThread{
    
        public NumberFizzBuzz(boolean div3, boolean div5, int max) {
            super( div3,  div5,  max, null);   // call parent constructor
        }
        
        public void print() {
            System.out.println(counter+"-");
        }
    }
}