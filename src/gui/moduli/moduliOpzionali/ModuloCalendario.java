package gui.moduli.moduliOpzionali;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * La classe ModuloCalendario genera il calendario presente nella "Home" del programma.
 */
public class ModuloCalendario extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
    static JLabel lblMese, lblAnno;
    static JButton btnPrec, btnSucc;
    static JTable tblCalendar;
    static JComboBox <String> cmbAnno;
    static DefaultTableModel mtblCalendar; 
    static JScrollPane stblCalendar;
    static int annoAttuale, meseAttuale, giornoAttuale, annoCorrente, meseCorrente;
    
    /**
     * Inizializza un nuovo oggetto ModuloCalendario.
     * 
     * @param pane il container di {@code pnlCalendar}.
     * @param pnlCalendar il pannello in cui viene visualizzato il calendario.
     */
    public ModuloCalendario(JPanel pane, JPanel pnlCalendar) {
 
        // Crea le etichette ed i controlli.
        lblMese = new JLabel ("Gennaio");
        lblAnno = new JLabel ("Cambia anno:");
        cmbAnno = new JComboBox <>();
        btnPrec = new JButton ("<<");
        btnSucc = new JButton (">>");
        mtblCalendar = new DefaultTableModel() {
        	
        	public boolean isCellEditable(int indiceRiga, int indiceColonna) {
        		
        		return false;
        	}
        	
        	private static final long serialVersionUID = 1L; 
        };
        
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        
        pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendario"));
        
        //Crea gli ActionListeners.
        btnPrec.addActionListener(new btnPrec_Action());
        btnSucc.addActionListener(new btnSucc_Action());
        cmbAnno.addActionListener(new cmbYear_Action());
        
        //Aggiunge le etichette ed i controlli.
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMese);
        pnlCalendar.add(lblAnno);
        pnlCalendar.add(cmbAnno);
        pnlCalendar.add(btnPrec);
        pnlCalendar.add(btnSucc);
        pnlCalendar.add(stblCalendar);
        
        pnlCalendar.setBounds(0, 0, 320, 180);
        lblMese.setBounds(100, 25, 100, 25);
        lblAnno.setBounds(22, 205, 80, 20);
        cmbAnno.setBounds(240, 205, 80, 20);
        btnPrec.setBounds(20, 25, 50, 25);
        btnSucc.setBounds(270, 25, 50, 25);
        stblCalendar.setBounds(20, 50, 300, 147);
        
        //Crea il calendario.
        GregorianCalendar cal = new GregorianCalendar(); 
        
        //Prende i valori attuali di giorno, mese e anno.
        giornoAttuale = cal.get(GregorianCalendar.DAY_OF_MONTH); 
        meseAttuale = cal.get(GregorianCalendar.MONTH); 
        annoAttuale = cal.get(GregorianCalendar.YEAR); 
        meseCorrente = meseAttuale; 
        annoCorrente = annoAttuale;
        
        //Agggiunge i giorni della settimana al calendario.
        String[] headers = {"Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab"}; 
        for (int i=0; i<7; i++){
            mtblCalendar.addColumn(headers[i]);
        }
        
     	//Crea lo sfondo del calendario.
        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); 
        
        //Impone che il calendario non è modificabile.
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);
        
        //Indica il numero di righe e di colonne.
        tblCalendar.setRowHeight(20);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);
        
        //Riempie la comboBox cmbAnno.
        for (int i=annoAttuale-10; i<=annoAttuale+10; i++) {
            cmbAnno.addItem(String.valueOf(i));
        }
        
        //Aggiorna il calendario.
        aggiornaCalendario (meseAttuale, annoAttuale); 
    }
    
    /**
     * Riempie il calendario con gli opportuni giorni.
     * 
     * @param mese il mese corrente
     * @param anno l'anno corrente
     */
    public static void aggiornaCalendario(int mese, int anno) {
    	
        String[] mesi =  {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        
        int numeroGiorni, inizioMese;
        
        btnPrec.setEnabled(true);
        btnSucc.setEnabled(true);
        
        if (mese == 0 && anno <= annoAttuale-10) {
        	btnPrec.setEnabled(false);
        } 
        if (mese == 11 && anno >= annoAttuale+10) {
        	btnSucc.setEnabled(false);
        } 
        
        lblMese.setText(mesi[mese]); 
        lblMese.setBounds(160-lblMese.getPreferredSize().width/2, 25, 180, 25); 
        cmbAnno.setSelectedItem(String.valueOf(anno)); 
        
        //Svuota la tabella.
        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                mtblCalendar.setValueAt(null, i, j);
            }
        }
        
        //Prende il primo giorno del mese e il numero di giorni.
        GregorianCalendar cal = new GregorianCalendar(anno, mese, 1);
        numeroGiorni = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        inizioMese = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Riempe la tabella.
        for (int i=1; i<=numeroGiorni; i++) {
            int row = new Integer((i+inizioMese-2)/7);
            int column = (i+inizioMese-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }
        
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }
    
    
    //Imposta i colori delle celle del calendario.
    static class tblCalendarRenderer extends DefaultTableCellRenderer {
    	
    	private static final long serialVersionUID = 1L;
    	
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        	
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6) {
            	//Sabato e domenica
                setBackground(new Color(255, 220, 220));
            } else {
            	//Gli altri giorni della settimana
                setBackground(new Color(255, 255, 255));
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == giornoAttuale && meseCorrente == meseAttuale && annoCorrente == annoAttuale) { 
                	//Il giorno corrente
                    setBackground(new Color(220, 220, 255));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    
    //Definisce le azioni da eseguire se viene cliccato il bottone btnPrec
    static class btnPrec_Action implements ActionListener {
        
    	public void actionPerformed (ActionEvent e) {
            
    		if (meseCorrente == 0) { 
    			//Indietro un anno
            	meseCorrente = 11;
                annoCorrente -= 1;
            } else { 
            	//Indietro un mese
                meseCorrente -= 1;
            }
            aggiornaCalendario(meseCorrente, annoCorrente);
        }
    }
    
  //Definisce le azioni da eseguire se viene cliccato il bottone btnSucc
    static class btnSucc_Action implements ActionListener {
    	
        public void actionPerformed (ActionEvent e) {
        	
            if (meseCorrente == 11) {
            	//Avanti un anno
                meseCorrente = 0;
                annoCorrente += 1;
            } else {
            	//Avanti un mese
                meseCorrente += 1;
            }
            aggiornaCalendario(meseCorrente, annoCorrente);
        }
    }
    
  //Definisce le azioni da eseguire se viene modificato il comboBox cmbAnno
    static class cmbYear_Action implements ActionListener {
    	
        public void actionPerformed (ActionEvent e) {
            
        	if (cmbAnno.getSelectedItem() != null) {
        		
                String b = cmbAnno.getSelectedItem().toString();
                annoCorrente = Integer.parseInt(b);
                aggiornaCalendario(meseCorrente, annoCorrente);
            }
        }
    }  
    
}