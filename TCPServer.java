package server;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

public class TCPServer {
	public static final int SERVER_PORT = 6789;
	public static int serverPort;
	
	public static Vector<ServerThread> connectedClients=new Vector<ServerThread>();
	
	public static Vector<ServerThread> getConnectedClients() {
		return connectedClients;
	}
	
	public static void main(String argv[]) throws Exception {
		//ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
		//System.out.println("Server is listing at port  "+SERVER_PORT);

		
		System.out.println("Enter port number: ");
		Scanner in=new Scanner(System.in);
		String s=in.nextLine();
		serverPort=Integer.parseInt(s);
		ServerSocket welcomeSocket=new ServerSocket(serverPort);
		System.out.println("Server is listening at port = "+serverPort);
		System.out.println(welcomeSocket);
		while(true) { // Listen forever
			try {
				Socket connectSocket = welcomeSocket.accept();
				ServerThread connectedClient=new ServerThread(connectSocket);
				System.out.println("Connection accepted");
				connectedClients.add(connectedClient);
				Thread th1=new Thread(connectedClient);
				
				th1.start();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			//DataInputStream receiver =new DataInputStream(connectSocket.getInputStream());
			//DataOutputStream sender=new DataOutputStream(connectSocket.getOutputStream());
			//clientSentence = receiver.readUTF();
			//capitalizedSentence =clientSentence.toUpperCase()+"\r\n";
			//sender.writeUTF(capitalizedSentence);
		}
	}
}
