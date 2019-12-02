package client;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import classes.*;

class Client {
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
			WriteThread writethread=new WriteThread(clientSocket);
			Thread th1=new Thread(writethread);
			th1.start();
				
			ReadThread readthread=new ReadThread(clientSocket);
			Thread th2=new Thread(readthread);
			th2.start();
		}
}
