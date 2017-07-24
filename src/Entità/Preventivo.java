package Entità;

import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import db.DBConnect;
import GUI.Admin.ModuloCt;

/* Classe per l'entità Preventivo */

public class Preventivo {
	
	private DBConnect preventivo;
	private boolean test;
	
	private String Veicolo;
	private String Inizio;
	private String Fine;
	private String Tipologia;
	private String Giorni;
	private String Anni;
	private String Anticipo;
	private int Anno_Inizio, Mese_Inizio, Giorno_Inizio;
	private int Anno_Fine, Mese_Fine, Giorno_Fine;
	private int Giorni_Noleggio;
	private double Sconto, Totale, Al_Giorno, Al_Mese;
	private final static String BREVE="Autobus_12_Posti|Autobus_16_Posti|Autocarro_Cabinato|Autocarro_Furgonato|Automobile_Berlina|Automobile_Cabriolet|Automobile_Coupè|Automobile_Fuoristrada|Automobile_Limousine|Automobile_Multispazio|Automobile_SUV|Automobile_Utilitaria|Motociclo_Motocicletta|Motociclo_Scooter";
	private final static String LUNGO="Automobile_Berlina|Automobile_Cabriolet|Automobile_Coupè|Automobile_Fuoristrada|Automobile_Multispazio|Automobile_SUV|Automobile_Utilitaria";
	private static final String TGPATTERN1 = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Autoveicolo
	private static final String TGPATTERN2 = "[a-zA-Z]{1}\\d\\d\\d[a-zA-Z]{2}"; //Pattern Targa Scooter
	private static final String TGPATTERN3 = "[a-zA-Z]{2}\\d\\d\\d\\d\\d"; //Pattern Targa Motocicletta e Quad-Bike
	private static final String TGPATTERN4 = "\\d[a-zA-Z]{2}\\d\\d\\d"; //Pattern Targa Mezzo Acquatico
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	
	/* Costruttore Preventivo */
	
	public Preventivo(){
		test=true;
		preventivo = new DBConnect();
		Totale = 0 ;
		Giorni = "6_7_Giorni";
	}
	
/* Metodo. Calcola un nuovo preventivo. */
	
	public void calcola(ModuloCt content){
		if (check(content)){
			/* Prende i valori di Anno, Mese, Giorno a partire dalle date inserite. */
			Anno_Inizio = Integer.parseInt(Inizio.substring(0,3)); 
			Mese_Inizio = Integer.parseInt(Inizio.substring(5,6));
			Giorno_Inizio = Integer.parseInt(Inizio.substring(8,9));
			Anno_Fine = Integer.parseInt(Fine.substring(0,3));
			Mese_Fine = Integer.parseInt(Fine.substring(5,6));
			Giorno_Fine = Integer.parseInt(Fine.substring(8,9));
			/* Calcola i giorni di noleggio.*/
			Giorni_Noleggio = calcolaGiorni(Anno_Inizio, Mese_Inizio, Giorno_Inizio, Anno_Fine, Mese_Fine, Giorno_Fine);
			try{
				/* Controlla se esiste un veicolo con la targa digitata. */
				preventivo.exequery("SELECT * FROM veicolo where Targa='"+Veicolo+"'","select"); 
				if(preventivo.rs.next()){
					Tipologia = preventivo.rs.getString("Costobt");
					/* Caso 1. Noleggio da 1 a 7 giorni. */
					if (Giorni_Noleggio>0 && Giorni_Noleggio<=7){
						if (Giorni_Noleggio==1){
							Giorni="1_Giorno";
						} else if (Giorni_Noleggio==2){
							Giorni="2_Giorni";
						} else if (Giorni_Noleggio==3 || Giorni_Noleggio==4){
							Giorni="3_4_Giorni";
						} else if (Giorni_Noleggio==5){
							Giorni="5_Giorni";
						} else if (Giorni_Noleggio==6 || Giorni_Noleggio==7){
							Giorni="6_7_Giorni";
						}
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); 
						/* Prende il PrezzoBT in base ai giorni di noleggio. */
						if (preventivo.rs.next()){
							Al_Giorno = preventivo.rs.getBigDecimal(Giorni).floatValue();
							System.out.println(Al_Giorno);
						}
						if (Giorni_Noleggio>=6){
							Totale = Al_Giorno;
						} else {
							Totale = Al_Giorno * Giorni_Noleggio;
							System.out.println(Totale);
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Preventivo non Calcolato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
			
	
	/* Metodo. Calcola i giorni di noleggio. */
	
	public int calcolaGiorni(int AnIn, int MeIn, int GioIn, int AnFi, int MeFi, int GioFi) {
		GregorianCalendar c1 = new GregorianCalendar(AnIn, MeIn, GioIn);
		GregorianCalendar c2 = new GregorianCalendar(AnFi, MeFi, GioFi);
		long millisecondi = c2.getTimeInMillis()-c1.getTimeInMillis();
		int giorni = (int) millisecondi/86400000;
		return giorni+1;
	}
	
	/* Metodo. Assegna i valori al Preventivo. */
	
	public void setValori(ModuloCt content){
		Veicolo = content.txtVeicolo.getText().trim();
		Inizio = content.frmtdtxtfldInizio.getText().trim();
		Fine = content.frmtdtxtfldFine.getText().trim();
	}
	
	/* Metodo. Verifica la correttezza dei dati inseriti. */
	
	private boolean check(ModuloCt content){
		boolean check=true;
		if(Inizio.equals("") || Inizio.equals("aaaa-mm-gg") || Fine.equals("") || Fine.equals("aaaa-mm-gg")){
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! Inserisci tutti i Campi!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if(Veicolo.length()<7 || Veicolo.length()>7 || !Veicolo.matches(TGPATTERN1)){
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La Targa del veicolo deve essere composta da: \n - Autoveicolo: 4 caratteri e 3 cifre (es. TO175RP); "
					+ "\n - Scooter: 3 caratteri e 3 cifre (es. X269DL); \n - Motocicletta e Quad-Bike: 2 caratteri e 5 cifre (es. AA12345);"
					+ "\n - Mezzo Acquatico: 2 caratteri e 4 cifre (es. 8PC567).",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Inizio.matches(DATEPATTERN) && !Inizio.equals("") && !Inizio.equals("aaaa-mm-gg")){
			content.frmtdtxtfldInizio.setText("");
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di inizio noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(!Fine.matches(DATEPATTERN) && !Fine.equals("") && !Fine.matches("aaaa-mm-gg")){
			content.frmtdtxtfldFine.setText("");
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore! La data di fine noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else {test=true;}
		return test;
	}
}
