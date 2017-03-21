import java.util.*;

public class FringeStack implements Fringe{

	private Stack<SearchNode> fringe = new Stack<SearchNode>();
	
	public void clear() {
		fringe.clear();
	}

	public boolean isEmpty() {
		return fringe.isEmpty();
	}

	public void insert(SearchNode node) {
		fringe.add(node);
	}
	
	//TODO Maybe change this?
	public SearchNode removeFirst() {
		return fringe.pop();
	}

}
