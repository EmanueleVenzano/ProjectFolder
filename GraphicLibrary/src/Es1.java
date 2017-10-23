import javax.swing.*;
import java.awt.*;

public class Es1 {
	public static void main (String args[]) {
		
		JFrame win;
		win = new JFrame ("Prima Finestra");
		Container c = win.getContentPane();
		c.add(new JLabel ("Buona lezione"));
		win.setSize(200, 200);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}
}
 