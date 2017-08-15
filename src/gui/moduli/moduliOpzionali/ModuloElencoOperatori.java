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
 * La classe ModuloElencoOperatori genera l'elenco degli operatori contenuti nel database.
 */	
public class ModuloElencoOperatori extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tblOperatori;
	private JScrollPane scroll = new JScrollPane(tblOperatori);
	private DBConnect operatori = new DBConnect();
	
	/**
	 * Inizializza un nuovo oggetto ModuloElencoOperatori e richiama il metodo {@code set}.
	 */
	public ModuloElencoOperatori() {
		set();
	}
	
	/**
	 * Costruisce l'elenco degli operatori contenuti nel database.
	 */
	public void set() {
		
		this.setBorder(BorderFactory.createTitledBorder("Elenco Operatori"));
		
		try {
			operatori.exequery("SELECT * FROM operatore","select");
		} catch (SQLException e) {  
		JOptionPane.showMessageDialog(null, "Errore, impossibile generare l'elenco degli operatori!",
				"Errore ",
				JOptionPane.ERROR_MESSAGE);}
		
		tblOperatori = new JTable();
		tblOperatori.setModel(new CostruisciTabella(operatori.rs).model);
		TableColumnAdjuster tca = new TableColumnAdjuster(tblOperatori);
		tca.adjustColumns();		
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(tblOperatori);
		
		/* Crea il layout per l'elenco degli operatori. */
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
		return "ModuloElencoOperatori [La classe ModuloElencoOperatori genera l'elenco degli operatori contenuti nel database.]";
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
		ModuloElencoOperatori other = (ModuloElencoOperatori) obj;
		if (operatori == null) {
			if (other.operatori != null)
				return false;
		} else if (!operatori.equals(other.operatori))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblOperatori == null) {
			if (other.tblOperatori != null)
				return false;
		} else if (!tblOperatori.equals(other.tblOperatori))
			return false;
		return true;
	}
	
	

}
