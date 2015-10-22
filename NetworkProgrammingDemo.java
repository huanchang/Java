// Networking programming
// Java.net API supports two internet protocols: TCP(Transmission control protocol) and UDP(User Datagram Protocol)

// Two subjects: socket programming and url processing


import java.net.ServerSocket;
import java.net.Socket;


public class NetworkProgrammingDemo{
    public static void main(String[] args) {
    }
}


// Socket programming
// Socket provide TCP connections between two computers

class ChatServer extends Thread{
    
    private ServerSocket serverSocket;
    
    public ChatServer(int port) throws IOException{
        serverSocket = new ServerSocket();
        serverSocket.setSoTimeout(10000);
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
                
            } catch(SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    
    
}