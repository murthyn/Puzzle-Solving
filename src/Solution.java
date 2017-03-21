import java.util.List;

public class Solution {
	
	State startState;
	State finalState;
	List<SearchNode> path;
	
	Solution(State inputStartState, State inputFinalState, List<SearchNode> inputPath){
		startState = inputStartState;
		finalState = inputFinalState;
		path = inputPath;
	}
	
	public void displayFinal(){
		finalState.display();
	}
	
	public void displayStart(){
		startState.display();
	}
	
	public void displayPath(){
		for (SearchNode i : path){
			i.getStateNode().display();
			i.getAppliedAction().display();
		}
		
	}
	
	/*
	 * Solution (State startState, State finalState, List<searchnode> path)
          method to display final state
          method to display path
          method to return the path length or cost

	 */
}
