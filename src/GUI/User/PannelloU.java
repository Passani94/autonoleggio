package GUI.User;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import GUI.Login;
import GUI.Admin.PannelloCliente;
import GUI.Admin.PannelloContratto;

public class PannelloU extends JFrame implements ActionListener{
	
	public String Username;
	public JPanel contentPane = new JPanel();
	private final JMenuBar menuBar = new JMenuBar();
	private JMenuItem mntmHome = new JMenuItem("Home");
	private JMenuItem mntmCliente = new JMenuItem("Pannello Cliente");
	private JMenuItem mntmContratto = new JMenuItem("Pannello Contratto");
	
	/* Crea il frame PannelloU.*/
	
	public PannelloU(String user){
		Username = user;
		try {
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.menu();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setBounds(100, 100, 1000, (screenSize.height-100));
			this.setLocationRelativeTo(null);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/External/car.png")));
			PannelloHomeU home = new PannelloHomeU(this);
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
	}
	
	/* Definisce le azioni da eseguire in base al menù clickato.*/
	
	public void actionPerformed(ActionEvent e){
		if(mntmHome == e.getSource()){			
			getContentPane().removeAll();
			PannelloHomeU hm = new PannelloHomeU(this);
			getContentPane().revalidate();
		}
		else if(mntmCliente == e.getSource()){
			getContentPane().removeAll();
			PannelloCliente cl = new PannelloCliente(this);
			getContentPane().revalidate();
		}
		else if(mntmContratto == e.getSource()){
			getContentPane().removeAll();
			PannelloContratto ct = new PannelloContratto(this);
			getContentPane().revalidate();
		}
	}	
}