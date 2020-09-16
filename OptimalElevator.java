import java.util.ArrayList;
/**
 * OptimalElevator class that is used in place of the 
 * normal elevator class when an optimal simulation is run.
 * @author Avish Parmar
 */
public class OptimalElevator {
	private int currentFloor;
	private int elevatorState;
	public final static int IDLE = 0;
	public final static int UP = 1;
	public final static int DOWN = 2;
	
	
	private ArrayList<Request> request = new ArrayList<Request>();
	/**
	 * Default OptimalElevator constructor
	 */
	public OptimalElevator() {
		
		setElevatorState(IDLE);
		setCurrentFloor(1);
	}
	
	/**
	 * Sets the current floor the elevator
	 * @param currentFloor
	 * Current floor location of this elevator
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	/**
	 * Returns the current floor of the elevator
	 * @return currentFloor
	 * Current floor of the elevator
	 */
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	/**
	 * Adds a request object to the elevator
	 * @param request
	 * Request object
	 */
	public void setRequest(Request request) {
		this.request.add(request);
		
	}
	
	/**
	 * Returns the list of all requests for the elevator
	 * @return request
	 * ArrayList of requests
	 */
	public ArrayList<Request> getRequest() {
		return this.request;
	}
	
	/**
	 * Sets the elevator state
	 * @param elevatorState
	 * State of the elevator
	 */
	public void setElevatorState(int elevatorState) {
		if(elevatorState == IDLE) 
			this.elevatorState = IDLE;
		else if(elevatorState == UP)
			this.elevatorState = UP;
		else if(elevatorState == DOWN)
			this.elevatorState = DOWN;
	}
	
	/**
	 * Returns the state of the elevator
	 * @return elevatorState
	 * State of the elevator
	 */
	public int getElevatorState() {
		return this.elevatorState;
	}
	
	/**
	 * toString() method for OptimalElevator
	 */
	public String toString() {
		
		if(this.getElevatorState() == IDLE)
			System.out.print("[Floor "+this.getCurrentFloor()+", IDLE, ---], ");
		
		
		else if(this.getElevatorState() == UP) {
			System.out.print("[Floor "+this.getCurrentFloor()+", UP, ");
			
			for(int i = 0; i < request.size(); i++) {
				System.out.print("("+request.get(i).getSourceFloor()+", "+request.get(i).getDestinationFloor()+", "+request.get(i).getTimeEntered()+"),");
			}
			
			System.out.print("]");
		}
			
		else if(this.getElevatorState() == DOWN) {
			System.out.print("[Floor "+this.getCurrentFloor()+", DOWN, ");
			
			for(int i = 0; i < request.size(); i++) {
				System.out.print("("+request.get(i).getSourceFloor()+", "+request.get(i).getDestinationFloor()+", "+request.get(i).getTimeEntered()+"),");
			}
			
			System.out.print("]");
		}
			
		
		return "";
	}
}
