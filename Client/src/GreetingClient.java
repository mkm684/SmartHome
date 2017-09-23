// File Name GreetingClient.java
import java.net.*;
import java.io.*;


public class GreetingClient {
	
	private OutputStream outToServer; 
	public DataOutputStream out;
	private InputStream inFromServer;
    public DataInputStream in;
	
	public GreetingClient(){

	}

   public void run() {
      String serverName = "127.0.0.1";
      int port = Integer.parseInt("5000");
      try {
         System.out.println("Connecting to " + serverName + " on port " + port);
         Socket client = new Socket(serverName, port);
         
         System.out.println("Just connected to server");
         outToServer = client.getOutputStream();
         out = new DataOutputStream(outToServer);
         
         //out.writeUTF("Hello from " + "SALI");
         inFromServer = client.getInputStream();
         in = new DataInputStream(inFromServer);
         /*if (in.readUTF() == " "){
        	 System.out.println("yes we connected");
        	 }*/
         System.out.println("Server says " + in.readUTF());
         
         
         //client.close();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}