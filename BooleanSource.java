package Homework3;
/**
 * BooleanSource class used to determine if a request arrives or not.
 * @author Avish Parmar
 * SBUID : 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 * Recitation: Section 01
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

