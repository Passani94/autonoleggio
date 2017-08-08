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
	
	private Contratto contratto;
	private Preventivo preventivo;
	private DBConnect elencoContratti;

	
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
			frmtdtxtfldInizio.setText("Seleziona una data");
			frmtdtxtfldInizio.addFocusListener(this);
			frmtdtxtfldInizio.setEditable(false);
			
			LookAndFeel previus=UIManager.getLookAndFeel();
			JDateChooser dateChooserInizio=null;
					
			try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
					dateChooserInizio= new JDateChooser();
					UIManager.setLookAndFeel(previus);
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
			frmtdtxtfldFine.addFocusListener(this);
			frmtdtxtfldFine.setEditable(false);
			
			JDateChooser dateChooserFine = new JDateChooser();
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserFine= new JDateChooser();
				UIManager.setLookAndFeel(previus);
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
			
			JLabel lblValida = new JLabel("Valida fino a *");
			lblValida.setFont(new Font("Arial", Font.BOLD, 14));
			
			frmtdtxtfldValida = new JFormattedTextField(dateformat);
			frmtdtxtfldValida.setText("Seleziona una data");
			frmtdtxtfldValida.addFocusListener(this);
			frmtdtxtfldValida.setEditable(false);
			
			JDateChooser dateChooserValida = new JDateChooser();
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserValida= new JDateChooser();
				UIManager.setLookAndFeel(previus);
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
			txtRilasciatada.addFocusListener(this);
			
			JLabel lblRilasciataIl = new JLabel("Rilasciata il *");
			lblRilasciataIl.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldRilasciatail = new JFormattedTextField(dateformat);
			frmtdtxtfldRilasciatail.setText("Seleziona una data");
			frmtdtxtfldRilasciatail.addFocusListener(this);
			frmtdtxtfldRilasciatail.setEditable(false);
			
			JDateChooser dateChooserRilasciataIl = new JDateChooser();
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserRilasciataIl= new JDateChooser();
				UIManager.setLookAndFeel(previus);
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
				frmtdtxtfldInizio.setEditable(false);
				
				LookAndFeel previus=UIManager.getLookAndFeel();
				JDateChooser dateChooserInizio=null;
						
				try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
						dateChooserInizio= new JDateChooser();
						UIManager.setLookAndFeel(previus);
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
				frmtdtxtfldFine.setText(Preventivo.getDataFine());
				frmtdtxtfldFine.addFocusListener(this);
				frmtdtxtfldFine.setEditable(false);
				
				JDateChooser dateChooserFine=null;
				
				try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
						dateChooserFine= new JDateChooser();
						UIManager.setLookAndFeel(previus);
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
				
				JLabel lblValida = new JLabel("Valida fino a *");
				lblValida.setFont(new Font("Arial", Font.BOLD, 14));
				
				frmtdtxtfldValida = new JFormattedTextField(dateformat);
				frmtdtxtfldValida.setText("Seleziona una data");
				frmtdtxtfldValida.addFocusListener(this);
				frmtdtxtfldValida.setEditable(false);
				
				JDateChooser dateChooserValida=null;
				
				try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
						dateChooserValida= new JDateChooser();
						UIManager.setLookAndFeel(previus);
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
				frmtdtxtfldRilasciatail.addFocusListener(this);
				frmtdtxtfldRilasciatail.setEditable(false);
				
				JDateChooser dateChooserRilasciataIl=null;
				
				try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
						dateChooserRilasciataIl= new JDateChooser();
						UIManager.setLookAndFeel(previus);
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
			frmtdtxtfldInizio.setText("Seleziona una data");
			frmtdtxtfldInizio.addFocusListener(this);
			frmtdtxtfldInizio.setEditable(false);
			
			JDateChooser dateChooserInizio=null;
			LookAndFeel previus=UIManager.getLookAndFeel();
			
			try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
					dateChooserInizio= new JDateChooser();
					UIManager.setLookAndFeel(previus);
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
			
			

			JLabel lblFine = new JLabel("Data Fine Noleggio");
			lblFine.setFont(new Font("Arial", Font.BOLD, 14));
		
			frmtdtxtfldFine = new JFormattedTextField(dateformat);
			frmtdtxtfldFine.setText("Seleziona una data");
			frmtdtxtfldFine.addFocusListener(this);
			frmtdtxtfldFine.setEditable(false);
			
			JDateChooser dateChooserFine=null;
			
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
				dateChooserFine= new JDateChooser();
				UIManager.setLookAndFeel(previus);
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
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(30)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblVeicolo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblInizio, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
								.addGap(76)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(frmtdtxtfldInizio)
									.addComponent(frmtdtxtfldFine)
									.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(dateChooserInizio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserFine, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(94)
								.addComponent(lblPreventivo, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(61)
								.addComponent(btnCalcola)
								.addGap(49)
								.addComponent(btnPassaAContratto)))
						.addContainerGap(129, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(37)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblVeicolo)
							.addComponent(txtVeicolo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(dateChooserInizio, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInizio, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldInizio, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(dateChooserFine, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldFine, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
								.addComponent(lblFine, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
						.addGap(37)
						.addComponent(lblPreventivo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(41)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnPassaAContratto, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
							.addComponent(btnCalcola, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
						.addGap(475))
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
			frmtdtxtfldInizio.setText("Seleziona una data");
			frmtdtxtfldInizio.setEditable(false);
			
			LookAndFeel previus=UIManager.getLookAndFeel();
			JDateChooser dateChooserInizio=null;
					
			try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
					dateChooserInizio= new JDateChooser();
					UIManager.setLookAndFeel(previus);
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
					UIManager.setLookAndFeel(previus);
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
					UIManager.setLookAndFeel(previus);
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
					UIManager.setLookAndFeel(previus);
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
			
		}
		else if (str.equals("Elenca")) {
			
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
				}
				catch (SQLException e) {
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
			contratto = new Contratto();
			contratto.setValori(this);
			contratto.aggiungi(this);
		}
		else if (btnCalcola == e.getSource()) {
			preventivo = new Preventivo();
			preventivo.setValori(this);
			try {
				preventivo.calcola(this);
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
			contratto = new Contratto();
			if (contratto.setCodice(this)) {
				contratto.elimina(this);
				}
		}		
		else if (btnFiltra == e.getSource()) {
			contratto = new Contratto();
			contratto.setValoriFiltra(this);
			contratto.filtra(this);
		}
		else if(btnModificaC == e.getSource()){
			contratto = new Contratto();
			contratto.setCodiceModifica(this);
			contratto.setValori(this);
			contratto.modifica(this);
		}
		else if(btnCerca == e.getSource()){
			contratto = new Contratto();
			if (contratto.setCodiceCerca(this)) {
				contratto.cerca(this);
			}
		}
	}
	
	/* Definisce le azioni da eseguire quando si ha il focus sui campi da inserire. */
	
	public void focusGained(FocusEvent e) {
		if (frmtdtxtfldInizio == e.getSource() && frmtdtxtfldInizio.getText().equals("Seleziona una data")) { 
			frmtdtxtfldInizio.setText("");
		}
		else if (frmtdtxtfldFine == e.getSource() && frmtdtxtfldFine.getText().equals("Seleziona una data")) {
			frmtdtxtfldFine.setText("");
		}
		else if (frmtdtxtfldValida == e.getSource() && frmtdtxtfldValida.getText().equals("Seleziona una data")) {
			frmtdtxtfldValida.setText("");
		}
		else if (frmtdtxtfldRilasciatail == e.getSource() && frmtdtxtfldRilasciatail.getText().equals("Seleziona una data")) {
			frmtdtxtfldRilasciatail.setText("");
		}
		
		if (!(frmtdtxtfldInizio == e.getSource()) && frmtdtxtfldInizio.getText().equals("")) { 
			frmtdtxtfldInizio.setText("Seleziona una data");
		}
		else if (!(frmtdtxtfldFine == e.getSource()) && frmtdtxtfldFine.getText().equals("")) {
			frmtdtxtfldFine.setText("Seleziona una data");
		}
		else if (!(frmtdtxtfldValida == e.getSource()) && frmtdtxtfldValida.getText().equals("")) {
			frmtdtxtfldValida.setText("Seleziona una data");
		}
		else if (!(frmtdtxtfldRilasciatail == e.getSource()) && frmtdtxtfldRilasciatail.getText().equals("")) {
			frmtdtxtfldRilasciatail.setText("Seleziona una data");
		}
    }
	
	/*Definisce le azioni da eseguire quando si perde il focus sui campi per inserire le date.*/
	
	public void focusLost(FocusEvent e) {
		
	}
}
