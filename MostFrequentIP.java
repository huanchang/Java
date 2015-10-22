/*
    Big data problem.

*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MostFrequentIP{

    private static final String OUTPUT_FILE = "Data/originalData.txt";
    private static final String SMALL_FILE_PREFIX = "Data/data_";
    private static final int NUMBER_OF_IPS = 1000000000;
    
    private static final int NUMBER_OF_FILES = 100;
    
    public static void main(String[] args) {
        generateIPs(NUMBER_OF_IPS,OUTPUT_FILE);
        divideBigIPFile(OUTPUT_FILE);
        
        
        IPCounter[] threads = new IPCounter[NUMBER_OF_FILES];
        for ( int i = 0; i < NUMBER_OF_FILES; ++i) {
            threads[i] = new IPCounter(""+i, SMALL_FILE_PREFIX+i+".txt");
            threads[i].start();
        }
        
        
    }
    

    public static void generateIPs(int n, String fileName) {
        // Generate n random IP address
        
        Random random = new Random();
        
        try{
            // Create a file object
            File file = new File(fileName);
            
            FileWriter writer = new FileWriter(file);
            
            for ( int j = 0; j <n; ++j) {
            
                // create a random ip address like 192.168.1.1
                StringBuilder sb = new StringBuilder();
            
                for ( int i = 0; i <4; ++i) {
                    sb.append(random.nextInt(255));
                    if (i<3) {
                        sb.append(".");
                    }
                }
                sb.append("\n");
            
                writer.write(sb.toString());
            }
            
            writer.flush();
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void divideBigIPFile(String inputFileName) {
        try{
            // Read source file
            File inputFile = new File(inputFileName);
            
            FileReader reader = new FileReader(inputFile);
            
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			// Create a string buffer for each file
			StringBuffer[] stringBuffers = new StringBuffer[NUMBER_OF_FILES];
			for ( int i = 0; i < NUMBER_OF_FILES; ++i) {
			    stringBuffers[i] = new StringBuffer();
			}
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
			    // Calculate hash(ip) and mode by 1024
			    //System.out.println(line.trim().hashCode());
				int index = (line.trim().hashCode())%NUMBER_OF_FILES;
				
				index = index<0?index+NUMBER_OF_FILES:index;
				
				stringBuffers[index].append(line).append("\n");
				
			}
			
			reader.close();
			
			
			// Write data into small files
			for ( int i = 0; i <NUMBER_OF_FILES; ++i) {
			    String smallFileName = SMALL_FILE_PREFIX + i + ".txt";
			    
			    File outputFile = new File(smallFileName);
			    
			    FileWriter outputWriter = new FileWriter(outputFile);
			    
			    outputWriter.write(stringBuffers[i].toString());
			    
			    outputWriter.flush();
			    
			    outputWriter.close();
			    
			}
			
			
			
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static class IPCounter extends Thread{
        private HashMap<String,Integer> counter;
        private ArrayList<String> ips;
        
        
        private final String inputFile;
        private final String threadId;
        
        public String mostFreqIP;
        public int maxNumOfVisited;
        
        
        public IPCounter(final String threadId, final String inputFile) {
            this.threadId = threadId;
            this.inputFile = inputFile;
            counter = new HashMap<String,Integer>();
            ips = new ArrayList<String>();
            
            
        }
        
        public void run() {
        
            readData();
            findMostFrequentIP();
        }
        
        public void readData() {
        
            try{
                File file = new File(this.inputFile);
            
                FileReader reader = new FileReader(file);
                
                BufferedReader bufferReader= new BufferedReader(reader);
                
                String line;
                
                while( (line = bufferReader.readLine())!=null ) {
                
                    String s = line.trim();
                    
                    if (counter.containsKey(s)){
                        counter.put(s, counter.get(s)+1);
                    } else {
                        counter.put(s, 1);
                        ips.add(s);
                    }
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
        public void findMostFrequentIP() {
            
            int maxNumOfVisited = 0;
            String mostFreqIP = null;
            
            for (int i = 0; i < ips.size(); ++i) {
            
                int curr = counter.get(ips.get(i));
                
                if (maxNumOfVisited < curr) {
                    maxNumOfVisited = curr;
                    mostFreqIP = ips.get(i);
                }
            }
            
            System.out.println(this+", IP:"+mostFreqIP+", Times:"+maxNumOfVisited+"\n");
        }
        
        @Override
        public String toString() {
            return "Thread-"+this.threadId+", File-"+this.inputFile;
        }
    }
}