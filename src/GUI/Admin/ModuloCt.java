package GUI.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;


public class ModuloCt extends JPanel implements ActionListener{

	JLabel lblPreventivo;
	private JTable tblNoleggi;
	private JButton btnFiltra;
	private JButton btnAggiungi;
	private JButton btnCalcola;
	private JScrollPane scroll = new JScrollPane(tblNoleggi);
	private JTextField txtRilasciatada;
	private JTextField txtCliente;
	private JTextField txtVeicolo;
	private JTextField txtTipologia;
	private JTextField txtCosto;
	private JTextField txtPagato;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtPatente;
	private JFormattedTextField frmtdtxtfldinizio;
	private JFormattedTextField	frmtdtxtfldFine;
	private JFormattedTextField frmtdtxtfldRilasciatail2;
	private JFormattedTextField frmtdtxtfldValida2;
	private JFormattedTextField frmtdtxtfldInizio2;
	private JFormattedTextField frmtdtxtfldFine2;
	
	/* Costruttore ModuloCt */
	
	public ModuloCt(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco Contratti"));
			
			tblNoleggi = new JTable();
			tblNoleggi.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Codice Noleggio", "Tipologia","Costo Totale","Pagato","Data Inizio","Data Fine","Nome","Cognome","Numero Patente","Rilasciata Da","Rilasciata il","Valida Fino a"
				}
			));
			tblNoleggi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
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
									.addGap(108)
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
			
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			
			frmtdtxtfldinizio = new JFormattedTextField(dateformat);
			frmtdtxtfldinizio.setText("aaaa/mm/gg");
		
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("aaaa/mm/gg");
		
			JLabel lblRilasciataDa = new JLabel("Rilasciata da");
			lblRilasciataDa.setFont(new Font("Arial", Font.BOLD, 14));
		
			txtRilasciatada = new JTextField();
			txtRilasciatada.setFont(new Font("Arial", Font.PLAIN, 12));
		
			JLabel lblRilasciataIl = new JLabel("Rilasciata il");
			lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
		
			JLabel lblValida = new JLabel("Valida Fino a");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldRilasciatail2 = new JFormattedTextField(dateformat);
			frmtdtxtfldRilasciatail2.setText("aaaa/mm/gg");
		
			frmtdtxtfldValida2 = new JFormattedTextField(dateformat);
			frmtdtxtfldValida2.setText("aaaa/mm/gg");
		
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
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPagato, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(81)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(frmtdtxtfldinizio, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtTipologia, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtPagato, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtCosto, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtCognome, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
												.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(frmtdtxtfldRilasciatail2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(frmtdtxtfldValida2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
									.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblMex, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(190)
											.addComponent(btnAggiungi, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
											.addGap(15)))
									.addGap(175))))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipologia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldinizio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldFine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPagato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPagato, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPatente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPatente, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRilasciataDa, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRilasciatada, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRilasciataIl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldRilasciatail2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblValida, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldValida2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addComponent(lblMex, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(28, Short.MAX_VALUE))
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
			
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			
			frmtdtxtfldInizio2 = new JFormattedTextField(dateformat);
			frmtdtxtfldInizio2.setText("aaaa/mm/gg");
		
			frmtdtxtfldFine2 = new JFormattedTextField(dateformat);
			frmtdtxtfldFine2.setText("aaaa/mm/gg");
		
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
								.addComponent(frmtdtxtfldFine2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(frmtdtxtfldInizio2)
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
								.addComponent(frmtdtxtfldInizio2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldFine2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
			try{
			/* Inserire cosa fa il pulsante Filtra*/
			String Cliente = txtCliente.getText();
			String Veicolo = txtVeicolo.getText();
			txtCliente.setText("");
		    txtVeicolo.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Contratti non trovati!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnAggiungi == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Aggiungi*/
			String Tipologia = txtTipologia.getText();
			String Inizio = frmtdtxtfldinizio.getText();
			String Fine = frmtdtxtfldFine.getText();
			String Costo = txtCosto.getText();
			String Pagato = txtPagato.getText();
			String Nome = txtNome.getText();
			String Cognome = txtCognome.getText();
			String Patente = txtPatente.getText();
			String Rilasciatada = txtRilasciatada.getText();
			String Rilasciatail = frmtdtxtfldRilasciatail2.getText();
			String Valida = frmtdtxtfldValida2.getText();
			String Cliente = txtCliente.getText();
			String Veicolo = txtVeicolo.getText();
			JOptionPane.showMessageDialog(null , "Nuovo Contratto Aggiunto!");
			txtTipologia.setText("");
			frmtdtxtfldinizio.setText("aaaa/mm/gg");
			frmtdtxtfldFine.setText("aaaa/mm/gg");
			txtCosto.setText("");
			txtPagato.setText("");
			txtNome.setText("");
			txtCognome.setText("");
			txtPatente.setText("");
			txtRilasciatada.setText("");
			frmtdtxtfldRilasciatail2.setText("aaaa/mm/gg");
			frmtdtxtfldValida2.setText("aaaa/mm/gg");
			txtCliente.setText("");
			txtVeicolo.setText("");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Contratto non Aggiunto!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnCalcola == e.getSource()){
			try{
			/* Inserire cosa fa il pulsante Calcola*/
			String Veicolo = txtVeicolo.getText();
			String Inizio = frmtdtxtfldInizio2.getText();
			String Fine = frmtdtxtfldFine2.getText();
			lblPreventivo.setText("0000");
			txtVeicolo.setText("");
			frmtdtxtfldInizio2.setText("aaaa/mm/gg");
			frmtdtxtfldFine2.setText("aaaa/mm/gg");
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore, Preventivo non Calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
