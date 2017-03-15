package GUI.Admin;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class ModuloOp extends JPanel{

	public ModuloOp(String str){
		set(str);
	}

	public void set(String str){
		if (str == "Elenca"){
			this.setBorder(BorderFactory.createTitledBorder("Elenco Operatori"));
		}
		else if (str == "Nuovo"){
			this.setBorder(BorderFactory.createTitledBorder("Nuovo Operatore"));
		}
		else if (str == "Elimina"){
			this.setBorder(BorderFactory.createTitledBorder("Elimina Operatore"));
		}
	}
}

