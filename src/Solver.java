import java.util.Collections;

public class Solver {
	
	public static void main(String[]args){
//		int[] array = new int[] {9,5,2,4,0,3,7,8,1,11,6,12,13,10,14,15};
		
//		IterativeDeepeningAStarSearch searchIDAS = new IterativeDeepeningAStarSearch();
//		State stateIS = new FifteenPuzzleState(array);
//		solve(stateIS, searchIDAS);
		
//		BreadthFirstSearch searchB = new BreadthFirstSearch();
//		State stateB = new FifteenPuzzleState(array);
//		solve(stateB, searchB);
//		
//		IterativeDeepeningSearch searchI = new IterativeDeepeningSearch(80);
//		State stateI = new FifteenPuzzleState(array);
//		solve(stateI, searchI);
		
//		AStarSearch searchA = new AStarSearch();
//		State stateA = new FifteenPuzzleState(array);
//		solve(stateA, searchA);
		
		//Format: [buttonTL, buttonTR, buttonBL, buttonBR, TL, TTM, TR, TML, TMM, TMR, BL, TBM, BR, BTM, BML, BMM, BMR, BBM]
		
//		int[] array = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 12, 12, 12, 12, 12}; //Path Length 1
//		int[] array = new int[] {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 12, 12, 12, 12, 12}; //Path Length 2
//		int[] array = new int[] {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 12, 12, 1, 1, 12, 1}; //Path Length 3
//		int[] array = new int[] {1, 0, 1, 0, 12, 12, 2, 12, 12, 12, 12, 12, 1, 10, 10, 10, 12, 11}; //Path Length 4
//		int[] array = new int[] {0, 1, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 12, 11, 11, 12, 11}; //Path Length 5
//		int[] array = new int[] {0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 12, 11, 11, 12, 11}; //Path Length 6		
//		int[] array = new int[] {0, 0, 1, 0, 3, 2, 3, 2, 2, 2, 2, 2, 3, 11, 10, 10, 11, 10}; //Path Length 7
//		int[] array = new int[] {0, 0, 1, 0, 4, 2, 4, 2, 2, 2, 2, 2, 4, 10, 9, 9, 10, 9}; //Path Length 8
		int[] array = new int[] {0, 0, 0, 0, 4, 2, 4, 2, 2, 2, 2, 2, 4, 10, 9, 9, 10, 9}; //Path Length 9
		
		
//		BreadthFirstSearch searchB = new BreadthFirstSearch();
//		State stateB = new RubikClockState(array);
//		solve(stateB, searchB);
		
//		IterativeDeepeningSearch searchI = new IterativeDeepeningSearch(10000);
//		State stateI = new RubikClockState(array);
//		solve(stateI, searchI);
//		
		AStarSearch searchA = new AStarSearch();
		State stateA = new RubikClockState(array);
		solve(stateA, searchA);
		
		IterativeDeepeningAStarSearch searchIDAS = new IterativeDeepeningAStarSearch();
		State stateIS = new RubikClockState(array);
		solve(stateIS, searchIDAS);
	}
	
	static public Solution solve(State start, Search strategy){
		double timeTotal = 0;
		Solution solution = null;
		for (int i = 0; i < 5; i++){
			long initialTime = System.nanoTime();
			solution = strategy.search(start);
			//displaySolution(solution, strategy);
			//solution.displayStart();
			System.out.println("Path length " + solution.path.size());
			long finalTime = System.nanoTime();
			//displaySolution(solution, strategy, (finalTime - initialTime)/(1000000000.0));
			timeTotal = timeTotal + (finalTime - initialTime)/(1000000000.0);
			//System.out.println("-------------------------");
		}
		System.out.println("Average time for " + strategy.toString() + " " + timeTotal/5.0);

		return solution;
	}
	
	static public void displaySolution(Solution inputSolution, Search strategy){
		System.out.println("Strategy: " + strategy.toString());
		System.out.println("Path: ");
		inputSolution.displayPath();
	}
	
	static public void displaySolution(Solution inputSolution, Search strategy, double time){
		System.out.println("Strategy: " + strategy.toString());
		System.out.println("Time: " + time + " seconds");
		System.out.println("Path: ");
		inputSolution.displayPath();
	}
	
}
