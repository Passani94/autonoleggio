package gui.pannelli;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

import autonoleggio.Login;
import gui.finestre.Finestra;
import gui.moduli.moduliOpzionali.ModuloCalendario;
import gui.moduli.moduliOpzionali.ModuloElencoMezziNoleggiabili;
import gui.moduli.moduliOpzionali.ModuloElencoMezziRitorno;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * La classe PannelloHome implementa un pannello contenuto all'interno del frame Finestra.
 */
public class PannelloHome extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L; 
	
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	private JPanel pnlCalendar = new JPanel(null);
	
	private ModuloElencoMezziRitorno pnlModulo = new ModuloElencoMezziRitorno();
	private ModuloElencoMezziNoleggiabili pnlModulo2 = new ModuloElencoMezziNoleggiabili();
	
	private Finestra frame;
	private JLabel user;
	
	/**
	 * Inizializza un nuovo oggetto PannelloHome.
	 * 
	 * @param window un frame Finestra.
	 */
	public PannelloHome(Finestra window) {
		frame = window;
		window.setTitle("Autonoleggio - Home");
		window.setContentPane(this.run(window.contentPane));
	}
	
	/**
	 * Modifica il pannello passato come argomento.
	 * 
	 * @param contentPane un pannello "vuoto".
	 * @return il pannello modificato.
	 */
	public JPanel run(JPanel contentPane) {
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); // Action Listener per il bottone Esci.
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); // Action Listener per il bottone Logout.
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		user = new JLabel(frame.username);
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		ModuloCalendario cal = new ModuloCalendario(contentPane,pnlCalendar);
		
		/* Crea il layout del PannelloHome.*/
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(pnlModulo, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlCalendar, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
							.addGap(19))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(user, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlModulo2, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
							.addGap(364))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlCalendar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlModulo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(pnlModulo2, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		cal.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 5));
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	/**
	 * Definisce le azioni da eseguire a seconda del bottone cliccato.
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (btnEsci == e.getSource()) { 
				int scelta = JOptionPane.showConfirmDialog(
					    null,
					    "Si desidera uscire dall'applicazione?",
					    "Conferma uscita",
					    JOptionPane.YES_NO_OPTION);
				if (scelta == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
		
		} else if(btnLogout == e.getSource()) {
				int scelta = JOptionPane.showConfirmDialog(
						null,
						"Si desidera effettuare il logout?",
						"Conferma logout",
						JOptionPane.YES_NO_OPTION);
				if (scelta == JOptionPane.YES_OPTION) {
					frame.dispose();
					Login log = new Login();
					log.run();
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
		return "PannelloHome [La classe PannelloHome implementa un pannello contenuto all'interno del frame Finestra.]";
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
		PannelloHome other = (PannelloHome) obj;
		if (btnEsci == null) {
			if (other.btnEsci != null)
				return false;
		} else if (!btnEsci.equals(other.btnEsci))
			return false;
		if (btnLogout == null) {
			if (other.btnLogout != null)
				return false;
		} else if (!btnLogout.equals(other.btnLogout))
			return false;
		if (frame == null) {
			if (other.frame != null)
				return false;
		} else if (!frame.equals(other.frame))
			return false;
		if (pnlCalendar == null) {
			if (other.pnlCalendar != null)
				return false;
		} else if (!pnlCalendar.equals(other.pnlCalendar))
			return false;
		if (pnlModulo == null) {
			if (other.pnlModulo != null)
				return false;
		} else if (!pnlModulo.equals(other.pnlModulo))
			return false;
		if (pnlModulo2 == null) {
			if (other.pnlModulo2 != null)
				return false;
		} else if (!pnlModulo2.equals(other.pnlModulo2))
			return false;
		return true;
	}
	
	
}