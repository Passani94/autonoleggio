package gui.moduli;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import db.DBConnect;
import entita.Operatore;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloOperatore si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.
 */
public class ModuloOperatore extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L; 
	
	/**
	 * La casella di testo in cui inserire la password dell'operatore.
	 */
	public JTextField txtPassword;
	
	/**
	 * La casella di testo in cui inserire l'username dell'operatore.
	 */
	public JTextField txtUsername;
	
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JTable tblOperatori;
	private JScrollPane scroll = new JScrollPane(tblOperatori);
	
	private Operatore operatore;
	private DBConnect elencoOperatori;
	
	/**
	 * Inizializza un nuovo oggetto ModuloOperatore e richiama il metodo {@code set} passando come argomento l'oggetto String {@code str}.
	 * 
	 * @param str una stringa che determina il diverso comportamento del metodo {@code set}.
	 */
	public ModuloOperatore(String str){
		set(str);
	}

	/**
	 * Si comporta in maniera differente a seconda dell'oggetto String che viene passato come argomento. <br><br>
	 * 
	 * - Se viene passato "Principale", <br>
	 * - Se viene passato "Opzionale", <br>
	 * - Se viene passato "Nuovo", viene creato il form per aggiungere un nuovo operatore. <br>
	 * - Se viene passato "Elimina", viene creato il form per eliminare un operatore. <br>
	 * - Se viene passato "Elenca", viene generato l'elenco degli operatori contenuti nel database.
	 * 
	 * @param str una stringa che determina cosa verrà mostrato a schermo.
	 */
	public void set(String str) {
		
		if (str.equals("Principale")) {
			
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Pannello Principale"));
			
			JLabel lblFunz = new JLabel("Pannello Principale");
			lblFunz.setHorizontalAlignment(SwingConstants.CENTER);
			lblFunz.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout iniziale del "Pannello Principale". */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap(18, Short.MAX_VALUE)
							.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE,420, GroupLayout.PREFERRED_SIZE)
							.addGap(170))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(120)
							.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(255, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
			
		} else if (str.equals("Opzionale")) {
			
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Pannello Opzionale"));
			
			JLabel lblFunz = new JLabel("Pannello Opzionale");
			lblFunz.setHorizontalAlignment(SwingConstants.CENTER);
			lblFunz.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout iniziale del "Pannello Opzionale". */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap(18, Short.MAX_VALUE)
							.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE,420, GroupLayout.PREFERRED_SIZE)
							.addGap(170))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(120)
							.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(255, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
			
		} else if (str == "Nuovo") {
			
			/* Viene creato il form per aggiungere un operatore. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Operatore"));
			
			btnAggiungi = new JButton("Aggiungi Operatore");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this); // Action Listener per il bottone Accedi.
			
			txtPassword = new JPasswordField();
			
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Arial", Font.BOLD, 14));

			GroupLayout gl_contentPane = new GroupLayout(this);
			
			/* Crea il layout del form per aggiungere un nuovo operatore. */
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtUsername, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(160, Short.MAX_VALUE)
							.addComponent(btnAggiungi)
							.addGap(177))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(65)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(133, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str == "Elimina") {
			
			/* Viene creato il form per eliminare un operatore. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Operatore"));
			
			btnElimina = new JButton("Elimina Operatore");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	// Action Listener per il bottone Elimina.
			
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout del form per eleminare un operatore. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(14))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(160, Short.MAX_VALUE)
							.addComponent(btnElimina)
							.addGap(177))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(50)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(167, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
			
		} else if (str == "Elenca") { 
			
			/* Viene generato l'elenco degli operatori contenuti nel database. */
			elencoOperatori = new DBConnect();
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Operatori"));
			
			try {
				elencoOperatori.exequery("SELECT * FROM operatore","select");
				
				tblOperatori = new JTable();
				tblOperatori.setModel(new CostruisciTabella(elencoOperatori.rs).model);
				TableColumnAdjuster tca = new TableColumnAdjuster(tblOperatori);
				tca.adjustColumns();
				
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setViewportView(tblOperatori);
				
				elencoOperatori.con.close();
			} catch (SQLException e) {  
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco degli operatori!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);}
			
			/* Crea il layout per l'elenco degli operatori. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(360, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addGap(10))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		}
	}
	
	/**
	 * Definisce le azioni da eseguire a seconda del bottone cliccato.
	 */
	public void actionPerformed(ActionEvent e){
		if (btnAggiungi == e.getSource()){
			operatore = new Operatore();
			operatore.setValori(this);
			operatore.aggiungi(this);
		}
		else if(btnElimina == e.getSource()){
			operatore = new Operatore();
			operatore.setUsername(this);
			operatore.elimina(this);
		}
	}

/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "ModuloOperatore [La classe ModuloOperatore si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.]";
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
		ModuloOperatore other = (ModuloOperatore) obj;
		if (btnAggiungi == null) {
			if (other.btnAggiungi != null)
				return false;
		} else if (!btnAggiungi.equals(other.btnAggiungi))
			return false;
		if (btnElimina == null) {
			if (other.btnElimina != null)
				return false;
		} else if (!btnElimina.equals(other.btnElimina))
			return false;
		if (elencoOperatori == null) {
			if (other.elencoOperatori != null)
				return false;
		} else if (!elencoOperatori.equals(other.elencoOperatori))
			return false;
		if (operatore == null) {
			if (other.operatore != null)
				return false;
		} else if (!operatore.equals(other.operatore))
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
		if (txtPassword == null) {
			if (other.txtPassword != null)
				return false;
		} else if (!txtPassword.equals(other.txtPassword))
			return false;
		if (txtUsername == null) {
			if (other.txtUsername != null)
				return false;
		} else if (!txtUsername.equals(other.txtUsername))
			return false;
		return true;
	}
	
	
}
