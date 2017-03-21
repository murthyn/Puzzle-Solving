import java.util.ArrayList;
import java.util.List;

public class FifteenPuzzleState implements State{

	private long puzzleState;
	
	public FifteenPuzzleState(int[] inputpuzzleState){
		int counter = 0;
		for(int num : inputpuzzleState){
			puzzleState = setTile(puzzleState, counter, num);
			counter += 1;
		}
		//System.out.println(puzzleState); //TODO REMOVE
	}
	
	public FifteenPuzzleState(long inputpuzzleState){
		puzzleState = inputpuzzleState;
	}
	
	public boolean equals(Object inputState){
		FifteenPuzzleState inputFifteenState = ((FifteenPuzzleState) inputState);
		return puzzleState == inputFifteenState.puzzleState;
	}
	
	public long getPuzzleState(){
		return puzzleState;
	}
	
	private static int getTile(long n, int tileIndex){
		return (int) ((n >> (4 * tileIndex)) & 15L);
	}
	
	private static long setTile(long n, int tileIndex, long set){
		n = ((~(15L << (4 * tileIndex))) & n); //cleared tile at tileIndex
		return ((set << (4 * tileIndex)) | n);
	}
	
	public static void main(String[]args){
//		System.out.println("Setting n to 5");
//		long n = setTile(0, 0, 5);
//		System.out.println(Long.toHexString(n));
//		System.out.println("Changing n to n with 15 in the 6th index");
//		n = setTile(n, 6, 15);
//		System.out.println(Long.toHexString(n));
//		System.out.println("Getting the 5th index. Should be 0.");
//		System.out.println(Integer.toHexString(getTile(n, 5)));
//		System.out.println("Getting the 6th index. Should be 15 (f).");
//		System.out.println(Integer.toHexString(getTile(n, 6)));
//		System.out.println("Setting 7th index to 5");
//		n = setTile(n, 7, 5);
//		System.out.println("Getting the 7th index. Should be 5");
//		System.out.println(Integer.toHexString(getTile(n, 7)));		
//		
//		System.out.println("Setting 8th index to 11");
//		n = setTile(n, 8, 11);
//		System.out.println("Getting the 8th index. Should be 11");
//		System.out.println(Integer.toHexString(getTile(n, 8)));		
//		
//		n = setTile(n, 10, 5);
//		System.out.println("Getting the 10th index. Should be 5");
//		System.out.println(Integer.toHexString(getTile(n, 10)));
//		
//		System.out.println("___");
//		
//		System.out.println("Setting 15th index to 6.");
//		n = setTile(n, 15, 6);
//		System.out.println("Getting 15th index. Should be 6");
//		System.out.println(Integer.toHexString(getTile(n, 15)));
		
		
	}
	
	public List<Action> listActions() {
		List<Action> possibleActions = new ArrayList<Action>();
		for (int i = 0; i < 16; i++){
			if (getTile(puzzleState, i) == 0){
				if (i >= 4){
					possibleActions.add(new FifteenPuzzleAction(FifteenPuzzleAction.DOWN));
				}
				if (i <= 11){
					possibleActions.add(new FifteenPuzzleAction(FifteenPuzzleAction.UP));
				}
				if (i % 4 != 0){
					possibleActions.add(new FifteenPuzzleAction(FifteenPuzzleAction.RIGHT));
				}
				if ((i + 1) % 4 != 0){
					possibleActions.add(new FifteenPuzzleAction(FifteenPuzzleAction.LEFT));
				}
			}
		}
		return possibleActions;
	}

	public boolean isGoalState() {
		long goalState = 0;
		for (int i = 0; i < 16; i++){
			goalState = setTile(goalState, i, i + 1);
		}
		goalState = setTile(goalState, 15, 0);
		return goalState == puzzleState;
	}

	public void display() {
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				System.out.print(getTile(puzzleState, 4*i + j));
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public State duplicate() {
		FifteenPuzzleState duplicateState = new FifteenPuzzleState(puzzleState);
		return duplicateState;
	}

	public void performAction(Action action) {
		FifteenPuzzleAction fifteenAction = (FifteenPuzzleAction) action;
		if (fifteenAction.getCurrentAction() == FifteenPuzzleAction.UP){
			for (int i = 0; i < 16; i++){
				if (getTile(puzzleState, i) == 0){
					puzzleState = setTile(puzzleState, i, getTile(puzzleState, i + 4));
					puzzleState = setTile(puzzleState, i + 4, 0);
					return;
				}
			}
		}
		else if (fifteenAction.getCurrentAction() == FifteenPuzzleAction.DOWN){
			for (int i = 0; i < 16; i++){
				if (getTile(puzzleState, i) == 0){
					puzzleState = setTile(puzzleState, i, getTile(puzzleState, i - 4));
					puzzleState = setTile(puzzleState, i - 4, 0);
					return;
				}
			}
		}
		else if (fifteenAction.getCurrentAction() == FifteenPuzzleAction.RIGHT){
			for (int i = 0; i < 16; i++){
				if (getTile(puzzleState, i) == 0){
					puzzleState = setTile(puzzleState, i, getTile(puzzleState, i - 1));
					puzzleState = setTile(puzzleState, i - 1, 0);
					return;
				}
			}
		}
		else if (fifteenAction.getCurrentAction() == FifteenPuzzleAction.LEFT){
			for (int i = 0; i < 16; i ++){
				if (getTile(puzzleState, i) == 0){
					puzzleState = setTile(puzzleState, i, getTile(puzzleState, i + 1));
					puzzleState = setTile(puzzleState, i + 1, 0);
					return;
				}
			}
		}
	}
	
	private int getXcoorGoal(int value){
		return value % 4;
	}
	private int getYcoorGoal(int value){
		return value / 4;
	}
	private int manhattenDistance(int x1, int y1, int x2, int y2){
		return Math.abs(x1-x2) + Math.abs(y1 - y2);
	}
	
	public int heuristic(){
		int heuristic = 0;
		int xcoor = 0;
		int ycoor = 0;
		for(int i = 0; i < 16; i++){
			int tile = getTile(puzzleState, i);
			heuristic += manhattenDistance(xcoor, ycoor, getXcoorGoal(tile), getYcoorGoal(tile));
			xcoor+=1;
			if(xcoor == 4){
				xcoor = 0;
				ycoor += 1;
			}
		}
		return heuristic;
	}

}
