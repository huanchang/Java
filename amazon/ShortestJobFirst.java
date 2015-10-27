// Short Job First (SJF) is a scheduling policy that selects the waiting process with the smallest execution time to execute next.

// CPU scheduling with least burst time(Non-preemptive)


// Example
// requestTime:    [0,1,2,3]
// duration:       [5,3,8,6]
public class ShortestJobFirst{

    public static void main(String[] args) {
        int[] requestTime = {0,1,2,3,6,10,30,31};
        int[] duration = {5,3,8,6,2,1,3,4};
        
        System.out.printf("Average wait time: %f\n", getAverageWait(requestTime, duration) );
    }

    public static float getAverageWait(int[] requestTime, int[] duration) {
        if (requestTime==null||duration==null||requestTime.length==0||duration.length==0) { 
            // Empty request queue
            return 0;
        }
        
        int counter = 0;    // count the number of executed request
        int n = requestTime.length; // The number of requests
        
        int waitTime = 0;
        int currentTime = 0;
        
        boolean[] executed = new boolean[n];    // executed flag for requests
        
        // Loop until all requests have been processed
        while(counter<n) {
            // Start from the first job
            if (counter == 0) {
                currentTime = requestTime[0];
                executed[0] = true;
                
                System.out.printf("Execute request-0 at time-%d, wait-0\n", currentTime);
                currentTime += duration[0];
            } else {
                // Select the executable request with shortest duration
                
                int minDuration = Integer.MAX_VALUE;
                int minIndex = -1;
                
                for (int i = 1; i <n; ++i) {
                    if (!executed[i] && requestTime[i] <= currentTime) { // check not executed request whose request time is not larger than current time
                        // update minimum durance if found a shorter job
                        if (minDuration>duration[i]) {
                            minDuration = duration[i];
                            minIndex = i;
                        }
                    }
                }
                
                if (minIndex==-1) {
                    // No requests found within currentTime, then found the next request waiting for executing
                    for (int i = 1; i <n; ++i) {
                        if (executed[i]==false) {
                            minIndex = i;
                            currentTime = requestTime[i];
                            break;
                        }
                    }
                    
                }
                
                
                // Execute found shortest job or next available job
                executed[minIndex] = true;
            
                // update total wait time
                waitTime += currentTime - requestTime[minIndex];
                
                System.out.printf("Execute request-%d at time-%d, wait-%d\n", minIndex, currentTime, currentTime - requestTime[minIndex]);
                
                currentTime += duration[minIndex];                
                 
            }
            
            counter++;
        }
        
        
        return (float)waitTime/(float) n;
    }
}