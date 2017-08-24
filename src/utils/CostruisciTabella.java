package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * La classe CostruisciTabella permette di costruire una tabella a partire dal ResultSet di una query SQL.
 */
public class CostruisciTabella {
	
	/**
	 * Contiene una tabella in forma matriciale (i.e. sotto forma di {@code Vector} di {@code Vectors}).
	 */
	public DefaultTableModel model;
	
	/**
	 * Inizializza un nuovo oggetto CostruisciTabella e richiama il metodo {@code costruisci} passando come argomento il ResultSet di una query SQL.
	 * 
	 * @param rs il ResultSet ottenuto da una query SQL.
	 */
	public CostruisciTabella(ResultSet rs){
		costruisci(rs);
	}
	
	
	/*Costruisce una tabella a partire dal ResultSet di una query SQL. */
	private void costruisci(ResultSet result){
	
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

	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "CostruisciTabella [La classe CostruisciTabella permette di costruire una tabella a partire dal ResultSet di una query SQL.]";
	}
	
	
}