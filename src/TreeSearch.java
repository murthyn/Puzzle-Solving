import java.util.ArrayList;
import java.util.List;

abstract class TreeSearch implements Search{
	
	private Fringe fringe;
		
	TreeSearch(Fringe inputFringe){
		fringe = inputFringe;
		fringe.clear();
	}
	
	public Solution search(State initialState){
		fringe.clear();
		fringe.insert(new SearchNode(initialState, null, null, 0, 0));
		//initialState.display(); //TODO REMOVE
		while (!fringe.isEmpty()){
			SearchNode currentNode = fringe.removeFirst();
			//System.out.println("Current Node State: ");
			//currentNode.getStateNode().display();
		//	System.out.println(" ");
			if (currentNode.getStateNode().isGoalState()){
				List<SearchNode> solutionPath = new ArrayList<SearchNode>();
				State finalState = currentNode.getStateNode();
				while(currentNode.getParentNode() != null){
					solutionPath.add(currentNode);
					currentNode = currentNode.getParentNode();
				}
				return new Solution(finalState, currentNode.getStateNode(), solutionPath);
			}
			//System.out.println(currentNode.getStateNode().listActions());
			for (Action appliedAction : currentNode.getStateNode().listActions()){		
				//System.out.println("Applied Action: ");
				//appliedAction.display(); //TODO REMOVE
				State duplicateState = currentNode.getStateNode().duplicate();
				//System.out.println("State before preforming action: ");
				//duplicateState.display();
				duplicateState.performAction(appliedAction);
				//System.out.println("State after preforming action: ");
				//duplicateState.display();
				SearchNode newChildSearchNode = new SearchNode(duplicateState, currentNode, appliedAction, currentNode.getCost() + appliedAction.getCost(), currentNode.getDepth() + 1);
				//LINE ABOVE IS BAD
				//System.out.println("Should prune? "+ prune(newChildSearchNode));
				if(!prune(newChildSearchNode)){
					fringe.insert(newChildSearchNode);
				}
			}
			//System.out.println("GOT HERE TREE SEARCH");
			
		}
		return null;
	}
	boolean prune(SearchNode nodePrune){
		return false;
	}
	
	
	
	
	
	/*
	 * TreeSearch(Fringe)
          boolean pruneThisNode(SearchNode) (returns false)
          Solution search(State startState)
              runs pseudocode algorithm above
	 
	 *Pseudocode for TreeSearch:
start with an empty fringe
insert a node containing the start-state into the fringe
repeat until the fringe is empty:
remove the first node from the fringe
if it contains the goal state, then we're done - return a solution!
otherwise, repeat for each child state that can be reached via a single action:
make a new search node for this child state
if the new node should not be pruned
then add it to the fringe
if the fringe is empty, then fail
	 *
	 */
	
}
