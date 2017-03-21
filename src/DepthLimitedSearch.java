
public class DepthLimitedSearch extends TreeSearch{
	private int depthLimit;
	
	DepthLimitedSearch(int inputDepthLimit){
		super(new FringeStack());
		depthLimit = inputDepthLimit;
	}

	boolean prune(SearchNode inputNode){
		if (inputNode.getDepth() > depthLimit){
			return true;
		}
		if (inputNode.getParentNode() != null && inputNode.getParentNode().getParentNode() != null){
			if (inputNode.getParentNode().getParentNode().getStateNode().equals(inputNode.getStateNode())){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		return "DL-DFS";
	}	
}
