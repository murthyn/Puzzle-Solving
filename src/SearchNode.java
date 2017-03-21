public class SearchNode implements Comparable{
	
	private State stateNode;
	private SearchNode parentNode;
	private Action appliedAction;
	private int cost;
	private int depth;
	private int evaluate;
	
	SearchNode(State inputState, SearchNode inputParentNode, Action inputappliedAction, int inputCost, int inputDepth){
		stateNode = inputState;
		parentNode = inputParentNode;
		appliedAction = inputappliedAction;
		cost = inputCost;
		depth = inputDepth;
		evaluate = stateNode.heuristic() + cost;
	}
	
	public State getStateNode(){
		return stateNode;
	}
	public SearchNode getParentNode(){
		return parentNode;
	}
	public Action getAppliedAction(){
		return appliedAction;
	}
	public int getCost(){
		return cost;
	}
	public int getDepth(){
		return depth;
	}
	
	public int evaluate(){
		return evaluate;
	}
	
	public int compareTo(Object otherNode){
		SearchNode inputNode = (SearchNode) otherNode;
		if(inputNode.evaluate() > this.evaluate()){
			return -1;
		}
		else if(inputNode.evaluate() < this.evaluate()){
			return 1;
		}
		else{
			return 0;
		}
	}
}
