//Round-robin (RR) is one of the algorithms employed by process and network schedulers in computing.
// As the term is generally used, time slices are assigned to each process in equal portions and in circular order, handling all processes without priority 


// Key word: time-share


import java.util.LinkedList;

public class RoundRobinSchedule{

    public static void main(String[] args) {
    
        int[] arrive = {0, 50, 130, 190, 210, 350};
        int[] execution = {250, 170, 75, 100, 130, 50};
        
        int q = 100;
        try {
            System.out.printf("Round Robin Result: %f\n", roundRobin(arrive, execution, q));
        } catch (NotEqualSizeException e) {
            System.out.println(e.getMessage());
        }
    }

     private static double roundRobin(int[] arriveTime, int[] executionTime, int q) throws NotEqualSizeException {
        
        if (arriveTime==null||executionTime==null) {
            return 0;
        }
        
        if (arriveTime.length!=executionTime.length) {
            throw new NotEqualSizeException();
        }
        
        double waitTime = 0;
        
        // create a cache suffer queue
        LinkedList<Process> queue = new LinkedList<Process>();
        
        int currentTime = 0;
        
        int n = arriveTime.length;
        
        int nextPosition = 0;   // index for 
        
        while (nextPosition<n||!queue.isEmpty()) {
        
            if ( queue.isEmpty()) {
                // Empty queue: add nextPosition into queue
                queue.add(new Process(arriveTime[nextPosition], executionTime[nextPosition]));
                nextPosition++;
                
                // update current time
                currentTime = arriveTime[nextPosition];
                
            } else {
                // Execute the first job in the queue
                
                Process p = queue.get(0);
                queue.remove(0);
                
                int duration = Math.min(q,p.executeTime);  // Execute q or to the end of this job which comes first
                
                waitTime = currentTime - p.arriveTime;  //update arrive time
                
                currentTime += duration;
                
                for ( int i = nextPosition; i<n; ++i) {
                    if ( arriveTime[nextPosition]<=currentTime) {
                        // add new jobs into queue
                        queue.add(new Process(arriveTime[nextPosition], executionTime[nextPosition]));
                        nextPosition = i+1;
                    } else {
                        break;
                    }
                }
                
                if (p.executeTime > duration) {
                    // Current job is not finished then add it to the end of the queue and reduce the remind execution time
                    p.executeTime -= q;
                    queue.add(p);
                }
                
            }
            
            consoleDisplayProcessed(queue);
            System.out.printf("Current wait time: %f.\n", waitTime);
        }
        
               
        return n==0? 0 : waitTime/(double) n;
     }
     
     
     private static class Process {
        public int arriveTime;
        public int executeTime;
        
        public Process(int a, int e) {
            this.arriveTime = a;
            this.executeTime = e;
        }
        
     }
     
     
     private static void consoleDisplayProcessed(LinkedList<Process> processes) {
        if (processes!=null) {
            System.out.printf("Queue:\tArriveTime\tExecuteTime\n");
            for ( int i = 0; i < processes.size(); ++i) {
                Process p = processes.get(i);
                
                System.out.printf("\t%d\t%d\n",p.arriveTime, p.executeTime);
            }
        }
     }
     
     public static class NotEqualSizeException extends Exception {
        public NotEqualSizeException() {
            super("arriveTime and executionTime should have the same length.");
        }
     }
}