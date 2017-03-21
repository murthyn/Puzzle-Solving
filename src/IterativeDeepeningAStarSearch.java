
public class IterativeDeepeningAStarSearch implements Search{
	
	IterativeDeepeningAStarSearch(){
		
	}
	
	
	public String toString(){
		return "IDA*";
	}

	@Override
	public Solution search(State startState) {
		SearchNode startNode = new SearchNode(startState, null, null, 0, 0);
		int currentEvaluate = startNode.evaluate();
		while(currentEvaluate != Integer.MAX_VALUE){
			DepthFirstAStarSearch dfas = new DepthFirstAStarSearch(currentEvaluate);
			Solution dfasSolution = dfas.search(startState);
			if(dfasSolution != null){
				return dfasSolution;
			}
			currentEvaluate = dfas.getNextBiggestNodeEvaluate();
		}
		return null;
	}
}
