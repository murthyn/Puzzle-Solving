
public class IterativeDeepeningSearch implements Search{
	private int maxDepth;
	
	IterativeDeepeningSearch(int inputMaxDepth){
		maxDepth = inputMaxDepth;
	}

	public Solution search(State startState) {
		for (int i = 1; i <= maxDepth; i ++){
			DepthLimitedSearch dls = new DepthLimitedSearch(i);
			if(dls.search(startState) != null){
				return dls.search(startState);
			}
		}
		return null;
	}	
	
	public String toString(){
		return "ID-DFS";
	}
}
