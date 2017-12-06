import java.util.ArrayList;

public class myMint {
	public static void main (String[] args) {
		my_exploration me = new my_exploration (Integer.parseInt(args[0]), Double.parseDouble(args[1]), Long.parseLong(args[2]));
		me.printSpace();
		System.out.println("_____________");
		me.findMinPath();
		System.out.println("_____________");
		me.print();
		System.out.println("_____________");
		me = new my_exploration (Integer.parseInt(args[0]), Double.parseDouble(args[1]), Long.parseLong(args[2]));
		ArrayList<ArrayList<GridWorld.Coordinate>> allPath = me.findAllPath();
		for (int i=0; i<allPath.size(); i++) {
			me.print(allPath.get(i));
		}
	}

}
