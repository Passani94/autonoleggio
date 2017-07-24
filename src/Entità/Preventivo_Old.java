package Entità;

import javax.swing.JOptionPane;

import GUI.Admin.ModuloCt;
import db.DBConnect;

/* Classe per l'entità Preventivo */

public class Preventivo_Old {
	
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
	private int Diff_Anno, Diff_Mese, Diff_Giorno;
	private double Sconto, Totale, Al_Giorno, Al_Mese;
	private final static String BREVE="Autobus_12_Posti|Autobus_16_Posti|Autocarro_Cabinato|Autocarro_Furgonato|Automobile_Berlina|Automobile_Cabriolet|Automobile_Coupè|Automobile_Fuoristrada|Automobile_Limousine|Automobile_Multispazio|Automobile_SUV|Automobile_Utilitaria|Motociclo_Motocicletta|Motociclo_Scooter";
	private final static String LUNGO="Automobile_Berlina|Automobile_Cabriolet|Automobile_Coupè|Automobile_Fuoristrada|Automobile_Multispazio|Automobile_SUV|Automobile_Utilitaria";
	private static final String TGPATTERN = "[a-zA-Z]{2}\\d\\d\\d[a-zA-Z]{2}";
	private static final String DATEPATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	
	/* Costruttore Preventivo */
	
