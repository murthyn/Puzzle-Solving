
public class RubikClockAction implements Action{

	public static final int BUTTONTL = 0;
	public static final int BUTTONTR = 1;
	public static final int BUTTONBL = 2;
	public static final int BUTTONBR = 3;
	public static final int KNOB0CLOCK = 4;
	public static final int KNOB0COUNTER = 5;
	public static final int KNOB1CLOCK = 6;
	public static final int KNOB1COUNTER = 7;
	public static final int KNOB2CLOCK = 8;
	public static final int KNOB2COUNTER = 9;
	public static final int KNOB3CLOCK = 10;
	public static final int KNOB3COUNTER = 11;
	
	private int currentAction; 
	
	RubikClockAction(int inputAction){
		currentAction = inputAction;
	}

	public void display() {
		if(currentAction == BUTTONTL){
			System.out.println("Action: Button Top Left");
		}
		else if(currentAction == BUTTONTR){
			System.out.println("Action: Button Top Right");
		}
		else if(currentAction == BUTTONBL){
			System.out.println("Action: Button Bottom Left");
		}
		else if(currentAction == BUTTONBR){
			System.out.println("Action: Button Bottom Right");
		}
		
		else if(currentAction == KNOB0CLOCK){
			System.out.println("Action: Knob Top Left Clockwise");
		}
		else if(currentAction == KNOB0COUNTER){
			System.out.println("Action: Knob Top Left Counter-clockwise");
		}
		else if(currentAction == KNOB1CLOCK){
			System.out.println("Action: Knob Top Right Clockwise");
		}
		else if(currentAction == KNOB1COUNTER){
			System.out.println("Action: Knob Top Right Counter-clockwise");
		}
		else if(currentAction == KNOB2CLOCK){
			System.out.println("Action: Knob Bottom Left Clockwise");
		}
		else if(currentAction == KNOB2COUNTER){
			System.out.println("Action: Knob Bottom Left Counter-clockwise");
		}
		else if(currentAction == KNOB3CLOCK){
			System.out.println("Action: Knob Bottom Right Clockwise");
		}
		else if(currentAction == KNOB3COUNTER){
			System.out.println("Action: Knob Bottom Right Counter-clockwise");
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
