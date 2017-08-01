package Utils;


import db.DBConnect;
import Utils.GestioneGiorni;

public class ControlloDisponibilita {
	
	private DBConnect connessione;
	
	public boolean controlla(String targa, String inizio, String fine) throws Exception {
		
		boolean disponibile=true;
		String dataInizio, dataFine;
		connessione = new DBConnect();
		connessione.exequery("SELECT Cod_Noleggio, Data_Inizio, Data_Fine FROM noleggio WHERE Veicolo='"+targa+"'","select");
		if (connessione.rs.next()) {
			do {
				dataInizio=connessione.rs.getString("Data_Inizio");
				dataFine=connessione.rs.getString("Data_Fine");
				if(!(GestioneGiorni.isMaggiore(inizio,dataFine))){
					disponibile=false;
				}
				else if (!(GestioneGiorni.isMaggiore(dataInizio,fine))){
					disponibile=false;
				}
			} while (disponibile && connessione.rs.next());
		}
		
		return disponibile;
	}
}
