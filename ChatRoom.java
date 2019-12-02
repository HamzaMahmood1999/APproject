package classes;

import java.util.*;

public class ChatRoom {
	public int chatroomID;
	public Set<User> users=new HashSet<User>();
	
	public ChatRoom() {}
	
	public ChatRoom(int id) {
		this.chatroomID=id;
	}

	public int getChatroomID() {
		return chatroomID;
	}

	public void setChatroomID(int chatroomID) {
		this.chatroomID = chatroomID;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	public void removeUser(User u) {
		users.remove(u);
	}
}
