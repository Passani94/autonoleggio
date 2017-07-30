package GUI.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import Entità.Veicolo;
import Utils.CostruisciTabella;
import Utils.TableColumnAdjuster;
import db.DBConnect;


public class ModuloFl extends JPanel implements ActionListener,FocusListener{

	private static final long serialVersionUID = 7526472295622516147L; 
	private JTable tblVeicoli;
	private JButton btnAggiungi;
	private JButton btnElimina;
	private JButton btnModificaV;
	private JButton btnCerca;
	private JScrollPane scroll = new JScrollPane(tblVeicoli);
	public JTextField txtTipologia;
	public JTextField txtNome;
	public JTextField txtDisp;
	public JTextField txtMarca;
	public JTextField txtAlimentazione;
	public JTextField txtKm;
	public JTextField txtTarga;
	public JTextField txtDimensioni;
	public JTextField txtTargaCerca;
	public JList<String> lstBreve;
	public JList<String> lstLungo;
	private final static String[] BREVE={"Autobus_12_Posti","Autobus_16_Posti","Autocarava_4_Posti","Autocarava_6_Posti","Autocarro_Cabinato","Autocarro_Furgonato","Automobile_Berlina","Automobile_Cabriolet","Automobile_Coupè","Automobile_Fuoristrada","Automobile_Limousine","Automobile_Multispazio","Automobile_SUV","Automobile_Utilitaria","Motociclo_Motocicletta","Motociclo_Scooter","Quadriciclo_Quad-Bike","Imbarcazione_Barca_a_motore","Imbarcazione_Catamarano","Natante_Gommone"};
	private final static String[] LUNGO={"Automobile_Berlina","Automobile_Cabriolet","Automobile_Coupè","Automobile_Fuoristrada","Automobile_Multispazio","Automobile_SUV","Automobile_Utilitaria"};
	public JFormattedTextField frmtdtxtfldImma;
	public JFormattedTextField frmtdtxtfldBollo;
	public JFormattedTextField frmtdtxtfldTagliando;
	public JFormattedTextField frmtdtxtfldAssicurazione;
	public JFormattedTextField frmtdtxtfldOrmeggio;
	public JFormattedTextField frmtdtxtfldAlaggio;
	public JComboBox <String> comboBoxTipologia;
	public JComboBox <String> comboBoxDisponibilita;
	public JComboBox <String> comboBoxAlimentazione;
	public JComboBox <String> comboBoxBreveTermine;
	public JComboBox <String> comboBoxLungoTermine;
	private Veicolo VL = new Veicolo();
	private DBConnect Veicoli = new DBConnect();
	
	/* Costruttore ModuloFl */
	
	public ModuloFl(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Veicoli"));
			
			try{Veicoli.exequery("SELECT * FROM veicolo","select");}
			catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco dei veicoli! ",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
			
			tblVeicoli = new JTable();
			tblVeicoli.setModel(new CostruisciTabella(Veicoli.rs).model);
			tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(tblVeicoli);
			tca.adjustColumns();
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblVeicoli);
			
