import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class NetworkProgrammingClientDemo{
    public static void main(String[] args) {
        
        // Get server name and port
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        
        try{
            System.out.println("Connecting to " + serverName +" on port " + port);
            
            // Create a socket client using server name and port
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
             
            // send message to server
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            
            
            // get message from server
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());
            
            client.close();
            
          }catch(IOException e)
          {
             e.printStackTrace();
          }
      
    }
}