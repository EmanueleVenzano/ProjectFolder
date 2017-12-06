import java.util.ArrayList;
import java.util.Stack;

public class my_exploration {
	private GridWorld space;
	public ArrayList<GridWorld.Coordinate> minPath;
	private Stack<GridWorld.Coordinate> accumulator;
	
	public my_exploration (int size, double density, long seed) {
		space = new GridWorld(size, density, seed);
		minPath = new ArrayList<GridWorld.Coordinate>();
		accumulator = new Stack<GridWorld.Coordinate>();
	}
	
	public ArrayList<ArrayList<GridWorld.Coordinate>> findAllPath() throws pathError {
		ArrayList<ArrayList<GridWorld.Coordinate>> percorsi = new ArrayList<ArrayList<GridWorld.Coordinate>>();
		ArrayList<GridWorld.Coordinate> temp = new ArrayList<GridWorld.Coordinate>();
		do {
			GridWorld.Coordinate actualCoordinate = space.getCurrentCell();
			if (temp.size()==0||!actualCoordinate.equals(temp.get(temp.size()-1))) {
				temp.add(actualCoordinate);
			}
			if(space.targetReached()) {
				ArrayList<GridWorld.Coordinate> toAdd = new ArrayList<GridWorld.Coordinate>();
				for (int i=0; i<temp.size(); i++) {
					GridWorld.Coordinate tc = temp.get(i);
					toAdd.add(tc);
				}
				percorsi.add(toAdd);
				temp.remove(temp.size()-1);
				if (temp.size()>0) {
					moveTo(actualCoordinate, temp.get(temp.size()-1));
				}
			}
			ArrayList<GridWorld.Coordinate> chooses = new ArrayList<GridWorld.Coordinate>();
			for (GridWorld.Coordinate s: space.getAdjacentFreeCells()) {
				chooses.add(s);
			}
			int deleted=0;
			if (!accumulator.empty()) {
				for (int i=0; i<chooses.size(); i++) {
					if (chooses.get(i).equals(accumulator.peek())) {
						chooses.remove(i);
						i--;
						deleted++;
					}
				}
			}
			GridWorld.Coordinate nextCoordinate = null;
			for (int i=0; i<chooses.size(); i++) {
				int flag=0;
				for (int j=temp.size()-2; j>=0; j--) {
					if (chooses.get(i).equals(temp.get(j))) {
						flag=1;
						break;
					}
				}
				if (flag==1) {
					continue;
				}
				nextCoordinate = chooses.get(i);
				break;
			}
			if (nextCoordinate!=null) {
				accumulator.push(nextCoordinate);
				moveTo(actualCoordinate, nextCoordinate);
			}else {
				for (int i=0; i<deleted; i++) {
					accumulator.pop();
				}
				temp.remove(temp.size()-1);
				if (temp.size()>0) {
					moveTo(actualCoordinate, temp.get(temp.size()-1));
				}
			}
		} while (!accumulator.empty());
		if (percorsi.size()==0) {
			throw new pathError("No path found");
		}
		return percorsi;
	}
	
	public void findMinPath() {
		ArrayList<GridWorld.Coordinate> temp = new ArrayList<GridWorld.Coordinate>();
		do {
			GridWorld.Coordinate actualCoordinate = space.getCurrentCell();
			if (temp.size()==0||!actualCoordinate.equals(temp.get(temp.size()-1))){
				temp.add(actualCoordinate);
			}
			if(space.targetReached()) {
				minPath=temp;
				break;
			}
			if (temp.size()>=space.getMinimumDistanceToTarget()+1) {
				temp.remove(temp.size()-1);
				moveTo(actualCoordinate, temp.get(temp.size()-1));
			}
			ArrayList<GridWorld.Coordinate> chooses = new ArrayList<GridWorld.Coordinate>();
			for (GridWorld.Coordinate s: space.getAdjacentFreeCells()) {
				chooses.add(s);
			}
			int deleted=0;
			if (!accumulator.empty()) {
				for (int i=0; i<chooses.size(); i++) {
					if (chooses.get(i).equals(accumulator.peek())) {
						chooses.remove(i);
						i--;
						deleted++;
					}
				}
			}
			GridWorld.Coordinate nextCoordinate = null;
			for (int i=0; i<chooses.size(); i++) {
				int flag=0;
				for (int j=temp.size()-2; j>=0; j--) {
					if (chooses.get(i).equals(temp.get(j))) {
						flag=1;
						break;
					}
				}
				if (flag==1) {
					continue;
				}
				nextCoordinate = chooses.get(i);
				break;
			}
			if (nextCoordinate!=null) {
				accumulator.push(nextCoordinate);
				moveTo(actualCoordinate, nextCoordinate);
			}else {
				for (int i=0; i<deleted; i++) {
					accumulator.pop();
				}
				temp.remove(temp.size()-1);
				if (temp.size()>0) {
					moveTo(actualCoordinate, temp.get(temp.size()-1));
				}
			}
		} while (!accumulator.empty());
		if (minPath.size()==0) {
			throw new pathError("No path found");
		}		
	}
	
	public void moveTo (GridWorld.Coordinate by, GridWorld.Coordinate to) {
		if (by.col==to.col) {
			if (by.row==to.row+1) {
				space.moveToAdjacentCell(GridWorld.Direction.NORTH);
			}else {
				space.moveToAdjacentCell(GridWorld.Direction.SOUTH);
			}
		}else if (by.col==to.col+1) {
			space.moveToAdjacentCell(GridWorld.Direction.WEST);
		}else {
			space.moveToAdjacentCell(GridWorld.Direction.EAST);
		}
	}
	
	public void printSpace() {
		space.print();
	}
	
	public void print() throws pathError {
		if (minPath.size()==0) {
			throw new pathError("Peth length zero");
		}
		String res = "Percorso:";
		for (int i=0; i<minPath.size(); i++) {
			res+=minPath.get(i).toString();
		}
		System.out.println(res);
	}
	
	public void print(ArrayList<GridWorld.Coordinate> list) throws pathError {
		if (list.size()==0) {
			throw new pathError("Peth length zero");
		}
		String res = "Percorso:";
		for (int i=0; i<list.size(); i++) {
			res+=list.get(i).toString();
		}
		System.out.println(res);
	}
}
