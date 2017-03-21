import java.util.*;

public class FringePriorityQueue implements Fringe{

	private PriorityQueue<SearchNode> fringe = new PriorityQueue<SearchNode>();
	
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
		return fringe.poll();
	}
}
