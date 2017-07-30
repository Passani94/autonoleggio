package Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CostruisciTabella {
	
	public DefaultTableModel model;
	
	/* Costruttore della classe */
	
	public CostruisciTabella(ResultSet rs){
		Costruisci(rs);
	}
	
	/* Metodo. Costruisce una tabella (JTable) a partire dal ResultSet ottenuto da una query SQL. */
	
	private void Costruisci(ResultSet result){
	
		try{
			ResultSetMetaData metaData = result.getMetaData();

			/* Estrae dal ResultSet i nomi delle colonne. */
			Vector<String> nomiColonne = new Vector<String>();
			int numeroColonne = metaData.getColumnCount();
			for (int colonna = 1; colonna <= numeroColonne; colonna++) {
				nomiColonne.add(metaData.getColumnName(colonna));
			}

			/* Processando il ResultSet riga per riga, estrae i valori della tabella. */
			Vector<Vector<Object>> dati = new Vector<Vector<Object>>();
			while (result.next()) {
				Vector<Object> riga = new Vector<Object>();
				for (int colonna = 1; colonna <= numeroColonne; colonna++) {
					riga.add(result.getObject(colonna));
				}
				dati.add(riga);
			}
	
			model = new DefaultTableModel(dati, nomiColonne);
		
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Impossibile recuperare i dati dal database! \n" + e,
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}