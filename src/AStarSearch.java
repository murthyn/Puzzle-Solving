import java.util.PriorityQueue;

public class AStarSearch extends TreeSearch{
	AStarSearch(){
		super(new FringePriorityQueue());
	}
	
	public String toString(){
		return "A*";
	}
	
	boolean prune(SearchNode nodePrune){
		if (nodePrune.getParentNode() != null && nodePrune.getParentNode().getParentNode() != null){
			if (nodePrune.getParentNode().getParentNode().getStateNode().equals(nodePrune.getStateNode())){
				return true;
			}
		}
		return false;
	}
}
