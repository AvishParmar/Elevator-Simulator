/**
 * Request class that that creates a request object
 * @author Avish Parmar
 */
public class Request {

	private int sourceFloor;
	private int destinationFloor;
	private int timeEntered;
	public boolean sourceVisited;
	/**
	 * Request constructor, assigns a randomly generated floor number to the object
	 * @param numFloor
	 * Number of floors
	 */
	public Request(int numFloor) {
		setSourceFloor((int)(Math.random()*numFloor + 1));
		setDestinationFloor((int)(Math.random()*numFloor+1));
		setSourceVisited(false);
	}
	/**
	 * Used for OPTIMAL SIMULATOR to track if the request's source floor is visited
	 * @param sourceVisited
	 * boolean value that is true if source floor is visited
	 */
	public void setSourceVisited(boolean sourceVisited) {
		this.sourceVisited = sourceVisited;
	}
	/**
	 * Returns whether the request's source floor has been visited or not
	 * @return sourceVisited
	 * True: If source floor has been visited
	 * False: If source floor has not been visited
	 */
	public boolean getSourceVisited() {
		return this.sourceVisited;
	}
	/**
	 * Sets the source floor for the request
	 * @param sourceFloor
	 * Source floor of the request
	 */
	public void setSourceFloor(int sourceFloor) {
		this.sourceFloor = sourceFloor;
	}
	/**
	 * Returns the source floor of the request
	 * @return sourceFloor
	 * Source floor of the request
	 */
	public int getSourceFloor() {
		return this.sourceFloor;
	}
	/**
	 * Sets the destination floor of the request
	 * @param destinationFloor
	 * Destination floor of the request
	 */
	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}
	/**
	 * Returns the destination floor of the request
	 * @return destinationFloor
	 * Destination floor of the request
	 */
	public int getDestinationFloor(){
		return this.destinationFloor;
	}
	/**
	 * Sets the time when this request was generated
	 * @param timeEntered
	 * Time the request was generated
	 */
	public void setTimeEntered(int timeEntered) {
		this.timeEntered = timeEntered;
	}
	/**
	 * Returns the time when this request was generated
	 * @return timeEntered
	 * Time the request was generated
	 */
	public int getTimeEntered() {
		return this.timeEntered;
	}
	/**
	 * toString method for request object that prints: (SourceFloor, DestinationFloor, TimeEntered)
	 */
	public String toString() {
		System.out.print("("+this.getSourceFloor()+", "+this.getDestinationFloor()+", "+this.getTimeEntered()+"), ");
		
		return "";
	}
}
