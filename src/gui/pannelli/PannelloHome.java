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
import gui.moduli.moduloHome.ModuloCalendario;
import gui.moduli.moduloHome.ModuloMezziNoleggiabili;
import gui.moduli.moduloHome.ModuloMezziRitorno;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PannelloHome extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L; 
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	private JPanel pnlCalendar = new JPanel(null);
	private ModuloMezziRitorno pnlModulo = new ModuloMezziRitorno();
	private ModuloMezziNoleggiabili pnlModulo2 = new ModuloMezziNoleggiabili();
	private Finestra frame;
	
	/* Modifica il contentPane Home.*/
	
	public JPanel run(JPanel contentPane) {
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); /* Action Listener per il bottone Esci.*/
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); /* Action Listener per il bottone Logout.*/
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel user = new JLabel(frame.username);
		user.setFont(new Font("Arial", Font.BOLD, 12));
		user.setForeground(Color.RED);
		
		ModuloCalendario cal = new ModuloCalendario(contentPane,pnlCalendar);
		
		/* Crea il Layout.*/
		
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
		cal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	/* Costruttore contentPane Home .*/
	
	public PannelloHome(Finestra pn) {
		frame = pn;
		pn.setTitle("Autonoleggio - Home");
		pn.setContentPane(this.run(pn.contentPane));
	}
	
	/* Definisce le azioni da eseguire in base al pulsante cliccato.*/
	
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
	}
}