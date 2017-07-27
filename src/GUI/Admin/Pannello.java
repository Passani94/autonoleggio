package GUI.Admin;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Login;

import javax.swing.JMenuBar;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Pannello extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L; 
	public String Username;
	public JPanel contentPane = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private final JButton btnExtra = new JButton("Extra");
	private final JButton btnOperatore = new JButton("Operatore");
	private final JButton btnFlotta = new JButton("Flotta");
	private final JButton btnContratto = new JButton("Contratto");
	private final JButton btnCliente = new JButton("Cliente");
	private final JButton btnHome = new JButton("Home");
	
	/* Crea il frame Pannello.*/
	
	public Pannello(String user) {
		Username = user;
		try {
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.menu();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setBounds(100, 100, 1000, (screenSize.height-100));
			this.setMinimumSize(new Dimension(1000,(screenSize.height-100)));
			this.setLocationRelativeTo(null);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/External/car.png")));
			new PannelloHome(this);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Crea il menù */
	
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