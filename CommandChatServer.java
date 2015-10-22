// Multithread server for chat

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class CommandChatServer{
    public static final int DEFAULT_LISTENING_PORT = 6066;
    
    public static void main(String[] args) {
        ServerSocket socket = null;
        
        try{
            socket = new ServerSocket(DEFAULT_LISTENING_PORT);
            System.out.println("Server started on port " + DEFAULT_LISTENING_PORT);
        } catch( IOException ioe) {
            e.printStackTrace();
            System.exit(1);
        }
        
        
        // Create a dispatcher for server, dispatching incoming messages to all clients
        ServerDispatcher dispatcher = new ServerDispatcher();
        dispatcher.start();
        
        // Begin accept clients
        while (true) {
        
            try{
                Socket client = socket.accept();
                
                ClientInfo clientInfo = new ClientInfo();
                clientInfo.addSocket(client);
                
                ClientListener clientListener = new ClientListener(clientInfo, dispatcher);
                ClientSender clientSender = new ClientSender(clientInfo, dispatcher);
                
                clientInfo.addListener(clientListener);
                clientInfo.addSender(clientSender);
                
                dispatch.addClient(clientInfo);
                
                
            } catch( IOException ioe) {
                e.printStackTrace();
            }
        }
        
    }
}


class ServerDispatcher extends Thread{
    private List<String> queueMessage = null;
    private List<ClientInfo> queueClients = null;
    
    public ServerDispatcher() {
        queueMessage = new LinkedList<>();
        queueClients = new LinkedList<>();
    }
    
    public synchronized addClient(ClientInfo client) {
        queueClients.add(client);
    }
    
    // Remove client if it exist in the client queue
    public synchronized removeClient(ClientInfo client) {
        int index = queueClients.indexOf(client);
        
        if ( index != -1 ) {
            queueClients.remove(index);
        }
        
    }
    
    // Get message from the queue
    public synchronized String getMessageFromQueue() throws InterruptedException{
        while(queueMessage.size()==0) {
           wait();
        }
        
        String message = queueMessage.get(0);
        queueMessage.remove(0);
        
        return message;
    }
    
    public synchronized void dispath( ClientInfo client, String message) {
        Socket = client.getSocket();
        
        // Get socket information of sender
        String senderIP = socket.getInetAddress().getHostAddress();
        String senderPort = "" + socket.getPort();
        String message = senderIP + ":" + senderPort + " : " + aMessage;
        queueMessage.add(message);
        
        notify();
    }
    
    
}









