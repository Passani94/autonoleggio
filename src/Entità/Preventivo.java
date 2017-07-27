package Entità;

import javax.swing.JOptionPane;

import db.DBConnect;

import GUI.Admin.ModuloCt;

import Utils.ArrotondaNumero;
import Utils.CalcolaGiorni;


/* Classe per l'entità Preventivo */

public class Preventivo {
	
	private DBConnect preventivo;
	
	private boolean test;	
	
	private String Disponibilita;
	private String Tipologia;
	private String Veicolo;
	private String Inizio;
	private String Fine;
	private String Giorni;
	private String Anni;
	private String Anticipo;
	private int Giorni_Noleggio;
	private double Al_Giorno, Al_Mese, Giorno_Extra, Sconto, Totale ;
	private final static String BREVE="Autobus_12_Posti|Autobus_16_Posti|Autocarro_Cabinato|Autocarro_Furgonato|Automobile_Berlina|Automobile_Cabriolet|"
			+ "Automobile_Coupè|Automobile_Fuoristrada|Automobile_Limousine|Automobile_Multispazio|Automobile_SUV|Automobile_Utilitaria|"
			+ "Motociclo_Motocicletta|Motociclo_Scooter|Quadriciclo_Quad_Bike";
	private final static String LUNGO="Automobile_Berlina|Automobile_Cabriolet|Automobile_Coupè|Automobile_Fuoristrada|"
			+ "Automobile_Multispazio|Automobile_SUV|Automobile_Utilitaria";
	private static final String TGPATTERN1 = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Autoveicolo
	private static final String TGPATTERN2 = "[a-zA-Z]{1}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Scooter
	private static final String TGPATTERN3 = "[a-zA-Z]{2}\\d\\d\\d\\d\\d"; //Pattern Targa Motocicletta e Quad-Bike
	private static final String TGPATTERN4 = "\\d[a-zA-Z]{2}\\d\\d\\d"; //Pattern Targa Mezzo Acquatico
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	
	/* Costruttore Preventivo */
	
	public Preventivo() {
		test = true;
		Totale = 0 ;
		Giorni = "6_7_Giorni";
		preventivo = new DBConnect();
	}
	
/* Metodo. Calcola un nuovo preventivo. */
	
