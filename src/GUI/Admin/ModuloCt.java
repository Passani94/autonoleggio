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
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import Entità.Contratto;
import Entità.Preventivo;
import Utils.CostruisciTabella;
import Utils.TableColumnAdjuster;
import db.DBConnect;


public class ModuloCt extends JPanel implements ActionListener,FocusListener{

	public JLabel lblPreventivo;
	public JTable tblNoleggi;
	private JButton btnFiltra;
	private JButton btnAggiungi;
	private JButton btnCalcola;
	private JScrollPane scroll = new JScrollPane(tblNoleggi);
	public JTextField txtRilasciatada;
	public JTextField txtCliente;
	public JTextField txtVeicolo;
	public JTextField txtTipologia;
	public JTextField txtCosto;
	public JTextField txtPagato;
	public JTextField txtNome;
	public JTextField txtCognome;
	public JTextField txtPatente;
	public JFormattedTextField frmtdtxtfldFine;
	public JFormattedTextField frmtdtxtfldRilasciatail;
	public JFormattedTextField frmtdtxtfldValida;
	public JFormattedTextField frmtdtxtfldInizio;
	private Contratto CT = new Contratto();
	private Preventivo PV = new Preventivo();
	private DBConnect Contratti = new DBConnect();
	
	/* Costruttore ModuloCt */
	
