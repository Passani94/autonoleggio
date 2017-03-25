package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnect{
	
	public Connection con;
	public PreparedStatement st;
	public ResultSet rs;
	
	/*Stabilisce la connessione col DB ed esegue la query*/
	
	public DBConnect(String query){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autonoleggio","root","");
			st = con.prepareStatement(query);
			rs = st.executeQuery();
		 } catch (ClassNotFoundException e) { // 
				JOptionPane.showMessageDialog(null, "Driver non trovato! " + e,
					    "Errore ",
						JOptionPane.ERROR_MESSAGE);
		    } catch (SQLException e) {
		    	JOptionPane.showMessageDialog(null, "Connessione non Possibile! " + e,
					    "Errore ",
						JOptionPane.ERROR_MESSAGE);
		    }
	}
}
