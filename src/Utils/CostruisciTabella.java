package Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/* Classe usata per costruire una JTable dato un resultset ottenuto da una query */

public class CostruisciTabella {
	public DefaultTableModel model;
	
	/* Costruttore della classe */
	
	public CostruisciTabella(ResultSet rs){
		Costruisci(rs);
	}
	
	/* Metodo. Costruisce la tabella */
	
	private void Costruisci(ResultSet result){
	try{
		ResultSetMetaData metaData = result.getMetaData();

		// Nomi delle colonne
		Vector<String> NomiColonne = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			NomiColonne.add(metaData.getColumnName(column));
		}

		// Dati della tabella
		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();
		while (result.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(result.getObject(columnIndex));
			}
			dati.add(vector);
		}
	
		model = new DefaultTableModel(dati, NomiColonne);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Impossibile recuperare i dati dal database! " + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
}