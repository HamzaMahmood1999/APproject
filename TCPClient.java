package client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
	public static int serverPort;
	public static void main(String[] args) throws Exception {
			String hostName = "localhost";
			
			String sentence;
			String modifiedSentence="";
			BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter port number: ");
			Scanner in=new Scanner(System.in);
			String s=in.nextLine();
			serverPort=Integer.parseInt(s);
			Socket clientSocket=new Socket(hostName, serverPort);
			System.out.println("Server is listening at port = "+serverPort);
			System.out.println(clientSocket);
			//while (true) {
				ClientThread writethread=new ClientThread(clientSocket);
				Thread th1=new Thread(writethread);
				th1.start();
				
				ClientThread2 readthread=new ClientThread2(clientSocket);
				Thread th2=new Thread(readthread);
				th2.start();
			/*
			 * DataOutputStream sender=new DataOutputStream(clientSocket.getOutputStream());
			 * DataInputStream receiver=new DataInputStream(clientSocket.getInputStream());
			 * sentence = inFromUser.readLine(); sender.writeUTF(sentence);
			 * System.out.println("CLIENT: "+sentence); modifiedSentence =
			 * receiver.readUTF(); System.out.println("FROM SERVER: " +modifiedSentence);
			 */
				//clientSocket.close();
			//}
		}
}