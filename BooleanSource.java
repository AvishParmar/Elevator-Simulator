/**
 * BooleanSource class used to determine if a request arrives or not.
 * @author Avish Parmar
 */
public class BooleanSource {
	
	private double probability;
	/**
	 * Constructor for BooleanSource
	 * @param p
	 * probability input
	 */
	public BooleanSource(double p){
		probability = p;
	}
	/**
	 * Determines if a request has arrived.
	 * @return (Math.random() < probability)
	 * True/False to imply if a request has arrived or not, respectively.
	 */
	public boolean requestArrived() {
		
		return (Math.random() < probability);
	
	}
}

