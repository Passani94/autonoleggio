package gui.moduli;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import db.DBConnect;
import entita.Cliente;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloCliente si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.
 */
public class ModuloCliente extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L; 
	
	private Cliente cliente;
	private DBConnect elencoClienti;
	
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JButton btnModificaC;
	private JButton btnCerca;
	private JScrollPane scroll;
	private JTable tblClienti;
	
	/**
	 * La casella di testo in cui inserire la ragione sociale del cliente.
	 */
	public JTextField txtRS;
	
	/**
	 * La casella di testo in cui inserire il Codice di Avviamento Postale (CAP) della città in cui risiede il cliente.
	 */
	public JTextField txtCAP;
	
	/**
	 * La casella di testo in cui inserire la città in cui risiede il cliente.
	 */
	public JTextField txtCitta;
	
	/**
	 * La casella di testo in cui inserire la via associata alla residenza del cliente.
	 */
	public JTextField txtVia;
	
	/**
	 * La casella di testo in cui inserire il numero civico associato alla residenza del cliente.
	 */
	public JTextField txtNumero;
	
	/**
	 * La casella di testo in cui inserire il Codice Fiscale (o la Partita IVA) del cliente.
	 */
	public JTextField txtCF_PIVA;
	
	/**
	 * La casella di testo in cui inserire l'email del cliente.
	 */
	public JTextField txtEmail;
	
	/**
	 * La casella di testo in cui inserire il recapito telefonico del cliente.
	 */
	public JTextField txtTelefono;
	
	/**
	 * La casella di testo in cui inserire il Codice Fiscale (o la Partita IVA) del cliente da cercare.
	 */
	public JTextField txtClienteCerca;
	
	/**
	 * Il combo box dal quale selezionare la tipologia di cliente.
	 */
	public JComboBox <String> comboBoxTipologia;
	
	
	/**
	 * Inizializza un nuovo oggetto ModuloCliente e richiama il metodo {@code set} passando come argomento l'oggetto String {@code str}.
	 * 
	 * @param str una stringa che determina il diverso comportamento del metodo {@code set}.
	 */
	public ModuloCliente(String str) {
		set(str);
	}

	/**
	 * Si comporta in maniera differente a seconda dell'oggetto String che viene passato come argomento. <br><br>
	 * - Se viene passato "Elenca", viene generato l'elenco dei clienti contenuti nel database. <br>
	 * - Se viene passato "Nuovo", viene creato il form per aggiungere un cliente. <br>
	 * - Se viene passato "Modifica", viene creato il form per modificare un cliente. <br>
	 * - Se viene passato "Elimina", viene creato il form per eliminare un cliente.
	 * 
	 * @param str una stringa che determina cosa verrà mostrato a schermo.
	 */
	public void set(String str) {
		
		if (str.equals("Principale")) {
			
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Pannello Principale"));
			
			JLabel lblFunz = new JLabel("Contenuto Principale");
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
			
			JLabel lblFunz = new JLabel("Contenuto Opzionale");
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
			
		} else if (str.equals("Nuovo")) {
			
			/* Viene creato il form per aggiungere un nuovo cliente. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Cliente"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);	// Action Listener per il bottone Aggiungi.
			
			txtCF_PIVA = new JTextField();
			txtCF_PIVA.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblid = new JLabel("CF o PIVA *");
			lblid.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setToolTipText("Seleziona la tipologia di cliente.\r\n");
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Associazione", "Azienda", "Privato"}));
			comboBoxTipologia.setBackground(Color.WHITE);
		
			txtRS = new JTextField();
			txtRS.setFont(new Font("Arial", Font.PLAIN, 12));
			txtRS.setToolTipText("Denominazione / Cognome Nome");
		
			JLabel lblRS = new JLabel("Ragione Sociale *");
			lblRS.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblCAP = new JLabel("CAP");
			lblCAP.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCAP = new JTextField();
			txtCAP.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblcitta = new JLabel("Città");
			lblcitta.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCitta = new JTextField();
			txtCitta.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblVia = new JLabel("Via");
			lblVia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtVia = new JTextField();
			txtVia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNumero = new JLabel("N. Civico");
			lblNumero.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNumero = new JTextField();
			txtNumero.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
						
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			/* Crea il layout del form usato per aggiungere un nuovo cliente. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(160)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
							.addGap(260))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(249, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtTelefono)
								.addComponent(comboBoxTipologia, Alignment.LEADING)
								.addComponent(txtCF_PIVA, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
								.addComponent(txtRS, Alignment.LEADING)
								.addComponent(txtCAP)
								.addComponent(txtCitta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(txtVia, Alignment.LEADING)
								.addComponent(txtNumero, Alignment.LEADING)
								.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
							.addGap(40))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(41))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Modifica")) {
			
			/* Viene creato il form per modificare un cliente. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Modifica Cliente"));
			
			btnCerca = new JButton("Cerca Cliente");
			btnCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCerca.addActionListener(this);	// Action Listener per il bottone Cerca.
			
			txtClienteCerca = new JTextField();
			txtClienteCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblClienteCerca = new JLabel("CF o PIVA Cliente da Modificare ");
			lblClienteCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModificaC = new JButton("Modifica Cliente");
			btnModificaC.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModificaC.addActionListener(this);	// Action Listener per il bottone Modifica.
			
			txtCF_PIVA = new JTextField();
			txtCF_PIVA.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblid = new JLabel("CF o PIVA*");
			lblid.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia*");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Associazione", "Azienda", "Privato"}));
			comboBoxTipologia.setBackground(Color.WHITE);
		
			txtRS = new JTextField();
			txtRS.setFont(new Font("Arial", Font.PLAIN, 12));
			txtRS.setToolTipText("Cognome Nome / Denominazione");
		
			JLabel lblRS = new JLabel("Ragione Sociale*");
			lblRS.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblCAP = new JLabel("CAP");
			lblCAP.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCAP = new JTextField();
			txtCAP.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblcitta = new JLabel("Città");
			lblcitta.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCitta = new JTextField();
			txtCitta.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblVia = new JLabel("Via");
			lblVia.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtVia = new JTextField();
			txtVia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNumero = new JLabel("N. Civico");
			lblNumero.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNumero = new JTextField();
			txtNumero.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Arial", Font.PLAIN, 12));
			
			comboBoxTipologia.setEditable(false);
			txtRS.setEditable(false);
			txtCAP.setEditable(false);
			txtCitta.setEditable(false);
			txtVia.setEditable(false);
			txtNumero.setEditable(false);
			txtCF_PIVA.setEditable(false);
			txtEmail.setEditable(false);
			txtTelefono.setEditable(false);
			
			/* Crea il layout del form usato per modificare un cliente. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(0, 0, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblClienteCerca)
										.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtClienteCerca, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
									.addGap(12))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(135)
									.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
									.addGap(145))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(135)
									.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
									.addGap(145))))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblClienteCerca)
								.addComponent(txtClienteCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRS, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCAP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCAP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblcitta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCitta, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Elimina")) {
			
			/* Viene creato il form per eliminare un cliente. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Cliente"));
			
			btnElimina = new JButton("Elimina Cliente");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	// Action Listener per il bottone Elimina.
			
			txtCF_PIVA = new JTextField();
			txtCF_PIVA.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCF_PIVA.setColumns(10);
			
			
			JLabel lblid = new JLabel("CF o P.IVA *");
			lblid.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout del form usato per un eliminare un cliente. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(lblid, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
							.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(62))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(179)
							.addComponent(btnElimina)
							.addContainerGap(194, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCF_PIVA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(50)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(169, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Elenca")) {
			
			/* Viene generato l'elenco dei clienti contenuti nel database. */
			elencoClienti = new DBConnect();
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Clienti"));
			
			try {
				elencoClienti.exequery("SELECT * FROM cliente","select");
				
				tblClienti = new JTable();
				tblClienti.setModel(new CostruisciTabella(elencoClienti.rs).model);
				tblClienti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumnAdjuster tca = new TableColumnAdjuster(tblClienti);
				tca.adjustColumns();
				
				scroll = new JScrollPane(tblClienti);
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setViewportView(tblClienti);
				
				elencoClienti.con.close();
			} catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei clienti!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
			}
			
			
			/* Crea il layout per l'elenco dei clienti. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.CENTER)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(360, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addGap(50))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/**
	 * Definisce le azioni da eseguire a seconda del bottone cliccato.
	 */
	public void actionPerformed(ActionEvent e) {
		if (btnAggiungi == e.getSource()) {
			cliente = new Cliente();
			cliente.setValori(this);
			cliente.aggiungi(this);
		
		} else if(btnElimina == e.getSource()) {
			cliente = new Cliente();
			cliente.setIDElimina(this);
			cliente.elimina(this);
		
		} else if(btnCerca == e.getSource()) {
			cliente = new Cliente();
			cliente.setIDCerca(this);
			cliente.cerca(this);
		
		} else if(btnModificaC == e.getSource()) {
			cliente = new Cliente();
			cliente.setValori(this);
			cliente.modifica(this);
		}
	}

	
/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "ModuloCliente [La classe ModuloCliente si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.]";
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
		ModuloCliente other = (ModuloCliente) obj;
		if (btnAggiungi == null) {
			if (other.btnAggiungi != null)
				return false;
		} else if (!btnAggiungi.equals(other.btnAggiungi))
			return false;
		if (btnCerca == null) {
			if (other.btnCerca != null)
				return false;
		} else if (!btnCerca.equals(other.btnCerca))
			return false;
		if (btnElimina == null) {
			if (other.btnElimina != null)
				return false;
		} else if (!btnElimina.equals(other.btnElimina))
			return false;
		if (btnModificaC == null) {
			if (other.btnModificaC != null)
				return false;
		} else if (!btnModificaC.equals(other.btnModificaC))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (comboBoxTipologia == null) {
			if (other.comboBoxTipologia != null)
				return false;
		} else if (!comboBoxTipologia.equals(other.comboBoxTipologia))
			return false;
		if (elencoClienti == null) {
			if (other.elencoClienti != null)
				return false;
		} else if (!elencoClienti.equals(other.elencoClienti))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblClienti == null) {
			if (other.tblClienti != null)
				return false;
		} else if (!tblClienti.equals(other.tblClienti))
			return false;
		if (txtCAP == null) {
			if (other.txtCAP != null)
				return false;
		} else if (!txtCAP.equals(other.txtCAP))
			return false;
		if (txtCF_PIVA == null) {
			if (other.txtCF_PIVA != null)
				return false;
		} else if (!txtCF_PIVA.equals(other.txtCF_PIVA))
			return false;
		if (txtCitta == null) {
			if (other.txtCitta != null)
				return false;
		} else if (!txtCitta.equals(other.txtCitta))
			return false;
		if (txtClienteCerca == null) {
			if (other.txtClienteCerca != null)
				return false;
		} else if (!txtClienteCerca.equals(other.txtClienteCerca))
			return false;
		if (txtEmail == null) {
			if (other.txtEmail != null)
				return false;
		} else if (!txtEmail.equals(other.txtEmail))
			return false;
		if (txtNumero == null) {
			if (other.txtNumero != null)
				return false;
		} else if (!txtNumero.equals(other.txtNumero))
			return false;
		if (txtRS == null) {
			if (other.txtRS != null)
				return false;
		} else if (!txtRS.equals(other.txtRS))
			return false;
		if (txtTelefono == null) {
			if (other.txtTelefono != null)
				return false;
		} else if (!txtTelefono.equals(other.txtTelefono))
			return false;
		if (txtVia == null) {
			if (other.txtVia != null)
				return false;
		} else if (!txtVia.equals(other.txtVia))
			return false;
		return true;
	}
	
	
}