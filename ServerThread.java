package server;
import java.io.*;
import java.net.*;
import java.util.*;
import server.*;

public class ServerThread implements Runnable {
	private Socket client;
	
	public ServerThread(Socket sentClient) {
		this.client=sentClient;
	}
	
	public Socket getServerThreadClient() {
		return this.client;
	}
	
	public void run() {
		try {
			String clientSentence, sendSentence;
			DataInputStream receiver =new DataInputStream(client.getInputStream()); //USE BYTE STREAM
			DataOutputStream sender=new DataOutputStream(client.getOutputStream());
			while (true) {
				clientSentence = receiver.readUTF();
				System.out.println("CLIENT: "+clientSentence);
				//BufferedReader serverInput =new BufferedReader(new InputStreamReader(System.in));
				//String s=serverInput.readLine();
				//sendSentence=s;
				Vector<ServerThread> connectedClients=Server.getConnectedClients();
				Iterator it=connectedClients.iterator();
				while (it.hasNext()) {
					ServerThread vectorSerThread=(ServerThread)it.next();
					if (!this.equals(vectorSerThread)) {
						Socket client2=vectorSerThread.getServerThreadClient();
						DataOutputStream sender2=new DataOutputStream(client2.getOutputStream());
						sender2.writeUTF(clientSentence);
					}
				}
				
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
