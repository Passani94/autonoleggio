package gui.moduli;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import db.DBConnect;
import entita.Contratto;
import entita.Preventivo;
import utils.ArrotondaNumero;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;


public class ModuloContratto extends JPanel implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btnAggiungi;
	private JButton btnCalcola;
	private JButton btnFiltra;
	private JButton btnPassaAContratto;
	private JButton btnElimina;
	private JButton btnCerca;
	private JButton btnModificaC;
	public JLabel lblPreventivo;
	public JTable tblNoleggi;
	private JScrollPane scroll = new JScrollPane(tblNoleggi);
	public JTextField txtVeicolo;
	public JTextField txtCliente;	
	public JTextField txtCosto;
	public JTextField txtAcconto;
	public JTextField txtCognome;
	public JTextField txtNome;
	public JTextField txtPatente;
	public JTextField txtContrattoCerca;
	public JComboBox <String> comboBoxTipologia;
	public JTextField txtRilasciatada;
	public JTextField txtCodice;
	public JFormattedTextField frmtdtxtfldInizio;
	public JFormattedTextField frmtdtxtfldFine;
	public JFormattedTextField frmtdtxtfldValida;
	public JFormattedTextField frmtdtxtfldRilasciatail;
	
	private Contratto CT = new Contratto();
	private Preventivo PV = new Preventivo();
	private DBConnect Contratti = new DBConnect();

	
	/* Costruttore ModuloCt */
	
	public ModuloContratto(String str){
		set(str);
	}

	public void set(String str){
		if (str.equals("Nuovo")) {
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Contratto di Noleggio"));
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
				
			comboBoxTipologia = new JComboBox<>();
			comboBoxTipologia.setBackground(Color.WHITE);
			comboBoxTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTipologia.setModel(new DefaultComboBoxModel<>(new String[] {"", "Breve", "Lungo"}));
			comboBoxTipologia.setToolTipText("(Breve/Lungo)");
			comboBoxTipologia.addFocusListener(this);
						
			JLabel lblVeicolo = new JLabel("Veicolo *");
			lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			txtVeicolo.addFocusListener(this);
			
			JLabel lblCliente = new JLabel("Cliente *");
			lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCliente = new JTextField();
			txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCliente.addFocusListener(this);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			JLabel lblDataInizio = new JLabel("Data Inizio *");
			lblDataInizio.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldInizio = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio.setText("aaaa-mm-gg");
			frmtdtxtfldInizio.addFocusListener(this);
			
			JLabel lblDataFine = new JLabel("Data Fine *");
			lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("aaaa-mm-gg");
			frmtdtxtfldFine.addFocusListener(this);
			
			JLabel lblCosto = new JLabel("Costo Totale *");
			lblCosto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCosto = new JTextField();
			txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCosto.addFocusListener(this);
		
			JLabel lblAcconto = new JLabel("Acconto");
			lblAcconto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtAcconto = new JTextField();
			txtAcconto.setFont(new Font("Arial", Font.PLAIN, 12));
			txtAcconto.addFocusListener(this);
			
			JLabel lblCognome = new JLabel("Cognome *");
			lblCognome.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtCognome = new JTextField();
			txtCognome.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCognome.addFocusListener(this);
		
			JLabel lblNome = new JLabel("Nome *");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			txtNome.addFocusListener(this);
			
			JLabel lblPatente = new JLabel("Numero Patente *");
			lblPatente.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtPatente = new JTextField();
			txtPatente.setFont(new Font("Arial", Font.PLAIN, 12));
			txtPatente.addFocusListener(this);
			
			JLabel lblValida = new JLabel("Valida fino a");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldValida = new JFormattedTextField(dateformat);
			frmtdtxtfldValida.setText("aaaa-mm-gg");
			frmtdtxtfldValida.addFocusListener(this);
			
			JLabel lblRilasciataDa = new JLabel("Rilasciata da");
			lblRilasciataDa.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
			txtRilasciatada.addFocusListener(this);
			
			JLabel lblRilasciataIl = new JLabel("Rilasciata il");
			lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldRilasciatail = new JFormattedTextField(dateformat);
			frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
			frmtdtxtfldRilasciatail.addFocusListener(this);
						
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			btnAggiungi.addFocusListener(this);
			
			/* Crea il Layout per un nuovo Contratto. */
			
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
											.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
											.addGap(15)))
									.addGap(178))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
									.addGap(51)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtPatente, Alignment.TRAILING)
												.addComponent(txtAcconto, Alignment.TRAILING)
												.addComponent(txtCosto, Alignment.TRAILING)
												.addComponent(frmtdtxtfldFine, Alignment.TRAILING)
												.addComponent(frmtdtxtfldInizio, Alignment.TRAILING)
												.addComponent(txtCliente)
												.addComponent(txtVeicolo)
												.addComponent(comboBoxTipologia, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
												.addComponent(txtCognome, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
												.addComponent(txtNome, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(frmtdtxtfldValida, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
												.addComponent(txtRilasciatada, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
										.addComponent(frmtdtxtfldRilasciatail, 163, 163, 163))
									.addContainerGap())))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(frmtdtxtfldInizio, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(19)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(274)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtAcconto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(36, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str.equals("Elimina")){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elimina Contratto"));
			
			btnElimina = new JButton("Elimina Contratto");
			btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
			btnElimina.addActionListener(this);	/* Action Listener per il bottone Elimina.*/
			
			txtCodice = new JTextField();
			txtCodice.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCodice.setColumns(10);
			
			JLabel lblCod = new JLabel("Codice Contratto");
			lblCod.setFont(new Font("Arial", Font.BOLD, 14));
			
			/* Crea il Layout per un eliminare un Contratto. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(lblCod, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
							.addComponent(txtCodice, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(txtCodice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(50)
							.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(169, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		
		else if (str.equals("Passaggio")) {
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
				comboBoxTipologia.addFocusListener(this);
				if( Preventivo.getGiorniNoleggio() <=181 ) {
					comboBoxTipologia.setSelectedIndex(1);
				}else {
					comboBoxTipologia.setSelectedIndex(2);
				}
				
				JLabel lblVeicolo = new JLabel("Veicolo *");
				lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
				
				txtVeicolo = new JTextField();
				txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
				String veicoloContratto = Preventivo.getVarVeicolo();
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
				frmtdtxtfldInizio.addFocusListener(this);
				
				JLabel lblDataFine = new JLabel("Data Fine *");
				lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
				
				frmtdtxtfldFine = new JFormattedTextField(dateformat);
				frmtdtxtfldFine.setText(Preventivo.getDataFine());
				frmtdtxtfldFine.addFocusListener(this);
				
				JLabel lblCosto = new JLabel("Costo Totale *");
				lblCosto.setFont(new Font("Arial", Font.BOLD, 14));
			
				txtCosto = new JTextField();
				txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
				double prezzoContratto = (Preventivo.getVarTotale());
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
				
				JLabel lblValida = new JLabel("Valida fino a");
				lblValida.setFont(new Font("Arial", Font.BOLD, 14));
				
				frmtdtxtfldValida = new JFormattedTextField(dateformat);
				frmtdtxtfldValida.setText("aaaa-mm-gg");
				frmtdtxtfldValida.addFocusListener(this);
				
				JLabel lblRilasciataDa = new JLabel("Rilasciata da");
				lblRilasciataDa.setFont(new Font("Arial", Font.BOLD, 14));
			
				txtRilasciatada = new JTextField();
				txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
				
				JLabel lblRilasciataIl = new JLabel("Rilasciata il");
				lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
			
				frmtdtxtfldRilasciatail = new JFormattedTextField(dateformat);
				frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
				frmtdtxtfldRilasciatail.addFocusListener(this);
							
				JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
				lblMex.setForeground(Color.RED);
				lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
				
				/* Crea il Layout per un nuovo Contratto. */
				
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
												.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
												.addGap(15)))
										.addGap(178))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
										.addGap(51)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
													.addComponent(txtPatente, Alignment.TRAILING)
													.addComponent(txtAcconto, Alignment.TRAILING)
													.addComponent(txtCosto, Alignment.TRAILING)
													.addComponent(frmtdtxtfldFine, Alignment.TRAILING)
													.addComponent(frmtdtxtfldInizio, Alignment.TRAILING)
													.addComponent(txtCliente)
													.addComponent(txtVeicolo)
													.addComponent(comboBoxTipologia, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
													.addComponent(txtCognome, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
													.addComponent(txtNome, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
													.addComponent(frmtdtxtfldValida, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
													.addComponent(txtRilasciatada, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
											.addComponent(frmtdtxtfldRilasciatail, 163, 163, 163))
										.addContainerGap())))
					);
					gl_contentPane.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(29)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(comboBoxTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(frmtdtxtfldInizio, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
										.addGap(19)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(274)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(txtAcconto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblAcconto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
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
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(23)
								.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(36, Short.MAX_VALUE))
					);
				this.setLayout(gl_contentPane);
				this.revalidate();
		}
		else if (str.equals("Preventivo")) {
				this.removeAll();
				this.setBorder(BorderFactory.createTitledBorder("Calcola Preventivo Contratto di Noleggio"));
			
				JLabel lblVeicolo = new JLabel("Veicolo da Noleggiare");
				lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
			
				txtVeicolo = new JTextField();
				txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
				txtVeicolo.setColumns(10);
				txtVeicolo.addFocusListener(this);
			
				DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
				JLabel lblInizio = new JLabel("Data Inizio Noleggio");
				lblInizio.setFont(new Font("Arial", Font.BOLD, 14));
			
				frmtdtxtfldInizio = new JFormattedTextField(dateformat);
				frmtdtxtfldInizio.setText("aaaa-mm-gg");
				frmtdtxtfldInizio.addFocusListener(this);
		
				JLabel lblFine = new JLabel("Data Fine Noleggio");
				lblFine.setFont(new Font("Arial", Font.BOLD, 14));
			
				frmtdtxtfldFine = new JFormattedTextField(dateformat);
				frmtdtxtfldFine.setText("aaaa-mm-gg");
				frmtdtxtfldFine.addFocusListener(this);
			
				lblPreventivo = new JLabel("");
				lblPreventivo.setFont(new Font("Arial", Font.BOLD, 14));
				lblPreventivo.setHorizontalAlignment(SwingConstants.CENTER);
			
				btnCalcola = new JButton("Calcola Preventivo");
				btnCalcola.setFont(new Font("Arial", Font.PLAIN, 12));
				btnCalcola.addActionListener(this);
				btnCalcola.addFocusListener(this);
			
				btnPassaAContratto = new JButton("Passa a contratto");
				btnPassaAContratto.setFont(new Font("Arial", Font.PLAIN, 12));
				btnPassaAContratto.addActionListener(this);
				btnPassaAContratto.addFocusListener(this);
			
				/* Crea il Layout per calcolare un Preventivo. */
			
				GroupLayout gl_contentPane = new GroupLayout(this);
				gl_contentPane.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(30)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(25)
												.addComponent(btnCalcola)
												.addGap(57)
												.addComponent(btnPassaAContratto)
												.addContainerGap())
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblInizio, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
																.addComponent(frmtdtxtfldInizio)
																.addComponent(txtVeicolo, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
												.addGap(62))))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(101)
								.addComponent(lblPreventivo, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(76, Short.MAX_VALUE))
						);
				gl_contentPane.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(37)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblVeicolo)
										.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldInizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(27)
								.addComponent(lblPreventivo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnPassaAContratto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCalcola, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
								.addContainerGap(27, Short.MAX_VALUE))
						);
				this.setLayout(gl_contentPane);
				this.revalidate();
		}
		else if (str.equals("Modifica")) {
			
			this.removeAll();
			
			this.setBorder(BorderFactory.createTitledBorder("Modifica Contratto di Noleggio"));
			
			btnCerca = new JButton("Cerca Contratto");
			btnCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCerca.addActionListener(this);	/* Action Listener per il bottone Cerca.*/
			
			txtContrattoCerca = new JTextField();
			txtContrattoCerca.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblContrattoCerca = new JLabel("Codice contratto da Modificare ");
			lblContrattoCerca.setFont(new Font("Arial", Font.BOLD, 14));
		
			btnModificaC = new JButton("Modifica Contratto");
			btnModificaC.setFont(new Font("Arial", Font.PLAIN, 12));
			btnModificaC.addActionListener(this);	/* Action Listener per il bottone Modifica.*/
			
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
		
			JLabel lblDataFine = new JLabel("Data Fine *\r\n");
			lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldFine = new JFormattedTextField();
			frmtdtxtfldFine.setFont(new Font("Arial", Font.PLAIN, 12));
		
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
			frmtdtxtfldValida.setEditable(false);
			
			JLabel lblValida = new JLabel("Valida fino a");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
			txtRilasciatada.setEditable(false);
			
			JLabel lblRilasciatada = new JLabel("Rilasciata da");
			lblRilasciatada.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldRilasciatail = new JFormattedTextField();
			frmtdtxtfldRilasciatail.setFont(new Font("Arial", Font.PLAIN, 12));
			frmtdtxtfldRilasciatail.setEditable(false);
			
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
			
			
			/* Crea il Layout per modificare un Cliente. */
			
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
										.addComponent(txtContrattoCerca)
										.addComponent(comboBoxTipologia, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(frmtdtxtfldRilasciatail)
										.addComponent(txtRilasciatada)
										.addComponent(frmtdtxtfldValida, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
										.addComponent(txtPatente, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(txtCognome, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(txtAcconto, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(txtCosto, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(frmtdtxtfldFine, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(frmtdtxtfldInizio, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(txtCliente, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
										.addComponent(txtVeicolo)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(150)
									.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(150)
									.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(279, Short.MAX_VALUE))
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldInizio, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
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
								.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRilasciatada, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnModificaC, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(41, Short.MAX_VALUE))
				);
			
			this.setLayout(gl_contentPane);
			this.revalidate();
			
		}
		else if (str.equals("Elenca")) {
				this.removeAll();
				this.setBorder(BorderFactory.createTitledBorder("Elenco Contratti"));

				try {
					Contratti.exequery("SELECT * FROM noleggio","select");
				}
				catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco noleggi!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				}
				
				tblNoleggi = new JTable();
				tblNoleggi.setModel(new CostruisciTabella(Contratti.rs).model);
				tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				TableColumnAdjuster tca = new TableColumnAdjuster(tblNoleggi);
				tca.adjustColumns();
				
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setViewportView(tblNoleggi);
				
				JLabel lblCliente = new JLabel("Cliente da filtrare");
				lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
				
				txtCliente = new JTextField();
				txtCliente.setColumns(10);
				
				JLabel lblVeicoloDaFiltrare = new JLabel("Veicolo da filtrare");
				lblVeicoloDaFiltrare.setFont(new Font("Arial", Font.BOLD, 13));
				
				txtVeicolo = new JTextField();
				txtVeicolo.setColumns(10);
				
				btnFiltra = new JButton("Filtra Contratti");
				btnFiltra.addActionListener(this); 	/* Action Listener per il bottone Filtra.*/
				
				/* Crea il Layout per l'elenco dei Contratti. */
				
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
		
	/* Definisce le azioni da eseguire in base al pulsante cliccato.*/
	
	public void actionPerformed(ActionEvent e) {
		if (btnAggiungi == e.getSource()) {
			CT.setValori(this);
			CT.aggiungi(this);
		}
		else if (btnCalcola == e.getSource()) {
			PV.setValori(this);
			try {
				PV.calcola(this);
			}catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Impossibile calcolare il preventivo!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
				
			}
		}
		else if (btnPassaAContratto == e.getSource()) {
			if (Preventivo.getVarTotale()!=0) {
				this.set("Passaggio");
			}
			else {
				JOptionPane.showMessageDialog(null, "Si deve prima calcolare il preventivo!",
						"INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(btnElimina == e.getSource()){
			if (CT.setCodice(this)) {
				CT.elimina(this);
				}
		}		
		else if (btnFiltra == e.getSource()) {
			CT.setValoriFiltra(this);
			CT.filtra(this);
		}
		else if(btnModificaC == e.getSource()){
			CT.setCodiceModifica(this);
			CT.setValori(this);
			CT.modifica(this);
		}
		else if(btnCerca == e.getSource()){
			if (CT.setCodiceCerca(this)) {
				CT.cerca(this);
			}
		}
	}
	
	/* Definisce le azioni da eseguire quando si ha il focus sui campi da inserire. */
	
	public void focusGained(FocusEvent e) {
		if (frmtdtxtfldInizio == e.getSource() && frmtdtxtfldInizio.getText().equals("aaaa-mm-gg")) { 
			frmtdtxtfldInizio.setText("");
		}
		else if (frmtdtxtfldFine == e.getSource() && frmtdtxtfldFine.getText().equals("aaaa-mm-gg")) {
			frmtdtxtfldFine.setText("");
		}
		else if (frmtdtxtfldValida == e.getSource() && frmtdtxtfldValida.getText().equals("aaaa-mm-gg")) {
			frmtdtxtfldValida.setText("");
		}
		else if (frmtdtxtfldRilasciatail == e.getSource() && frmtdtxtfldRilasciatail.getText().equals("aaaa-mm-gg")) {
			frmtdtxtfldRilasciatail.setText("");
		}
		
		if (!(frmtdtxtfldInizio == e.getSource()) && frmtdtxtfldInizio.getText().equals("")) { 
			frmtdtxtfldInizio.setText("aaaa-mm-gg");
		}
		else if (!(frmtdtxtfldFine == e.getSource()) && frmtdtxtfldFine.getText().equals("")) {
			frmtdtxtfldFine.setText("aaaa-mm-gg");
		}
		else if (!(frmtdtxtfldValida == e.getSource()) && frmtdtxtfldValida.getText().equals("")) {
			frmtdtxtfldValida.setText("aaaa-mm-gg");
		}
		else if (!(frmtdtxtfldRilasciatail == e.getSource()) && frmtdtxtfldRilasciatail.getText().equals("")) {
			frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
		}
    }
	
	/*Definisce le azioni da eseguire quando si perde il focus sui campi per inserire le date.*/
	
	public void focusLost(FocusEvent e) {
		
	}
}
