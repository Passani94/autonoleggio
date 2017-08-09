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
import gui.moduli.ModuloFlotta;


public class PannelloFlotta extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 7336472295622776147L; 
	private JButton btnAggiorna = new JButton("Aggiorna Elenco");
	private JButton btnNuovo = new JButton("Nuovo Veicolo");
	private JButton btnElimina = new JButton("Elimina Veicolo");
	private JButton btnModifica = new JButton("Modifica Veicolo");
	private ModuloFlotta pnlModulo = new ModuloFlotta("Elenca");
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	private Finestra frame;
	private JScrollPane scrollPane = new JScrollPane(pnlModulo);
	
	/* Modifica il contentPane Flotta.*/
	
	public JPanel run(JPanel contentPane){
		
		scrollPane.setViewportView(pnlModulo);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
		btnAggiorna.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAggiorna.addActionListener(this); /* Action Listener per il bottone Aggiorna.*/
		
		btnNuovo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNuovo.addActionListener(this); /* Action Listener per il bottone Nuovo.*/
		
		btnElimina.setFont(new Font("Arial", Font.PLAIN, 12));
		btnElimina.addActionListener(this); /* Action Listener per il bottone Elimina.*/
		
		btnModifica.setFont(new Font("Arial", Font.PLAIN, 12));
		btnModifica.addActionListener(this); /* Action Listener per il bottone Modifica.*/
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); /* Action Listener per il bottone Esci.*/
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); /* Action Listener per il bottone Logout.*/
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel user = new JLabel(frame.username);
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		/* Crea il Layout.*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnNuovo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNuovo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnModifica, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnElimina, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAggiorna, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
						.addGap(15)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	/* Costruttore contentPane Flotta .*/
	
	public PannelloFlotta(Finestra pn) {
		frame = pn;
		pn.setTitle("Autonoleggio - Flotta");
		pn.setContentPane(this.run(pn.contentPane));
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnEsci == e.getSource()){ 
			int scelta = JOptionPane.showConfirmDialog(
				    null,
				    "Si desidera uscire dall'applicazione?",
				    "Conferma uscita",
				    JOptionPane.YES_NO_OPTION);
			if (scelta == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		else if(btnLogout == e.getSource()){
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
		}
		else if(btnAggiorna == e.getSource()){
			btnAggiorna.setText("Aggiorna Elenco");
			pnlModulo.set("Elenca");
		}
		else if(btnNuovo == e.getSource()){
			btnAggiorna.setText("Elenco Veicoli");
			pnlModulo.set("Nuovo");
		}
		else if(btnElimina == e.getSource()){
			btnAggiorna.setText("Elenco Veicoli");
			pnlModulo.set("Elimina");
		}
		else if(btnModifica == e.getSource()){
			btnAggiorna.setText("Elenco Veicoli");
			pnlModulo.set("Modifica");
		}
	}

	@Override
	public String toString() {
		return "PannelloFlotta [Questa classe genera il pannello flotta.]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PannelloFlotta other = (PannelloFlotta) obj;
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
		return true;
	}
	
	
}