package Homework3;


import java.util.LinkedList;
import java.util.Queue;
/**
 * RequestQueue class that stores all the requests in a queue
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 * Recitation: Section 01
 */
@SuppressWarnings("serial")

public class RequestQueue extends LinkedList<Request> implements Queue<Request>{
	/**
	 * Default RequestQuest constructor
	 */
	public RequestQueue() {
		
	}
	/**
	 * Adds a request object to the queue
	 * @param request
	 * Request object
	 */
	public void enqueue(Request request) {
		super.add(request);
	}
	/**
	 * Removes a request object from the queue and returns it
	 * @return temp
	 * Request object
	 */
	public Request dequeue() {
		if(super.isEmpty()) {
			
		}
		Request temp = (Request)super.remove();
		return temp;
		
	}
	/**
	 * Returns size of the queue
	 */
	public int size() {
		return super.size();
	}
	/**
	 * Returns a boolean value signifying whether the queue is empty (true) or not (false)
	 */
	public boolean isEmpty() {
		return super.isEmpty();
	}
	/**
	 * toString method for RequestQueue
	 */
	public String toString() {
		System.out.print("\nRequests:");
		for(int i = 0; i < super.size(); i++) {
			super.get(i).toString();
		}
		return "";
	}
	
}
