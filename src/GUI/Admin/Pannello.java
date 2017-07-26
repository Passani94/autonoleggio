package GUI.Admin;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Login;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class Pannello extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 7526466295622776147L; 
	public String Username;
	public JPanel contentPane = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private JMenuItem mntmHome = new JMenuItem("Home");
	private JMenuItem mntmCliente = new JMenuItem("Pannello Cliente");
	private JMenuItem mntmContratto = new JMenuItem("Pannello Contratto");
	private JMenuItem mntmFlotta = new JMenuItem("Pannello Flotta");
	private JMenuItem mntmOperatore = new JMenuItem("Pannello Operatore");
	private JMenuItem mntmExtra = new JMenuItem("Funzionalità Aggiuntive");
	
	/* Crea il frame Pannello.*/
	
	public Pannello(String user) {
		Username = user;
		try {
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.menu();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setBounds(100, 100, 1000, (screenSize.height-100));
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
		
		JMenu mnHome = new JMenu("Home");
		menuBar.add(mnHome);
		
		mntmHome.addActionListener(this);  /* Action Listener per il menu/Home.*/
		mnHome.add(mntmHome);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		mntmCliente.addActionListener(this);  /* Action Listener per il menu/Cliente.*/
		mnCliente.add(mntmCliente);
		
		JMenu mnContratto = new JMenu("Contratto");
		menuBar.add(mnContratto);
		
		mntmContratto.addActionListener(this);  /* Action Listener per il menu/Contratto.*/
		mnContratto.add(mntmContratto);
		
		JMenu mnFlotta = new JMenu("Flotta");
		menuBar.add(mnFlotta);
		
		mntmFlotta.addActionListener(this);  /* Action Listener per il menu/Flotta.*/
		mnFlotta.add(mntmFlotta);
		
		JMenu mnOperatore = new JMenu("Operatore");
		menuBar.add(mnOperatore);
		
		mntmOperatore.addActionListener(this);  /* Action Listener per il menu/Operatore.*/
		mnOperatore.add(mntmOperatore);
		
		JMenu mnExtra = new JMenu("Extra");
		menuBar.add(mnExtra);
		
		mntmExtra.addActionListener(this);  /* Action Listener per il menu/Extra.*/
		mnExtra.add(mntmExtra);
	}
	
	/* Definisce le azioni da eseguire in base al menù clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if(mntmHome == e.getSource()){			
			getContentPane().removeAll();
			new PannelloHome(this);
			getContentPane().revalidate();
		}
		else if(mntmOperatore == e.getSource()){
			getContentPane().removeAll();
			new PannelloOperatore(this);
			getContentPane().revalidate();
		}
		else if(mntmFlotta == e.getSource()){
			getContentPane().removeAll();
			new PannelloFlotta(this);
			getContentPane().revalidate();
		}
		else if(mntmCliente == e.getSource()){
			getContentPane().removeAll();
			new PannelloCliente(this);
			getContentPane().revalidate();
		}
		else if(mntmExtra == e.getSource()){
			getContentPane().removeAll();
			new PannelloExtra(this);
			getContentPane().revalidate();
		}
		else if(mntmContratto == e.getSource()){
			getContentPane().removeAll();
			new PannelloContratto(this);
			getContentPane().revalidate();
		}
	}	
}