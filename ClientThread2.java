package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClientThread2 implements Runnable {
	private Socket client;
	
	public ClientThread2() {}
	
	public ClientThread2(Socket sentClient) {
		this.client=sentClient;
	}
	
	public void setClient(Socket sentClient) {
		this.client=sentClient;
	}
	
	public void run() {
		try {
			BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				DataInputStream receiver=new DataInputStream(client.getInputStream());
				String sentence = receiver.readUTF();
				System.out.println("FROM SERVER: " +sentence);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}