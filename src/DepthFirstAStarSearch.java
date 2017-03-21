
public class DepthFirstAStarSearch extends TreeSearch{

	private int depthLimit;
	private int nextBiggestNodeEvaluate;
	
	DepthFirstAStarSearch(int inputDepthLimit){
		super(new FringeStack());
		depthLimit = inputDepthLimit;
		nextBiggestNodeEvaluate = Integer.MAX_VALUE;
	}

	boolean prune(SearchNode inputNode){
		int inputNodeEvaluate = inputNode.evaluate();
		if (inputNodeEvaluate > depthLimit){
			if(inputNodeEvaluate < nextBiggestNodeEvaluate){
				nextBiggestNodeEvaluate = inputNodeEvaluate;
			}
			return true;
		}
		if (inputNode.getParentNode() != null && inputNode.getParentNode().getParentNode() != null){
			if (inputNode.getParentNode().getParentNode().getStateNode().equals(inputNode.getStateNode())){
				return true;
			}
		}
		return false;
	}
	
	int getNextBiggestNodeEvaluate(){
		return nextBiggestNodeEvaluate;
	}
	
}
