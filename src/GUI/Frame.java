package GUI;

import java.awt.*;
import javax.swing.*;

public class Frame {
	public JFrame newframe(String titolo){ 			/*Metodo per creare un frame base*/
		JFrame frame = new JFrame(titolo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel();
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
	}
	
	public void dimensiona(JFrame frame){ 		/*Metodo per far adattare la dimensione di un frame al suo contenuto*/
		frame.pack();
	}
	
	public void dimensiona(JFrame frame, int x, int y){  /*Metodo per dimensionare un frame date due dimensioni*/
		frame.setSize(x,y);
	}
}