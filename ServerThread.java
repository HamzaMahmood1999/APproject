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
			DataInputStream receiver =new DataInputStream(client.getInputStream());
			DataOutputStream sender=new DataOutputStream(client.getOutputStream());
			while (true) {
				clientSentence = receiver.readUTF();
				System.out.println("CLIENT: "+clientSentence);
				BufferedReader serverInput =new BufferedReader(new InputStreamReader(System.in));
				String s=serverInput.readLine();
				sendSentence=s;
				Vector<ServerThread> connectedClients=TCPServer.getConnectedClients();
				Iterator it=connectedClients.iterator();
				while (it.hasNext()) {
					ServerThread vectorSerThread=(ServerThread)it.next();
					System.out.println(this+"~~~~~"+vectorSerThread);
					if (!this.equals(vectorSerThread)) {
						Socket client2=vectorSerThread.getServerThreadClient();
						DataOutputStream sender2=new DataOutputStream(client2.getOutputStream());
						sender2.writeUTF(sendSentence);
					}
				}
				
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
