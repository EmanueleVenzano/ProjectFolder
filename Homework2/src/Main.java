
/**
 * 
 * @author MM
 * Classe che realizza un algoritmo di navigazione utilizzando il simulatore GridWorld
 * Il simulatore è già stato incluso nel progetto. 
 * Non saranno forniti i sorgenti di Gridworld
 * Fare riferimento alla documentazione presente in doc (index.html)
 *
 */

public class Main {
public static void main(String[] args) {
	// Costruttore Gridworld
	GridWorld gw = new GridWorld(10,0.1,1500);
	gw.print();
	System.out.println("everything seems ok.");

}
}
