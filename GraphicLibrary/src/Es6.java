import javax.swing.*;
import java.awt.*;

class MyFramee extends JFrame {
	JButton nord = new JButton ("Nord");
	JButton centro = new JButton ("Centro");
	JButton ovest = new JButton ("Ovest");
	JButton sud1 = new JButton ("Sud1");
	JButton sud2 = new JButton ("Sud2");
	
	public MyFramee() {
		super("Border_Layout");
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(nord,BorderLayout.NORTH);
		c.add(centro,BorderLayout.CENTER);
		c.add(ovest,BorderLayout.WEST);
		c.add(sud1,BorderLayout.SOUTH);
		c.add(sud2,BorderLayout.SOUTH);
		setSize(300, 300);
		setVisible(true);
	}
}

public class Es6 {
	public static void main (String args[]) {
		MyFramee win = new MyFramee();
	}
}