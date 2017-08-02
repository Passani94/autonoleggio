package gui.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autonoleggio.Login;
import gui.pannelli.PannelloCliente;
import gui.pannelli.PannelloContratto;
import gui.pannelli.PannelloExtra;
import gui.pannelli.PannelloFlotta;
import gui.pannelli.PannelloHome;
import gui.pannelli.PannelloOperatore;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Finestra extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L; 
	public String username;
	public JPanel contentPane = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private final JButton btnExtra = new JButton("Extra");
	private final JButton btnOperatore = new JButton("Operatore");
	private final JButton btnFlotta = new JButton("Flotta");
	private final JButton btnContratto = new JButton("Contratto");
	private final JButton btnCliente = new JButton("Cliente");
	private final JButton btnHome = new JButton("Home");
	
	/* Crea il frame Pannello.*/
	
	public Finestra(String user) {
		username = user;
		try {
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.menu();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setBounds(100, 100, 1000, (screenSize.height-100));
			this.setMinimumSize(new Dimension(1000,(screenSize.height-100)));
			this.setLocationRelativeTo(null);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/External/car.png")));
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter(){
	        	public void windowClosing(WindowEvent we)
	        	  { 
	        		int scelta = JOptionPane.showConfirmDialog(
						    null,
						    "Si desidera uscire dall'applicazione?",
						    "Conferma uscita",
						    JOptionPane.YES_NO_OPTION);
					if (scelta == JOptionPane.YES_OPTION){
						System.exit(0);
					}
			}});
			new PannelloHome(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Crea il men� */
	
	public void menu(){
		setJMenuBar(menuBar);
		
		menuBar.add(btnHome);
		btnHome.addActionListener(this); /* ActionListener per il bottone Home. */
		
		menuBar.add(btnCliente);
		btnCliente.addActionListener(this); /* ActionListener per il bottone Cliente. */
		
		menuBar.add(btnContratto);
		btnContratto.addActionListener(this); /* ActionListener per il bottone Contratto. */
		
		menuBar.add(btnFlotta);
		btnFlotta.addActionListener(this); /* ActionListener per il bottone Flotta. */
		
		menuBar.add(btnOperatore);
		btnOperatore.addActionListener(this); /* ActionListener per il bottone Operatore. */
		
		menuBar.add(btnExtra);
		btnExtra.addActionListener(this); /* ActionListener per il bottone Extra. */
		
		if (!username.equals("admin")) {
			btnFlotta.setEnabled(false);
			btnFlotta.removeActionListener(this);
			
			btnOperatore.setEnabled(false);
			btnOperatore.removeActionListener(this);
			
			btnExtra.setEnabled(false);
			btnExtra.removeActionListener(this);
		}
	}
	
	/* Definisce le azioni da eseguire in base al bottone cliccato.*/
	
	public void actionPerformed(ActionEvent e){
		if(btnHome == e.getSource()){			
			getContentPane().removeAll();
			new PannelloHome(this);
			getContentPane().revalidate();
		}
		else if(btnOperatore == e.getSource()){
			getContentPane().removeAll();
			new PannelloOperatore(this);
			getContentPane().revalidate();
		}
		else if(btnFlotta == e.getSource()){
			getContentPane().removeAll();
			new PannelloFlotta(this);
			getContentPane().revalidate();
		}
		else if(btnCliente == e.getSource()){
			getContentPane().removeAll();
			new PannelloCliente(this);
			getContentPane().revalidate();
		}
		else if(btnExtra == e.getSource()){
			getContentPane().removeAll();
			new PannelloExtra(this);
			getContentPane().revalidate();
		}
		else if(btnContratto == e.getSource()){
			getContentPane().removeAll();
			new PannelloContratto(this);
			getContentPane().revalidate();
		}
	}	
}