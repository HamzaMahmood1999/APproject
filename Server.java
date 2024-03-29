package server;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

import classes.ChatRoom;
import database.DBCreation;

public class Server {
	public static final int SERVER_PORT = 6789;
	public static int serverPort;
	
	public static Vector<ServerThread> connectedClients=new Vector<ServerThread>();
	
	public static Vector<ChatRoom> chatrooms=new Vector<ChatRoom>();
	
	public static Vector<ServerThread> getConnectedClients() {
		return connectedClients;
	}
	
	public static void main(String argv[]) throws Exception {
		DBCreation userdatabase=new DBCreation();
		userdatabase.createUsersDB();
		userdatabase.createUsersTable();
		userdatabase.createUser("abc@1", "password");
		userdatabase.createUser("no", "sleepy");
		userdatabase.createUser("thisisrandom", "ok");
		userdatabase.createUser("abc@123", "password");
		
		
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
		}
	}
}
