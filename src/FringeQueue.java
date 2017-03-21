import java.util.*;

public class FringeQueue implements Fringe{

	private Queue<SearchNode> fringe = new LinkedList<SearchNode>();
	
	public void clear() {
		fringe.clear();	
	}

	public boolean isEmpty() {
		return fringe.isEmpty();
	}

	@Override
	public void insert(SearchNode node) {
		fringe.add(node);
	}

	@Override
	public SearchNode removeFirst() {
		return fringe.remove();
	}

}
