import java.util.ArrayList;
/**
 * Simulator class that runs the normal simulation.
 * @author Avish Parmar
 */

public class Simulator{

	public static boolean DEBUG = false;
	
	private static ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	/**
	 * Simulates an instance using the given parameter
	 * @param probability
	 * Probability of a request arriving
	 * @param floors
	 * Number of floors
	 * @param numElevators
	 * Number of elevators
	 * @param simulationLength
	 * Number of times the simulation will run
	 */
	public static void simulate(double probability, int floors, int numElevators, int simulationLength) {
		
		double requests = 0;
		int waitTime = 0;
		double avgWaitTime = 0;
		int simCount = 1;
		
		
		RequestQueue request = new RequestQueue();
		
		BooleanSource probabilitySource = new BooleanSource(probability);
		
		for(int i = 0; i < numElevators; i++) {
			elevators.add(new Elevator());	
		}
		
		while(simCount <= simulationLength) {
			if(probabilitySource.requestArrived()) {
				
				Request r = new Request(floors);
				r.setTimeEntered(simCount);
				requests++;
				if(DEBUG) {
					System.out.println("\nStep "+simCount+":"+" A request arrives from Floor "
							+r.getSourceFloor()+" to Floor "+r.getDestinationFloor());
				}
				
				request.enqueue(r);
				
				
				for(int i = 0; i < numElevators; i++) {
					if(elevators.get(i).getElevatorState() == Elevator.IDLE){
						if(!(request.isEmpty())){
							Request r2 = request.dequeue();
							elevators.get(i).setRequest(r2);
							if(elevators.get(i).getCurrentFloor() != elevators.get(i).getRequest().getSourceFloor()){
								elevators.get(i).setElevatorState(Elevator.TO_SOURCE);
								
							}else
								elevators.get(i).setElevatorState(Elevator.TO_DESTINATION);
						}	
					}
				}
	
			}else {
				if(DEBUG)
					System.out.print("\nStep "+simCount+":"+" Nothing Arrives");
			}
			
			
			if(!(request.isEmpty()))
				waitTime++;
			
			if(DEBUG) {
				request.toString();
				System.out.print("\nElevators: ");
			}
			
			for(int i = 0; i < numElevators; i++) {
				
				if(elevators.get(i).getElevatorState() == Elevator.TO_SOURCE) {
					if(elevators.get(i).getCurrentFloor() < elevators.get(i).getRequest().getSourceFloor()) {
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor()+1);
						
					}
					else if(elevators.get(i).getCurrentFloor() > elevators.get(i).getRequest().getSourceFloor()) {
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor()-1);
						
					}	
					if(elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().getSourceFloor()) {
						if(elevators.get(i).getRequest().getSourceFloor() == elevators.get(i).getRequest().getDestinationFloor()) {
							waitTime += simCount - elevators.get(i).getRequest().getTimeEntered();
							elevators.get(i).setElevatorState(Elevator.IDLE);	
						}
						else {
							waitTime += simCount - elevators.get(i).getRequest().getTimeEntered();
							elevators.get(i).setElevatorState(Elevator.TO_DESTINATION);
						}	
					}
				}
				else if(elevators.get(i).getElevatorState() == Elevator.TO_DESTINATION) {
					
					if(elevators.get(i).getCurrentFloor() < elevators.get(i).getRequest().getDestinationFloor())
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor()+1);
					
					else if(elevators.get(i).getCurrentFloor() > elevators.get(i).getRequest().getDestinationFloor())
						elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor()-1);
					
					if(elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().getDestinationFloor())
						elevators.get(i).setElevatorState(Elevator.IDLE);		
						
				}
				if(DEBUG)
					elevators.get(i).toString();
			}
			if(DEBUG)
				System.out.println("\nWait Time = "+waitTime);
			simCount++;
		
		}
		
		System.out.println("\nTotal Wait Time: "+waitTime);
		int requestInt = (int)requests;
		System.out.println("Total Requests: "+requestInt);
		avgWaitTime = waitTime/requests;
		String str = String.format("%.2f", avgWaitTime);
		System.out.println("Average Wait Time: "+str);
		
	}
}
