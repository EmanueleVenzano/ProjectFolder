import javax.swing.*;
import java.awt.*;

public class Es2 {
	public static void main (String args[]) {
		
		JFrame win;
		win = new JFrame ("Esempio di JComboBox");
		String lista[] = new String[10];
		for (int i=0; i<lista.length; i++) {
			lista[i] = "Elemento numero "+i;
		}
		JComboBox cBox = new JComboBox(lista);
		Container c = win.getContentPane();
		c.add(cBox);
		win.setSize(200, 200);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}
}