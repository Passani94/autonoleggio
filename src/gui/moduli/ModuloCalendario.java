package gui.moduli;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ModuloCalendario extends JPanel{
	
	private static final long serialVersionUID = 7526472295622776147L; 
    static JLabel lblMonth, lblYear;
    static JButton btnPrev, btnNext;
    static JTable tblCalendar;
    static JComboBox <String> cmbYear;
    static DefaultTableModel mtblCalendar; 
    static JScrollPane stblCalendar;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    
    public ModuloCalendario(JPanel pane,JPanel pnlCalendar){
 
        //Crea i controlli
        lblMonth = new JLabel ("January");
        lblYear = new JLabel ("Cambia anno:");
        cmbYear = new JComboBox <>();
        btnPrev = new JButton ("<<");
        btnNext = new JButton (">>");
        mtblCalendar = new DefaultTableModel()
        {
        	public boolean isCellEditable(int rowIndex, int mColIndex)
        	{return false;
        	}
        	private static final long serialVersionUID = 7526472295622776147L; 
        	};
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        
        pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendario"));
        
        //Crea gli ActionListeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());
        
        //Aggiunge i controlli
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(lblYear);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);
        pnlCalendar.add(stblCalendar);
        
        pnlCalendar.setBounds(0, 0, 320, 180);
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
        lblYear.setBounds(10, 205, 80, 20);
        cmbYear.setBounds(230, 205, 80, 20);
        btnPrev.setBounds(10, 25, 50, 25);
        btnNext.setBounds(260, 25, 50, 25);
        stblCalendar.setBounds(10, 50, 300, 147);
        
        //Prende i valori attuali di giorno/mese/anno
        GregorianCalendar cal = new GregorianCalendar(); //Crea il calendaio
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); 
        realMonth = cal.get(GregorianCalendar.MONTH); 
        realYear = cal.get(GregorianCalendar.YEAR); 
        currentMonth = realMonth; 
        currentYear = realYear;
        
        //Agggiunge la prima riga del calendario
        String[] headers = {"Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab"}; 
        for (int i=0; i<7; i++){
            mtblCalendar.addColumn(headers[i]);
        }
        
        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Crea lo sfondo a griglia del calendario
        
        //Impone che il calendario non è modificabile
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);
        
        //Permette di scegliere il giorno sul calendario
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Indica il numero di righe/colonne
        tblCalendar.setRowHeight(20);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);
        
        //Riempie la tabella
        for (int i=realYear-100; i<=realYear+100; i++){
            cmbYear.addItem(String.valueOf(i));
        }
        
        //Aggiorna il calendario 
        refreshCalendar (realMonth, realYear); 
    }
    
    public static void refreshCalendar(int month, int year){
        String[] months =  {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        int nod, som; //Numero di giorni e inizio del mese
        
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} 
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} 
        lblMonth.setText(months[month]); 
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); 
        cmbYear.setSelectedItem(String.valueOf(year)); 
        
        //Svuota la tabella
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }
        
        //Prende il primo giorno del mese e il numero di giorni
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Disegna il calendario
        for (int i=1; i<=nod; i++){
            int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }
        
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }
    
    //Colori celle
    static class tblCalendarRenderer extends DefaultTableCellRenderer{
    	
    	private static final long serialVersionUID = 7525572295622776147L; 
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6){ //Week-end
                setBackground(new Color(255, 220, 220));
            }
            else{ //Week
                setBackground(new Color(255, 255, 255));
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                    setBackground(new Color(220, 220, 255));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    
    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Indietro un anno
            	currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Indietro un mese
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Avanti un anno
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Avanti un mese
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }
}