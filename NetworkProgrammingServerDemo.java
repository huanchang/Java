// Networking programming
// Java.net API supports two internet protocols: TCP(Transmission control protocol) and UDP(User Datagram Protocol)

// Two subjects: socket programming and url processing

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class NetworkProgrammingServerDemo{
    public static void main(String[] args) {
        int port;
        
        try{
            port = Integer.parseInt(args[0]);
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return;
        }
        
        try{
            Thread t = new ChatServer(port);
            t.start();
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
}


// Socket programming
// Socket provide TCP connections between two computers

class ChatServer extends Thread implements Observable{
    
    private ServerSocket serverSocket;
    
    public ChatServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }
    
    public void run() {
        
        while(true) {
            try{
                System.out.println("Waiting for client on port:"+ serverSocket.getLocalPort());
                
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "+ server.getRemoteSocketAddress());
                
                // GET input stream
                DataInputStream in = new DataInputStream(server.getInputStream());
                
                System.out.println(in.readUTF());
                
                // Set output stream
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
                
                // Close remote server
                server.close();
                
            } catch(SocketTimeoutException e) {
                System.out.println("Socket timed out!");
                break;
            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    
    
}