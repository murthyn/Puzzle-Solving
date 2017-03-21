
public class BreadthFirstSearch extends TreeSearch{
	BreadthFirstSearch(){
		super(new FringeQueue());
	}
	
	public String toString(){
		return "BFS";
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
