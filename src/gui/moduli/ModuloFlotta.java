package gui.moduli;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import db.DBConnect;
import entita.Veicolo;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloFlotta si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.
 */
public class ModuloFlotta extends JPanel implements ActionListener, FocusListener{

	private static final long serialVersionUID = 1L; 
	
	/**
	 * Il combo box dal quale selezionare la tipologia di veicolo.
	 */
	public JComboBox <String> comboBoxTipologia;
	
	/**
	 * Il combo box dal quale selezionare la disponibilità di un veicolo.
	 */
	public JComboBox <String> comboBoxDisponibilita;
	
	/**
	 * Il combo box dal quale seleziona l'alimentazione di un veicolo.
	 */
	public JComboBox <String> comboBoxAlimentazione;
	
	/**
	 * Il combo box dal quale selezionare il codice del prezzo per il noleggio a breve termine.
	 */
	public JComboBox <String> comboBoxBreveTermine;
	
	/**
	 * Il combo box dal quale selezionare il codice del prezzo per il noleggio a lungo termine.
	 */
	public JComboBox <String> comboBoxLungoTermine;
	
	/**
	 * La casella di testo in cui inserire la data di immatricolazione del veicolo.
	 */
	public JFormattedTextField frmtdtxtfldImma;
	
	/**
	 * La casella di testo in cui inserire la data di scadenza del bollo.
	 */
	public JFormattedTextField frmtdtxtfldBollo;
	
	/**
	 * La casella di testo in cui inserire la data di scadenza del tagliando.
	 */
	public JFormattedTextField frmtdtxtfldTagliando;
	
	/**
	 * La casella di testo in cui inserire la data di scadenza dell'assicurazione.
	 */
	public JFormattedTextField frmtdtxtfldAssicurazione;
	
	/**
	 * La casella di testo in cui inserire la data di scadenza dell'ormeggio.
	 */
	public JFormattedTextField frmtdtxtfldOrmeggio;
	
	/**
	 * La casella di testo in cui inserire la data di scadenza dell'alaggio.
	 */
	public JFormattedTextField frmtdtxtfldAlaggio;
	
	/**
	 * La casella di testo in cui inserire il nome del veicolo.
	 */
	public JTextField txtNome;
	
	/**
	 * La casella di testo in cui inserire la marca del veicolo.
	 */
	public JTextField txtMarca;
	
	/**
	 * La casella di testo in cui inserire i kilometri effettuati dal veicolo.
	 */
	public JTextField txtKm;
	
	/**
	 * La casella di testo in cui inserire la targa del veicolo.
	 */
	public JTextField txtTarga;
	
	/**
	 * La casella di testo in cui inserire le dimensioni del veicolo.
	 */
	public JTextField txtDimensioni;
	
	/**
	 * La casella di testo in cui inserire la targa del veicolo da cercare.
	 */
	public JTextField txtTargaCerca;
	
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JButton btnModifica;
	private JButton btnCerca;
	private JTable tblVeicoli;
	private JScrollPane scroll = new JScrollPane(tblVeicoli);
	
	private Veicolo veicolo;
	private DBConnect elencoVeicoli;
	
	/**
	 * Inizializza un nuovo oggetto ModuloFlotta e richiama il metodo {@code set} passando come argomento l'oggetto String {@code str}.
	 * 
	 * @param str una stringa che determina il diverso comportamento del metodo {@code set}.
	 */
	public ModuloFlotta(String str) {
		set(str);
	}

