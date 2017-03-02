package GUI;

import java.awt.*;
import javax.swing.*;

public class Frame {
	private static void newframe(){ 			/*Metodo per creare un frame base*/
		JFrame frame = new JFrame("Autonoleggio");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel();
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}