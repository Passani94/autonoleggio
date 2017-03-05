package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnect{
	
	public Connection con;
	public Statement st;
	
	/*Stabilisce la connessione col DB*/
	
	public DBConnect(String user, String pass){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autonoleggio",user,pass);
			st = con.createStatement();
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
	
	/*Costruttore DBConnect*/
	
	public DBConnect() {
		
	}
}