	/**
	 * Si comporta in maniera differente a seconda dell'oggetto String che viene passato come argomento. <br><br>
	 * 
	 * - Se viene passato "Nuovo", viene creato il form per aggiungere un nuovo veicolo. <br>
	 * - Se viene passato "Modifica", viene creato il form per modificare un veicolo. <br>
	 * - Se viene passato "Elimina", viene creato il form per eliminare un veicolo. <br>
	 * - Se viene passato "Elenca", viene generato l'elenco dei veicoli contenuti nel database.
	 * 
	 * @param str una stringa che determina cosa verrà mostrato a schermo.
	 */
	public void set(String str) {
		
		if (str.equals("Elenca")){
			
			/* Viene generato l'elenco dei veicoli contenuti nel database. */
			elencoVeicoli = new DBConnect();
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Veicoli"));
			
			try {
				elencoVeicoli.exequery("SELECT * FROM veicolo","select");
				
				tblVeicoli = new JTable();
				tblVeicoli.setModel(new CostruisciTabella(elencoVeicoli.rs).model);
				tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumnAdjuster tca = new TableColumnAdjuster(tblVeicoli);
				tca.adjustColumns();
				
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setViewportView(tblVeicoli);
				
				elencoVeicoli.con.close();
			} catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco dei veicoli! ",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
			}
			
			/* Crea il layout per l'elenco dei veicoli. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(115, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(75, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Nuovo")) {
			
			/* Viene creato il form per aggiungere un veicolo. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Veicolo"));
			
			btnAggiungi = new JButton("Aggiungi Veicolo");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			btnAggiungi.addFocusListener(this);
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTarga.addFocusListener(this);
			
			JLabel lblTarga = new JLabel("Targa *");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			txtNome.addFocusListener(this);
		
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblDisponibilita = new JLabel("Disponibilità *");
			lblDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblMarca = new JLabel("Marca *");
			lblMarca.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
			txtMarca.addFocusListener(this);
		
			JLabel lblAlimentazione = new JLabel("Alimentazione *");
			lblAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblKm = new JLabel("Km Effettuati *");
			lblKm.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			txtKm.addFocusListener(this);
			
			JLabel lblDimensioni = new JLabel("Dimensioni (cm)");
			lblDimensioni.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtDimensioni = new JTextField();
			txtDimensioni.setFont(new Font("Arial", Font.PLAIN, 12));
			txtDimensioni.setText("lun/lar/alt");
			txtDimensioni.addFocusListener(this);
			
			
			JLabel lblImma = new JLabel("Data Immatricolazione *");
			lblImma.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblBollo = new JLabel("Data Scadenza Bollo *");
			lblBollo.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblTagliando = new JLabel("Data Scadenza Tagliando *");
			lblTagliando.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAssicurazione = new JLabel("Data Scadenza Assicurazione *\r\n");
			lblAssicurazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblOrmeggio = new JLabel("Data Scadenza Ormeggio *");
			lblOrmeggio.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAlaggio = new JLabel("Data Scadenza Alaggio *");
			lblAlaggio.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			frmtdtxtfldImma = new JFormattedTextField(dateformat);
			frmtdtxtfldImma.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldImma.setColumns(10);
			frmtdtxtfldImma.setText("Seleziona una data");
			frmtdtxtfldImma.addFocusListener(this);
			frmtdtxtfldImma.setEditable(false);
			
			LookAndFeel previous=UIManager.getLookAndFeel();
			JDateChooser dateChooserImma=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserImma= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserImma.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserImma=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldImma.setText(sdf.format(dateChooserImma.getDate()));				
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
			
			frmtdtxtfldBollo = new JFormattedTextField(dateformat);
			frmtdtxtfldBollo.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldBollo.setColumns(10);
			frmtdtxtfldBollo.setText("Seleziona una data");
			frmtdtxtfldBollo.addFocusListener(this);
			frmtdtxtfldBollo.setEditable(false);
			
			JDateChooser dateChooserBollo=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserBollo= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserBollo.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserBollo=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldBollo.setText(sdf.format(dateChooserBollo.getDate()));				
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
			
			frmtdtxtfldTagliando = new JFormattedTextField(dateformat);
			frmtdtxtfldTagliando.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldTagliando.setColumns(10);
			frmtdtxtfldTagliando.setText("Seleziona una data");
			frmtdtxtfldTagliando.addFocusListener(this);
			
			frmtdtxtfldTagliando.setEditable(false);
			
			JDateChooser dateChooserTagliando=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserTagliando= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserTagliando.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserTagliando=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldTagliando.setText(sdf.format(dateChooserTagliando.getDate()));				
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
			
			frmtdtxtfldAssicurazione = new JFormattedTextField(dateformat);
			frmtdtxtfldAssicurazione.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAssicurazione.setColumns(10);
			frmtdtxtfldAssicurazione.setText("Seleziona una data");
			frmtdtxtfldAssicurazione.addFocusListener(this);			
			frmtdtxtfldAssicurazione.setEditable(false);
			
			JDateChooser dateChooserAssicurazione=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserAssicurazione= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserAssicurazione.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserAssicurazione=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldAssicurazione.setText(sdf.format(dateChooserAssicurazione.getDate()));				
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
			
			frmtdtxtfldOrmeggio = new JFormattedTextField(dateformat);
			frmtdtxtfldOrmeggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldOrmeggio.setColumns(10);
			frmtdtxtfldOrmeggio.setText("Seleziona una data");
			frmtdtxtfldOrmeggio.addFocusListener(this);
			frmtdtxtfldOrmeggio.setEditable(false);
			
			JDateChooser dateChooserOrmeggio=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserOrmeggio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserOrmeggio.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserOrmeggio=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldOrmeggio.setText(sdf.format(dateChooserOrmeggio.getDate()));				
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
			
			frmtdtxtfldAlaggio = new JFormattedTextField(dateformat);
			frmtdtxtfldAlaggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAlaggio.setColumns(10);
			frmtdtxtfldAlaggio.setText("Seleziona una data");
			frmtdtxtfldAlaggio.addFocusListener(this);
			frmtdtxtfldAlaggio.setEditable(false);
			
			JDateChooser dateChooserAlaggio=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserAlaggio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserAlaggio.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserAlaggio=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldAlaggio.setText(sdf.format(dateChooserAlaggio.getDate()));								
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
			
			JLabel lblBreve = new JLabel("Costo Breve Termine *");
			lblBreve.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblLungo = new JLabel("Costo Lungo Termine");
			lblLungo.setFont(new Font("Arial", Font.BOLD, 14));
			
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setBackground(Color.WHITE);
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setToolTipText("Seleziona una tipologia.");
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Autobus_12_Posti", "Autobus_16_Posti", "Autocaravan_4_Posti",
					"Autocaravan_6_Posti", "Autocarro_Cabinato", "Autocarro_Furgonato", "Barca_Motore", "Berlina", "Cabriolet", "Catamarano", "Coup\u00E8",
					"Fuoristrada", "Gommone", "Limousine", "Motocicletta", "Multispazio", "Quad_BIke", "Scooter", "SUV", "Utilitaria"}));
			comboBoxTipologia.addActionListener(new ActionListener() {
	            
				/* Disabilita le textBox relative ai mezzi acquatici nel caso venga selezionato un veicolo su ruote. */
				public void actionPerformed(ActionEvent event) {
	            
					@SuppressWarnings("rawtypes")
					JComboBox comboBox = (JComboBox) event.getSource();

	                Object selected = comboBox.getSelectedItem();
	                if(selected.toString().equals("Barca_Motore") || selected.toString().equals("Catamarano") || selected.toString().equals("Gommone") ) {
	                	frmtdtxtfldOrmeggio.setEnabled(true);
						frmtdtxtfldAlaggio.setEnabled(true);
	                }else {
	                	frmtdtxtfldOrmeggio.setEnabled(false);
						frmtdtxtfldAlaggio.setEnabled(false);
	                }
	            }
	        });
			
			comboBoxAlimentazione = new JComboBox<>();
			comboBoxAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxAlimentazione.setToolTipText("Seleziona l'alimentazione del veicolo.\r\n");
			comboBoxAlimentazione.setModel(new DefaultComboBoxModel<>(new String[] {"", "Benzina", "Diesel", "Metano", "GPL"}));
			comboBoxAlimentazione.setBackground(Color.WHITE);
			comboBoxAlimentazione.addFocusListener(this);
			
			comboBoxBreveTermine = new JComboBox<>();
			comboBoxBreveTermine.setToolTipText("Seleziona un costo a breve termine.");
			comboBoxBreveTermine.setModel(new DefaultComboBoxModel<>(new String[] {"", "Autobus_12_Posti", "Autobus_16_Posti", "Autocaravan_4_Posti",
					"Autocaravan_6_Posti", "Autocarro_Cabinato", "Autocarro_Furgonato", "Automobile_Berlina", "Automobile_Cabriolet", "Automobile_Coup\u00E8",
					"Automobile_Fuoristrada", "Automobile_Limousine", "Automobile_Multispazio", "Automobile_SUV", "Automobile_Utilitaria", "Imbarcazione_Barca_Motore",
					"Imbarcazione_Catamarano", "Motociclo_Motocicletta\t", "Motociclo_Scooter", "Natante_Gommone", "Quadriciclo_Quad_Bike"}));
			comboBoxBreveTermine.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxBreveTermine.setBackground(Color.WHITE);
			comboBoxBreveTermine.addFocusListener(this);
			
			comboBoxLungoTermine = new JComboBox<>();
			comboBoxLungoTermine.setModel(new DefaultComboBoxModel<>(new String[] {"", "Automobile_Berlina", "Automobile_Cabriolet", "Automobile_Coup\u00E8",
					"Automobile_Fuoristrada", "Automobile_Multispazio", "Automobile_SUV", "Automobile_Utilitaria"}));
			comboBoxLungoTermine.setToolTipText("Seleziona un costo a lungo termine");
			comboBoxLungoTermine.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxLungoTermine.setBackground(Color.WHITE);
			comboBoxLungoTermine.addFocusListener(this);
			
			/* Crea il layout del form per aggiungere un nuovo veicolo. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblKm, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAlaggio)
									.addComponent(lblOrmeggio)
									.addComponent(lblAssicurazione)
									.addComponent(lblMarca)
									.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTagliando)
									.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblBreve, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblLungo, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblImma)
									.addComponent(lblBollo))
								.addGap(5)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBoxLungoTermine, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxBreveTermine, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(frmtdtxtfldAlaggio)
									.addComponent(frmtdtxtfldOrmeggio)
									.addComponent(frmtdtxtfldAssicurazione)
									.addComponent(frmtdtxtfldTagliando)
									.addComponent(frmtdtxtfldBollo)
									.addComponent(frmtdtxtfldImma)
									.addComponent(comboBoxAlimentazione, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtMarca)
									.addComponent(txtNome)
									.addComponent(comboBoxTipologia, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtKm, Alignment.TRAILING)
									.addComponent(txtDimensioni, Alignment.TRAILING)
									.addComponent(txtTarga, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
								.addGap(6)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(dateChooserAlaggio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserOrmeggio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserAssicurazione, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserTagliando, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserBollo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserImma, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(160)
								.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(150, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(12)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxAlimentazione, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblKm, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtDimensioni, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(dateChooserImma, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldImma, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
								.addComponent(lblImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(dateChooserBollo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldBollo, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldTagliando, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addComponent(dateChooserTagliando, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(17)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAssicurazione, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addComponent(dateChooserAssicurazione, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(17)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldOrmeggio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldAlaggio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(dateChooserOrmeggio, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(dateChooserAlaggio, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblBreve, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxBreveTermine, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblLungo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxLungoTermine, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(25)
						.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGap(14))
			);
				this.setLayout(gl_contentPane);
				this.revalidate();
		
		} else if (str.equals("Modifica")) {
			
			/* Viene creato il form per modificare un veicolo. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Modifica Veicolo"));
			
			btnCerca = new JButton("Cerca Veicolo");
			btnCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCerca.addActionListener(this);
			btnCerca.addFocusListener(this);
			
			txtTargaCerca = new JTextField();
			txtTargaCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTargaCerca.addFocusListener(this);
			
			JLabel lblTargaCerca = new JLabel("Targa Veicolo da Modificare");
			lblTargaCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModifica = new JButton("Modifica Veicolo");
			btnModifica.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModifica.addActionListener(this);
			btnModifica.addFocusListener(this);
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTarga.addFocusListener(this);
			
			JLabel lblTarga = new JLabel("Targa *");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			txtNome.addFocusListener(this);
		
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblDisponibilita = new JLabel("Disponibilità *");
			lblDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblMarca = new JLabel("Marca *");
			lblMarca.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
			txtMarca.addFocusListener(this);
		
			JLabel lblAlimentazione = new JLabel("Alimentazione *");
			lblAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblKm = new JLabel("Km Effettuati *");
			lblKm.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			txtKm.addFocusListener(this);
			
			JLabel lblDimensioni = new JLabel("Dimensioni (cm)");
			lblDimensioni.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtDimensioni = new JTextField("lun/lar/alt");
			txtDimensioni.setFont(new Font("Arial", Font.PLAIN, 12));
			txtDimensioni.addFocusListener(this);
			
			JLabel lblImma = new JLabel("Data Immatricolazione");
			lblImma.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblBollo = new JLabel("Data Scadenza Bollo");
			lblBollo.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblTagliando = new JLabel("Data Scadenza Tagliando");
			lblTagliando.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAssicurazione = new JLabel("Data Scadenza Assicurazione");
			lblAssicurazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblOrmeggio = new JLabel("Data Scadenza Ormeggio");
			lblOrmeggio.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblAlaggio = new JLabel("Data Scadenza Alaggio");
			lblAlaggio.setFont(new Font("Arial", Font.BOLD, 14));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setBackground(Color.WHITE);
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setToolTipText("Seleziona una tipologia.");
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Autobus_12_Posti", "Autobus_16_Posti", "Autocaravan_4_Posti",
					"Autocaravan_6_Posti", "Autocarro_Cabinato", "Autocarro_Furgonato", "Barca_Motore", "Berlina", "Cabriolet", "Catamarano", "Coup\u00E8",
					"Fuoristrada", "Gommone", "Limousine", "Motocicletta", "Multispazio", "Quad_BIke", "Scooter", "SUV", "Utilitaria"}));
			comboBoxTipologia.addFocusListener(this);
			
			comboBoxDisponibilita = new JComboBox<>();
			comboBoxDisponibilita.setBackground(Color.WHITE);
			comboBoxDisponibilita.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxDisponibilita.setModel(new DefaultComboBoxModel<>(new String[] {"", "SI", "NO"}));
			comboBoxDisponibilita.setToolTipText("(SI/NO)");
			comboBoxDisponibilita.addFocusListener(this);
			
			comboBoxAlimentazione = new JComboBox<>();
			comboBoxAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxAlimentazione.setToolTipText("Seleziona l'alimentazione del veicolo.\r\n");
			comboBoxAlimentazione.setModel(new DefaultComboBoxModel<>(new String[] {"", "Benzina", "Diesel", "Metano", "GPL"}));
			comboBoxAlimentazione.setBackground(Color.WHITE);
			comboBoxAlimentazione.addFocusListener(this);
			
			frmtdtxtfldImma = new JFormattedTextField(dateformat);
			frmtdtxtfldImma.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldImma.setColumns(10);
			frmtdtxtfldImma.setText("Seleziona una data");
			frmtdtxtfldImma.addFocusListener(this);		
			frmtdtxtfldImma.setEditable(false);
			
			LookAndFeel previous=UIManager.getLookAndFeel();
			JDateChooser dateChooserImma=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserImma= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserImma.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserImma=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldImma.setText(sdf.format(dateChooserImma.getDate()));				
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
			
			frmtdtxtfldBollo = new JFormattedTextField(dateformat);
			frmtdtxtfldBollo.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldBollo.setColumns(10);
			frmtdtxtfldBollo.setText("Seleziona una data");
			frmtdtxtfldBollo.addFocusListener(this);
			frmtdtxtfldBollo.setEditable(false);
			
			JDateChooser dateChooserBollo=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserBollo= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserBollo.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserBollo=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldBollo.setText(sdf.format(dateChooserBollo.getDate()));				
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
			
			frmtdtxtfldTagliando = new JFormattedTextField(dateformat);
			frmtdtxtfldTagliando.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldTagliando.setColumns(10);
			frmtdtxtfldTagliando.setText("Seleziona una data");
			frmtdtxtfldTagliando.addFocusListener(this);
			frmtdtxtfldTagliando.setEditable(false);
			
			JDateChooser dateChooserTagliando=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserTagliando= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserTagliando.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserTagliando=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldTagliando.setText(sdf.format(dateChooserTagliando.getDate()));				
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
			
			frmtdtxtfldAssicurazione = new JFormattedTextField(dateformat);
			frmtdtxtfldAssicurazione.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAssicurazione.setColumns(10);
			frmtdtxtfldAssicurazione.setText("Seleziona una data");
			frmtdtxtfldAssicurazione.addFocusListener(this);
			frmtdtxtfldAssicurazione.setEditable(false);
			
			JDateChooser dateChooserAssicurazione=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserAssicurazione= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserAssicurazione.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserAssicurazione=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldAssicurazione.setText(sdf.format(dateChooserAssicurazione.getDate()));									
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
			
			frmtdtxtfldOrmeggio = new JFormattedTextField(dateformat);
			frmtdtxtfldOrmeggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldOrmeggio.setColumns(10);
			frmtdtxtfldOrmeggio.setText("Seleziona una data");
			frmtdtxtfldOrmeggio.addFocusListener(this);
			frmtdtxtfldOrmeggio.setEditable(false);
			
			JDateChooser dateChooserOrmeggio=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserOrmeggio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserOrmeggio.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserOrmeggio=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldOrmeggio.setText(sdf.format(dateChooserOrmeggio.getDate()));				
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
			
			frmtdtxtfldAlaggio = new JFormattedTextField(dateformat);
			frmtdtxtfldAlaggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAlaggio.setColumns(10);
			frmtdtxtfldAlaggio.setText("Seleziona una data");
			frmtdtxtfldAlaggio.addFocusListener(this);
			frmtdtxtfldAlaggio.setEditable(false);
			
			JDateChooser dateChooserAlaggio=null;
					
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserAlaggio= new JDateChooser();
				UIManager.setLookAndFeel(previous);
				dateChooserAlaggio.addPropertyChangeListener("date", new PropertyChangeListener (){
					public void propertyChange (PropertyChangeEvent e) {
						JDateChooser dateChooserAlaggio=(JDateChooser) e.getSource();
						SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
						frmtdtxtfldAlaggio.setText(sdf.format(dateChooserAlaggio.getDate()));				
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
			
			txtNome.setEditable(false);
			comboBoxDisponibilita.setEditable(false);
			txtMarca.setEditable(false);
			txtKm.setEditable(false);
			txtTarga.setEditable(false);
			txtDimensioni.setEditable(false);
			frmtdtxtfldImma.setEditable(false);
			frmtdtxtfldBollo.setEditable(false);
			frmtdtxtfldTagliando.setEditable(false);
			frmtdtxtfldAssicurazione.setEditable(false);
			frmtdtxtfldOrmeggio.setEditable(false);
			frmtdtxtfldAlaggio.setEditable(false);
			
			/* Crea il layout del form per modificare un veicolo. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(164)
							.addComponent(btnCerca)
							.addContainerGap(336, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTargaCerca, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDisponibilita, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImma)
								.addComponent(lblBollo)
								.addComponent(lblTagliando)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblAssicurazione, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblKm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
								.addComponent(lblOrmeggio)
								.addComponent(lblAlaggio))
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(frmtdtxtfldAlaggio)
								.addComponent(frmtdtxtfldOrmeggio)
								.addComponent(frmtdtxtfldAssicurazione)
								.addComponent(frmtdtxtfldTagliando)
								.addComponent(frmtdtxtfldBollo)
								.addComponent(frmtdtxtfldImma)
								.addComponent(txtDimensioni)
								.addComponent(txtKm)
								.addComponent(comboBoxAlimentazione, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBoxDisponibilita, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtNome)
								.addComponent(txtMarca)
								.addComponent(comboBoxTipologia, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtTargaCerca, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(txtTarga, Alignment.TRAILING, 161, 161, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(dateChooserOrmeggio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(dateChooserTagliando, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
													.addComponent(dateChooserBollo, 0, 0, Short.MAX_VALUE)
													.addComponent(dateChooserImma, GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)))
											.addGap(68))
										.addComponent(dateChooserAssicurazione, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
									.addGap(74))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(dateChooserAlaggio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addComponent(btnModifica)
							.addContainerGap(309, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTargaCerca)
									.addGap(18)
									.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtTargaCerca, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTarga)
								.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxDisponibilita, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDisponibilita, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxAlimentazione, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtKm, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblKm, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDimensioni, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(dateChooserImma, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldImma, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooserBollo, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldBollo, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooserTagliando, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldTagliando, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
									.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooserAssicurazione, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(frmtdtxtfldAssicurazione, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooserOrmeggio, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldOrmeggio, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
									.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooserAlaggio, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldAlaggio, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
									.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addGap(28)
							.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(40))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Elimina")) {
			
			/* Viene creato il form per eliminare un veicolo. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Veicolo"));
			
			btnElimina = new JButton("Elimina Veicolo");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	// Action Listener per il bottone Elimina.
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTarga.setColumns(10);
			
			JLabel lblTarga = new JLabel("Targa Veicolo");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il layout del form per un eliminare un veicolo. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
							.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(50)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(169, Short.MAX_VALUE))
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
			veicolo = new Veicolo();
			veicolo.setValori(this,"aggiungi");
			veicolo.aggiungi(this);
		
		} else if(btnElimina == e.getSource()) {
			veicolo = new Veicolo();
			veicolo.setTargaElimina(this);
			veicolo.elimina(this);
		
		} else if(btnCerca == e.getSource()) {
			veicolo = new Veicolo();
			veicolo.setTargaCerca(this);
			veicolo.cerca(this);
		
		} else if(btnModifica == e.getSource()) {
			veicolo = new Veicolo();
			veicolo.setValori(this,"modifica");
			veicolo.modifica(this);
		}
	}
	
	/**
	 * Definisce le azioni da eseguire quando viene acquisito il focus. 
	 */
	public void focusGained(FocusEvent e){
		
		if (txtDimensioni == e.getSource() && txtDimensioni.getText().equals("lun/lar/alt")) {
			txtDimensioni.setText("");
		
		} else if (frmtdtxtfldImma == e.getSource() && frmtdtxtfldImma.getText().equals("Seleziona una data")) {
			frmtdtxtfldImma.setText("");
		
		} else if (frmtdtxtfldBollo == e.getSource() && frmtdtxtfldBollo.getText().equals("Seleziona una data")) {
			frmtdtxtfldBollo.setText("");
		
		} else if (frmtdtxtfldTagliando == e.getSource() && frmtdtxtfldTagliando.getText().equals("Seleziona una data")){
			frmtdtxtfldTagliando.setText("");
		
		} else if (frmtdtxtfldAssicurazione == e.getSource() && frmtdtxtfldAssicurazione.getText().equals("Seleziona una data")) {
			frmtdtxtfldAssicurazione.setText("");
		
		} else if (frmtdtxtfldOrmeggio == e.getSource() && frmtdtxtfldOrmeggio.getText().equals("Seleziona una data")) {
			frmtdtxtfldOrmeggio.setText("");
		
		} else if (frmtdtxtfldAlaggio == e.getSource() && frmtdtxtfldAlaggio.getText().equals("Seleziona una data")) {
			frmtdtxtfldAlaggio.setText("");
		}
		
		if (!(txtDimensioni == e.getSource()) && txtDimensioni.getText().equals("")) {
			txtDimensioni.setText("lun/lar/alt");
		
		} else if (!(frmtdtxtfldImma == e.getSource()) && frmtdtxtfldImma.getText().equals("")) {
			frmtdtxtfldImma.setText("Seleziona una data");
		
		} else if (!(frmtdtxtfldBollo == e.getSource()) && frmtdtxtfldBollo.getText().equals("")) {
			frmtdtxtfldBollo.setText("Seleziona una data");
		
		} else if (!(frmtdtxtfldTagliando == e.getSource()) && frmtdtxtfldTagliando.getText().equals("")){
			frmtdtxtfldTagliando.setText("Seleziona una data");
		
		} else if (!(frmtdtxtfldAssicurazione == e.getSource()) && frmtdtxtfldAssicurazione.getText().equals("")) {
			frmtdtxtfldAssicurazione.setText("Seleziona una data");
		
		} else if (!(frmtdtxtfldOrmeggio == e.getSource()) && frmtdtxtfldOrmeggio.getText().equals("")) {
			frmtdtxtfldOrmeggio.setText("Seleziona una data");
		
		} else if (!(frmtdtxtfldAlaggio == e.getSource()) && frmtdtxtfldAlaggio.getText().equals("")) {
			frmtdtxtfldAlaggio.setText("Seleziona una data");
		}
    }
	
	/**
	 * Definisce le azioni da eseguire quando viene perso il focus. 
	 */
	public void focusLost(FocusEvent e) {
		
	}

