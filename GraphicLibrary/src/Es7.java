import javax.swing.*;
import java.awt.*;

class MyFrameee extends JFrame {
	JPanel nordPnl = new JPanel();
	JPanel centroPnl = new JPanel();
	JPanel sudPnl = new JPanel();
	
	JLabel infoLbl = new Label ("Selezionare:");
	
	JCheckBox opz1Chk = new JCheckBox ("Opz1");
	JCheckBox opz2Chk = new JCheckBox ("Opz2");
	
	JButton okBtn = new JButton ("OK");
	JButton cancBtn = new JButton ("Annulla");
	
	public MyFrameee() {
		super("Esempio");
		
		centroPnl.setLayout(new GridLayout (2,1));
		centroPnl.add(opz1Chk);
		centroPnl.add(opz2Chk);
		
		nordPnl.add(infoLbl);
		
		sudPnl.add(okBtn);
		sudPnl.add(cancBtn);
		
		getContentPane().add(nordPnl,BorderLayout.NORTH);
		getContentPane().add(centroPnl,BorderLayout.CENTER);
		getContentPane().add(sudPnl,BorderLayout.SOUTH);
		
		pack();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.getWidth()-this.getWidth())/2,dim.getHeight()-this.getHeight()/2);
		setVisible(true);
	}
}

public class Es7 {
	public static void main (String args[]) {
		MyFrameee win = new MyFrameee();
	}
}