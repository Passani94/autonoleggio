package utils;


import java.sql.SQLException;
import java.text.ParseException;

import db.DBConnect;
import utils.GestioneGiorni;

/**
 * La classe Noleggiabilità permette di controllare se un veicolo è noleggiabile o meno.
 */

public class Noleggiabilita {
	
	private static DBConnect connessione;
	
	/**
	 * Inizializza un nuovo oggetto Noleggiabilita.
	 */
	public Noleggiabilita() {
		
	}
	
	/**
	 * Verifica se un determinato veicolo è noleggiabile o meno.
	 * 
	 * @param targa lo specifico veicolo che si intende noleggiare
	 * @param inizio la data di inizio noleggio
	 * @param fine la data di fine noleggio
	 * @return true se il veicolo è noleggiabile; false altrimenti
	 * 
	 * @throws SQLException se avviene un errore che coinvolge il database
	 * @throws ParseException se avviene un errore durante il parsing delle stringhe passate come argomenti.
	 */
	public static boolean controlla(String targa, String inizio, String fine) throws SQLException, ParseException {
		
		boolean noleggiabile=true;
		String dataInizio, dataFine;
		connessione = new DBConnect();
		connessione.exequery("SELECT Cod_Noleggio, Data_Inizio, Data_Fine FROM noleggio WHERE Veicolo='"+targa+"'","select");
		if (connessione.rs.next()) {
			do {
				dataInizio=connessione.rs.getString("Data_Inizio");
				dataFine=connessione.rs.getString("Data_Fine");
				if (!GestioneGiorni.isMinore(inizio,dataInizio) && !GestioneGiorni.isMaggiore(fine,dataFine)) {
					noleggiabile=false;
				}else if (!GestioneGiorni.isMaggiore(inizio,dataInizio) && !GestioneGiorni.isMinore(fine,dataFine)) {
					noleggiabile=false;						
				}else if (!GestioneGiorni.isMaggiore(inizio,dataInizio) && !GestioneGiorni.isMinore(fine,dataInizio)) {
					noleggiabile=false;			
				}else if (!GestioneGiorni.isMaggiore(inizio,dataFine) && !GestioneGiorni.isMinore(fine,dataFine)) {
					noleggiabile=false;
				}else {
					noleggiabile=true;
				}
			} while (noleggiabile && connessione.rs.next());
		}
		
		connessione.con.close();
		return noleggiabile;
	}

	/**
	 * Restituisce una rappresentazione in stringa dell'oggetto.
	 */
	public String toString() {
		return "Noleggiabilita [La classe Noleggiabilità permette di controllare se un veicolo è noleggiabile o meno.]";
	}
	
	
	
}