/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "ModuloFlotta [La classe ModuloFlotta si comporta in maniera differente a seconda dell'oggetto String che viene passato al costruttore.]";
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
		ModuloFlotta other = (ModuloFlotta) obj;
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
		if (btnModifica == null) {
			if (other.btnModifica != null)
				return false;
		} else if (!btnModifica.equals(other.btnModifica))
			return false;
		if (comboBoxAlimentazione == null) {
			if (other.comboBoxAlimentazione != null)
				return false;
		} else if (!comboBoxAlimentazione.equals(other.comboBoxAlimentazione))
			return false;
		if (comboBoxBreveTermine == null) {
			if (other.comboBoxBreveTermine != null)
				return false;
		} else if (!comboBoxBreveTermine.equals(other.comboBoxBreveTermine))
			return false;
		if (comboBoxDisponibilita == null) {
			if (other.comboBoxDisponibilita != null)
				return false;
		} else if (!comboBoxDisponibilita.equals(other.comboBoxDisponibilita))
			return false;
		if (comboBoxLungoTermine == null) {
			if (other.comboBoxLungoTermine != null)
				return false;
		} else if (!comboBoxLungoTermine.equals(other.comboBoxLungoTermine))
			return false;
		if (comboBoxTipologia == null) {
			if (other.comboBoxTipologia != null)
				return false;
		} else if (!comboBoxTipologia.equals(other.comboBoxTipologia))
			return false;
		if (elencoVeicoli == null) {
			if (other.elencoVeicoli != null)
				return false;
		} else if (!elencoVeicoli.equals(other.elencoVeicoli))
			return false;
		if (frmtdtxtfldAlaggio == null) {
			if (other.frmtdtxtfldAlaggio != null)
				return false;
		} else if (!frmtdtxtfldAlaggio.equals(other.frmtdtxtfldAlaggio))
			return false;
		if (frmtdtxtfldAssicurazione == null) {
			if (other.frmtdtxtfldAssicurazione != null)
				return false;
		} else if (!frmtdtxtfldAssicurazione.equals(other.frmtdtxtfldAssicurazione))
			return false;
		if (frmtdtxtfldBollo == null) {
			if (other.frmtdtxtfldBollo != null)
				return false;
		} else if (!frmtdtxtfldBollo.equals(other.frmtdtxtfldBollo))
			return false;
		if (frmtdtxtfldImma == null) {
			if (other.frmtdtxtfldImma != null)
				return false;
		} else if (!frmtdtxtfldImma.equals(other.frmtdtxtfldImma))
			return false;
		if (frmtdtxtfldOrmeggio == null) {
			if (other.frmtdtxtfldOrmeggio != null)
				return false;
		} else if (!frmtdtxtfldOrmeggio.equals(other.frmtdtxtfldOrmeggio))
			return false;
		if (frmtdtxtfldTagliando == null) {
			if (other.frmtdtxtfldTagliando != null)
				return false;
		} else if (!frmtdtxtfldTagliando.equals(other.frmtdtxtfldTagliando))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (tblVeicoli == null) {
			if (other.tblVeicoli != null)
				return false;
		} else if (!tblVeicoli.equals(other.tblVeicoli))
			return false;
		if (txtDimensioni == null) {
			if (other.txtDimensioni != null)
				return false;
		} else if (!txtDimensioni.equals(other.txtDimensioni))
			return false;
		if (txtKm == null) {
			if (other.txtKm != null)
				return false;
		} else if (!txtKm.equals(other.txtKm))
			return false;
		if (txtMarca == null) {
			if (other.txtMarca != null)
				return false;
		} else if (!txtMarca.equals(other.txtMarca))
			return false;
		if (txtNome == null) {
			if (other.txtNome != null)
				return false;
		} else if (!txtNome.equals(other.txtNome))
			return false;
		if (txtTarga == null) {
			if (other.txtTarga != null)
				return false;
		} else if (!txtTarga.equals(other.txtTarga))
			return false;
		if (txtTargaCerca == null) {
			if (other.txtTargaCerca != null)
				return false;
		} else if (!txtTargaCerca.equals(other.txtTargaCerca))
			return false;
		if (veicolo == null) {
			if (other.veicolo != null)
				return false;
		} else if (!veicolo.equals(other.veicolo))
			return false;
		return true;
	}

	
}