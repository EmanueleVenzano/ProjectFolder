import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class CenteredFrame extends JFrame{
	public CenteredFrame() {
		//acquisire le dimensioni dello schermo
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		//centra il frame nello schermo
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		
		//imposta l'icona e il titolo del frame
		Image img = kit.getImage("icon.gif");
		setIconImage(img);
		setTitle("CenteredFrame");
	}
}

public class Es02 {
	public static void main (String[] args){
		CenteredFrame frame = new CenteredFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
