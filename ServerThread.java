package server;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private Socket client;
	
	public ServerThread(Socket sentClient) {
		this.client=sentClient;
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
				sender.writeUTF(sendSentence);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
