package Homework3;
/**
 * Elevator class operates an elevator object
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 * Recitation: Section 01
 */
public class Elevator {
	
	public final static int IDLE = 0;
	public final static int TO_SOURCE = 1;
	public final static int TO_DESTINATION = 2;
	
	private int currentFloor;
	private int elevatorState;
	private Request request;
	/**
	 * Default Elevator constructor
	 */
	public Elevator() {
		setRequest(null);
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
	 * Assigns a request object to the elevator
	 * @param request
	 * Request object
	 */
	public void setRequest(Request request) {
		this.request = request;
	}
	/**
	 * Returns the request object assigned to the elevator
	 * @return request
	 * Request object
	 */
	public Request getRequest() {
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
		else if(elevatorState == TO_SOURCE)
			this.elevatorState = TO_SOURCE;
		else if(elevatorState == TO_DESTINATION) 
			this.elevatorState = TO_DESTINATION;
		
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
	 * toString() method for elevator
	 */
	public String toString() {
		
		if(this.getElevatorState() == IDLE)
			System.out.print("[Floor "+this.getCurrentFloor()+", IDLE, ---], ");
		else if(this.getElevatorState() == TO_SOURCE)
			System.out.print("[Floor "+this.getCurrentFloor()+", TO_SOURCE, ("+this.getRequest().getSourceFloor()+", "
					+this.getRequest().getDestinationFloor()+", "+this.getRequest().getTimeEntered()+")], ");
		else if(this.getElevatorState() == TO_DESTINATION)
			System.out.print("[Floor "+this.getCurrentFloor()+", TO_DESTINATION, ("+this.getRequest().getSourceFloor()+", "
					+this.getRequest().getDestinationFloor()+", "+this.getRequest().getTimeEntered()+")], ");
		
		return "";
	}
}
