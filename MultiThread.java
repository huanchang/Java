class RunnableDemo implements Runnable {
	// Runnable is a interface in java.lang
	// Any class whose instances are intended to be executed by a thread should implement Runnable
	
	// Must define a method: run() with no arguments


	// Thread
   private Thread t;
   private String threadName;	// Define a customized name of a thread
   public static int count = 0;
   
   RunnableDemo( String name){
       threadName = name;
       System.out.println("Creating " +  threadName );
   }
   
   public void run() {
   
      System.out.println("Running " +  threadName );
      
      try {
      
      	// Output 4 digits and then sleep for 50 miliseconds
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
         
     } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
     }
     
     System.out.println("Thread " +  threadName + " exiting.");
     System.out.printf("Status of thread: %s\n", t.getState() );
   }
   
   public void start ()
   {
      System.out.println("Starting " +  threadName );
      
      if (t == null)
      {
      	// create a new thread
         t = new Thread (this, threadName);
         count++;
         
      }
      
      // start run()
      t.start ();
      
   }
   
   public void setPriority( int priority ){
   		if( t == null ) {
   			t = new Thread( this, threadName );
   		}
   		System.out.printf("Old priority: %d.\n", t.getPriority() );
   		t.setPriority(priority);
   		System.out.printf("New priority: %d.\n", t.getPriority() );
   }

}

public class MultiThread {

   public static void main(String args[]) {
   
      RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.setPriority(Thread.MIN_PRIORITY);
      R1.start();
      
      System.out.printf("Totally %d thread.\n", RunnableDemo.count);
      RunnableDemo R2 = new RunnableDemo( "Thread-2");
      R2.setPriority(Thread.MAX_PRIORITY);
      R2.start();
      
      System.out.printf("Totally %d thread.\n", RunnableDemo.count);
   }   
   
}