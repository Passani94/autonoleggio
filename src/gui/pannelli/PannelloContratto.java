package gui.pannelli;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import autonoleggio.Login;
import gui.finestre.Finestra;
import gui.moduli.ModuloContratto;
import gui.moduli.moduliOpzionali.ModuloElencoClienti;
import gui.moduli.moduliOpzionali.ModuloElencoContratti;
import gui.moduli.moduliOpzionali.ModuloElencoPrezziExtra;
import gui.moduli.moduliOpzionali.ModuloElencoVeicoli;

/**
 * La classe PannelloContratto implementa un pannello contenuto all'interno del frame Finestra.
 */
public class PannelloContratto extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L; 
	
	private JButton btnAggiorna = new JButton("Elenco Contratti");
	private JButton btnNuovo = new JButton("Nuovo Contratto");
	private JButton btnElimina = new JButton("Elimina Contratto");
	private JButton btnPreventivo = new JButton("Calcola Preventivo");
	private JButton btnModifica = new JButton("Modifica Contratto");
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	
	private ModuloContratto pnlModulo = new ModuloContratto("Principale");
	private ModuloContratto pnlModulo2 = new ModuloContratto("Opzionale");
	private ModuloContratto pnlModulo3 = new ModuloContratto("Opzionale");
	private ModuloElencoClienti pnlModulo4 = new ModuloElencoClienti();
	private ModuloElencoContratti pnlModulo5 = new ModuloElencoContratti();
	private ModuloElencoPrezziExtra pnlModulo6 = new ModuloElencoPrezziExtra();
	private ModuloElencoVeicoli pnlModulo7 = new ModuloElencoVeicoli("Disponibili");
	
	private JScrollPane scrollPane = new JScrollPane(pnlModulo);
	private JScrollPane scrollPane2 = new JScrollPane(pnlModulo2);
	private JScrollPane scrollPane3 = new JScrollPane(pnlModulo2);
	
	private Finestra frame;
	private JLabel user;
	
	/**
	 * Inizializza un nuovo oggetto PannelloContratto.
	 * 
	 * @param window un frame Finestra.
	 */
	public PannelloContratto(Finestra window) {
		frame = window;
		window.setTitle("Autonoleggio - Contratto");
		window.setContentPane(this.run(window.contentPane));
	}
	
	/**
	 * Modifica il pannello PannelloContratto.
	 * 
	 * @param contentPane un pannello "vuoto".
	 * @return il pannello modificato.
	 */
	public JPanel run(JPanel contentPane) {
		
		scrollPane.setViewportView(pnlModulo);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane2.setViewportView(pnlModulo2);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane3.setViewportView(pnlModulo3);
		scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
		btnAggiorna.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAggiorna.addActionListener(this); // Action Listener per il bottone Aggiorna.
		
		btnNuovo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNuovo.addActionListener(this); // Action Listener per il bottone Nuovo.
		
		btnPreventivo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPreventivo.addActionListener(this);// Action Listener per il bottone Modifica.
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); // Action Listener per il bottone Esci.
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); // Action Listener per il bottone Logout.
		
		btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
		btnElimina.addActionListener(this); // Action Listener per il bottone Elimina.
		
		btnModifica.setFont(new Font("Arial", Font.PLAIN, 12));
		btnModifica.addActionListener(this); // Action Listener per il bottone Modifica.
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		user = new JLabel(frame.username);		
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		/* Crea il layout del PannelloContratto.*/
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnPreventivo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNuovo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 492, Short.MAX_VALUE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
									.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPreventivo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNuovo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
						.addGap(15)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
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
			if (scelta == JOptionPane.YES_OPTION){
				frame.dispose();
				Login log = new Login();
				log.run();
			}
		
		} else if(btnPreventivo == e.getSource()) {
			btnAggiorna.setText("Elenco Contratti");
			pnlModulo.set("Preventivo");
			scrollPane2.setViewportView(pnlModulo7);
			scrollPane3.setViewportView(pnlModulo4);
		
		} else if(btnNuovo == e.getSource()) {
			btnAggiorna.setText("Elenco Contratti");
			pnlModulo.set("Nuovo");
			scrollPane2.setViewportView(pnlModulo7);
			scrollPane3.setViewportView(pnlModulo4);
			
		} else if(btnModifica == e.getSource()) {
			btnAggiorna.setText("Elenco Contratti");
			pnlModulo.set("Modifica");
			scrollPane2.setViewportView(pnlModulo5);
			scrollPane3.setViewportView(pnlModulo6);
			
		} else if(btnElimina == e.getSource()) {
			btnAggiorna.setText("Elenco Contratti");
			pnlModulo.set("Elimina");
			scrollPane2.setViewportView(pnlModulo5);
			scrollPane3.setViewportView(pnlModulo3);
		
		} else if(btnAggiorna == e.getSource()) {
			btnAggiorna.setText("Aggiorna Elenco");
			pnlModulo.set("Elenca");
			scrollPane2.setViewportView(pnlModulo2);
			scrollPane3.setViewportView(pnlModulo3);
		
		} 
	}

/* OVERRIDING METODI toString() ED equals() */
	
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto.
	 * 
	 * @return una stringa rappresentante l'oggetto.
	 */
	public String toString() {
		return "PannelloContratto [La classe PannelloContratto implementa un pannello contenuto all'interno del frame Finestra.]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PannelloContratto other = (PannelloContratto) obj;
		if (btnAggiorna == null) {
			if (other.btnAggiorna != null)
				return false;
		} else if (!btnAggiorna.equals(other.btnAggiorna))
			return false;
		if (btnElimina == null) {
			if (other.btnElimina != null)
				return false;
		} else if (!btnElimina.equals(other.btnElimina))
			return false;
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
		if (btnModifica == null) {
			if (other.btnModifica != null)
				return false;
		} else if (!btnModifica.equals(other.btnModifica))
			return false;
		if (btnNuovo == null) {
			if (other.btnNuovo != null)
				return false;
		} else if (!btnNuovo.equals(other.btnNuovo))
			return false;
		if (btnPreventivo == null) {
			if (other.btnPreventivo != null)
				return false;
		} else if (!btnPreventivo.equals(other.btnPreventivo))
			return false;
		if (frame == null) {
			if (other.frame != null)
				return false;
		} else if (!frame.equals(other.frame))
			return false;
		if (pnlModulo == null) {
			if (other.pnlModulo != null)
				return false;
		} else if (!pnlModulo.equals(other.pnlModulo))
			return false;
		if (scrollPane == null) {
			if (other.scrollPane != null)
				return false;
		} else if (!scrollPane.equals(other.scrollPane))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}	
	
}