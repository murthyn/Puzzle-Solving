import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class RubikClockState implements State{

	private ArrayList<Integer> puzzleState;
	
	public RubikClockState(int[] state){ //Format: [buttonTL, buttonTR, buttonBL, buttonBR, TL, TTM, TR, TML, TMM, TMR, BL, TBM, BR, BTM, BML, BMM, BMR, BBM]
		puzzleState = new ArrayList<Integer>();
		//Buttons: 1 is up, 0 is down (all should be up at end). Clocks: Clock position (12, 1, 2, etc.)
		for(int i : state){
			puzzleState.add(i);
		}
	}
	
	public RubikClockState(ArrayList<Integer> inputState){
		ArrayList<Integer> inputStateArrayList = new ArrayList<Integer>();
		for(int i : inputState){
			inputStateArrayList.add(i);
		}
		puzzleState = inputStateArrayList;
	}
	
	public boolean equals(Object inputState){
		RubikClockState inputRubikState = (RubikClockState) inputState;
		boolean equal = true;
		if (puzzleState.size() == inputRubikState.puzzleState.size()){
		   for (int i = 0; i < puzzleState.size(); i++){
		      if (puzzleState.get(i) != inputRubikState.puzzleState.get(i)) equal = false;
		   }
		}
		else equal = false;
		return equal;
	}
	
	public ArrayList<Integer> getPuzzleState(){
		return puzzleState;
	}
	
	public static void main(String[] args){
		int[] array = new int[] {1, 1, 1, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12};
		RubikClockState stateI = new RubikClockState(array);
		stateI.display();
		stateI.performAction(new RubikClockAction(RubikClockAction.KNOB0CLOCK));
		stateI.display();
		System.out.println(stateI.heuristic());
	}
	
	public List<Action> listActions() {
		List<Action> possibleActions = new ArrayList<Action>();
		possibleActions.add(new RubikClockAction(RubikClockAction.BUTTONBL));
		possibleActions.add(new RubikClockAction(RubikClockAction.BUTTONBR));
		possibleActions.add(new RubikClockAction(RubikClockAction.BUTTONTL));
		possibleActions.add(new RubikClockAction(RubikClockAction.BUTTONTR));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB0COUNTER));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB0CLOCK));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB1CLOCK));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB1COUNTER));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB2COUNTER));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB2CLOCK));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB3CLOCK));
		possibleActions.add(new RubikClockAction(RubikClockAction.KNOB3COUNTER));
		return possibleActions;
	}

	public boolean isGoalState() {
		ArrayList<Integer> goalState = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++){
			goalState.add(1);
		}
		for(int i = 0; i < 14; i++){
			goalState.add(12);
		}
		//System.out.println("Reached goal state! This comment is in RubikClockState within isGoalState()");
		boolean equal = true;
		if (puzzleState.size() == goalState.size()){
		   for (int i = 0; i < puzzleState.size(); i++){
		      if (puzzleState.get(i) != goalState.get(i)) equal = false;
		   }
		}
		else equal = false;
		return equal;
	}

	public void display() {
		System.out.println(puzzleState.get(0) + "        " + puzzleState.get(1));
		for(int i = 0; i < 3; i++){
			System.out.println(makeDisplayStrings(puzzleState.get(3*i+4), puzzleState.get(3*i+5), puzzleState.get(3*i+6)));
		}
		System.out.println(puzzleState.get(2) + "        " + puzzleState.get(3));
		
		System.out.println(makeDisplayStrings(puzzleState.get(4), puzzleState.get(13), puzzleState.get(6)));
		System.out.println(makeDisplayStrings(puzzleState.get(14), puzzleState.get(15), puzzleState.get(16)));
		System.out.println(makeDisplayStrings(puzzleState.get(10), puzzleState.get(17), puzzleState.get(12)));
		System.out.println();
		
	}
	
	private String makeDisplayStrings(int index1, int index2, int index3){
		String returnString = " ";
		if(puzzleState.get(index1).toString().length() == 1) returnString += index1 + "  ";
		else returnString += index1 + " ";
		if(puzzleState.get(index2).toString().length() == 1) returnString += index2 + "  ";
		else returnString += index2 + " ";
		if(puzzleState.get(index3).toString().length() == 1) returnString += index3 + "  ";
		else returnString += index3 + " ";
		return returnString;	
	}

	public State duplicate() {
		RubikClockState duplicateState = new RubikClockState(puzzleState);
		return duplicateState;
	}

	public void performAction(Action action) {
		RubikClockAction rubikAction = (RubikClockAction) action;
		int b0 = puzzleState.get(0);
		int b1 = puzzleState.get(1);
		int b2 = puzzleState.get(2);
		int b3 = puzzleState.get(3);
		//BUTTONS (reverse what is is at the moment)
		if(rubikAction.getCurrentAction() == RubikClockAction.BUTTONTL){
			if(b0 == 0) puzzleState.set(0, 1);
			if(b0 == 1) puzzleState.set(0, 0);
		}
		else if(rubikAction.getCurrentAction() == RubikClockAction.BUTTONTR){
			if(b1 == 0) puzzleState.set(1, 1);
			if(b1 == 1) puzzleState.set(1, 0);
		}
		else if(rubikAction.getCurrentAction() == RubikClockAction.BUTTONBL){
			if(b2 == 0) puzzleState.set(2, 1);
			if(b2 == 1) puzzleState.set(2, 0);
		}
		else if(rubikAction.getCurrentAction() == RubikClockAction.BUTTONBR){
			if(b3 == 0) puzzleState.set(3, 1);
			if(b3 == 1) puzzleState.set(3, 0);
		}
		
		//CLOCKS: Clockwise
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB0CLOCK){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.incrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 4; i < 12; i ++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(10);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(6);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				this.incrementClock(4);
				this.decrementClock(13);
				this.decrementClock(15);
				this.decrementClock(16);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(5);
				this.incrementClock(7);
				this.incrementClock(8);
				this.incrementClock(10);
				this.incrementClock(11);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(5);
				this.incrementClock(6);
				this.incrementClock(7);
				this.incrementClock(8);
				this.incrementClock(9);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(6);
				this.decrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.decrementClock(13);
				this.incrementClock(4);
				this.decrementClock(15);
				this.decrementClock(16);
				this.decrementClock(17);
				this.incrementClock(10);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.incrementClock(6);
				this.decrementClock(13);
				this.incrementClock(4);
				this.decrementClock(14);
				this.decrementClock(15);
				this.decrementClock(16);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.incrementClock(6);
				this.incrementClock(4);
				this.incrementClock(10);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(10);
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(5);
				this.incrementClock(7);
				this.incrementClock(8);
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(6);
				this.incrementClock(10);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
		}
		
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB1CLOCK){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.incrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 4; i < 12; i ++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(10);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				this.incrementClock(6);
				this.decrementClock(13);
				this.decrementClock(14);
				this.decrementClock(15);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 5; i < 13; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(12);
				this.decrementClock(13);
				this.decrementClock(14);
				this.decrementClock(15);
				this.decrementClock(17);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(5);
				this.incrementClock(6);
				this.incrementClock(7);
				this.incrementClock(8);
				this.incrementClock(9);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
				this.incrementClock(6);
				this.incrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 5; i < 12; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.incrementClock(5);
				this.incrementClock(6);
				this.incrementClock(8);
				this.incrementClock(9);
				this.incrementClock(11);
				this.incrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.incrementClock(6);
				this.decrementClock(13);
				this.incrementClock(4);
				this.decrementClock(14);
				this.decrementClock(15);
				this.decrementClock(16);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.incrementClock(6);
				this.incrementClock(4);
				this.incrementClock(10);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(5);
				this.incrementClock(6);
				this.incrementClock(8);
				this.incrementClock(9);
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(10);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(6);
				this.incrementClock(10);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
		}
		
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB2CLOCK){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.incrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 4; i < 12; i ++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				this.incrementClock(10);
				this.decrementClock(15);
				this.decrementClock(16);
				this.decrementClock(17);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(6);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 5; i < 13; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(5);
				this.incrementClock(7);
				this.incrementClock(8);
				this.incrementClock(10);
				this.incrementClock(11);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(10);
				this.incrementClock(12);
				this.decrementClock(14);
				this.decrementClock(15);
				this.decrementClock(16);
				this.decrementClock(17);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
				this.incrementClock(6);
				this.incrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 5; i < 12; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.decrementClock(13);
				this.incrementClock(4);
				this.decrementClock(15);
				this.decrementClock(16);
				this.decrementClock(17);
				this.incrementClock(10);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.incrementClock(7);
				this.incrementClock(8);
				this.incrementClock(9);
				this.incrementClock(10);
				this.incrementClock(11);
				this.incrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.incrementClock(6);
				this.incrementClock(4);
				this.incrementClock(10);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(7);
				this.incrementClock(8);
				this.incrementClock(10);
				this.incrementClock(11);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(10);
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(10);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(6);
				this.incrementClock(10);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
		}
		
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB3CLOCK){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.incrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				this.incrementClock(12);
				this.decrementClock(14);
				this.decrementClock(15);
				this.decrementClock(17);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(10);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(6);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 5; i < 13; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(12);
				this.decrementClock(13);
				this.decrementClock(14);
				this.decrementClock(15);
				this.decrementClock(17);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(10);
				this.incrementClock(12);
				this.decrementClock(14);
				this.decrementClock(15);
				this.decrementClock(16);
				this.decrementClock(17);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.incrementClock(i);
				}
				this.decrementClock(6);
				this.decrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.incrementClock(5);
				this.incrementClock(6);
				this.incrementClock(8);
				this.incrementClock(9);
				this.incrementClock(11);
				this.incrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.incrementClock(7);
				this.incrementClock(8);
				this.incrementClock(9);
				this.incrementClock(10);
				this.incrementClock(11);
				this.incrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.incrementClock(8);
				this.incrementClock(9);
				this.incrementClock(11);
				this.incrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.incrementClock(10);
				this.incrementClock(4);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(6);
				this.incrementClock(10);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.incrementClock(4);
				this.incrementClock(6);
				this.incrementClock(10);
				this.incrementClock(12);
				for(int i = 13; i < 18; i++){
					this.decrementClock(i);
				}
			}
		}
		
		//CLOCKS: Counterclockwise
		
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB0COUNTER){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.decrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 4; i < 12; i ++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.incrementClock(10);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.incrementClock(6);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				this.decrementClock(4);
				this.incrementClock(13);
				this.incrementClock(15);
				this.incrementClock(16);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(5);
				this.decrementClock(7);
				this.decrementClock(8);
				this.decrementClock(10);
				this.decrementClock(11);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(5);
				this.decrementClock(6);
				this.decrementClock(7);
				this.decrementClock(8);
				this.decrementClock(9);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.incrementClock(6);
				this.incrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.incrementClock(13);
				this.decrementClock(4);
				this.incrementClock(15);
				this.incrementClock(16);
				this.incrementClock(17);
				this.decrementClock(10);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.decrementClock(6);
				this.incrementClock(13);
				this.decrementClock(4);
				this.incrementClock(14);
				this.incrementClock(15);
				this.incrementClock(16);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.decrementClock(6);
				this.decrementClock(4);
				this.decrementClock(10);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(10);
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(5);
				this.decrementClock(7);
				this.decrementClock(8);
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(6);
				this.decrementClock(10);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
		}
		
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB1COUNTER){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.decrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 4; i < 12; i ++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.incrementClock(10);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				this.decrementClock(6);
				this.incrementClock(13);
				this.incrementClock(14);
				this.incrementClock(15);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 5; i < 13; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(12);
				this.incrementClock(13);
				this.incrementClock(14);
				this.incrementClock(15);
				this.incrementClock(17);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(5);
				this.decrementClock(6);
				this.decrementClock(7);
				this.decrementClock(8);
				this.decrementClock(9);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
				this.decrementClock(6);
				this.decrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 5; i < 12; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.decrementClock(5);
				this.decrementClock(6);
				this.decrementClock(8);
				this.decrementClock(9);
				this.decrementClock(11);
				this.decrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.decrementClock(6);
				this.incrementClock(13);
				this.decrementClock(4);
				this.incrementClock(14);
				this.incrementClock(15);
				this.incrementClock(16);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.decrementClock(6);
				this.decrementClock(4);
				this.decrementClock(10);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(5);
				this.decrementClock(6);
				this.decrementClock(8);
				this.decrementClock(9);
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(10);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(6);
				this.decrementClock(10);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
		}
		
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB2COUNTER){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.decrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 4; i < 12; i ++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				this.decrementClock(10);
				this.incrementClock(15);
				this.incrementClock(16);
				this.incrementClock(17);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.decrementClock(6);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 5; i < 13; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(5);
				this.decrementClock(7);
				this.decrementClock(8);
				this.decrementClock(10);
				this.decrementClock(11);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(10);
				this.decrementClock(12);
				this.incrementClock(14);
				this.incrementClock(15);
				this.incrementClock(16);
				this.incrementClock(17);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
				this.decrementClock(6);
				this.decrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				for(int i = 5; i < 12; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.incrementClock(13);
				this.decrementClock(4);
				this.incrementClock(15);
				this.incrementClock(16);
				this.incrementClock(17);
				this.decrementClock(10);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.decrementClock(7);
				this.decrementClock(8);
				this.decrementClock(9);
				this.decrementClock(10);
				this.decrementClock(11);
				this.decrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.decrementClock(6);
				this.decrementClock(4);
				this.decrementClock(10);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(7);
				this.decrementClock(8);
				this.decrementClock(10);
				this.decrementClock(11);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(10);
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(10);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(6);
				this.decrementClock(10);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
		}
			
		else if(rubikAction.getCurrentAction() == RubikClockAction.KNOB3COUNTER){
			if(b0 == 1 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i ++){
					this.decrementClock(i);
				}
			}
			else if (b0 == 1 && b1 == 1 && b2 == 1 && b3 == 0){
				this.decrementClock(12);
				this.incrementClock(14);
				this.incrementClock(15);
				this.incrementClock(17);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.incrementClock(10);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.incrementClock(6);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 1){
				for(int i = 5; i < 13; i++){
					this.decrementClock(i);
				}
			}
			else if(b0 == 1 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(12);
				this.incrementClock(13);
				this.incrementClock(14);
				this.incrementClock(15);
				this.incrementClock(17);
			}
			else if(b0 == 1 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(10);
				this.decrementClock(12);
				this.incrementClock(14);
				this.incrementClock(15);
				this.incrementClock(16);
				this.incrementClock(17);
			}
			else if(b0 == 1 && b1 == 0 && b2 == 0 && b3 == 1){
				for(int i = 4; i < 13; i++){
					this.decrementClock(i);
				}
				this.incrementClock(6);
				this.incrementClock(10);
			}
			else if(b0 == 0 && b1 == 1 && b2 == 1 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 1){
				this.decrementClock(5);
				this.decrementClock(6);
				this.decrementClock(8);
				this.decrementClock(9);
				this.decrementClock(11);
				this.decrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 1){
				this.decrementClock(7);
				this.decrementClock(8);
				this.decrementClock(9);
				this.decrementClock(10);
				this.decrementClock(11);
				this.decrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 0 && b3 == 1){
				this.decrementClock(8);
				this.decrementClock(9);
				this.decrementClock(11);
				this.decrementClock(12);
			}
			else if(b0 == 0 && b1 == 0 && b2 == 1 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b0 == 0 && b1 == 1 && b2 == 0 && b3 == 0){
				this.decrementClock(10);
				this.decrementClock(4);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b1 == 1 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(6);
				this.decrementClock(10);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
			else if(b1 == 0 && b1 == 0 && b2 == 0 && b3 == 0){
				this.decrementClock(4);
				this.decrementClock(6);
				this.decrementClock(10);
				this.decrementClock(12);
				for(int i = 13; i < 18; i++){
					this.incrementClock(i);
				}
			}
		}	
	}
	
	private void incrementClock(int index){
		if(puzzleState.get(index) == 12){
			puzzleState.set(index, 1);
		}
		else{
			puzzleState.set(index, puzzleState.get(index) + 1);
		}
	}
	
	private void decrementClock(int index){
		if(puzzleState.get(index) == 1){
			puzzleState.set(index, 12);
		}
		else{
			puzzleState.set(index, puzzleState.get(index) - 1);
		}
	}

	public int heuristic() {
		int heuristic = 0;
		
		for(int i = 4; i < puzzleState.size(); i++){
			if(puzzleState.get(i) > 6){
				heuristic += Math.abs(12 - puzzleState.get(i));
			}
			else{
				heuristic += puzzleState.get(i);
			}
		}
//		int b0 = puzzleState.get(0);
//		int b1 = puzzleState.get(1);
//		int b2 = puzzleState.get(2);
//		int b3 = puzzleState.get(3);
//		int sum = b0 + b1 + b2 + b3;
//		
//		if(sum == 0 || sum == 4){
			heuristic = heuristic / 9;
//		}
//		else if(sum == 3 || sum == 1){
//			heuristic = heuristic / 8;
//		}
//		else if(sum == 2){
//			heuristic = heuristic / 7;
//		}
		
		for(int i = 0; i < 4; i++){
			heuristic += Math.abs(1-puzzleState.get(i));
		}
		
		return heuristic;
	}
	
	
}
