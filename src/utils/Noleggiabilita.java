package utils;


import db.DBConnect;
import utils.GestioneGiorni;

public class Noleggiabilita {
	
	private static DBConnect connessione;
	
	public static boolean controlla(String targa, String inizio, String fine) throws Exception {
		
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
		
		return noleggiabile;
	}
}
