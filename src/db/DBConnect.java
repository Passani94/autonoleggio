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
	
	/* Costruttore, stabilisce la connessione col DB*/
	
	public DBConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autonoleggio","root","");
		}catch (ClassNotFoundException e) {  
			JOptionPane.showMessageDialog(null, "Driver non trovato! " + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore, connession non stabilita! " + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* Metodo per effettuare la query */
	
	public void exequery(String query, String tipo){
		if (tipo.equals("select")){ /* se la query è una select usa questo codice */
			try {
				st = con.prepareStatement(query);
				rs = st.executeQuery();
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore SQL: " + e,
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (tipo.equals("update") || tipo.equals("insert") || tipo.equals("delete")){  /* codice usato per tutti gli altri tipi di query */
			try {
				st = con.prepareStatement(query);
				st.executeUpdate();
			}catch (SQLException e) { // 
					JOptionPane.showMessageDialog(null, "Errore SQL: " + e,
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	
}
