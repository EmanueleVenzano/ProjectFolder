import javax.swing.*;
import java.awt.*;

class MMMyFrame extends JFrame {
	/*
	JButton uno = new JButton ("Uno");
	JButton due = new JButton ("Due");
	JButton tre = new JButton ("Tre");
	JButton quattro = new JButton ("Quattro");
	JButton cinque = new JButton ("Cinque");
	*/
	public MMMyFrame() {
		super("Grid_Layout");
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(4,4));
		for (int i=0; i<16; i++) {
			c.add(new JButton(String.valueOf(i)));
		}
		setSize(300, 300);
		setVisible(true);
	}
}

public class Es5 {
	public static void main (String args[]) {
		MMMyFrame win = new MMMyFrame();
	}
}