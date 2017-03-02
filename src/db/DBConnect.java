package db;

import java.sql.*;

public class DBConnect{
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect(String user, String pass){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autonoleggio",user,pass);
			st = con.createStatement();
		}catch(Exception ex){
		System.out.println("Errore:"+ex); /*da sostituire nella GUI*/
		}
	}
	
	public void getData(String query){  /*trovare un modo per adattare il codice in base alla query*/
		try{
			rs = st.executeQuery(query);
			System.out.println("Contenuto richiesto dalla query"); /*da sostituire nella GUI*/
			while (rs.next()){
				String name= rs.getString("CF_PIVA");
				System.out.println("Nome:"+name); /*da sostituire nella GUI*/
			}
		}catch(Exception ex){
		System.out.println("Errore:"+ex); /*da sostituire nella GUI*/
		}
	}
}
