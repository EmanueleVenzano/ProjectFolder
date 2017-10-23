import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {
	JLabel jl = new JLabel ("Buona Lezione");
	
	public MyFrame() {
		super ("Prima Finestra");
		Container c = this.getContentPane();
		c.add(jl);
		this.setSize(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

public class Es3 {
	public static void main (String args[]) {
		MyFrame win = new MyFrame();
	}
}