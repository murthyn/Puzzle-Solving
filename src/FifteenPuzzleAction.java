
public class FifteenPuzzleAction implements Action{

	public static final int DOWN = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int LEFT = 3;
	
	private int currentAction;
	
	//list of possible actions
	FifteenPuzzleAction(int inputAction){
		currentAction = inputAction;
	}
	
	public void display() {
		if (currentAction == 0){
			System.out.println("Action: Down");
		}
		else if (currentAction == 1){
			System.out.println("Action: Up");
		}
		else if (currentAction == 2){
			System.out.println("Action: Right");
		}
		else if (currentAction == 3){
			System.out.println("Action: Left");
		}
		else{
			System.out.println("Invalid action: Action is not valid");
		}
	}

	public int getCurrentAction(){
		return currentAction;
	}
	
	public int getCost() {
		return 1;
	}

}
