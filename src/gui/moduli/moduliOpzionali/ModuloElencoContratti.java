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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import db.DBConnect;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;


/**
 * La classe ModuloElencoContratti genera l'elenco dei contratti contenuti nel database.
 */
public class ModuloElencoContratti extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
		private JTable tblContratti;
		private JScrollPane scroll = new JScrollPane(tblContratti);
		private DBConnect contratti = new DBConnect();
		
		/**
		 * Inizializza un nuovo oggetto ModuloElencoContratti e richiama il metodo {@code set}.
		 */
		public ModuloElencoContratti() {
			set();
		}
		
		/**
		 * Costruisce l'elenco dei contratti contenuti nel database.
		 */
		public void set() {
			
			this.setBorder(BorderFactory.createTitledBorder("Elenco Contratti"));
			
			try {
				contratti.exequery("SELECT Cod_Noleggio, Tipologia, Veicolo, Cliente, Data_Inizio, Data_Fine FROM noleggio","select");
				
				tblContratti = new JTable();
				tblContratti.setModel(new CostruisciTabella(contratti.rs).model);
				tblContratti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumnAdjuster tca = new TableColumnAdjuster(tblContratti);
				tca.adjustColumns();		
				
				JTableHeader header= tblContratti.getTableHeader();
				TableColumnModel colMod = header.getColumnModel();
				TableColumn tabCol = colMod.getColumn(0);
				tabCol.setHeaderValue("Codice");
				
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setViewportView(tblContratti);
				
				contratti.con.close();
			} catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore, impossibile generare l'elenco dei contratti!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
			
			
			
			/* Crea il layout per l'elenco dei contratti contenuti nel database. */
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
			return "ModuloElencoContratti [La classe ModuloElencoContratti genera l'elenco dei contratti contenuti nel database.]";
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
			ModuloElencoContratti other = (ModuloElencoContratti) obj;
			if (contratti == null) {
				if (other.contratti != null)
					return false;
			} else if (!contratti.equals(other.contratti))
				return false;
			if (scroll == null) {
				if (other.scroll != null)
					return false;
			} else if (!scroll.equals(other.scroll))
				return false;
			if (tblContratti == null) {
				if (other.tblContratti != null)
					return false;
			} else if (!tblContratti.equals(other.tblContratti))
				return false;
			return true;
		}
		
		
		
}
