package database;

import java.sql.*;
import java.util.*;

public class DBCreation {
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "root";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "students";
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("root", this.userName);
		connectionProps.put("root", this.password);
		System.out.println("trying to get connection!! ");
		conn = DriverManager.getConnection("jdbc:mysql://"
		+ this.serverName + ":" + this.portNumber + "/" 
		+ this.dbName,connectionProps);		
		System.out.println(" Connection achieved!! ");
		return conn;
	}
	
	public void createUsersDB() throws SQLException, Exception {
		Connection conn=null;
		Statement stmt=null;
		
		try{
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    //STEP 3: Open a connection
		    //System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    stmt = conn.createStatement();
		    
		    String createSQL="CREATE DATABASE Users";
		    try {
		    	stmt.execute(createSQL);
		    }
		    catch (Exception e) {}
		    
			
		} catch (SQLException se) {
			  //Handle errors for JDBC
			  se.printStackTrace();
			} catch (Exception e) {
			  //Handle errors for Class.forName
			  e.printStackTrace();
			} finally {
			  //finally block used to close resources
			  try {
			     if (stmt!=null)
			        stmt.close();
			  } catch(SQLException se2) {}// nothing we can do
			  try {
			     if (conn!=null)
			        conn.close();
			  } catch (SQLException se) {
			     se.printStackTrace();
			  }//end finally try
			}//end try
	}
	
	public void createUsersTable() throws SQLException, Exception {
		Connection conn=null;
		Statement stmt=null;
		
		try{
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    //STEP 3: Open a connection
		    //System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    stmt = conn.createStatement();
		    
		    String createTableSQL="CREATE TABLE `users`.`user` (" + 
		    		" `userid` INT(5) NOT NULL AUTO_INCREMENT," +
		    		" `username` VARCHAR(25) NOT NULL," +
		    		" `password` VARCHAR(20) NOT NULL," +
		    		" `name` VARCHAR(25)," +
		    		" PRIMARY KEY (`userid`),"+
		    		" UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);";    
		    try {
		    	stmt.execute(createTableSQL);
		    }
		    catch (Exception e) {}
		    
			
		} catch (SQLException se) {
			  //Handle errors for JDBC
			  se.printStackTrace();
			} catch (Exception e) {
			  //Handle errors for Class.forName
			  e.printStackTrace();
			} finally {
			  //finally block used to close resources
			  try {
			     if (stmt!=null)
			        stmt.close();
			  } catch(SQLException se2) {}// nothing we can do
			  try {
			     if (conn!=null)
			        conn.close();
			  } catch (SQLException se) {
			     se.printStackTrace();
			  }//end finally try
			}//end try
	}
	
	
	public void createUser(String username, String password) throws SQLException, Exception {
		Connection conn=null;
		Statement stmt=null;
		
		try{
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    //STEP 3: Open a connection
		    //System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    stmt = conn.createStatement();
		    
		    String checkUserQuery="SELECT * FROM `users`.`user` WHERE username='"+username+"'";
		    ResultSet rs=stmt.executeQuery(checkUserQuery);
		    int count=0;
		    
		    while (rs.next()) {
		    	count++;
		    }
		    
		    if (count==0) { //if there exists no user with this username
			    String createUserSQL="INSERT into `users`.`user`(`username`, `password`) VALUES ('"+username+"', '"+password+"')";  
			    try {
			    	stmt.execute(createUserSQL);
			    }
			    catch (Exception e) {
			    	System.out.println(e);
			    }
		    }
		    
		    else {} //if there exists a user with this username
		    
			
		} catch (SQLException se) {
			  //Handle errors for JDBC
			  se.printStackTrace();
			} catch (Exception e) {
			  //Handle errors for Class.forName
			  e.printStackTrace();
			} finally {
			  //finally block used to close resources
			  try {
			     if (stmt!=null)
			        stmt.close();
			  } catch(SQLException se2) {}// nothing we can do
			  try {
			     if (conn!=null)
			        conn.close();
			  } catch (SQLException se) {
			     se.printStackTrace();
			  }//end finally try
			}//end try
	}
	
	public boolean validateUser(String findUsername, String password) throws SQLException, Exception { //for login
		Connection conn=null;
		Statement stmt=null;
		
		try{
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    //STEP 3: Open a connection
		    //System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    stmt = conn.createStatement();
		    
		    String checkUserQuery="SELECT * FROM `users`.`user` WHERE username="+findUsername;
		    ResultSet rs=stmt.executeQuery(checkUserQuery);
		    int count=0;
		    
		    try {
		    	while (rs.next()) {
		    		count++;
		    		String checkPassword=rs.getString("password");
		    		if (password.equals(checkPassword)) {
		    			return true;  //password is correct and user exists in the database
		    		}
		    		else {
		    			return false;  //password is incorrect
		    		}
		    	}
		    	
		    	if (count==0) {
		    		return false; //no user exists with the given username
		    	}
		    	else {
		    		return true; //user exists in the database
		    	}
		    }
		    catch (Exception e) {
		    	System.out.println(e);
		    }
		    
		    return false;
		    
			
		} catch (SQLException se) {
			  //Handle errors for JDBC
			  se.printStackTrace();
			} catch (Exception e) {
			  //Handle errors for Class.forName
			  e.printStackTrace();
			} finally {
			  //finally block used to close resources
			  try {
			     if (stmt!=null)
			        stmt.close();
			  } catch(SQLException se2) {}// nothing we can do
			  try {
			     if (conn!=null)
			        conn.close();
			  } catch (SQLException se) {
			     se.printStackTrace();
			  }//end finally try
			}//end try
		
		return false;
	}
	
}
