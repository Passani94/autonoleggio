package GUI.Admin;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import GUI.*;


public class PannelloExtra extends JPanel implements ActionListener{
	private JButton btnScadenze = new JButton("Scadenze Varie");
	private JButton btnStatistica = new JButton("Veicoli Pi\u00F9 Noleggiati");
	private JButton btnMensile = new JButton("Calcola Profitto Mensile");
	private JButton btnAnnuale = new JButton("Calcola Profitto Annuale");
	private ModuloEx pnlModulo = new ModuloEx();
	private JButton btnEsci = new JButton("Esci");
	private JButton btnLogout = new JButton("Logout");
	private Pannello frame;
	private JScrollPane scrollPane = new JScrollPane(pnlModulo);
	
	/* Modifica il contentPane Extra.*/
	
	public JPanel run(JPanel contentPane){
	       
		scrollPane.setViewportView(pnlModulo);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		btnScadenze.setFont(new Font("Arial", Font.PLAIN, 12));
		btnScadenze.addActionListener(this); /* Action Listener per il bottone Aggiorna.*/
		
		btnStatistica.setFont(new Font("Arial", Font.PLAIN, 12));
		btnStatistica.addActionListener(this); /* Action Listener per il bottone Nuovo.*/
		
		btnMensile.setFont(new Font("Arial", Font.PLAIN, 12));
		btnMensile.addActionListener(this); /* Action Listener per il bottone Elimina.*/
		
		btnAnnuale.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAnnuale.addActionListener(this); /* Action Listener per il bottone Modifica.*/
		
		btnEsci.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEsci.addActionListener(this); /* Action Listener per il bottone Esci.*/
		
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLogout.addActionListener(this); /* Action Listener per il bottone Logout.*/
		
		JLabel lbllog = new JLabel("Loggato come");
		lbllog.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel user = new JLabel(frame.Username);
		user.setFont(new Font("Arial", Font.PLAIN, 12));
		user.setForeground(Color.RED);
		
		/* Crea il Layout.*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(user, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnMensile, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
									.addComponent(btnAnnuale, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
									.addComponent(btnScadenze, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
									.addComponent(btnStatistica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(user, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbllog, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addGap(15)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnEsci, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGap(147)
								.addComponent(btnStatistica, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(btnMensile, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(26)
								.addComponent(btnAnnuale, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(btnScadenze, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(175, Short.MAX_VALUE))))
			);
		contentPane.setLayout(gl_contentPane);
		return contentPane;
	}
	
	/* Costruttore contentPane Extra .*/
	
	public PannelloExtra(Pannello pn) {
		frame = pn;
		pn.setTitle("Autonoleggio - Altre Funzionalità");
		pn.setContentPane(this.run(pn.contentPane));
	}
	
	/* Definisce le azioni da eseguire in base al pulsante clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if (btnEsci == e.getSource()){
			System.exit(0);}
		else if(btnLogout == e.getSource()){
			frame.dispose();
			Login log = new Login();
			log.run();
			}
		else if(btnStatistica == e.getSource()){
			pnlModulo.set("Statistica");
		}
		else if(btnMensile == e.getSource()){
			pnlModulo.set("Mensile");
		}
		else if(btnAnnuale == e.getSource()){
			pnlModulo.set("Annuale");
		}
		else if(btnScadenze == e.getSource()){
			pnlModulo.set("Scadenze");
		}
	}
}