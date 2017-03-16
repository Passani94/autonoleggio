package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect{
	
	public Connection con;
	public Statement st;
	public ResultSet rs;
	
	/*Stabilisce la connessione col DB*/
	
	public DBConnect(String query){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autonoleggio","root","");
			st = con.createStatement();
			rs = st.executeQuery(query);
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
}
