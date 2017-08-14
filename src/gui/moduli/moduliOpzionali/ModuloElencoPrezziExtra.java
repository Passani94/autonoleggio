package gui.moduli.moduliOpzionali;

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;

import db.DBConnect;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloElencoPrezziExtra genera il tarifarrio per il kilometraggio extra.
 */
public class ModuloElencoPrezziExtra extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable tblPrezzi;
	private JScrollPane scroll = new JScrollPane(tblPrezzi);
	private DBConnect prezzi = new DBConnect();
	
	/**
	 * Inizializza un nuovo oggetto ModuloElencoPrezziExtra e richiama il metodo {@code set}.
	 */
	public ModuloElencoPrezziExtra() {
		set();
	}
	
	/**
	 * Costruisce il tarifarrio per il kilometraggio extra.
	 */
	public void set() {
		
		this.setBorder(BorderFactory.createTitledBorder("Tariffario Km Extra"));
		
		try {
			prezzi.exequery("SELECT v.Targa, v.Nome, v.Km_Effettuati, b.Km_al_Giorno, b.Km_Extra "
					+ "FROM veicolo v, breve_termine b WHERE v.Costobt = b.Cod_BT","select");
		} catch (SQLException e) {  
		JOptionPane.showMessageDialog(null, "Errore, impossibile generare il tarifarrio per il kilometraggio extra!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);}
		
		tblPrezzi = new JTable();
		tblPrezzi.setModel(new CostruisciTabella(prezzi.rs).model);
		tblPrezzi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(tblPrezzi);
		tca.adjustColumns();		
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(tblPrezzi);
		
		/* Crea il layout per il tarifarrio per il kilometraggio extra. */
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addContainerGap())
			);
		this.setLayout(gl_contentPane);
	}


/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "ModuloPrezziExtra [La classe ModuloElencoPrezziExtra genera il tarifarrio per il kilometraggio extra.]";
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
		ModuloElencoPrezziExtra other = (ModuloElencoPrezziExtra) obj;
		if (prezzi == null) {
			if (other.prezzi != null)
				return false;
		} else if (!prezzi.equals(other.prezzi))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblPrezzi == null) {
			if (other.tblPrezzi != null)
				return false;
		} else if (!tblPrezzi.equals(other.tblPrezzi))
			return false;
		return true;
	}
	
	
	
}
