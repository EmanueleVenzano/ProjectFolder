import javax.swing.*;
import java.awt.*;

class MMyFrame extends JFrame {
	JButton uno = new JButton ("Uno");
	JButton due = new JButton ("Due");
	JButton tre = new JButton ("Tre");
	JButton quattro = new JButton ("Quattro");
	JButton cinque = new JButton ("Cinque");
	
	public MMyFrame() {
		super("Flow_Layout");
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		c.add(uno);
		c.add(due);
		c.add(tre);
		c.add(quattro);
		c.add(cinque);
		setSize(300, 100);
		setVisible(true);
	}
}

public class Es4 {
	public static void main (String args[]) {
		MMyFrame win = new MMyFrame();
	}
}