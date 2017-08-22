package entita;

import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import db.DBConnect;
import gui.moduli.ModuloContratto;
import utils.ArrotondaNumero;
import utils.GestioneGiorni;
import utils.Noleggiabilita;

/**
 * La classe Preventivo rappresenta i preventivi dell'autonoleggio.
 */
public class Preventivo {
	
	private DBConnect preventivo;
	
	private boolean test;	
	
	private String[] Date = new String[2];
	private String disponibilita;
	private String tipologia;
	private static String veicolo;
	private static String inizio;
	private static String fine;
	private String giorni;
	private String anni;
	private String acconto;
	private static long giorniNoleggio;
	private static double totale;
	private double alGiorno, alMese, giornoExtra, meseScontato, sconto;
	
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
	
	
	/**
	 * Inizializza un nuovo oggetto Preventivo e stabilisce una connessione con il database.
	 */
	public Preventivo() {
		test = true;
		preventivo = new DBConnect();
	}
	
	/**
	 * Calcola un nuovo preventivo.
	 * 
	 * @param content il form {@code "Calcola Preventivo"} ed i relativi dati inseriti.
	 * 
	 * @throws Exception se avviene qualche errore che coinvolge chiamate alle funzioni da noi definite.
	 */
	public void calcola(ModuloContratto content) throws Exception {
		
		if (check(content)) {
			giorniNoleggio = GestioneGiorni.calcolaGiorni(inizio, fine);
			try {
				totale = 0;
				giorni = "6_7_Giorni";
				content.lblPreventivo.setText("");
				/* Controlla se esiste un veicolo con la targa digitata. */
				preventivo.exequery("SELECT * FROM veicolo where Targa='"+veicolo+"'","select"); 
				if (preventivo.rs.next()) {
					tipologia = preventivo.rs.getString("Costobt");
					disponibilita = preventivo.rs.getString("Disponibilita");
					
					if (disponibilita.equals("NO")) {		
						JOptionPane.showMessageDialog(null, "Info! Il veicolo non è disponibile.",
								"INFO",
								JOptionPane.INFORMATION_MESSAGE);
				    } else if(((giorniNoleggio==366 || giorniNoleggio==367) && !tipologia.matches(LUNGO)) ||
			    				((giorniNoleggio==731 || giorniNoleggio==732) && !tipologia.matches(LUNGO)) || 
			    					((giorniNoleggio==1096 || giorniNoleggio==1097) && !tipologia.matches(LUNGO))) {
				    	JOptionPane.showMessageDialog(null, "Veicolo non noleggiabile a lungo termine.",
								"Errore ",
								JOptionPane.ERROR_MESSAGE);
					} else if ((giorniNoleggio>31 && giorniNoleggio<59) || (giorniNoleggio>61 && giorniNoleggio<89) || (giorniNoleggio>91 && giorniNoleggio<119) 
								|| (giorniNoleggio>121 && giorniNoleggio<149) || (giorniNoleggio>151 && giorniNoleggio<179) 
									|| (giorniNoleggio>181 && giorniNoleggio<366) || (giorniNoleggio>367 && giorniNoleggio<731) 
										|| (giorniNoleggio>732 && giorniNoleggio<1096) || (giorniNoleggio>1097)) {
						if (giorniNoleggio>31 && giorniNoleggio<59) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 1 Mese di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 2 Mesi di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else if (giorniNoleggio>61 && giorniNoleggio<89) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 2 Mesi di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 3 Mesi di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else if (giorniNoleggio>91 && giorniNoleggio<119) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 3 Mesi di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 4 Mesi di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else if (giorniNoleggio>121 && giorniNoleggio<149) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 4 Mesi di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 5 Mesi di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else if (giorniNoleggio>151 && giorniNoleggio<179) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 5 Mesi di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 6 Mesi di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else if (giorniNoleggio>181 && giorniNoleggio<366) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 6 Mesi di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 1 Anno di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else if (giorniNoleggio>366 && giorniNoleggio<731) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 1 Anno di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 2 Anni di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else if (giorniNoleggio>732 && giorniNoleggio<1096) {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 2 Anni di Noleggio: "+ Date[0] +"\n"
									+ "Data Fine 3 Anni di Noleggio: "+ Date[1],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						} else {
							Date = GestioneGiorni.dataFine(inizio, giorniNoleggio);
							JOptionPane.showMessageDialog(null, "Periodo di noleggio non consentito! \n \n"
									+ "Data Fine 3 Anni di Noleggio: "+ Date[0],
				    				"INFO", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
				    	if (giorniNoleggio>0 && giorniNoleggio<=7) {
				    		/* Caso 1. Noleggio da 1 a 7 giorni. */
				    		if (giorniNoleggio==1) {
				    			giorni = "1_Giorno";
				    		} else if (giorniNoleggio==2) {
				    			giorni = "2_Giorni";
				    		} else if (giorniNoleggio==3 || giorniNoleggio==4) {
				    			giorni = "3_4_Giorni";
				    		} else if (giorniNoleggio==5) {
				    			giorni = "5_Giorni";
				    		} else if (giorniNoleggio==6 || giorniNoleggio==7) {
				    			giorni = "6_7_Giorni";
				    		}
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il PrezzoBT in base ai giorni di noleggio. */
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (giorniNoleggio>=6) {
				    			totale = alGiorno;
				    		} else {
							totale = alGiorno * giorniNoleggio;
				    		}
				    	} else if (giorniNoleggio>7 && giorniNoleggio<14) { 
				    		/* Caso 2. Noleggio da 8 a 13 giorni. */
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    			giornoExtra = preventivo.rs.getBigDecimal("Giorno_Extra").floatValue();
				    		}
				    		totale = alGiorno + ((giorniNoleggio - 7) * giornoExtra);
				    	} else if (giorniNoleggio==14) {
				    		/* Caso 3. Noleggio di 2 settimane. */
				    		sconto = 0.1;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			sconto = alGiorno * 2 * sconto;
				    			totale = (alGiorno * 2) - sconto;
				    		} else {
				    			totale = (alGiorno * 2);
				    		}
				    	} else if (giorniNoleggio>14 && giorniNoleggio<21) {
				    		/* Caso 4. Noleggio da 15 a 20 giorni. */
				    		sconto = 0.1;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */	
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    			giornoExtra = preventivo.rs.getBigDecimal("Giorno_Extra").floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			sconto = alGiorno * 2 * sconto;
				    			totale = ((alGiorno * 2) - sconto) + ((giorniNoleggio - 14) * giornoExtra);
				    		} else {
				    			totale = (alGiorno * 2) + ((giorniNoleggio - 14) * giornoExtra);
				    		}
				    	} else if (giorniNoleggio==21) { 
				    		/* Caso 4. Noleggio di 3 settimane. */
				    		sconto = 0.15;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			sconto = alGiorno * 3 * sconto;
				    			totale = (alGiorno * 3) - sconto;
				    		} else {
				    			totale = alGiorno * 3;
				    		}
				    	} else if (giorniNoleggio>21 && giorniNoleggio<29) {
				    		/* Caso 6. Noleggio da 22 a 30 giorni. */
				    		sconto = 0.15;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    			giornoExtra = preventivo.rs.getBigDecimal("Giorno_Extra").floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			sconto = alGiorno * 3 * sconto;
				    			totale = ((alGiorno * 3) - sconto) + ((giorniNoleggio - 21) * giornoExtra);
				    		} else {
				    			totale = (alGiorno * 3) + ((giorniNoleggio - 21) * giornoExtra);
				    		}
				    	} else if (giorniNoleggio>=29 && giorniNoleggio<=31) {
				    		/* Caso 7. Noleggio di 1 mese. */
				    		sconto = 0.2;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			sconto = alGiorno * 4 * sconto;
				    			totale = (alGiorno * 4) - sconto;
				    		} else {
				    			totale = alGiorno * 4;
				    		}
				    	} else if (giorniNoleggio>=59 && giorniNoleggio<=61) {
				    		/* Caso 7. Noleggio di 2 mesi. */
				    		sconto = 0.2;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */		
				    		if (preventivo.rs.next()){
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			meseScontato = (alGiorno * 4) - (alGiorno * 4 * 0.2);
				    			sconto = (meseScontato * 2) * sconto;
				    			totale = (meseScontato * 2) - sconto;
				    		} else {
				    			totale = (alGiorno * 4) * 2;
				    		}
				    	} else if (giorniNoleggio>=89 && giorniNoleggio<=91) { 
				    		/* Caso 8. Noleggio di 3 mesi. */
				    		sconto = 0.3;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */	
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			meseScontato = (alGiorno * 4) - (alGiorno * 4 * 0.2);
				    			sconto = (meseScontato * 3) * sconto;
				    			totale = (meseScontato * 3) - sconto;
				    		} else {
				    			totale = (alGiorno * 4) * 3;
				    		}
				    	} else if (giorniNoleggio>=119 && giorniNoleggio<=121) {
				    	 	/* Caso 9. Noleggio di 4 mesi. */
				    		sconto = 0.35;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			meseScontato = (alGiorno * 4) - (alGiorno * 4 * 0.2);
				    			sconto = (meseScontato * 4) * sconto;
				    			totale = (meseScontato * 4) - sconto;
				    		} else {
				    			totale = (alGiorno * 4) * 4;
				    		}
				    	} else if (giorniNoleggio>=149 && giorniNoleggio<=151) {  
				    		/* Caso 10. Noleggio di 5 mesi. */
				    		sconto = 0.5;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */	
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			meseScontato = (alGiorno * 4) - (alGiorno * 4 * 0.2);
				    			sconto = (meseScontato * 5) * sconto;
				    			totale = (meseScontato * 5) - sconto;
				    		} else {
				    			totale = (alGiorno * 4) * 5;
				    		}
				    	} else if (giorniNoleggio>=179 && giorniNoleggio<=181) {  
				    		/* Caso 11. Noleggio di 6 mesi. */
				    		sconto = 0.6;
				    		preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo per 6-7 giorni. */		
				    		if (preventivo.rs.next()) {
				    			alGiorno = preventivo.rs.getBigDecimal(giorni).floatValue();
				    		}
				    		if (tipologia.matches(BREVE)) {
				    			meseScontato = (alGiorno * 4) - (alGiorno * 4 * 0.2);
				    			sconto = (meseScontato * 6) * sconto;
				    			totale = (meseScontato * 6) - sconto;
				    		} else {
				    			totale = (alGiorno * 4) * 6;
				    		}
				    	} else if ((giorniNoleggio==366 || giorniNoleggio==367) && tipologia.matches(LUNGO)) {
				    		/* Caso 12. Noleggio di 1 anno. */
				    		anni="1_Anno";
				    		preventivo.exequery("SELECT * FROM lungo_termine where Cod_LT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo in base agli anni di noleggio. */
				    		if (preventivo.rs.next()) {
				    			alMese = preventivo.rs.getBigDecimal(anni).floatValue();
				    			acconto = preventivo.rs.getBigDecimal("Anticipo").toString();
				    		}
				    		totale = alMese * 12;
				    	} else if ((giorniNoleggio==731 || giorniNoleggio==732) && tipologia.matches(LUNGO)) {
				    		/* Caso 13. Noleggio di 2 anni. */
				    		anni="2_Anni";
				    		preventivo.exequery("SELECT * FROM lungo_termine where Cod_LT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo in base agli anni di noleggio. */
				    		if (preventivo.rs.next()) {
				    			alMese = preventivo.rs.getBigDecimal(anni).floatValue();
				    			acconto = preventivo.rs.getBigDecimal("Anticipo").toString();
				    		}
				    		totale = alMese * 24;	
				    	} else if ((giorniNoleggio==1096 || giorniNoleggio==1097) && tipologia.matches(LUNGO)) { 
				    		/* Caso 14. Noleggio di 3 anni. */
				    		anni="3_Anni";
				    		preventivo.exequery("SELECT * FROM lungo_termine where Cod_LT='"+tipologia+"'","select"); 
				    		/* Prende il prezzo in base agli anni di noleggio. */
				    		if (preventivo.rs.next()){
				    			alMese = preventivo.rs.getBigDecimal(anni).floatValue();
				    			acconto = preventivo.rs.getBigDecimal("Anticipo").toString();
				    		}
				    		totale = alMese * 36;
				    	}
				    	
				    	/* Calcola lo sconto in base alla tipologia di cliente. */				    	
				    	if (content.comboBoxTipologiaCliente.getSelectedIndex()==1) {
				    		totale = totale - (totale * 0.20);
				    	} else if ((content.comboBoxTipologiaCliente.getSelectedIndex()==2) && (((giorniNoleggio==366 || giorniNoleggio==367) && tipologia.matches(LUNGO)) ||
				    			((giorniNoleggio==732 || giorniNoleggio==733) && tipologia.matches(LUNGO)) || 
				    			((giorniNoleggio==1098 || giorniNoleggio==1099) && tipologia.matches(LUNGO)))) {
				    		totale = totale - (totale * 0.10);
				    	}
												
				    	/* Calcola il sovrapprezzo per un conducente under 25. */
				    	if (giorniNoleggio>0 && giorniNoleggio<16) { 
				    		int eta = JOptionPane.showConfirmDialog(
							    null,
							    "Il conducente ha meno di 25 anni?",
							    "Età Conducente?",
							    JOptionPane.YES_NO_OPTION);
				    		if (eta == JOptionPane.YES_OPTION) {
				    			totale = totale + (giorniNoleggio * 20);
				    		}
				    	}
					
					/* Calcola il sovrapprezzo per kilometraggio illimitato. */
				    	int km = JOptionPane.showConfirmDialog(
						    null,
						    "Si desidera avere km illimitati?",
						    "Km Illimitati?",
						    JOptionPane.YES_NO_OPTION);
				    	if (km == JOptionPane.YES_OPTION) {
				    		totale = totale * 1.15;
				    	}
				    	
				    	/* Visualizza il totale e l'anticipo (nel solo caso di LungoTermine). */
				    	content.lblPreventivo.setText("Totale Preventivo = "+String.valueOf(ArrotondaNumero.arrotonda(totale,2))+" €");
				    	
				    	if(((giorniNoleggio==366 || giorniNoleggio==367) && tipologia.matches(LUNGO)) ||
				    			((giorniNoleggio==732 || giorniNoleggio==733) && tipologia.matches(LUNGO)) || 
				    				((giorniNoleggio==1098 || giorniNoleggio==1099) && tipologia.matches(LUNGO))) {
				    		JOptionPane.showMessageDialog(null, "L'acconto da pagare è di "+ acconto + " €.",
				    				"Acconto", JOptionPane.INFORMATION_MESSAGE);
				    	}
				    }
				} else { 
				    JOptionPane.showMessageDialog(null, "Errore! La targa digitata non è associata ad alcun veicolo.",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
				}
				preventivo.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Preventivo non calcolato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
	
	/**
	 * Verifica che i dati del preventivo da calcolare siano corretti.
	 * 
	 * @param content il form {@code "Calcola Preventivo"} ed i relativi dati inseriti.
	 * @return true se i dati inseriti sono validi; false altrimenti.
	 */
	private boolean check(ModuloContratto content) throws Exception {
		
		boolean check=true;		
		if (content.comboBoxTipologiaCliente.getSelectedIndex()==0 || inizio.equals("Seleziona una data") || fine.equals("Seleziona una data")) {
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i Campi!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		} else if (!veicolo.matches(TGPATTERN1) && !veicolo.matches(TGPATTERN2) && !veicolo.matches(TGPATTERN3) && !veicolo.matches(TGPATTERN4)) {
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (!inizio.matches(DATEPATTERN) && !inizio.equals("Seleziona una data")) {
			content.frmtdtxtfldInizio.setText("");
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di inizio noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (GestioneGiorni.nonConsentito(inizio)) {
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di inizio noleggio deve essere almeno uguale alla data odierna!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (!fine.matches(DATEPATTERN) && !fine.matches("Seleziona una data")) {
			content.frmtdtxtfldFine.setText("");
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di fine noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (GestioneGiorni.isMaggiore(inizio, fine)) {
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di fine noleggio deve essere maggiore della data di inizio!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if (!Noleggiabilita.controlla(veicolo,inizio,fine)) {
			check=false;
			JOptionPane.showMessageDialog(null, "Il veicolo non è noleggiabile per il periodo indicato!",
				    "INFO ",
				    JOptionPane.INFORMATION_MESSAGE);
		}
		if (check==false) {
			test=check; 
		} else {
			test=true;
		}
		return test;
	}
	
	/**
	 * Metodo usato quando si sceglie "Passa a Contratto" nel form {@code "Calcola Preventivo"}.
	 * 
	 * @return la targa del veicolo noleggiato
	 */
	public static String getVarVeicolo() {
		
		return veicolo;
	}
	
	/**
	 * Metodo usato quando si sceglie "Passa a Contratto" nel form {@code "Calcola Preventivo"}.
	 * 
	 * @return la data di inizio noleggio
	 */
	public static String getDataInizio() {
		
		return inizio;
	}
	
	/**
	 * Metodo usato quando si sceglie "Passa a Contratto" nel form {@code "Calcola Preventivo"}.
	 * 
	 * @return la data di fine noleggio
	 */
	public static String getDataFine() {
		
		return fine;
	}
	/**
	 * Metodo usato quando si sceglie "Passa a Contratto" nel form {@code "Calcola Preventivo"}.
	 * 
	 * @return la durata del noleggio espressa in giorni
	 */
	public static long getGiorniNoleggio () {
		
		return giorniNoleggio;
	}	
	
	/**
	 * Metodo usato quando si sceglie "Passa a Contratto" nel form {@code "Calcola Preventivo"}.
	 * 
	 * @return il costo totale del noleggio
	 */
	public static double getVarTotale() {
		
		return totale;
	}
	
	/**
	 * Assegna i valori inseriti nella form alle variabili dell'oggetto {@code Preventivo}.
	 * 
	 * @param content il form {@code "Calcola Preventivo"} ed i relativi dati inseriti.
	 */
	public void setValori(ModuloContratto content) {
		
		veicolo = content.txtVeicolo.getText().trim();
		inizio = content.frmtdtxtfldInizio.getText().trim();
		fine = content.frmtdtxtfldFine.getText().trim();
	}
	
/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "Preventivo [La classe Preventivo rappresenta i preventivi dell'autonoleggio.]";
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
		Preventivo other = (Preventivo) obj;
		if (!Arrays.equals(Date, other.Date))
			return false;
		if (Double.doubleToLongBits(alGiorno) != Double.doubleToLongBits(other.alGiorno))
			return false;
		if (Double.doubleToLongBits(alMese) != Double.doubleToLongBits(other.alMese))
			return false;
		if (anni == null) {
			if (other.anni != null)
				return false;
		} else if (!anni.equals(other.anni))
			return false;
		if (acconto == null) {
			if (other.acconto != null)
				return false;
		} else if (!acconto.equals(other.acconto))
			return false;
		if (disponibilita == null) {
			if (other.disponibilita != null)
				return false;
		} else if (!disponibilita.equals(other.disponibilita))
			return false;
		if (giorni == null) {
			if (other.giorni != null)
				return false;
		} else if (!giorni.equals(other.giorni))
			return false;
		if (Double.doubleToLongBits(giornoExtra) != Double.doubleToLongBits(other.giornoExtra))
			return false;
		if (Double.doubleToLongBits(meseScontato) != Double.doubleToLongBits(other.meseScontato))
			return false;
		if (preventivo == null) {
			if (other.preventivo != null)
				return false;
		} else if (!preventivo.equals(other.preventivo))
			return false;
		if (Double.doubleToLongBits(sconto) != Double.doubleToLongBits(other.sconto))
			return false;
		if (test != other.test)
			return false;
		if (tipologia == null) {
			if (other.tipologia != null)
				return false;
		} else if (!tipologia.equals(other.tipologia))
			return false;
		return true;
	}		
}