	public ModuloCt(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Contratti"));

			try{Contratti.exequery("SELECT * FROM noleggio","select");}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco noleggi!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);}
			
			tblNoleggi = new JTable();
			tblNoleggi.setModel(new CostruisciTabella(Contratti.rs).model);
			tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(tblNoleggi);
			tca.adjustColumns();
			
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setViewportView(tblNoleggi);
			
			btnFiltra = new JButton("Filtra Contratti");
			btnFiltra.addActionListener(this); 	/* Action Listener per il bottone Filtra.*/
			
			JLabel lblCliente = new JLabel("Cliente da Filtrare");
			lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
			
			txtCliente = new JTextField();
			txtCliente.setColumns(10);
			
			JLabel lblVeicoloDaFiltrare = new JLabel("Veicolo da Filtrare");
			lblVeicoloDaFiltrare.setFont(new Font("Arial", Font.BOLD, 13));
			
			txtVeicolo = new JTextField();
			txtVeicolo.setColumns(10);
			
			/* Crea il Layout per l'elenco dei Contratti. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(51)
									.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
									.addGap(69))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtCliente)
										.addComponent(lblCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblVeicoloDaFiltrare, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtVeicolo, Alignment.TRAILING))
									.addGap(90)
									.addComponent(btnFiltra, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
							.addGap(18, 19, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente)
								.addComponent(lblVeicoloDaFiltrare, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFiltra)
								.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Nuovo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Contratto di Noleggio"));
			
			btnAggiungi = new JButton("Aggiungi");
			btnAggiungi.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAggiungi.addActionListener(this);
			
			JLabel lblTipologia = new JLabel("Tipologia*");
			lblTipologia.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtTipologia = new JTextField();
			txtTipologia.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblDataInizio = new JLabel("Data Inizio*");
			lblDataInizio.setFont(new Font("Arial", Font.BOLD, 14));

			JLabel lblDataFine = new JLabel("Data Fine*");
			lblDataFine.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblCosto = new JLabel("Costo Totale*");
			lblCosto.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCosto = new JTextField();
			txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblPagato = new JLabel("Gi\u00E0 Pagato");
			lblPagato.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtPagato = new JTextField();
			txtPagato.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblNome = new JLabel("Nome*");
			lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtNome = new JTextField();
			txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblCognome = new JLabel("Cognome*");
			lblCognome.setFont(new Font("Arial", Font.BOLD, 14));
			
			txtCognome = new JTextField();
			txtCognome.setFont(new Font("Arial", Font.PLAIN, 12));
			
			JLabel lblPatente = new JLabel("Numero Patente*");
			lblPatente.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtPatente = new JTextField();
			txtPatente.setFont(new Font("Arial", Font.PLAIN, 12));
						
			JLabel lblMex = new JLabel("Inserire tutti i campi con l'asterisco!");
			lblMex.setForeground(Color.RED);
			lblMex.setFont(new Font("Arial", Font.PLAIN, 14));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			frmtdtxtfldInizio = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio.setText("aaaa-mm-gg");
			frmtdtxtfldInizio.addFocusListener(this);
			
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("aaaa-mm-gg");
			frmtdtxtfldFine.addFocusListener(this);
			
			JLabel lblRilasciataDa = new JLabel("Rilasciata da");
			lblRilasciataDa.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblRilasciataIl = new JLabel("Rilasciata il");
			lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblValida = new JLabel("Valida Fino a");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldRilasciatail = new JFormattedTextField(dateformat);
			frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");
			frmtdtxtfldRilasciatail.addFocusListener(this);
			
			frmtdtxtfldValida = new JFormattedTextField(dateformat);
			frmtdtxtfldValida.setText("aaaa-mm-gg");
			frmtdtxtfldValida.addFocusListener(this);
			
			JLabel lblCliente = new JLabel("Cliente*");
			lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblVeicolo = new JLabel("Veicolo*");
			lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtCliente = new JTextField();
			txtCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			
			/* Crea il Layout per un nuovo Contratto. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblMex, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(190)
											.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
											.addGap(15)))
									.addGap(175))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPagato, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
									.addGap(81)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtVeicolo)
										.addComponent(txtCliente)
										.addComponent(frmtdtxtfldValida)
										.addComponent(frmtdtxtfldRilasciatail)
										.addComponent(txtRilasciatada)
										.addComponent(frmtdtxtfldInizio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(frmtdtxtfldFine, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
										.addComponent(txtCognome, Alignment.TRAILING)
										.addComponent(txtPatente, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
										.addComponent(txtNome, Alignment.TRAILING)
										.addComponent(txtPagato, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(txtCosto, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(txtTipologia, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
									.addContainerGap())))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldInizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPagato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPagato, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldRilasciatail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldValida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}
		else if (str == "Preventivo"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Calcola Preventivo Contratto di Noleggio"));
			
			btnCalcola = new JButton("Calcola Preventivo");
			btnCalcola.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCalcola.addActionListener(this);	/* Action Listener per il bottone Calcola.*/
			
			txtVeicolo = new JTextField();
			txtVeicolo.setFont(new Font("Arial", Font.PLAIN, 12));
			txtVeicolo.setColumns(10);
			
			JLabel lblVeicolo = new JLabel("Veicolo Da Noleggiare");
			lblVeicolo.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblInizio = new JLabel("Data Inizio Noleggio");
			lblInizio.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblFine = new JLabel("Data Fine Noleggio");
			lblFine.setFont(new Font("Arial", Font.BOLD, 14));
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			
			frmtdtxtfldInizio = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio.setText("aaaa-mm-gg");
			frmtdtxtfldInizio.addFocusListener(this);
			
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("aaaa-mm-gg");
			frmtdtxtfldFine.addFocusListener(this);
			
			lblPreventivo = new JLabel("");
			lblPreventivo.setFont(new Font("Arial", Font.BOLD, 14));
			lblPreventivo.setHorizontalAlignment(SwingConstants.CENTER);
		
			/* Crea il Layout per calcolare un Preventivo. */
			
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInizio, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(frmtdtxtfldInizio)
									.addComponent(txtVeicolo, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
							.addGap(62))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(163)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblPreventivo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCalcola, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap(194, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(btnCalcola, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(50, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		}		
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnFiltra == e.getSource()){
			CT.setValoriFiltra(this);
			CT.filtra(this);
		}
		else if(btnAggiungi == e.getSource()){
			CT.setValori(this);
			CT.aggiungi(this);
		}
		else if(btnCalcola == e.getSource()){
			PV.setValori(this);
			PV.calcola(this);
		}
	}
	
	/* Definisce le azioni da eseguire quando si ha il focus sui campi per inserire le date. */
	
	public void focusGained(FocusEvent e){
		if(frmtdtxtfldInizio == e.getSource()){frmtdtxtfldInizio.setText("");}
		else if(frmtdtxtfldFine == e.getSource()){frmtdtxtfldFine.setText("");}
		else if(frmtdtxtfldRilasciatail == e.getSource()){frmtdtxtfldRilasciatail.setText("");}
		else if(frmtdtxtfldValida == e.getSource()){frmtdtxtfldValida.setText("");}
		if(frmtdtxtfldInizio.getText().equals("") && !(frmtdtxtfldInizio == e.getSource())){frmtdtxtfldInizio.setText("aaaa-mm-gg");}
		if(frmtdtxtfldFine.getText().equals("") && !(frmtdtxtfldFine == e.getSource())){frmtdtxtfldFine.setText("aaaa-mm-gg");}
		if(frmtdtxtfldRilasciatail.getText().equals("") && !(frmtdtxtfldRilasciatail == e.getSource())){frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");}
		if(frmtdtxtfldValida.getText().equals("") && !(frmtdtxtfldValida == e.getSource())){frmtdtxtfldValida.setText("aaaa-mm-gg");}
    }
	
	/* Definisce le azioni da eseguire quando si perde il focus sui campi per inserire le date. */
	
	public void focusLost(FocusEvent e) {
		if(frmtdtxtfldInizio.getText().equals("")){frmtdtxtfldInizio.setText("aaaa-mm-gg");}
		if(frmtdtxtfldFine.getText().equals("")){frmtdtxtfldFine.setText("aaaa-mm-gg");}
		if(frmtdtxtfldRilasciatail.getText().equals("")){frmtdtxtfldRilasciatail.setText("aaaa-mm-gg");}
		if(frmtdtxtfldValida.getText().equals("")){frmtdtxtfldValida.setText("aaaa-mm-gg");}
	}
}