	public Preventivo_Old(){
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
			/* Calcola la differenza tra la data di inizio e la data di fine noleggio. */
			Diff_Anno = Anno_Fine - Anno_Inizio; 
			Diff_Mese = Mese_Fine - Mese_Inizio;
			Diff_Giorno = Giorno_Fine - Giorno_Inizio;
			try{
				/* Controlla se esiste un veicolo con la targa digitata. */
				preventivo.exequery("SELECT * FROM veicolo where Targa='"+Veicolo+"'","select"); 
				if(preventivo.rs.next()){
					Tipologia = preventivo.rs.getString("Costobt");
					/* Caso in cui il preventivo è per un noleggio da 1 a 7 giorni. */
					if (Diff_Anno==0 && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno<=7){
						if (Diff_Giorno==1){
							Giorni="1_Giorno";
						} else if (Diff_Giorno==2){
							Giorni="2_Giorni";
						} else if (Diff_Giorno==3 || Diff_Giorno==4){
							Giorni="3_4_Giorni";
						} else if (Diff_Giorno==5){
							Giorni="5_Giorni";
						} else if (Diff_Giorno==6 || Diff_Giorno==7){
							Giorni="6_7_Giorni";
						}
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo in base ai giorni di noleggio. */
						if (preventivo.rs.next()){
							Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();
						}
						if (Diff_Giorno>=6){
							Totale = Al_Giorno;
						}else{
							Totale = Al_Giorno * Diff_Giorno;
						}
					} else if (Diff_Anno==0 && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno<14){ /* Caso in cui il preventivo è per un noleggio tra 7 e 14 giorni. */
						Sconto=0.1;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 2 * Sconto;
						Totale = (Al_Giorno * 2) - Sconto; 
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per un giorno extra. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Km_Extra").floatValue();}
						Totale = Totale + Al_Giorno*(Diff_Giorno-7);
					} else if (Diff_Anno==0 && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno==14){ /* Caso in cui il preventivo è per un noleggio di 2 settimane. */
						Sconto=0.1;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 2 * Sconto;
						Totale = (Al_Giorno * 2) - Sconto;
					} else if (Diff_Anno==0 && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno<21){ /* Caso in cui il preventivo è per un noleggio tra 14 e 21 giorni. */
						Sconto=0.1;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 2 * Sconto;
						Totale = (Al_Giorno * 2) - Sconto;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per un giorno extra. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Km_Extra").floatValue();}
						Totale = Totale + Al_Giorno*(Diff_Giorno-14);
					} else if (Diff_Anno==0 && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno==21){ /* Caso in cui il preventivo è per un noleggio di 3 settimane. */
						Sconto=0.15;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						preventivo.rs.next();
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 3 * Sconto;
						Totale = (Al_Giorno * 3) - Sconto;
					} else if (Diff_Anno==0 && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno<31){ /* Caso in cui il preventivo è per un noleggio tra 21 e 31 giorni. */
						Sconto=0.1;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 3 * Sconto;
						Totale = (Al_Giorno * 3) - Sconto;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per un giorno extra. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Km_Extra").floatValue();}
						Totale = Totale + Al_Giorno*(Diff_Giorno-21);
					}  else if (Diff_Anno==0 && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno==31){ /* Caso in cui il preventivo è per un noleggio da 1 mese. */
						Sconto=0.2;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 4 * Sconto;
						Totale = (Al_Giorno * 4) - Sconto;
					} else if (Diff_Anno==0 && (Diff_Mese==1 || Diff_Mese==2) && Tipologia.matches(BREVE)){ /* Caso in cui il preventivo è per un noleggio di due mesi. */
						Sconto=0.2;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 4 * Sconto;
						Totale = (Al_Giorno * 4) - Sconto;
						Totale = Totale * 2;
					} else if (Diff_Anno==0 && (Diff_Mese==2 || Diff_Mese==3) && Tipologia.matches(BREVE)){ /* Caso in cui il preventivo è per un noleggio di tre mesi. */
						Sconto=0.3;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 4 * Sconto;
						Totale = (Al_Giorno * 4) - Sconto;
						Totale = Totale * 3;
					} else if (Diff_Anno==0 && (Diff_Mese==3 || Diff_Mese==4) && Tipologia.matches(BREVE)){ /* Caso in cui il preventivo è per un noleggio di quattro mesi. */
						Sconto=0.35;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 4 * Sconto;
						Totale = (Al_Giorno * 4) - Sconto;
						Totale = Totale * 4;					
					} else if (Diff_Anno==0 && (Diff_Mese==4 || Diff_Mese==5) && Tipologia.matches(BREVE)){ /* Caso in cui il preventivo è per un noleggio di cinque mesi. */
						Sconto=0.5;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 4 * Sconto;
						Totale = (Al_Giorno * 4) - Sconto;
						Totale = Totale * 5;
					} else if (Diff_Anno==0 && (Diff_Mese==5 || Diff_Mese==6) && Tipologia.matches(BREVE)){ /* Caso in cui il preventivo è per un noleggio di sei mesi. */
						Sconto=0.6;
						preventivo.exequery("SELECT * FROM breve_termine where Cod_BT='"+Tipologia+"'","select"); /* Prende il prezzo per 6-7 giorni. */
						if (preventivo.rs.next()){Al_Giorno = preventivo.rs.getBigDecimal("Giorni").floatValue();}
						Sconto = Al_Giorno * 4 * Sconto;
						Totale = (Al_Giorno * 4) - Sconto;
						Totale = Totale * 6;
					} else if ((Diff_Anno>0 || Diff_Mese>6) && Tipologia.matches(LUNGO)){ /* Caso in cui il preventivo è per un noleggio a lungo termine. */
						if (Diff_Anno==1){
							Anni="1_Anno";
						} else if (Diff_Anno==2){
							Anni="2_Anni";
						} else if (Diff_Anno==3){
							Anni="3_Anni";
						}
						preventivo.exequery("SELECT * FROM lungo_termine where Cod_LT='"+Tipologia.substring(10)+"'","select"); /* Prende il prezzo in base agli anni di noleggio. */
						if (preventivo.rs.next()){Al_Mese = preventivo.rs.getBigDecimal("Anni").floatValue();}
						Totale = Al_Mese * 12;
						Anticipo = preventivo.rs.getBigDecimal("Anticipo").toString();
						JOptionPane.showMessageDialog(null,
								"L'Anticipo per il noleggio da pagare è "+Anticipo);
					} else{
						JOptionPane.showMessageDialog(null, "Errore, Il Veicolo con la Targa "+Veicolo+" non è noleggiabile per il periodo indicato!",
								"Errore ",
								JOptionPane.ERROR_MESSAGE);
					}
					if (Diff_Anno==0  && (Diff_Mese==0 || Diff_Mese==1) && Diff_Giorno<15){ /* Calcola il sovrapprezzo per un conducente under 25. */
						int eta = JOptionPane.showConfirmDialog(
							    null,
							    "Il conducente ha meno di 25 anni?",
							    "Età Conducente?",
							    JOptionPane.YES_NO_OPTION);
						if (eta == JOptionPane.YES_OPTION){Totale = Totale + (Diff_Giorno * 20);}
					}
					int km = JOptionPane.showConfirmDialog(
						    null,
						    "Si desidera non avere limiti sul numero di km al giorno?",
						    "Km Illimitati?",
						    JOptionPane.YES_NO_OPTION);
					if (km == JOptionPane.YES_OPTION){Totale = Totale * 1.15;}
					content.lblPreventivo.setText("Totale Preventivato= "+String.valueOf(Totale));/* Visualizza il totale. */
				} else{
					JOptionPane.showMessageDialog(null, "Errore, Il Veicolo con la Targa "+Veicolo+" non è presente nel DB!",
							"Errore ",
							JOptionPane.ERROR_MESSAGE);
					content.txtVeicolo.setText("");
					content.txtVeicolo.requestFocus();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Preventivo non Calcolato!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/* Metodo per assegnare i valori al Preventivo. */
	
	public void setValori(ModuloCt content){
		Veicolo = content.txtVeicolo.getText().trim();
		Inizio = content.frmtdtxtfldInizio.getText().trim();
		Fine = content.frmtdtxtfldFine.getText().trim();
	}
	
	/* Metodo per verificare la correttezza dei dati inseriti. */
	
	private boolean check(ModuloCt content){
		boolean check=true;
		if(Inizio.equals("") || Inizio.equals("aaaa-mm-gg") || Fine.equals("") || Fine.equals("aaaa-mm-gg")){
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, Inserisci tutti i Campi!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);
		}else if(Veicolo.length()<7 || Veicolo.length()>7 || !Veicolo.matches(TGPATTERN)){
			content.txtVeicolo.setText("");
			content.txtVeicolo.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, La Targa del veicolo deve essere composta da 4 caratteri e 3 cifre(Es: TO175RP)!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}else if (!Inizio.matches(DATEPATTERN) && !Inizio.equals("") && !Inizio.equals("aaaa-mm-gg")){
			content.frmtdtxtfldInizio.setText("");
			content.frmtdtxtfldInizio.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di inizio noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		} else if(!Fine.matches(DATEPATTERN) && !Fine.equals("") && !Fine.matches("aaaa-mm-gg")){
			content.frmtdtxtfldFine.setText("");
			content.frmtdtxtfldFine.requestFocus();
			check=false;
			JOptionPane.showMessageDialog(null, "Errore, la data di fine noleggio inserita non è valida!",
			    "Errore ",
			    JOptionPane.ERROR_MESSAGE);
		}
		if (check==false) test=check; 
			else {test=true;}
		return test;
	}
}
