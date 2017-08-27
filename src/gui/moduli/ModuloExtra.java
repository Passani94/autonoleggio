package gui.moduli;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import db.DBConnect;
import utils.CostruisciTabella;
import utils.TableColumnAdjuster;

/**
 * La classe ModuloExtra permette di gestire profitti, scadenze e statiche dell'autonoleggio.
 */
public class ModuloExtra extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L; 
	
	private JButton btnProfitto;
	private JButton btnProfittoA;
	private JTable tblAlaggio;
	private JTable tblAssicurazione;
	private JTable tblBollo;
	private JTable tblOrmeggio;
	private JLabel lblProfitto;
	private JTable tblTagliando;
	private JTable tblVeicoli;
	private JScrollPane scroll = new JScrollPane(tblVeicoli);
	private JScrollPane scrollAlaggio = new JScrollPane(tblAlaggio);
	private JScrollPane scrollAssicurazione = new JScrollPane(tblAssicurazione);
	private JScrollPane scrollBollo = new JScrollPane(tblBollo);
	private JScrollPane scrollOrmeggio = new JScrollPane(tblOrmeggio);
	private JScrollPane scrollTagliando = new JScrollPane(tblTagliando);
	private JComboBox <String> comboBoxAnnuale;
	private JComboBox <String> comboBoxMeseMensile;
	private JComboBox <String> comboBoxAnnoMensile;
	
	private DBConnect extra;
	private DBConnect profitto;
	
	private Integer annoCorrente;
	
	private String dataQuery, dataQuery2;
	
	/**
	 * Inizializza un nuovo oggetto ModuloExtra e genera il layout iniziale del pannello "Funzionalità Aggiuntive".
	 */
	public ModuloExtra() {
		
		this.setBorder(BorderFactory.createTitledBorder("Funzionalità Aggiuntive"));
		
		JLabel lblFunz = new JLabel("Seleziona la funzionalità desiderata");
		lblFunz.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunz.setFont(new Font("Arial", Font.BOLD, 14));
		
		/* Crea il layout iniziale del pannello "Funzionalità Aggiuntive". */
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
						.addContainerGap(50, Short.MAX_VALUE)
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
	}
	
	/**
	 * Inizializza un nuovo oggetto ModuloExtra e richiama il metodo {@code set} passando come argomento l'oggetto String {@code str}.
	 * 
	 * @param str una stringa che determina il diverso comportamento del metodo {@code set}.
	 */
	public ModuloExtra (String str) {
		set(str);	
	}
	
	/**
	 * Si comporta in maniera differente a seconda dell'oggetto String che viene passato come argomento. <br><br>
	 * 
	 * - Se viene passato "Statistica", viene creato l'elenco dei veicoli più noleggiati. <br>
	 * - Se viene passato "Mensile", viene creato il form per calcolare il profitto mensile. <br>
	 * - Se viene passato "Annuale", viene creato il form per calcolare il profitto annuale. <br>
	 * - Se viene passato "Scadenze", viene creata la vista delle scadenze.
	 * 
	 * @param str una stringa che determina cosa verrà mostrato a schermo.
	 */
	public void set(String str) {
		
		if (str.equals("Statistica")) {
			
			/* Viene creato l'elenco dei veicoli più noleggiati. */
			extra = new DBConnect();
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Elenco veicoli più noleggiati"));
			
			try {
				extra.exequery("SELECT COUNT(*) as Numero_Noleggi, b.Targa, b.Tipologia, b.Marca, b.Nome "
						+ "FROM noleggio a INNER JOIN veicolo b ON a.Veicolo = b.Targa GROUP BY b.Targa ORDER BY Numero_Noleggi DESC","select");
				
				tblVeicoli = new JTable();
				tblVeicoli.setModel(new CostruisciTabella(extra.rs).model);
				tblVeicoli.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				
				JTableHeader header= tblVeicoli.getTableHeader();
				TableColumnModel colMod = header.getColumnModel();
				TableColumn tabCol = colMod.getColumn(0);
				tabCol.setHeaderValue("Noleggi");
				
				TableColumnAdjuster tca = new TableColumnAdjuster(tblVeicoli);
				tca.adjustColumns();
				
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setViewportView(tblVeicoli);
				
				extra.con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli più noleggiati!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
			}
			
			/* Crea il layout per l'elenco dei veicoli più noleggiati. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(54, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
							.addGap(10))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Mensile")) {
			
			/* Viene creato il form per calcolare il profitto mensile. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Profitto Mensile"));
			
			JLabel lblMensile = new JLabel("Profitto Mensile");
			lblMensile.setHorizontalAlignment(SwingConstants.CENTER);
			lblMensile.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblMese = new JLabel("Seleziona Mese-Anno");
			lblMese.setHorizontalAlignment(SwingConstants.CENTER);
			lblMese.setFont(new Font("Arial", Font.BOLD, 12));
			
			btnProfitto = new JButton("Calcola Profitto");
			btnProfitto.addActionListener(this); // Action Listener per il bottone Profitto.
						
			lblProfitto = new JLabel("");
			lblProfitto.setFont(new Font("Arial", Font.BOLD, 12));
			lblProfitto.setHorizontalAlignment(SwingConstants.CENTER);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM");
			dateformat.setLenient(false);
					
			comboBoxMeseMensile = new JComboBox <>();
			comboBoxMeseMensile.setModel(new DefaultComboBoxModel<>(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
			comboBoxMeseMensile.setMaximumRowCount(13);
			comboBoxAnnoMensile = new JComboBox <> ();
			GregorianCalendar cal=new GregorianCalendar();
			annoCorrente=cal.get(GregorianCalendar.YEAR); 
			
			comboBoxAnnoMensile.addItem("");
			for (int i=annoCorrente; i>=annoCorrente-10; i--){				
				comboBoxAnnoMensile.addItem(String.valueOf(i));
		        }
			
			/* Crea il layout del form per il calcolo del profitto mensile. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(34)
						.addComponent(lblMese, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addGap(40)
						.addComponent(comboBoxMeseMensile, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(comboBoxAnnoMensile, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
						.addComponent(btnProfitto)
						.addGap(78))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(114)
								.addComponent(lblMensile, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(120)
								.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(239, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(26)
						.addComponent(lblMensile, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMese, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxMeseMensile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnProfitto)
							.addComponent(comboBoxAnnoMensile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(200, Short.MAX_VALUE))
			);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Annuale")) {
			
			/* Viene creato il form per calcolare il profitto annuale. */
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Profitto Annuale"));
			
			JLabel lblAnnuale = new JLabel("Profitto Annuale");
			lblAnnuale.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnnuale.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblAnno = new JLabel("Seleziona Anno");
			lblAnno.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnno.setFont(new Font("Arial", Font.BOLD, 12));
			
			btnProfittoA = new JButton("Calcola Profitto");
			btnProfittoA.addActionListener(this);	/* Action Listener per il bottone Profitto Annuale.*/
						
			lblProfitto = new JLabel("");
			lblProfitto.setFont(new Font("Arial", Font.BOLD, 12));
			lblProfitto.setHorizontalAlignment(SwingConstants.CENTER);
			
			DateFormat dateformat = new SimpleDateFormat("yyyy");
			dateformat.setLenient(false);
				
			GregorianCalendar cal=new GregorianCalendar();
			annoCorrente=cal.get(GregorianCalendar.YEAR); 
			
					
			comboBoxAnnuale = new JComboBox <>();
			comboBoxAnnuale.addItem("");
			for (int i=annoCorrente; i>=annoCorrente-10; i--){
				comboBoxAnnuale.addItem(String.valueOf(i));
		        }
			
			/* Crea il layout del form per il calcolo del profitto annuale. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(114)
									.addComponent(lblAnnuale, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(120)
									.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(34)
									.addComponent(lblAnno, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addGap(29)
									.addComponent(comboBoxAnnuale, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addGap(80)
									.addComponent(btnProfittoA)))
							.addContainerGap(136, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(lblAnnuale, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAnno, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxAnnuale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnProfittoA))
							.addGap(30)
							.addComponent(lblProfitto, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(200, Short.MAX_VALUE))
				);
			this.setLayout(gl_contentPane);
			this.revalidate();
		
		} else if (str.equals("Scadenze")) {
			
			/* Viene creata la vista delle scadenze. */
			extra = new DBConnect();
			DateFormat fmt = new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
			dataQuery = fmt.format(c.getTime());
			c.setTime(c.getTime());
			c.add(Calendar.MONTH,1);
			dataQuery2 = fmt.format(c.getTime());
			
			this.removeAll();
			this.setBorder(BorderFactory.createTitledBorder("Prossime Scadenze"));
			
			JLabel lblBollo = new JLabel("Bollo");
			lblBollo.setHorizontalAlignment(SwingConstants.LEFT);
			lblBollo.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblTagliando = new JLabel("Tagliando");
			lblTagliando.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTagliando.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblAssicuarazione = new JLabel("Assicurazione");
			lblAssicuarazione.setHorizontalAlignment(SwingConstants.LEFT);
			lblAssicuarazione.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblOrmeggio = new JLabel("Ormeggio");
			lblOrmeggio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblOrmeggio.setFont(new Font("Arial", Font.BOLD, 13));
			
			JLabel lblAlaggio = new JLabel("Alaggio");
			lblAlaggio.setHorizontalAlignment(SwingConstants.LEFT);
			lblAlaggio.setFont(new Font("Arial", Font.BOLD, 13));
			
			try{
				extra.exequery("SELECT Data_Scadenza_Bollo, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati  "
					+ "FROM veicolo WHERE (Data_Scadenza_Bollo LIKE '"+dataQuery+"-%' OR Data_Scadenza_Bollo LIKE '"+dataQuery2+"-%') "
					+ "ORDER BY Data_Scadenza_Bollo ASC","select");
				
				tblBollo = new JTable();
				tblBollo.setModel(new CostruisciTabella(extra.rs).model);
				tblBollo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JTableHeader header= tblBollo.getTableHeader();
				TableColumnModel colMod = header.getColumnModel();
				TableColumn tabCol = colMod.getColumn(0);
				tabCol.setHeaderValue("Data_Scadenza");
				
				TableColumnAdjuster tca = new TableColumnAdjuster(tblBollo);
				tca.adjustColumns();
				
				scrollBollo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollBollo.setViewportView(tblBollo);
			} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con bollo in scadenza!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				extra.exequery("SELECT Data_Scadenza_Tagliando, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
					+ "FROM veicolo WHERE (Data_Scadenza_Tagliando LIKE '"+dataQuery+"-%' OR Data_Scadenza_Tagliando LIKE '"+dataQuery2+"-%') "
					+ "ORDER BY Data_Scadenza_Tagliando ASC","select");
				
				tblTagliando = new JTable();
				tblTagliando.setModel(new CostruisciTabella(extra.rs).model);
				tblTagliando.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JTableHeader header= tblTagliando.getTableHeader();
				TableColumnModel colMod = header.getColumnModel();
				TableColumn tabCol = colMod.getColumn(0);
				tabCol.setHeaderValue("Data_Scadenza");
				
				TableColumnAdjuster tca = new TableColumnAdjuster(tblTagliando);
				tca.adjustColumns();
				
				scrollTagliando.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollTagliando.setViewportView(tblTagliando);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con tagliando in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				extra.exequery("SELECT Data_Scadenza_Assicurazione, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
						+ "FROM veicolo WHERE (Data_Scadenza_Assicurazione LIKE '"+dataQuery+"-%' OR Data_Scadenza_Assicurazione LIKE '"+dataQuery2+"-%') "
						+ "ORDER BY Data_Scadenza_Assicurazione ASC","select");
				
				tblAssicurazione = new JTable();
				tblAssicurazione.setModel(new CostruisciTabella(extra.rs).model);
				tblAssicurazione.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JTableHeader header= tblAssicurazione.getTableHeader();
				TableColumnModel colMod = header.getColumnModel();
				TableColumn tabCol = colMod.getColumn(0);
				tabCol.setHeaderValue("Data_Scadenza");
				
				TableColumnAdjuster tca = new TableColumnAdjuster(tblAssicurazione);
				tca.adjustColumns();
				
				scrollAssicurazione.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollAssicurazione.setViewportView(tblAssicurazione);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con assicurazione in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
		
			try {
				extra.exequery("SELECT Data_Scadenza_Ormeggio, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
						+ "FROM veicolo WHERE (Data_Scadenza_Ormeggio LIKE '"+dataQuery+"-%' OR Data_Scadenza_Ormeggio LIKE '"+dataQuery2+"-%') "
						+ "ORDER BY Data_Scadenza_Ormeggio ASC","select");
				
				tblOrmeggio = new JTable();
				tblOrmeggio.setModel(new CostruisciTabella(extra.rs).model);
				tblOrmeggio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JTableHeader header= tblOrmeggio.getTableHeader();
				TableColumnModel colMod = header.getColumnModel();
				TableColumn tabCol = colMod.getColumn(0);
				tabCol.setHeaderValue("Data_Scadenza");
				
				TableColumnAdjuster tca = new TableColumnAdjuster(tblOrmeggio);
				tca.adjustColumns();
				
				scrollOrmeggio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollOrmeggio.setViewportView(tblOrmeggio);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con ormeggio in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				extra.exequery("SELECT Data_Scadenza_Costo_Alaggio, Targa, Tipologia, Marca, Nome, Alimentazione, Km_Effettuati "
						+ "FROM veicolo WHERE (Data_Scadenza_Costo_Alaggio LIKE '"+dataQuery+"-%' OR Data_Scadenza_Costo_Alaggio LIKE '"+dataQuery2+"-%') "
						+ "ORDER BY Data_Scadenza_Costo_Alaggio ASC","select");
				
				tblAlaggio = new JTable();
				tblAlaggio.setModel(new CostruisciTabella(extra.rs).model);
				tblAlaggio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				JTableHeader header= tblAlaggio.getTableHeader();
				TableColumnModel colMod = header.getColumnModel();
				TableColumn tabCol = colMod.getColumn(0);
				tabCol.setHeaderValue("Data_Scadenza");
				
				TableColumnAdjuster tca = new TableColumnAdjuster(tblAlaggio);
				tca.adjustColumns();
				
				scrollAlaggio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollAlaggio.setViewportView(tblAlaggio);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errore! Impossibile caricare l'elenco dei veicoli con alaggio in scadenza!",
						"Errore ",
						JOptionPane.ERROR_MESSAGE);
			}
			
			/* Crea il layout per la vista delle scadenze. */
			GroupLayout gl_contentPane = new GroupLayout(this);
			gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(94))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(scrollAlaggio, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
												.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(scrollAssicurazione, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblAssicuarazione, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
														.addComponent(scrollBollo, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblTagliando)
														.addComponent(scrollTagliando, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblOrmeggio)))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(63)
													.addComponent(scrollOrmeggio, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))))
										.addComponent(lblAlaggio))))
							.addContainerGap())
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBollo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTagliando, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollBollo, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollTagliando, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAssicuarazione, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOrmeggio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollAssicurazione, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollOrmeggio, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addComponent(lblAlaggio, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(scrollAlaggio, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
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
		
		if (btnProfitto == e.getSource()) {
			try {
				profitto = new DBConnect();
				lblProfitto.setText("");
				String mese=comboBoxAnnoMensile.getSelectedItem()+"-"+comboBoxMeseMensile.getSelectedItem();
					profitto.exequery("SELECT SUM(Costo_Totale) as Profitto_Totale FROM noleggio WHERE Data_Inizio LIKE '" +mese+"-%'","select");
					if (profitto.rs.next()) {
						if(comboBoxAnnoMensile.getSelectedIndex() == 0 || comboBoxMeseMensile.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Errore! La data inserita non è valida!",
								    "Errore ",
								    JOptionPane.ERROR_MESSAGE);
						} else if (profitto.rs.getString(1) == null) {
							lblProfitto.setText("Profitto del " + mese + ": 0 €");
						} else {
						lblProfitto.setText("Profitto del " + mese + ":  " + profitto.rs.getString(1) + " €");
						}
					}
					profitto.con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Profitto non calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (btnProfittoA == e.getSource()) {
			try {
				profitto = new DBConnect();
				lblProfitto.setText("");
				String anno=comboBoxAnnuale.getSelectedItem().toString();
				profitto.exequery("SELECT SUM(Costo_Totale) as Profitto_Totale FROM noleggio WHERE Data_Inizio LIKE '" +anno+"-%-%'","select");
					if (profitto.rs.next()) {
						if(comboBoxAnnuale.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Errore! La data inserita non è valida!",
								    "Errore ",
								    JOptionPane.ERROR_MESSAGE);
						} else if (profitto.rs.getString(1) == null) {
							lblProfitto.setText("Profitto del " + anno + ": 0 €");
						} else {
						lblProfitto.setText("Profitto del " + anno + ":  " + profitto.rs.getString(1) + " €");
						}
					}
					profitto.con.close();
				} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Errore! Profitto non calcolato!",
					    "Errore ",
					    JOptionPane.ERROR_MESSAGE);
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
		return "ModuloExtra [La classe ModuloExtra permette di gestire profitti, scadenze e statiche dell'autonoleggio.]";
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
		ModuloExtra other = (ModuloExtra) obj;
		if (annoCorrente != other.annoCorrente)
			return false;
		if (btnProfitto == null) {
			if (other.btnProfitto != null)
				return false;
		} else if (!btnProfitto.equals(other.btnProfitto))
			return false;
		if (btnProfittoA == null) {
			if (other.btnProfittoA != null)
				return false;
		} else if (!btnProfittoA.equals(other.btnProfittoA))
			return false;
		if (comboBoxAnnoMensile == null) {
			if (other.comboBoxAnnoMensile != null)
				return false;
		} else if (!comboBoxAnnoMensile.equals(other.comboBoxAnnoMensile))
			return false;
		if (comboBoxAnnuale == null) {
			if (other.comboBoxAnnuale != null)
				return false;
		} else if (!comboBoxAnnuale.equals(other.comboBoxAnnuale))
			return false;
		if (comboBoxMeseMensile == null) {
			if (other.comboBoxMeseMensile != null)
				return false;
		} else if (!comboBoxMeseMensile.equals(other.comboBoxMeseMensile))
			return false;
		if (dataQuery == null) {
			if (other.dataQuery != null)
				return false;
		} else if (!dataQuery.equals(other.dataQuery))
			return false;
		if (dataQuery2 == null) {
			if (other.dataQuery2 != null)
				return false;
		} else if (!dataQuery2.equals(other.dataQuery2))
			return false;
		if (extra == null) {
			if (other.extra != null)
				return false;
		} else if (!extra.equals(other.extra))
			return false;
		if (lblProfitto == null) {
			if (other.lblProfitto != null)
				return false;
		} else if (!lblProfitto.equals(other.lblProfitto))
			return false;
		if (profitto == null) {
			if (other.profitto != null)
				return false;
		} else if (!profitto.equals(other.profitto))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (scrollAlaggio == null) {
			if (other.scrollAlaggio != null)
				return false;
		} else if (!scrollAlaggio.equals(other.scrollAlaggio))
			return false;
		if (scrollAssicurazione == null) {
			if (other.scrollAssicurazione != null)
				return false;
		} else if (!scrollAssicurazione.equals(other.scrollAssicurazione))
			return false;
		if (scrollBollo == null) {
			if (other.scrollBollo != null)
				return false;
		} else if (!scrollBollo.equals(other.scrollBollo))
			return false;
		if (scrollOrmeggio == null) {
			if (other.scrollOrmeggio != null)
				return false;
		} else if (!scrollOrmeggio.equals(other.scrollOrmeggio))
			return false;
		if (scrollTagliando == null) {
			if (other.scrollTagliando != null)
				return false;
		} else if (!scrollTagliando.equals(other.scrollTagliando))
			return false;
		if (tblAlaggio == null) {
			if (other.tblAlaggio != null)
				return false;
		} else if (!tblAlaggio.equals(other.tblAlaggio))
			return false;
		if (tblAssicurazione == null) {
			if (other.tblAssicurazione != null)
				return false;
		} else if (!tblAssicurazione.equals(other.tblAssicurazione))
			return false;
		if (tblBollo == null) {
			if (other.tblBollo != null)
				return false;
		} else if (!tblBollo.equals(other.tblBollo))
			return false;
		if (tblOrmeggio == null) {
			if (other.tblOrmeggio != null)
				return false;
		} else if (!tblOrmeggio.equals(other.tblOrmeggio))
			return false;
		if (tblTagliando == null) {
			if (other.tblTagliando != null)
				return false;
		} else if (!tblTagliando.equals(other.tblTagliando))
			return false;
		if (tblVeicoli == null) {
			if (other.tblVeicoli != null)
				return false;
		} else if (!tblVeicoli.equals(other.tblVeicoli))
			return false;
		return true;
	}
	
	
}