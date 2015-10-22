import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class CommandChatClient implements Runnable{
    
    private Socket socket = null;
    private ChatClientThread client = null;
    
    private Thread thread = null;
    
    // Input/Output stream
    private InputStream console = null;
    private OutputStream streamOut = null;
    
    
    public static void main(String[] args) {
    
        if ( args.length != 2 ) {
            System.out.println("Usage: java NetworkProgrammingClientDemo host port");
        } else {
            // create a socket with host and port
            CommandChatClient socket = new CommandChatClient(args[0], parseInt(args[1]));
        }
        
    }
    
    public CommandChatClient(String host, int port) {
    
        System.out.println("Creating new client...");
        
        try{
        
            // Create a socket client using server name and port
            server = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            
            start();
             
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void start() throws IOException {
        // GET console input
        console   = new DataInputStream(System.in);
        streamOut = new DataOutputStream(server.getOutputStream());
        
        if (thread == null) {  
        
            client = new ChatClientThread(this, socket);
            
            thread = new Thread(this);                   
            thread.start();
        }
    }
    
    public void run() throws IOException {
    
        // Repeat getting message from command line
        while( thread != null ) {
            try{  
                streamOut.writeUTF(console.readLine());
                streamOut.flush();
             } catch(IOException e) {  
                System.out.println("Sending error: " + e.getMessage());
                stop();
             }
        }
        
    }
    
    
    // Close socket and input/output stream
    public void stop() {  
        if (thread != null) {  
            thread.stop();  
            thread = null;
        }
      
        try {  
            if (console != null) {
                console.close();
            }
            if (streamOut != null) {
                streamOut.close();
            }
            
            if (socket!= null) {
                socket.close();
            }
        } catch(IOException e) {  
            System.out.println("Error closing ..."); 
        }
        
        // close client thread
        client.close();  
        client.stop();
   }
    
        
}


class ChatClientThread extends Thread{
    
}
