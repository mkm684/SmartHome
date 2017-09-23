// File Name GreetingServer.java
import java.net.*;
import java.io.*;

public class GreetingServer  {
   private ServerSocket serverSocket;
   private Socket server;
   public DataInputStream in;
   public DataOutputStream out;
   
   public GreetingServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
   }

   public void run() {
         try {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            server = serverSocket.accept();
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("HELLO FROM HE SERVER");
            
          //System.out.println("Just connected to " + "Sali");
          //DataInputStream in = new DataInputStream(server.getInputStream());
            
          //System.out.println("clinet says"+in.readUTF());
          //DataOutputStream out = new DataOutputStream(server.getOutputStream());
          //out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
          //out.writeUTF("Thank you for connecting to Adam ");
          //server.close();
            
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
         }catch(IOException e) {
            e.printStackTrace();
         }
   }

}