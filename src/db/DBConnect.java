package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * La classe DBConnect fornisce metodi per interagire con il database.
 */
public class DBConnect {
	
	/**
	 * La connessione allo specifico database.
	 */
	public Connection con;
	
	/**
	 * La query SQL precompilata.
	 */
	public PreparedStatement st;
	
	/**
	 * Il ResultSet ottenuto dalla query SQL.
	 */
	public ResultSet rs;
	
	/**
	 * Inizializza un oggetto DBConnect e stabilisce la connessione con il database.
	 */
	public DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autonoleggio","root","");
		} catch (ClassNotFoundException e) {  
			JOptionPane.showMessageDialog(null, "Driver non trovato! " + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore! Connessione non stabilita! \n\n" + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Precompila la query SQL desiderata e, nel caso di una operazione di {@code select}, assegna il ResultSet derivante.
	 * 
	 * @param query la query SQL desiderata.
	 * @param tipo la tipologia di query SQL (delete, insert, select, update). 
	 * 
	 * @throws SQLException se avviene un errore che coinvolge il database.
	 */
	public void exequery(String query, String tipo) throws SQLException {
		
		if (tipo.equals("select")) {
			/* La query SQL desiderata è una select. */
			try {
				st = con.prepareStatement(query);
				rs = st.executeQuery();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore SQL: " + e,
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				throw new SQLException(e);
			} 
		} else if (tipo.equals("delete") || tipo.equals("insert") || tipo.equals("update")) {
			/*La query SQL desiderata esegue una operazione di delete, insert o update. */
			try {
				st = con.prepareStatement(query);
				st.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore SQL: " + e,
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				throw new SQLException(e);
			}
		}
	}
	
/* OVERRIDING METODI toString() ED equals() */	

	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "DBConnect [con=" + con + ", st=" + st + ", rs=" + rs + "]";
	}

	/**
	 * Confronta questo oggetto con quello passato come argomento.
	 * 
	 * @param obj l'oggetto da confrontare.
	 * @return true se i due oggetti sono uguali; false altrimenti.
	 */
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
