package gui.moduli;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.LookAndFeel;

import com.toedter.calendar.JDateChooser;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import db.DBConnect;
import entita.Contratto;
import entita.Preventivo;
import utils.ArrotondaNumero;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloContratto si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.
 */
public class ModuloContratto extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/**
	 * L'etichetta in cui viene stampato il costo totale del preventivo.
	 */
	public JLabel lblPreventivo;
	
	/**
	 * La tabella contenente l'elenco dei contratti.
	 */
	public JTable tblNoleggi;
	
	/**
	 * La casella di testo in cui inserire la targa del veicolo che si intende noleggiare.
	 */
	public JTextField txtVeicolo;
	
	/**
	 * La casella di testo in cui inserire il Codice Fiscale (o la Partita IVA) del cliente.
	 */
	public JTextField txtCliente;	
	
	/**
	 * La casella di testo in cui inserire il costo totale del noleggio.
	 */
	public JTextField txtCosto;
	
	/**
	 * La casella di testo in cui inserire l'acconto lasciato dal cliente.
	 */
	public JTextField txtAcconto;
	
	/**
	 * La casella di testo in cui inserire il cognome riportato sulla patente del conducente.
	 */
	public JTextField txtCognome;
	
	/**
	 * La casella di testo in cui insreire il nome riportato sulla patente del conducente.
	 */
	public JTextField txtNome;
	
	/**
	 * La casella di testo in cui inserire il numero di patente.
	 */
	public JTextField txtPatente;
	
	/**
	 * La casella di testo in cui inserire il codice del contratto da cercare.
	 */
	public JTextField txtContrattoCerca;
	
	/**
	 * La casella di testo in cui inserire l'ente che ha rilasciato la patente.
	 */
	public JTextField txtRilasciatada;
	
	/**
	 * La casella di testo in cui inserire il codice del contratto da eliminare.
	 */
	public JTextField txtCodiceElimina;
	
	/**
	 * Il combo box dal quale selezionare la tipologia di noleggio.
	 */
	public JComboBox <String> comboBoxTipologia;
	
	/**
	 * La casella di testo in cui inserire la data di inizio noleggio.
	 */
	public JFormattedTextField frmtdtxtfldInizio;
	
	/**
	 * La casella di testo in cui inserire la data di fine noleggio.
	 */
	public JFormattedTextField frmtdtxtfldFine;
	
	/**
	 * La casella di testo in cui inserire la data di fine validità della patente.
	 */
	public JFormattedTextField frmtdtxtfldValida;
	
	/**
	 * La casella di testo in cui inserire la data in cui è stata rilasciata la patente.
	 */
	public JFormattedTextField frmtdtxtfldRilasciatail;
	
	/**
	 *Il combo box dal quale selezionare la tipologia di cliente.
	 */
	public JComboBox <String> comboBoxTipologiaCliente;

	private JButton btnAggiungi;
	private JButton btnCalcola;
	private JButton btnFiltra;
	private JButton btnPassaAContratto;
	private JButton btnElimina;
	private JButton btnCerca;
	private JButton btnModificaC;
	private JLabel lblTipologiaCliente;	
	private JScrollPane scroll = new JScrollPane(tblNoleggi);
	
	private Contratto contratto;
	private Preventivo preventivo;
	private DBConnect elencoContratti;

	
	/**
	 * Inizializza un nuovo oggetto ModuloContratto e richiama il metodo {@code set} passando come argomento l'oggetto String {@code str}.
	 * 
	 * @param str una stringa che determina il diverso comportamento del metodo {@code set}.
	 */
	public ModuloContratto(String str){
		set(str);
	}

	/**
	 * Si comporta in maniera differente a seconda dell'oggetto String che viene passato come argomento. <br><br>
	 * 
	* - Se viene passato "Principale", viene creato un modulo vuoto recante la scritta "Modulo Principale".<br>
	 * - Se viene passato "Opzionale", viene creato un modulo vuoto recante la scritta "Modulo Opzionale".<br>
	 * - Se viene passato "Preventivo", viene creato il form per calcolare un preventivo. <br>
	 * - Se viene passato "Passaggio", viene creato il form per aggiungere un contratto, precaricato con i dati del preventivo. <br>
	 * - Se viene passato "Nuovo", viene creato il form per aggiungere un nuovo contratto. <br>
	 * - Se viene passato "Modifica", viene creato il form per modificare un contratto. <br>
	 * - Se viene passato "Elimina", viene creato il form per eliminare un contratto. <br>
	 * - Se viene passato "Elenca", viene creato l'elenco dei contratti contenuti nel database.
	 * 
	 * @param str una stringa che determina cosa verrà mostrato a schermo.
	 */
	public void set(String str){
		
		if (str.equals("Principale")) {
			
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Modulo Principale"));
			
			JLabel lblFunz = new JLabel("Modulo Principale");
			lblFunz.setHorizontalAlignment(SwingConstants.CENTER);
			lblFunz.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout iniziale del "Modulo Principale". */
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
			
			JLabel lblFunz = new JLabel("Modulo Opzionale");
			lblFunz.setHorizontalAlignment(SwingConstants.CENTER);
			lblFunz.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout iniziale del "Modulo Opzionale". */
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
							.addGap(60)
							.addComponent(lblFunz, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(255, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
			
		} else if (str.equals("Preventivo")) {
			
			/* Viene creato il form per calcolare un preventivo. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Calcola Preventivo Contratto di Noleggio"));
		
			JLabel lblVeicolo = new JLabel("Veicolo da Noleggiare");
			lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			txtVeicolo.setColumns(10);
		
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
			JLabel lblInizio = new JLabel("Data Inizio Noleggio");
			lblInizio.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldInizio = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio.setText("Seleziona una data");
			frmtdtxtfldInizio.setEditable(false);
			
			JDateChooser dateChooserInizio=null;
			LookAndFeel previous=UIManager.getLookAndFeel();
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserInizio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserInizio.addPropertyChangeListener("date", new PropertyChangeListener () {
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserInizio=(JDateChooser) e.getSource();
							SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
							frmtdtxtfldInizio.setText(sdf.format(dateChooserInizio.getDate()));				
					}
				});
				
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}	
			
			JLabel lblFine = new JLabel("Data Fine Noleggio");
			lblFine.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("Seleziona una data");
			frmtdtxtfldFine.setEditable(false);
			
			JDateChooser dateChooserFine=null;
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserFine= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserFine.addPropertyChangeListener("date", new PropertyChangeListener () {
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserFine=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldFine.setText(sdf.format(dateChooserFine.getDate()));				
					}
				});	
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}	
		
			lblPreventivo = new JLabel("");
			lblPreventivo.setFont(new Font("Arial", Font.BOLD, 14));
			lblPreventivo.setHorizontalAlignment(SwingConstants.CENTER);
		
			btnCalcola = new JButton("Calcola Preventivo");
			btnCalcola.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCalcola.addActionListener(this);
		
			btnPassaAContratto = new JButton("Passa a Contratto");
			btnPassaAContratto.setFont(new Font("Arial", Font.PLAIN, 12));
			btnPassaAContratto.addActionListener(this);			

			comboBoxTipologiaCliente = new JComboBox <>();
			comboBoxTipologiaCliente.setToolTipText("Seleziona la tipologia di cliente.\r\n");
			comboBoxTipologiaCliente.setModel(new DefaultComboBoxModel <>(new String[] {"", "Associazione", "Azienda", "Privato"}));
			comboBoxTipologiaCliente.setBackground(Color.WHITE);
			
			lblTipologiaCliente = new JLabel("Tipologia Cliente");
			lblTipologiaCliente.setFont(new Font("Arial", Font.BOLD, 14));
		
			/* Crea il layout del form per il calcolo del preventivo. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblInizio, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipologiaCliente))
									.addGap(76)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(frmtdtxtfldFine)
										.addComponent(frmtdtxtfldInizio)
										.addComponent(txtVeicolo, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
										.addComponent(comboBoxTipologiaCliente, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
										.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(62)
									.addComponent(btnCalcola)
									.addGap(47)
									.addComponent(btnPassaAContratto))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(91)
									.addComponent(lblPreventivo, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(178, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxTipologiaCliente, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipologiaCliente))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVeicolo))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(frmtdtxtfldInizio, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
										.addComponent(lblInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(12))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldFine, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
									.addGap(54)
									.addComponent(lblPreventivo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(49)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnCalcola, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
										.addComponent(btnPassaAContratto, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
									.addGap(50))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
				);
				this.setLayout(gl_contentPane);
				this.revalidate();
		
		 } else if (str.equals("Passaggio")) {
			
			/* Viene creato il form per aggiungere un contratto, precaricato con i dati del preventivo. */			
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Contratto di Noleggio"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setBackground(Color.WHITE);
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Breve", "Lungo"}));
			comboBoxTipologia.setToolTipText("(Breve/Lungo)");
			
			if( Preventivo.getGiorniNoleggio() <=181 ) {
				comboBoxTipologia.setSelectedIndex(1);
			} else {
				comboBoxTipologia.setSelectedIndex(2);
			}
			
			JLabel lblVeicolo = new JLabel("Veicolo *");
			lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			String veicoloContratto = Preventivo.getVeicolo();
			txtVeicolo.setText(veicoloContratto);
			txtVeicolo.setEditable(false);
			
			JLabel lblCliente = new JLabel("Cliente *");
			lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCliente = new JTextField();
			txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			JLabel lblDataInizio = new JLabel("Data Inizio *");
			lblDataInizio.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldInizio = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio.setText(Preventivo.getDataInizio());
			frmtdtxtfldInizio.setEditable(false);
			
			LookAndFeel previous=UIManager.getLookAndFeel();
			JDateChooser dateChooserInizio=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserInizio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserInizio.addPropertyChangeListener("date", new PropertyChangeListener () {
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserInizio=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldInizio.setText(sdf.format(dateChooserInizio.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}
			
			JLabel lblDataFine = new JLabel("Data Fine *");
			lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText(Preventivo.getDataFine());
			frmtdtxtfldFine.setEditable(false);
			
			JDateChooser dateChooserFine=null;
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserFine= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserFine.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserFine=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldFine.setText(sdf.format(dateChooserFine.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}
			
			JLabel lblCosto = new JLabel("Costo Totale *");
			lblCosto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCosto = new JTextField();
			txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
			double prezzoContratto = (Preventivo.getTotale());
			prezzoContratto = ArrotondaNumero.arrotonda(prezzoContratto, 2);
			txtCosto.setText(String.valueOf(prezzoContratto));
			txtCosto.setEditable(false);
		
			JLabel lblAcconto = new JLabel("Acconto");
			lblAcconto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAcconto = new JTextField();
			txtAcconto.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblCognome = new JLabel("Cognome *");
			lblCognome.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtCognome = new JTextField();
			txtCognome.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblPatente = new JLabel("Numero Patente *");
			lblPatente.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtPatente = new JTextField();
			txtPatente.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblValida = new JLabel("Valida fino a *");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldValida = new JFormattedTextField(dateformat);
			frmtdtxtfldValida.setText("Seleziona una data");
			frmtdtxtfldValida.setEditable(false);
			
			JDateChooser dateChooserValida=null;
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserValida= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserValida.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserValida=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldValida.setText(sdf.format(dateChooserValida.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}	
			
			JLabel lblRilasciataDa = new JLabel("Rilasciata da");
			lblRilasciataDa.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblRilasciataIl = new JLabel("Rilasciata il *");
			lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldRilasciatail = new JFormattedTextField(dateformat);
			frmtdtxtfldRilasciatail.setText("Seleziona una data");
			frmtdtxtfldRilasciatail.setEditable(false);
			
			JDateChooser dateChooserRilasciataIl=null;
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserRilasciataIl= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserRilasciataIl.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserRilasciataIl=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldRilasciatail.setText(sdf.format(dateChooserRilasciataIl.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}			
			
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			/* Crea il layout del form per aggiungere un nuovo contratto. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblMex, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(190)
											.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
											.addGap(15)))
									.addGap(178))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
												.addGap(114)))
										.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(frmtdtxtfldFine, Alignment.LEADING)
												.addComponent(frmtdtxtfldInizio, Alignment.LEADING)
												.addComponent(txtVeicolo, Alignment.LEADING)
												.addComponent(comboBoxTipologia, Alignment.LEADING, 0, 165, Short.MAX_VALUE)
												.addComponent(txtCliente, Alignment.LEADING)
												.addComponent(txtCosto, Alignment.LEADING)
												.addComponent(txtAcconto, Alignment.LEADING)
												.addComponent(txtCognome, Alignment.LEADING)
												.addComponent(txtPatente, Alignment.LEADING)
												.addComponent(txtNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
												.addComponent(frmtdtxtfldValida, Alignment.LEADING)
												.addComponent(txtRilasciatada, Alignment.LEADING))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(dateChooserValida, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
												.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
												.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(frmtdtxtfldRilasciatail, 163, 163, 163)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(dateChooserRilasciataIl, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooserInizio, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldInizio, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldFine, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
								.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtAcconto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
								.addComponent(dateChooserValida, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
									.addGap(23)
									.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addComponent(dateChooserRilasciataIl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
	
		 } else if (str.equals("Nuovo")) {
			
			/* Viene creato il form per aggiungere un contratto. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Contratto di Noleggio"));
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
				
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setBackground(Color.WHITE);
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Breve", "Lungo"}));
			comboBoxTipologia.setToolTipText("(Breve/Lungo)");
						
			JLabel lblVeicolo = new JLabel("Veicolo *");
			lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblCliente = new JLabel("Cliente *");
			lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCliente = new JTextField();
			txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			JLabel lblDataInizio = new JLabel("Data Inizio *");
			lblDataInizio.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldInizio = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio.setText("Seleziona una data");
			frmtdtxtfldInizio.setEditable(false);
			
			LookAndFeel previous=UIManager.getLookAndFeel();
			JDateChooser dateChooserInizio=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserInizio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserInizio.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserInizio=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldInizio.setText(sdf.format(dateChooserInizio.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}	
							
			JLabel lblDataFine = new JLabel("Data Fine *");
			lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("Seleziona una data");
			frmtdtxtfldFine.setEditable(false);
			
			JDateChooser dateChooserFine = new JDateChooser();
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserFine= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserFine.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserFine=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldFine.setText(sdf.format(dateChooserFine.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}	
			
			
			JLabel lblCosto = new JLabel("Costo Totale *");
			lblCosto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCosto = new JTextField();
			txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblAcconto = new JLabel("Acconto");
			lblAcconto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAcconto = new JTextField();
			txtAcconto.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblCognome = new JLabel("Cognome *");
			lblCognome.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtCognome = new JTextField();
			txtCognome.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblPatente = new JLabel("Numero Patente *");
			lblPatente.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtPatente = new JTextField();
			txtPatente.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblValida = new JLabel("Valida fino a *");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldValida = new JFormattedTextField(dateformat);
			frmtdtxtfldValida.setText("Seleziona una data");
			frmtdtxtfldValida.setEditable(false);
			
			JDateChooser dateChooserValida = new JDateChooser();
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserValida= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserValida.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserValida=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldValida.setText(sdf.format(dateChooserValida.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}	
			
			JLabel lblRilasciataDa = new JLabel("Rilasciata da");
			lblRilasciataDa.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblRilasciataIl = new JLabel("Rilasciata il *");
			lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldRilasciatail = new JFormattedTextField(dateformat);
			frmtdtxtfldRilasciatail.setText("Seleziona una data");
			frmtdtxtfldRilasciatail.setEditable(false);
			
			JDateChooser dateChooserRilasciataIl = new JDateChooser();
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserRilasciataIl= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserRilasciataIl.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserRilasciataIl=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldRilasciatail.setText(sdf.format(dateChooserRilasciataIl.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}	
						
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			
			/* Crea il layout del form per aggiungere un nuovo contratto. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblMex, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(190)
										.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addGap(15)))
								.addGap(178))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
											.addGap(114)))
									.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(frmtdtxtfldFine, Alignment.LEADING)
											.addComponent(frmtdtxtfldInizio, Alignment.LEADING)
											.addComponent(txtVeicolo, Alignment.LEADING)
											.addComponent(comboBoxTipologia, Alignment.LEADING, 0, 165, Short.MAX_VALUE)
											.addComponent(txtCliente, Alignment.LEADING)
											.addComponent(txtCosto, Alignment.LEADING)
											.addComponent(txtAcconto, Alignment.LEADING)
											.addComponent(txtCognome, Alignment.LEADING)
											.addComponent(txtPatente, Alignment.LEADING)
											.addComponent(txtNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
											.addComponent(frmtdtxtfldValida, Alignment.LEADING)
											.addComponent(txtRilasciatada, Alignment.LEADING))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(dateChooserValida, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
											.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
											.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(frmtdtxtfldRilasciatail, 163, 163, 163)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(dateChooserRilasciataIl, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(29)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldInizio, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldFine, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtAcconto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addComponent(dateChooserValida, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
								.addGap(23)
								.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addComponent(dateChooserRilasciataIl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
			);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		 } else if (str.equals("Modifica")) {
			
			/* Viene creato il form per modificare un contratto. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Modifica Contratto di Noleggio"));
			
			btnCerca = new JButton("Cerca Contratto");
			btnCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCerca.addActionListener(this);	// Action Listener per il bottone Cerca.
			
			txtContrattoCerca = new JTextField();
			txtContrattoCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblContrattoCerca = new JLabel("Codice Contratto da Modificare ");
			lblContrattoCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModificaC = new JButton("Modifica Contratto");
			btnModificaC.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModificaC.addActionListener(this);	// Action Listener per il bottone Modifica.
			
			JLabel lblTipologia = new JLabel("Tipologia *\r\n");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblVeicolo = new JLabel("Veicolo *\r\n");
			lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
		
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Breve", "Lungo"}));
			comboBoxTipologia.setBackground(Color.WHITE);
		
			txtCliente = new JTextField();
			txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCliente.setToolTipText("Cognome Nome / Denominazione");
		
			JLabel lblCliente = new JLabel("Cliente *");
			lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblDataInizio = new JLabel("Data Inizio *");
			lblDataInizio.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldInizio = new JFormattedTextField();
			frmtdtxtfldInizio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldInizio.setText("Seleziona una data");
			frmtdtxtfldInizio.setEditable(false);
			
			LookAndFeel previous=UIManager.getLookAndFeel();
			JDateChooser dateChooserInizio=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserInizio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserInizio.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserInizio=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldInizio.setText(sdf.format(dateChooserInizio.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}	
		
			JLabel lblDataFine = new JLabel("Data Fine *\r\n");
			lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldFine = new JFormattedTextField();
			frmtdtxtfldFine.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldFine.setText("Seleziona una data");
			frmtdtxtfldFine.setEditable(false);
			
			JDateChooser dateChooserFine=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserFine= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserFine.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserFine=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldFine.setText(sdf.format(dateChooserFine.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}	
		
			JLabel lblCosto = new JLabel("Costo Totale *\r\n\r\n");
			lblCosto.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtCosto = new JTextField();
			txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblAcconto = new JLabel("Acconto");
			lblAcconto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAcconto = new JTextField();
			txtAcconto.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblCognome = new JLabel("Cognome *");
			lblCognome.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtCognome = new JTextField();
			txtCognome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			txtVeicolo.setEditable(false);
			
			JLabel lblPatente = new JLabel("Numero Patente *");
			lblPatente.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtPatente = new JTextField();
			txtPatente.setFont(new Font("Arial", Font.PLAIN, 12));
			txtPatente.setEditable(false);
			
			frmtdtxtfldValida = new JFormattedTextField();
			frmtdtxtfldValida.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldValida.setText("Seleziona una data");
			frmtdtxtfldValida.setEditable(false);
			
			JDateChooser dateChooserValida=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserValida= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserValida.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserValida=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldValida.setText(sdf.format(dateChooserValida.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}	
			
			JLabel lblValida = new JLabel("Valida fino a");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
			txtRilasciatada.setEditable(false);
			
			JLabel lblRilasciatada = new JLabel("Rilasciata da");
			lblRilasciatada.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldRilasciatail = new JFormattedTextField();
			frmtdtxtfldRilasciatail.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldRilasciatail.setText("Seleziona una data");
			frmtdtxtfldRilasciatail.setEditable(false);
			
			JDateChooser dateChooserRilasciataIl=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserRilasciataIl= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserRilasciataIl.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserRilasciataIl=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldRilasciatail.setText(sdf.format(dateChooserRilasciataIl.getDate()));				
					}
				});	
			} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			} catch (InstantiationException e1) {
					e1.printStackTrace();
			} catch (IllegalAccessException e1) {
					e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
			}	
			
			JLabel lblRilasciataIl = new JLabel("Rilasciata il");
			lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
			
			comboBoxTipologia.setEditable(false);
			txtVeicolo.setEditable(false);
			txtCliente.setEditable(false);
			frmtdtxtfldInizio.setEditable(false);
			frmtdtxtfldFine.setEditable(false);
			txtCosto.setEditable(false);
			txtAcconto.setEditable(false);
			txtCognome.setEditable(false);
			txtNome.setEditable(false);
			
			/* Crea il layout del form per modificare un contratto. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblContrattoCerca, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblTipologia, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblVeicolo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblDataInizio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblDataFine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblCosto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblAcconto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblCognome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblRilasciataIl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblRilasciatada, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblValida, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblPatente, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
								.addGap(22)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(frmtdtxtfldRilasciatail)
									.addComponent(txtRilasciatada)
									.addComponent(frmtdtxtfldValida)
									.addComponent(txtPatente)
									.addComponent(txtNome)
									.addComponent(txtCognome)
									.addComponent(txtAcconto)
									.addComponent(txtCosto)
									.addComponent(frmtdtxtfldFine)
									.addComponent(frmtdtxtfldInizio)
									.addComponent(txtCliente)
									.addComponent(txtVeicolo)
									.addComponent(comboBoxTipologia, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtContrattoCerca, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
								.addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(dateChooserRilasciataIl, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserValida, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(150)
								.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(150)
								.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(159, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(39)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtContrattoCerca, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblContrattoCerca))
						.addGap(18)
						.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTipologia))
						.addGap(15)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(dateChooserInizio, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldInizio, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtAcconto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(20)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(20)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblRilasciatada, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addComponent(dateChooserValida, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addComponent(dateChooserRilasciataIl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(21, Short.MAX_VALUE))
			);
			
			this.setLayout(gl_contentPane);
			this.revalidate();
			
		} else if (str.equals("Elimina")){
			
			/* Viene creato il form per eliminare un contratto. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Contratto"));
			
			btnElimina = new JButton("Elimina Contratto");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	// Action Listener per il bottone Elimina.
			
			txtCodiceElimina = new JTextField();
			txtCodiceElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCodiceElimina.setColumns(10);
			
			JLabel lblCod = new JLabel("Codice Contratto");
			lblCod.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout del form per un eliminare un contratto. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(lblCod, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
							.addComponent(txtCodiceElimina, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addGap(82))
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
								.addComponent(lblCod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCodiceElimina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(50)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(169, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Elenca")) {
			
			/* Viene generato l'elenco dei contratti contenuti nel database. */
			elencoContratti = new DBConnect();			
				this.removeAll();
				this.setBorder(BorderFactory.createTitledBorder("Elenco Contratti"));

				try {
					elencoContratti.exequery("SELECT * FROM noleggio","select");
					
					tblNoleggi = new JTable();
					tblNoleggi.setModel(new CostruisciTabella(elencoContratti.rs).model);
					tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					TableColumnAdjuster tca = new TableColumnAdjuster(tblNoleggi);
					tca.adjustColumns();
					
					scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scroll.setViewportView(tblNoleggi);
					
					elencoContratti.con.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco noleggi!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				}
				
				JLabel lblCliente = new JLabel("Cliente da filtrare");
				lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
				
				txtCliente = new JTextField();
				txtCliente.setColumns(10);
				
				JLabel lblVeicoloDaFiltrare = new JLabel("Veicolo da filtrare");
				lblVeicoloDaFiltrare.setFont(new Font("Arial", Font.BOLD, 13));
				
				txtVeicolo = new JTextField();
				txtVeicolo.setColumns(10);
				
				btnFiltra = new JButton("Filtra Contratti");
				btnFiltra.addActionListener(this); 	// Action Listener per il bottone Filtra.
				
				/* Crea il layout per l'elenco dei contratti. */
				GroupLayout gl_contentPane = new GroupLayout(this);
				gl_contentPane.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtCliente)
											.addComponent(lblCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblVeicoloDaFiltrare, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtVeicolo, Alignment.TRAILING))
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnFiltra, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
										.addGap(17)))
								.addGap(28))
					);
					gl_contentPane.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblCliente)
									.addComponent(lblVeicoloDaFiltrare, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnFiltra))
								.addContainerGap())
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
			contratto = new Contratto();
			contratto.setValori(this);
			contratto.aggiungi(this);
			
		} else if (btnCalcola == e.getSource()) {
			preventivo = new Preventivo();
			preventivo.setValori(this);
			try {
				preventivo.calcola(this);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Impossibile calcolare il preventivo!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		
		} else if (btnPassaAContratto == e.getSource()) {
			if (Preventivo.getTotale()!=0) {
				this.set("Passaggio");
			} else {
				JOptionPane.showMessageDialog(null, "Si deve prima calcolare il preventivo!",
						"INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		
		} else if(btnElimina == e.getSource()) {
			contratto = new Contratto();
			if (contratto.setCodiceElimina(this)) {
				contratto.elimina(this);
			}
		
		} else if (btnFiltra == e.getSource()) {
			contratto = new Contratto();
			contratto.setValoriFiltra(this);
			contratto.filtra(this);
		
		} else if(btnModificaC == e.getSource()) {
			contratto = new Contratto();
			contratto.setCodiceModifica(this);
			contratto.setValori(this);
			contratto.modifica(this);
		
		} else if(btnCerca == e.getSource()) {
			contratto = new Contratto();
			if (contratto.setCodiceCerca(this)) {
				contratto.cerca(this);
			}
		}
	}
	
/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "ModuloContratto [La classe ModuloContratto si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.]";
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
		ModuloContratto other = (ModuloContratto) obj;
		if (btnAggiungi == null) {
			if (other.btnAggiungi != null)
				return false;
		} else if (!btnAggiungi.equals(other.btnAggiungi))
			return false;
		if (btnCalcola == null) {
			if (other.btnCalcola != null)
				return false;
		} else if (!btnCalcola.equals(other.btnCalcola))
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
		if (btnFiltra == null) {
			if (other.btnFiltra != null)
				return false;
		} else if (!btnFiltra.equals(other.btnFiltra))
			return false;
		if (btnModificaC == null) {
			if (other.btnModificaC != null)
				return false;
		} else if (!btnModificaC.equals(other.btnModificaC))
			return false;
		if (btnPassaAContratto == null) {
			if (other.btnPassaAContratto != null)
				return false;
		} else if (!btnPassaAContratto.equals(other.btnPassaAContratto))
			return false;
		if (comboBoxTipologia == null) {
			if (other.comboBoxTipologia != null)
				return false;
		} else if (!comboBoxTipologia.equals(other.comboBoxTipologia))
			return false;
		if (contratto == null) {
			if (other.contratto != null)
				return false;
		} else if (!contratto.equals(other.contratto))
			return false;
		if (elencoContratti == null) {
			if (other.elencoContratti != null)
				return false;
		} else if (!elencoContratti.equals(other.elencoContratti))
			return false;
		if (frmtdtxtfldFine == null) {
			if (other.frmtdtxtfldFine != null)
				return false;
		} else if (!frmtdtxtfldFine.equals(other.frmtdtxtfldFine))
			return false;
		if (frmtdtxtfldInizio == null) {
			if (other.frmtdtxtfldInizio != null)
				return false;
		} else if (!frmtdtxtfldInizio.equals(other.frmtdtxtfldInizio))
			return false;
		if (frmtdtxtfldRilasciatail == null) {
			if (other.frmtdtxtfldRilasciatail != null)
				return false;
		} else if (!frmtdtxtfldRilasciatail.equals(other.frmtdtxtfldRilasciatail))
			return false;
		if (frmtdtxtfldValida == null) {
			if (other.frmtdtxtfldValida != null)
				return false;
		} else if (!frmtdtxtfldValida.equals(other.frmtdtxtfldValida))
			return false;
		if (lblPreventivo == null) {
			if (other.lblPreventivo != null)
				return false;
		} else if (!lblPreventivo.equals(other.lblPreventivo))
			return false;
		if (preventivo == null) {
			if (other.preventivo != null)
				return false;
		} else if (!preventivo.equals(other.preventivo))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblNoleggi == null) {
			if (other.tblNoleggi != null)
				return false;
		} else if (!tblNoleggi.equals(other.tblNoleggi))
			return false;
		if (txtAcconto == null) {
			if (other.txtAcconto != null)
				return false;
		} else if (!txtAcconto.equals(other.txtAcconto))
			return false;
		if (txtCliente == null) {
			if (other.txtCliente != null)
				return false;
		} else if (!txtCliente.equals(other.txtCliente))
			return false;
		if (txtCodiceElimina == null) {
			if (other.txtCodiceElimina != null)
				return false;
		} else if (!txtCodiceElimina.equals(other.txtCodiceElimina))
			return false;
		if (txtCognome == null) {
			if (other.txtCognome != null)
				return false;
		} else if (!txtCognome.equals(other.txtCognome))
			return false;
		if (txtContrattoCerca == null) {
			if (other.txtContrattoCerca != null)
				return false;
		} else if (!txtContrattoCerca.equals(other.txtContrattoCerca))
			return false;
		if (txtCosto == null) {
			if (other.txtCosto != null)
				return false;
		} else if (!txtCosto.equals(other.txtCosto))
			return false;
		if (txtNome == null) {
			if (other.txtNome != null)
				return false;
		} else if (!txtNome.equals(other.txtNome))
			return false;
		if (txtPatente == null) {
			if (other.txtPatente != null)
				return false;
		} else if (!txtPatente.equals(other.txtPatente))
			return false;
		if (txtRilasciatada == null) {
			if (other.txtRilasciatada != null)
				return false;
		} else if (!txtRilasciatada.equals(other.txtRilasciatada))
			return false;
		if (txtVeicolo == null) {
			if (other.txtVeicolo != null)
				return false;
		} else if (!txtVeicolo.equals(other.txtVeicolo))
			return false;
		return true;
	}
	
	
}
