package GUI.Admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pannello extends JFrame implements ActionListener{

	public JPanel contentPane = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private JMenuItem mntmHome = new JMenuItem("Home");
	private JMenuItem mntmOperatore = new JMenuItem("Operatore");

	/* Crea il frame Pannello.*/
	
	public Pannello() {
		try {
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.menu();
			this.setTitle("Autonoleggio - Pannello");
			this.setBounds(100, 100, 800, 600);
			this.setLocationRelativeTo(null);
			Home home = new Home(this);
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Crea il menù */
	
	public void menu(){
		setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		menuBar.add(mnHome);
		
		mntmHome.addActionListener(this);  /* Action Listener per il menu/Operatore.*/
		mnHome.add(mntmHome);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenu mnContratto = new JMenu("Contratto");
		menuBar.add(mnContratto);
		
		JMenu mnFlotta = new JMenu("Flotta");
		menuBar.add(mnFlotta);
		
		JMenu mnOperatore = new JMenu("Operatore");
		menuBar.add(mnOperatore);
		
		mntmOperatore.addActionListener(this);  /* Action Listener per il menu/Operatore.*/
		mnOperatore.add(mntmOperatore);
	}
	
	/* Definisce le azioni da eseguire in base al menù clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if(mntmHome == e.getSource()){			
			getContentPane().removeAll();
			Home op = new Home(this);
			getContentPane().revalidate();
			}
		else if(mntmOperatore == e.getSource()){
			getContentPane().removeAll();
			Operatore op = new Operatore(this);
			getContentPane().revalidate();
		}
	}
}