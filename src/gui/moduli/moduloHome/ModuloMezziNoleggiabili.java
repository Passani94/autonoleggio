package gui.moduli.moduloHome;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import db.DBConnect;
import utils.CostruisciTabella;
import utils.Noleggiabilita;
import utils.TableColumnAdjuster;

public class ModuloMezziNoleggiabili extends JPanel{
	
	private static final long serialVersionUID = 1L; 
	private JTable tblDisponibili;
	private JScrollPane scroll = new JScrollPane(tblDisponibili);
	private DBConnect noleggiabili = new DBConnect();
	private String dataOggi;
	private Date day;
	private Vector <String> targhe,disp;
	//private String[] targhe,disp;
	
	public void set(){
		int i,numero=0;
		this.setBorder(BorderFactory.createTitledBorder("Mezzi noleggiabili in giornata"));
		
		TimeZone zone = TimeZone.getTimeZone("GMT+1");
		
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(zone);
		fmt.setLenient(false);
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 1);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		day = today.getTime();
		dataOggi=fmt.format(day);		
		
		try{
			noleggiabili.exequery("CREATE TABLE mezzinoleggiabili AS SELECT Targa, Tipologia, Marca, Nome,"
					+ "Disponibilita, Alimentazione, Km_Effettuati, Dimensioni FROM veicolo","update");
			noleggiabili.exequery("SELECT * FROM mezzinoleggiabili", "select");
						
			targhe= new Vector <>();
			disp= new Vector <>();
			
			while(noleggiabili.rs.next()) {
				targhe.addElement(noleggiabili.rs.getString(1));
				disp.addElement(noleggiabili.rs.getString(5));
			}
			
			numero=targhe.size();
		}
		catch (SQLException e) {  
			JOptionPane.showMessageDialog(null, "Errore, impossibile caricare l'elenco dei veicoli!",
					"Errore ",
					JOptionPane.ERROR_MESSAGE);
		}
		for (i=0;i<numero;i++) {
				try {
					if ((!Noleggiabilita.controlla(targhe.get(i),dataOggi,dataOggi)) || (disp.get(i).equals("NO")))
					{
						noleggiabili.exequery("DELETE FROM mezzinoleggiabili WHERE Targa='"+targhe.get(i)+"'", "delete");
					}
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}
				
		try {
			noleggiabili.exequery("SELECT Targa, Tipologia, Marca, Nome, Alimentazione , "
					+ "Km_Effettuati, Dimensioni FROM mezzinoleggiabili", "select");
		} catch (SQLException e) {
	     
			e.printStackTrace();
		}
		
		tblDisponibili = new JTable();
		tblDisponibili.setModel(new CostruisciTabella(noleggiabili.rs).model);
		tblDisponibili.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(tblDisponibili);
		tca.adjustColumns();
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(tblDisponibili);
		
		try {
			noleggiabili.exequery("DROP TABLE mezzinoleggiabili", "delete");
		} catch (SQLException e) {
	     
			e.printStackTrace();
		}
		
		/* Crea il Layout per l'elenco dei mezzi Disponibili. */
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addContainerGap())
			);
		this.setLayout(gl_contentPane);
		}
	
	/* Costruttore ModuloHmU */
	
	public ModuloMezziNoleggiabili() {
		set();
	}

	@Override
	public String toString() {
		return "ModuloMezziNoleggiabili [dataOggi=" + dataOggi + ", day=" + day + ", targhe=" + targhe + ", disp="
				+ disp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataOggi == null) ? 0 : dataOggi.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((disp == null) ? 0 : disp.hashCode());
		result = prime * result + ((noleggiabili == null) ? 0 : noleggiabili.hashCode());
		result = prime * result + ((scroll == null) ? 0 : scroll.hashCode());
		result = prime * result + ((targhe == null) ? 0 : targhe.hashCode());
		result = prime * result + ((tblDisponibili == null) ? 0 : tblDisponibili.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuloMezziNoleggiabili other = (ModuloMezziNoleggiabili) obj;
		if (dataOggi == null) {
			if (other.dataOggi != null)
				return false;
		} else if (!dataOggi.equals(other.dataOggi))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (disp == null) {
			if (other.disp != null)
				return false;
		} else if (!disp.equals(other.disp))
			return false;
		if (noleggiabili == null) {
			if (other.noleggiabili != null)
				return false;
		} else if (!noleggiabili.equals(other.noleggiabili))
			return false;
		if (scroll == null) {
			if (other.scroll != null)
				return false;
		} else if (!scroll.equals(other.scroll))
			return false;
		if (targhe == null) {
			if (other.targhe != null)
				return false;
		} else if (!targhe.equals(other.targhe))
			return false;
		if (tblDisponibili == null) {
			if (other.tblDisponibili != null)
				return false;
		} else if (!tblDisponibili.equals(other.tblDisponibili))
			return false;
		return true;
	}
	
	
}	