			/* Crea il Layout per l'elenco dei Veicoli. */
			
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
		}
		else if (str == "Nuovo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Veicolo"));
			
			btnAggiungi = new JButton("Aggiungi Veicolo");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);	/* Action Listener per il bottone Aggiungi.*/
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTarga = new JLabel("Targa*");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblDisponibilita = new JLabel("Disponibilità (SI/NO) *");
			lblDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtDisp = new JTextField();
			txtDisp.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblMarca = new JLabel("Marca *");
			lblMarca.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblAlimentazione = new JLabel("Alimentazione *");
			lblAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAlimentazione = new JTextField();
			txtAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblKm = new JLabel("Km Effettuati *");
			lblKm.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblDimensioni = new JLabel("Dimensioni");
			lblDimensioni.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtDimensioni = new JTextField();
			txtDimensioni.setFont(new Font("Arial", Font.PLAIN, 12));
			
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
		
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			frmtdtxtfldImma = new JFormattedTextField(dateformat);
			frmtdtxtfldImma.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldImma.setColumns(10);
			frmtdtxtfldImma.setText("aaaa-mm-gg");
			frmtdtxtfldImma.addFocusListener(this);
			
			frmtdtxtfldBollo = new JFormattedTextField(dateformat);
			frmtdtxtfldBollo.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldBollo.setColumns(10);
			frmtdtxtfldBollo.setText("aaaa-mm-gg");
			frmtdtxtfldBollo.addFocusListener(this);
			
			frmtdtxtfldTagliando = new JFormattedTextField(dateformat);
			frmtdtxtfldTagliando.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldTagliando.setColumns(10);
			frmtdtxtfldTagliando.setText("aaaa-mm-gg");
			frmtdtxtfldTagliando.addFocusListener(this);
			
			frmtdtxtfldAssicurazione = new JFormattedTextField(dateformat);
			frmtdtxtfldAssicurazione.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAssicurazione.setColumns(10);
			frmtdtxtfldAssicurazione.setText("aaaa-mm-gg");
			frmtdtxtfldAssicurazione.addFocusListener(this);
			
			frmtdtxtfldOrmeggio = new JFormattedTextField(dateformat);
			frmtdtxtfldOrmeggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldOrmeggio.setColumns(10);
			frmtdtxtfldOrmeggio.setText("aaaa-mm-gg");
			frmtdtxtfldOrmeggio.addFocusListener(this);
			
			frmtdtxtfldAlaggio = new JFormattedTextField(dateformat);
			frmtdtxtfldAlaggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAlaggio.setColumns(10);
			frmtdtxtfldAlaggio.setText("aaaa-mm-gg");
			frmtdtxtfldAlaggio.addFocusListener(this);
			
			JLabel lblBreve = new JLabel("Costo Breve Termine *");
			lblBreve.setFont(new Font("Arial", Font.BOLD, 14));
		
		    final DefaultListModel<String> modelB = new DefaultListModel<String>();
		    for (int i = 0, n = BREVE.length; i < n; i++) {
		      modelB.addElement(BREVE[i]);
		    }
			lstBreve = new JList<String>(modelB);
			lstBreve.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lstBreve.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			lstBreve.setVisibleRowCount(-1);
			
			new JScrollPane(lstBreve);
			
			JLabel lblLungo = new JLabel("Costo Lungo Termine");
			lblLungo.setFont(new Font("Arial", Font.BOLD, 14));
			
		    final DefaultListModel<String> modelL = new DefaultListModel<String>();
		    for (int i = 0, n = LUNGO.length; i < n; i++) {
		    	modelL.addElement(LUNGO[i]);
		    }
			lstLungo = new JList<String>(modelL);
			lstLungo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lstLungo.setLayoutOrientation(JList.VERTICAL);
			lstLungo.setVisibleRowCount(-1);
			
			new JScrollPane(lstLungo);
			
			/* Crea il Layout per un nuovo Veicolo. */
			
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setBackground(Color.WHITE);
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setToolTipText("Seleziona una tipologia.");
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Autobus_12_Posti", "Autobus_16_Posti", "Autocaravan_4_Posti", "Autocaravan_6_Posti", "Autocarro_Cabinato", "Autocarro_Furgonato", "Barca_Motore", "Berlina", "Cabriolet", "Catamarano", "Coup\u00E8", "Fuoristrada", "Gommone", "Limousine", "Motocicletta", "Multispazio", "Quad_BIke", "Scooter", "SUV", "Utilitaria"}));
			
			comboBoxDisponibilita = new JComboBox<>();
			comboBoxDisponibilita.setBackground(Color.WHITE);
			comboBoxDisponibilita.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxDisponibilita.setModel(new DefaultComboBoxModel<>(new String[] {"", "SI", "NO"}));
			comboBoxDisponibilita.setToolTipText("(SI/NO)");
			
			comboBoxAlimentazione = new JComboBox<>();
			comboBoxAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxAlimentazione.setToolTipText("Seleziona l'alimentazione del veicolo.\r\n");
			comboBoxAlimentazione.setModel(new DefaultComboBoxModel<>(new String[] {"", "Benzina", "Diesel", "Metano", "GPL"}));
			comboBoxAlimentazione.setBackground(Color.WHITE);
			
			comboBoxBreveTermine = new JComboBox<>();
			comboBoxBreveTermine.setToolTipText("Seleziona un costo a breve termine.");
			comboBoxBreveTermine.setModel(new DefaultComboBoxModel<>(new String[] {"", "Autobus_12_Posti", "Autobus_16_Posti", "Autocaravan_4_Posti", "Autocaravan_6_Posti", "Autocarro_Cabinato", "Autocarro_Furgonato", "Automobile_Berlina", "Automobile_Cabriolet", "Automobile_Coup\u00E8", "Automobile_Fuoristrada", "Automobile_Limousine", "Automobile_Multispazio", "Automobile_SUV", "Automobile_Utilitaria", "Imbarcazione_Barca_Motore", "Imbarcazione_Catamarano", "Motociclo_Motocicletta\t", "Motociclo_Scooter", "Natante_Gommone", "Quadriciclo_Quad_Bike"}));
			comboBoxBreveTermine.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxBreveTermine.setBackground(Color.WHITE);
			
			comboBoxLungoTermine = new JComboBox<>();
			comboBoxLungoTermine.setModel(new DefaultComboBoxModel<>(new String[] {"", "Automobile_Berlina", "Automobile_Cabriolet", "Automobile_Coup\u00E8", "Automobile_Fuoristrada", "Automobile_Multispazio", "Automobile_SUV", "Automobile_Utilitaria"}));
			comboBoxLungoTermine.setToolTipText("Seleziona un costo a lungo termine");
			comboBoxLungoTermine.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxLungoTermine.setBackground(Color.WHITE);
			
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
										.addComponent(lblBollo)
										.addComponent(lblMarca)
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTagliando)
										.addComponent(lblTarga, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDisponibilita)
										.addComponent(lblAlimentazione, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBreve, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLungo, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblImma))
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
										.addComponent(comboBoxDisponibilita, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtNome)
										.addComponent(comboBoxTipologia, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtKm, Alignment.TRAILING)
										.addComponent(txtDimensioni, Alignment.TRAILING)
										.addComponent(txtTarga, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(160)
									.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(154, Short.MAX_VALUE))
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxDisponibilita, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDisponibilita, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldImma, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldBollo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldTagliando, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAssicurazione, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldOrmeggio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAlaggio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
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
							.addContainerGap())
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Elimina"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Veicolo"));
			
			btnElimina = new JButton("Elimina Veicolo");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	/* Action Listener per il bottone Elimina.*/
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTarga.setColumns(10);
			
			JLabel lblTarga = new JLabel("Targa Veicolo");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il Layout per un eliminare un Veicolo. */
			
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
							.addGap(116)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(169, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Modifica"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Modifica Veicolo"));
			
			btnCerca = new JButton("Cerca Veicolo");
			btnCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCerca.addActionListener(this);	/* Action Listener per il bottone Cerca.*/
			
			txtTargaCerca = new JTextField();
			txtTargaCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTargaCerca = new JLabel("Targa Veicolo da Modificare");
			lblTargaCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModificaV = new JButton("Modifica Veicolo");
			btnModificaV.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModificaV.addActionListener(this);	/* Action Listener per il bottone Modifica.*/
			
			txtTarga = new JTextField();
			txtTarga.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblTarga = new JLabel("Targa *");
			lblTarga.setFont(new Font("Arial", Font.BOLD, 14));
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblDisponibilita = new JLabel("Disponibilità *");
			lblDisponibilita.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtDisp = new JTextField();
			txtDisp.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblMarca = new JLabel("Marca *");
			lblMarca.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtMarca = new JTextField();
			txtMarca.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblAlimentazione = new JLabel("Alimentazione *");
			lblAlimentazione.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAlimentazione = new JTextField();
			txtAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblKm = new JLabel("Km Effettuati *");
			lblKm.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtKm = new JTextField();
			txtKm.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblDimensioni = new JLabel("Dimensioni");
			lblDimensioni.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtDimensioni = new JTextField();
			txtDimensioni.setFont(new Font("Arial", Font.PLAIN, 12));
			
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
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Autobus_12_Posti", "Autobus_16_Posti", "Autocaravan_4_Posti", "Autocaravan_6_Posti", "Autocarro_Cabinato", "Autocarro_Furgonato", "Barca_Motore", "Berlina", "Cabriolet", "Catamarano", "Coup\u00E8", "Fuoristrada", "Gommone", "Limousine", "Motocicletta", "Multispazio", "Quad_BIke", "Scooter", "SUV", "Utilitaria"}));
			
			comboBoxDisponibilita = new JComboBox<>();
			comboBoxDisponibilita.setBackground(Color.WHITE);
			comboBoxDisponibilita.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxDisponibilita.setModel(new DefaultComboBoxModel<>(new String[] {"", "SI", "NO"}));
			comboBoxDisponibilita.setToolTipText("(SI/NO)");
			
			comboBoxAlimentazione = new JComboBox<>();
			comboBoxAlimentazione.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxAlimentazione.setToolTipText("Seleziona l'alimentazione del veicolo.\r\n");
			comboBoxAlimentazione.setModel(new DefaultComboBoxModel<>(new String[] {"", "Benzina", "Diesel", "Metano", "GPL"}));
			comboBoxAlimentazione.setBackground(Color.WHITE);
			
			frmtdtxtfldImma = new JFormattedTextField(dateformat);
			frmtdtxtfldImma.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldImma.setColumns(10);
			frmtdtxtfldImma.addFocusListener(this);
			
			frmtdtxtfldBollo = new JFormattedTextField(dateformat);
			frmtdtxtfldBollo.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldBollo.setColumns(10);
			frmtdtxtfldBollo.setText("aaaa-mm-gg");
			frmtdtxtfldBollo.addFocusListener(this);
			
			frmtdtxtfldTagliando = new JFormattedTextField(dateformat);
			frmtdtxtfldTagliando.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldTagliando.setColumns(10);
			frmtdtxtfldTagliando.setText("aaaa-mm-gg");
			frmtdtxtfldTagliando.addFocusListener(this);
			
			frmtdtxtfldAssicurazione = new JFormattedTextField(dateformat);
			frmtdtxtfldAssicurazione.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAssicurazione.setColumns(10);
			frmtdtxtfldAssicurazione.setText("aaaa-mm-gg");
			frmtdtxtfldAssicurazione.addFocusListener(this);
			
			frmtdtxtfldOrmeggio = new JFormattedTextField(dateformat);
			frmtdtxtfldOrmeggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldOrmeggio.setColumns(10);
			frmtdtxtfldOrmeggio.setText("aaaa-mm-gg");
			frmtdtxtfldOrmeggio.addFocusListener(this);
			
			frmtdtxtfldAlaggio = new JFormattedTextField(dateformat);
			frmtdtxtfldAlaggio.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldAlaggio.setColumns(10);
			frmtdtxtfldAlaggio.setText("aaaa-mm-gg");
			frmtdtxtfldAlaggio.addFocusListener(this);
			
			txtTipologia.setEditable(false);
			txtNome.setEditable(false);
			txtDisp.setEditable(false);
			txtMarca.setEditable(false);
			txtAlimentazione.setEditable(false);
			txtKm.setEditable(false);
			txtTarga.setEditable(false);
			txtDimensioni.setEditable(false);
			frmtdtxtfldImma.setEditable(false);
			frmtdtxtfldBollo.setEditable(false);
			frmtdtxtfldTagliando.setEditable(false);
			frmtdtxtfldAssicurazione.setEditable(false);
			frmtdtxtfldOrmeggio.setEditable(false);
			frmtdtxtfldAlaggio.setEditable(false);
			
			/* Crea il Layout per modificare un Veicolo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(164)
							.addComponent(btnCerca)
							.addContainerGap(235, Short.MAX_VALUE))
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
								.addComponent(lblDimensioni, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImma)
								.addComponent(lblBollo)
								.addComponent(lblTagliando)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblAssicurazione, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblKm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
								.addComponent(lblOrmeggio)
								.addComponent(lblAlaggio))
							.addGap(65)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(frmtdtxtfldAlaggio, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(frmtdtxtfldOrmeggio, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(frmtdtxtfldImma, 161, 161, Short.MAX_VALUE)
								.addComponent(txtDimensioni, 161, 161, Short.MAX_VALUE)
								.addComponent(txtKm, 161, 161, Short.MAX_VALUE)
								.addComponent(comboBoxAlimentazione, 0, 161, Short.MAX_VALUE)
								.addComponent(comboBoxDisponibilita, 0, 161, Short.MAX_VALUE)
								.addComponent(txtMarca, 161, 161, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtTargaCerca, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
									.addComponent(txtTarga, 161, 161, Short.MAX_VALUE))
								.addComponent(comboBoxTipologia, Alignment.TRAILING, 0, 161, Short.MAX_VALUE)
								.addComponent(txtNome, 161, 161, Short.MAX_VALUE)
								.addComponent(frmtdtxtfldBollo, 161, 161, Short.MAX_VALUE)
								.addComponent(frmtdtxtfldTagliando, 161, 161, Short.MAX_VALUE)
								.addComponent(frmtdtxtfldAssicurazione, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
							.addGap(359))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addComponent(btnModificaV)
							.addContainerGap(202, Short.MAX_VALUE))
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
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldImma, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImma, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldBollo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldTagliando, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAssicurazione, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldAssicurazione, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldOrmeggio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldAlaggio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addComponent(btnModificaV, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(40))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/

	public void actionPerformed(ActionEvent e){
		
		if (btnAggiungi == e.getSource()){
			VL.setValori(this,"aggiungi");
			VL.aggiungi(this);
		}
		else if(btnElimina == e.getSource()){
			VL.setTarga(this);
			VL.elimina(this);
		}
		else if(btnCerca == e.getSource()){
			VL.setTargaCerca(this);
			VL.cerca(this);
		}
		else if(btnModificaV == e.getSource()){
			VL.setValori(this,"modifica");
			VL.modifica(this);
		}
	}
	
	/* Definisce le azioni da eseguire quando si ha il focus sui campi per inserire le date. */
	
	public void focusGained(FocusEvent e){
		if(frmtdtxtfldImma == e.getSource()){frmtdtxtfldImma.setText("");}
		else if(frmtdtxtfldBollo == e.getSource()){frmtdtxtfldBollo.setText("");}
		else if(frmtdtxtfldTagliando == e.getSource()){frmtdtxtfldTagliando.setText("");}
		else if(frmtdtxtfldAssicurazione == e.getSource()){frmtdtxtfldAssicurazione.setText("");}
		else if(frmtdtxtfldOrmeggio == e.getSource()){frmtdtxtfldOrmeggio.setText("");}
		else if(frmtdtxtfldAlaggio == e.getSource()){frmtdtxtfldAlaggio.setText("");}
		if(frmtdtxtfldImma.getText().equals("") && !(frmtdtxtfldImma == e.getSource())){frmtdtxtfldImma.setText("aaaa-mm-gg");}
		if(frmtdtxtfldBollo.getText().equals("") && !(frmtdtxtfldBollo == e.getSource())){frmtdtxtfldBollo.setText("aaaa-mm-gg");}
		if(frmtdtxtfldTagliando.getText().equals("") && !(frmtdtxtfldTagliando == e.getSource())){frmtdtxtfldTagliando.setText("aaaa-mm-gg");}
		if(frmtdtxtfldAssicurazione.getText().equals("") && !(frmtdtxtfldAssicurazione == e.getSource())){frmtdtxtfldAssicurazione.setText("aaaa-mm-gg");}
		if(frmtdtxtfldOrmeggio.getText().equals("") && !(frmtdtxtfldOrmeggio == e.getSource())){frmtdtxtfldOrmeggio.setText("aaaa-mm-gg");}
		if(frmtdtxtfldAlaggio.getText().equals("") && !(frmtdtxtfldAlaggio == e.getSource())){frmtdtxtfldAlaggio.setText("aaaa-mm-gg");}
    }
	
	/* Definisce le azioni da eseguire quando si perde il focus sui campi per inserire le date. */
	
	public void focusLost(FocusEvent e) {
		if(frmtdtxtfldImma.getText().equals("")){frmtdtxtfldImma.setText("aaaa-mm-gg");}
		if(frmtdtxtfldBollo.getText().equals("")){frmtdtxtfldBollo.setText("aaaa-mm-gg");}
		if(frmtdtxtfldTagliando.getText().equals("")){frmtdtxtfldTagliando.setText("aaaa-mm-gg");}
		if(frmtdtxtfldAssicurazione.getText().equals("")){frmtdtxtfldAssicurazione.setText("aaaa-mm-gg");}
		if(frmtdtxtfldOrmeggio.getText().equals("")){frmtdtxtfldOrmeggio.setText("aaaa-mm-gg");}
		if(frmtdtxtfldAlaggio.getText().equals("")){frmtdtxtfldAlaggio.setText("aaaa-mm-gg");}
	}

}