	public void calcola(ModuloCt content) throws Exception {
		if (check(content)) {
			Giorni_Noleggio = CalcolaGiorni.calcolaGiorni(Inizio, Fine);
			try {
				Totale = 0;
				content.lblPreventivo.setText("");
				/* Controlla se esiste un veicolo con la targa digitata. */
				preventivo.exequery("SELECT * FROM veicolo where Targa='"+Veicolo+"'","select"); 
				if (preventivo.rs.next()) {
					Tipologia = preventivo.rs.getString("Costobt");
					Disponibilita = preventivo.rs.getString("Disponibilita");
					if(((Giorni_Noleggio==366 || Giorni_Noleggio==367) && !Tipologia.matches(LUNGO)) ||
			    				((Giorni_Noleggio==732 || Giorni_Noleggio==733) && !Tipologia.matches(LUNGO)) || 
			    					((Giorni_Noleggio==1098 || Giorni_Noleggio==1099) && !Tipologia.matches(LUNGO))) {
				    	JOptionPane.showMessageDialog(null, "Veicolo non noleggiabile a lungo termine.",
								"Errore ",
								JOptionPane.ERROR_MESSAGE);
					}else if (Disponibilita.equals("NO")) {		
						JOptionPane.showMessageDialog(null, "Info! Il veicolo non è disponibile.",
								"INFO",
								JOptionPane.INFORMATION_MESSAGE);
				    }else {
				    	if (Giorni_Noleggio>0 && Giorni_Noleggio<=7) {
				    		/* Caso 1. Noleggio da 1 a 7 giorni. */
				    		if (Giorni_Noleggio==1) {
				    			Giorni = "1_Giorno";
				    		}else if (Giorni_Noleggio==2) {
				    			Giorni = "2_Giorni";
				    		}else if (Giorni_Noleggio==3 || Giorni_Noleggio==4) {
				    			Giorni = "3_4_Giorni";
				    		}else if (Giorni_Noleggio==5) {
				    			Giorni = "5_Giorni";
				    		}else if (Giorni_Noleggio==6 || Giorni_Noleggio==7) {
				    			Giorni = "6_7_Giorni";
				    		}
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il PrezzoBT in base ai giorni di noleggio. */
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Giorni_Noleggio>=6){
				    			Totale = Al_Giorno;
				    		}else {
							Totale = Al_Giorno * Giorni_Noleggio;
				    		}
				    	}else if (Giorni_Noleggio>7 && Giorni_Noleggio<14){ 
				    		/* Caso 2. Noleggio da 8 a 13 giorni. */
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()){
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    			Giorno_Extra = preventivo.rs.getBigDecimal("Giorno_Extra").floatValue();
				    		}
				    		Totale = Al_Giorno + ((Giorni_Noleggio - 7) * Giorno_Extra);
				    	}else if (Giorni_Noleggio==14) {
				    		/* Caso 3. Noleggio di 2 settimane. */
				    		Sconto = 0.1;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = Al_Giorno * 2 * Sconto;
				    			Totale = (Al_Giorno * 2) - Sconto;
				    		}else {
				    			Totale = (Al_Giorno * 2);
				    		}
				    	}else if (Giorni_Noleggio>14 && Giorni_Noleggio<21) {
				    		/* Caso 4. Noleggio da 15 a 20 giorni. */
				    		Sconto = 0.1;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */	
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    			Giorno_Extra = preventivo.rs.getBigDecimal("Giorno_Extra").floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = Al_Giorno * 2 * Sconto;
				    			Totale = ((Al_Giorno * 2) - Sconto) + ((Giorni_Noleggio - 14) * Giorno_Extra);
				    		}else {
				    			Totale = (Al_Giorno * 2) + ((Giorni_Noleggio - 14) * Giorno_Extra);
				    		}
				    	}else if (Giorni_Noleggio==21) { 
				    		/* Caso 4. Noleggio di 3 settimane. */
				    		Sconto = 0.15;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = Al_Giorno * 3 * Sconto;
				    			Totale = (Al_Giorno * 3) - Sconto;
				    		}else {
				    			Totale = Al_Giorno * 3;
				    		}
				    	}else if (Giorni_Noleggio>21 && Giorni_Noleggio<30) {
				    		/* Caso 6. Noleggio da 22 a 30 giorni. */
				    		Sconto = 0.15;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    			Giorno_Extra = preventivo.rs.getBigDecimal("Giorno_Extra").floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = Al_Giorno * 3 * Sconto;
				    			Totale = ((Al_Giorno * 3) - Sconto) + ((Giorni_Noleggio - 21) * Giorno_Extra);
				    		}else {
				    			Totale = (Al_Giorno * 3) + ((Giorni_Noleggio - 21) * Giorno_Extra);
				    		}
				    	}else if (Giorni_Noleggio==30) {
				    		/* Caso 7. Noleggio di 1 mese. */
				    		Sconto = 0.2;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = Al_Giorno * 4 * Sconto;
				    			Totale = (Al_Giorno * 4) - Sconto;
				    		}else {
				    			Totale = Al_Giorno * 4;
				    		}
				    	}else if (Giorni_Noleggio==60) {
				    		/* Caso 7. Noleggio di 2 mesi. */
				    		Sconto = 0.2;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */		
				    		if (preventivo.rs.next()){
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = ((Al_Giorno * 4) * 2) * Sconto;
				    			Totale = ((Al_Giorno * 4) * 2) - Sconto;
				    		}else {
				    			Totale = (Al_Giorno * 4) * 2;
				    		}
				    	}else if (Giorni_Noleggio==90) { 
				    		/* Caso 8. Noleggio di 3 mesi. */
				    		Sconto = 0.3;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */	
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = ((Al_Giorno * 4) * 3) * Sconto;
				    			Totale = ((Al_Giorno * 4) * 3) - Sconto;
				    		}else {
				    			Totale = (Al_Giorno * 4) * 3;
				    		}
				    	}else if (Giorni_Noleggio==120) {
				    		/* Caso 9. Noleggio di 4 mesi. */
				    		Sconto = 0.35;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = ((Al_Giorno * 4) * 4) * Sconto;
				    			Totale = ((Al_Giorno * 4) * 4) - Sconto;
				    		}else {
				    			Totale = (Al_Giorno * 4) * 4;
				    		}
				    	}else if (Giorni_Noleggio==150) {  
				    		/* Caso 10. Noleggio di 5 mesi. */
				    		Sconto = 0.5;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */	
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = ((Al_Giorno * 4) * 5) * Sconto;
				    			Totale = ((Al_Giorno * 4) * 5) - Sconto;
				    		}else {
				    			Totale = (Al_Giorno * 4) * 5;
				    		}
				    	}else if (Giorni_Noleggio==180) {  
				    		/* Caso 11. Noleggio di 6 mesi. */
				    		Sconto = 0.6;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */		
				    		if (preventivo.rs.next()) {
				    			Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
				    		}
				    		if (Tipologia.matches(BREVE)) {
				    			Sconto = ((Al_Giorno * 4) * 6) * Sconto;
				    			Totale = ((Al_Giorno * 4) * 6) - Sconto;
				    		}else {
				    			Totale = (Al_Giorno * 4) * 6;
				    		}
				    	} else if ((Giorni_Noleggio==366 || Giorni_Noleggio==367) && Tipologia.matches(LUNGO)){
				    		/* Caso 12. Noleggio di 1 anno. */
				    		Anni="1_Anno";
				    		preventivo.exequery("SELECT * FROM lungo_termine where Cod_LT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo in base agli anni di noleggio. */
				    		if (preventivo.rs.next()) {
				    			Al_Mese = preventivo.rs.getBigDecimal(Anni).floatValue();
				    			Anticipo = preventivo.rs.getBigDecimal("Anticipo").toString();
				    		}
				    		Totale = Al_Mese * 12;
				    	}else if ((Giorni_Noleggio==732 || Giorni_Noleggio==733) && Tipologia.matches(LUNGO)) {
				    		/* Caso 13. Noleggio di 2 anni. */
				    		Anni="2_Anni";
				    		preventivo.exequery("SELECT * FROM lungo_termine where Cod_LT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo in base agli anni di noleggio. */
				    		if (preventivo.rs.next()) {
				    			Al_Mese = preventivo.rs.getBigDecimal(Anni).floatValue();
				    			Anticipo = preventivo.rs.getBigDecimal("Anticipo").toString();
				    		}
				    		Totale = Al_Mese * 24;	
				    	}else if ((Giorni_Noleggio==1098 || Giorni_Noleggio==1099) && Tipologia.matches(LUNGO)) { 
				    		/* Caso 14. Noleggio di 3 anni. */
				    		Anni="3_Anni";
				    		preventivo.exequery("SELECT * FROM lungo_termine where Cod_LT='"+Tipologia+"'","select"); 
				    		/* Prende il prezzo in base agli anni di noleggio. */
				    		if (preventivo.rs.next()){
				    			Al_Mese = preventivo.rs.getBigDecimal(Anni).floatValue();
				    			Anticipo = preventivo.rs.getBigDecimal("Anticipo").toString();
				    		}
				    		Totale = Al_Mese * 36;
				    	}
					
				    	/* Calcola il sovrapprezzo per un conducente under 25. */
				    	if (Giorni_Noleggio>0 && Giorni_Noleggio<16) { 
				    		int eta = JOptionPane.showConfirmDialog(
							    null,
							    "Il conducente ha meno di 25 anni?",
							    "Età Conducente?",
							    JOptionPane.YES_NO_OPTION);
				    		if (eta == JOptionPane.YES_OPTION) {
				    			Totale = Totale + (Giorni_Noleggio * 20);
				    		}
				    	}
					
				    	int km = JOptionPane.showConfirmDialog(
						    null,
						    "Si desidera avere km illimitati?",
						    "Km Illimitati?",
						    JOptionPane.YES_NO_OPTION);
				    	if (km == JOptionPane.YES_OPTION) {
				    		Totale = Totale * 1.15;
				    	}
				    	System.out.println(Giorni_Noleggio);
				    	/* Visualizza il totale e l'anticipo (nel solo caso di LungoTermine). */
				    	content.lblPreventivo.setText("Totale Preventivo = "+String.valueOf(ArrotondaNumero.arrotonda(Totale,2))+" €");
				    	if(((Giorni_Noleggio==366 || Giorni_Noleggio==367) && Tipologia.matches(LUNGO)) ||
				    			((Giorni_Noleggio==732 || Giorni_Noleggio==733) && Tipologia.matches(LUNGO)) || 
				    				((Giorni_Noleggio==1098 || Giorni_Noleggio==1099) && Tipologia.matches(LUNGO))) {
				    		JOptionPane.showMessageDialog(null, "L'anticipo da pagare è di "+ Anticipo + " €.",
				    				"Anticipo", JOptionPane.INFORMATION_MESSAGE);
				    	}
				    }
				}else { 
				    JOptionPane.showMessageDialog(null, "Errore! La targa digitata non è associata ad alcun veicolo.",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
				}		
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Preventivo non calcolato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
			
	/* Metodo. Assegna i valori al Preventivo. */
	
	public void setValori(ModuloCt content) {
		Veicolo = content.txtVeicolo.getText().trim();
		Inizio = content.frmtdtxtfldInizio.getText().trim();
		Fine = content.frmtdtxtfldFine.getText().trim();
	}
	
	/* Metodo. Verifica la correttezza dei dati inseriti. */
	
	private boolean check(ModuloCt content) {
		boolean check=true;
		if (Inizio.equals("") || Inizio.equals("aaaa-mm-gg") || Fine.equals("") || Fine.equals("aaaa-mm-gg")) {
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i Campi!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if (!Veicolo.matches(TGPATTERN1) && !Veicolo.matches(TGPATTERN2) && !Veicolo.matches(TGPATTERN3) && !Veicolo.matches(TGPATTERN4)) {
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Inizio.matches(DATEPATTERN) && !Inizio.equals("") && !Inizio.equals("aaaa-mm-gg")) {
			content.frmtdtxtfldInizio.setText("");
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di inizio noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Fine.matches(DATEPATTERN) && !Fine.equals("") && !Fine.matches("aaaa-mm-gg")) {
			content.frmtdtxtfldFine.setText("");
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di fine noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) {
			test=check; 
		}else {
			test=true;
		}
		return test;
	}	
}
