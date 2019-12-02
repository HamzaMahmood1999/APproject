package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class WriteThread implements Runnable {
	private Socket client;
	
	public WriteThread() {}
	
	public WriteThread(Socket sentClient) {
		this.client=sentClient;
	}
	
	public void setClient(Socket sentClient) {
		this.client=sentClient;
	}
	
	public void run() {
		try {
			BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				DataOutputStream sender=new DataOutputStream(client.getOutputStream());
				String sentence = inFromUser.readLine();
				sender.writeUTF(sentence);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
