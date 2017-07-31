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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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

import Entità.Contratto;
import Entità.Preventivo;
import Utils.ArrotondaNumero;
import Utils.CostruisciTabella;
import Utils.TableColumnAdjuster;
import db.DBConnect;


public class ModuloCt extends JPanel implements ActionListener, FocusListener{

	private static final long serialVersionUID = 1L;
	
	private JButton btnAggiungi;
	private JButton btnCalcola;
	private JButton btnFiltra;
	private JButton btnPassaAContratto;
	public JLabel lblPreventivo;
	public JTable tblNoleggi;
	private JScrollPane scroll = new JScrollPane(tblNoleggi);
	public JTextField txtTipologia;
	public JTextField txtVeicolo;
	public JTextField txtCliente;	
	public JTextField txtCosto;
	public JTextField txtAcconto;
	public JTextField txtCognome;
	public JTextField txtNome;
	public JTextField txtPatente;
	public JTextField txtRilasciatada;
	public JFormattedTextField frmtdtxtfldInizio;
	public JFormattedTextField frmtdtxtfldFine;
	public JFormattedTextField frmtdtxtfldValida;
	public JFormattedTextField frmtdtxtfldRilasciatail;
	
	private Contratto CT = new Contratto();
	private Preventivo PV = new Preventivo();
	private DBConnect Contratti = new DBConnect();

	
	/* Costruttore ModuloCt */
	
	public ModuloCt(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Passaggio") {
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Contratto di Noleggio"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			
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
												.addComponent(txtTipologia, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
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
										.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
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
			if (str == "Nuovo") {
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Contratto di Noleggio"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			
			JLabel lblTipologia = new JLabel("Tipologia *");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
			
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
												.addComponent(txtTipologia, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
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
										.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
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
		else if (str == "Preventivo") {
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
			btnCalcola.addActionListener(this);	/* Action Listener per il bottone Calcola.*/
		
			/* Crea il Layout per calcolare un Preventivo. */
			
			btnPassaAContratto = new JButton("Passa a contratto");
			btnPassaAContratto.setFont(new Font("Arial", Font.PLAIN, 12));
			btnPassaAContratto.addActionListener(this);
			
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
		else if (str == "Elenca") {
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
		else if (btnFiltra == e.getSource()) {
			CT.setValoriFiltra(this);
			CT.filtra(this);
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
		else if (frmtdtxtfldValida == e.getSource() && frmtdtxtfldValida.getText().equals("aaaa-mmm-gg")) {
			frmtdtxtfldValida.setText("");
		}
		else if (frmtdtxtfldRilasciatail == e.getSource() && frmtdtxtfldRilasciatail.getText().equals("aaaa-mmm-gg")){
			frmtdtxtfldRilasciatail.setText("");
		}
		
		if(frmtdtxtfldInizio.getText().equals("") && !(frmtdtxtfldInizio == e.getSource())){
			frmtdtxtfldInizio.setText("aaaa-mm-gg");
		}
		if(frmtdtxtfldFine.getText().equals("") && !(frmtdtxtfldFine == e.getSource())){
			frmtdtxtfldFine.setText("aaaa-mm-gg");
		}
		if(frmtdtxtfldValida.getText().equals("") && !(frmtdtxtfldValida == e.getSource())){
			frmtdtxtfldValida.setText("aaaa-mm-gg");
		}
		if(frmtdtxtfldRilasciatail.getText().equals("") && !(frmtdtxtfldRilasciatail == e.getSource())){
			frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
		}
    }
	
	/* Definisce le azioni da eseguire quando si perde il focus sui campi per inserire le date. */
	
	public void focusLost(FocusEvent e) {
		if (frmtdtxtfldInizio.getText().equals("")) {
			frmtdtxtfldInizio.setText("aaaa-mm-gg");
		}
		if (frmtdtxtfldFine.getText().equals("")) {
			frmtdtxtfldFine.setText("aaaa-mm-gg");
		}
		if (frmtdtxtfldValida.getText().equals("")) {
			frmtdtxtfldValida.setText("aaaa-mm-gg");
		}
		if (frmtdtxtfldRilasciatail.getText().equals("")) {
			frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
		}
		
	}
}
