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
	
	/* Costruttore. Stabilisce la connessione col DB*/
	
	public DBConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autonoleggio","root","");
		}catch (ClassNotFoundException e) {  
			JOptionPane.showMessageDialog(null, "Driver non trovato! " + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore! Connessione non stabilita! \n\n" + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* Metodo. Codice per effettuare la query */
	
	public void exequery(String query, String tipo) throws SQLException{
		/* Codice usato se la query è una select. */
		if (tipo.equals("select")){
			try {
				st = con.prepareStatement(query);
				rs = st.executeQuery();
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore SQL: " + e,
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				throw new SQLException(e);
			} /*Codice usato per tutte le altre query.*/
		} else if (tipo.equals("update") || tipo.equals("insert") || tipo.equals("delete")){
			try {
				st = con.prepareStatement(query);
				st.executeUpdate();
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore SQL: " + e,
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				throw new SQLException(e);
			}
		}
	}

	@Override
	public String toString() {
		return "DBConnect [con=" + con + ", st=" + st + ", rs=" + rs + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DBConnect other = (DBConnect) obj;
		if (con == null) {
			if (other.con != null)
				return false;
		} else if (!con.equals(other.con))
			return false;
		if (rs == null) {
			if (other.rs != null)
				return false;
		} else if (!rs.equals(other.rs))
			return false;
		if (st == null) {
			if (other.st != null)
				return false;
		} else if (!st.equals(other.st))
			return false;
		return true;
	}
	
	
}
