import java.util.ArrayList;
/**
 * OptimalSimulator that runs an optimal simulation.
 * @author Avish Parmar
 */
public class OptimalSimulator {
	
	public static boolean DEBUG = false;
	private static ArrayList<OptimalElevator> elevators = new ArrayList<OptimalElevator>();
	/**
	 * Runs an optimal simulation based on the parameters
	 * @param probability
	 * Probability of a request arriving
	 * @param floors
	 * Number of floors
	 * @param numElevators
	 * Number of elevators
	 * @param simulationLength
	 * Number of times this simulation will run
	 */
	public static void simulate(double probability, int floors, int numElevators, int simulationLength) {
		
		double requests = 0;
		int waitTime = 0;
		double avgWaitTime = 0;
		int simCount = 1;
		
		
		RequestQueue request = new RequestQueue();
		
		BooleanSource probabilitySource = new BooleanSource(probability);
		
		for(int i = 0; i < numElevators; i++) {
			elevators.add(new OptimalElevator());	
		}
		
		while(simCount <= simulationLength) {
			if(probabilitySource.requestArrived()) {
				
				Request r = new Request(floors);
				r.setTimeEntered(simCount);
				requests++;
				if(DEBUG)
					System.out.println("\nStep "+simCount+":"+" A request arrives from Floor "
							+r.getSourceFloor()+" to Floor "+r.getDestinationFloor());
				
				request.enqueue(r);
				
				for(int i = 0; i < numElevators; i++) {
					if(elevators.get(i).getElevatorState() == OptimalElevator.IDLE){
	
						if(!(request.isEmpty())){
							
							Request r2 = request.dequeue();
							elevators.get(i).setRequest(r2);
							
							
							if(elevators.get(i).getCurrentFloor() < r2.getSourceFloor()) {
								elevators.get(i).setElevatorState(OptimalElevator.UP);
							}
							
							else if(elevators.get(i).getCurrentFloor() > r2.getSourceFloor()) {
								elevators.get(i).setElevatorState(OptimalElevator.DOWN);
							}
							
							else if(elevators.get(i).getCurrentFloor() == r2.getSourceFloor()) {
								r2.setSourceVisited(true);
								if(elevators.get(i).getCurrentFloor() < r2.getDestinationFloor())
									elevators.get(i).setElevatorState(OptimalElevator.UP);
								 
								else if(elevators.get(i).getCurrentFloor() > r2.getDestinationFloor()) 
									elevators.get(i).setElevatorState(OptimalElevator.DOWN);
							
								else if(elevators.get(i).getCurrentFloor() == r2.getDestinationFloor())
									elevators.get(i).getRequest().remove(elevators.get(i).getRequest().indexOf(r2));
							}
						}	
					}
					else if(elevators.get(i).getElevatorState() == OptimalElevator.UP) {
						
						
						for(int j = 0; j < request.size(); j++) {
							
							if(request.get(j).getSourceFloor() == elevators.get(i).getCurrentFloor() && 
									(elevators.get(i).getRequest().get(0).getDestinationFloor() >= request.get(j).getDestinationFloor())) {
								
								Request r2 = request.remove(j);
								r2.setSourceVisited(true);
								elevators.get(i).setRequest(r2);
							}
							
							else if(request.get(j).getSourceFloor() == elevators.get(i).getCurrentFloor() &&
									(request.get(j).getSourceFloor() == request.get(j).getDestinationFloor())){
								
								request.remove(j);
							}
						}
					}
					else if(elevators.get(i).getElevatorState() == OptimalElevator.DOWN) {
						
						for(int j = 0; j < request.size(); j++) {
							
							if(request.get(j).getSourceFloor() == elevators.get(i).getCurrentFloor() && 
									(elevators.get(i).getRequest().get(0).getDestinationFloor() <= request.get(j).getDestinationFloor())) {
								
								
								Request r2 = request.remove(j);
								r2.setSourceVisited(true);
								elevators.get(i).setRequest(r2);
							}
						
							else if(request.get(j).getSourceFloor() == elevators.get(i).getCurrentFloor() &&
									(request.get(j).getSourceFloor() == request.get(j).getDestinationFloor())) {
								
								request.remove(j);
							}
						}
					}
				}
			}

			else {
				if(DEBUG) {
					System.out.print("\nStep "+simCount+":"+" Nothing Arrives");
				}
			}
			
			if(!(request.isEmpty()))
				waitTime++;
			
			if(DEBUG) {
				request.toString();
				System.out.print("\nElevators: ");
			}
			
			
			for(int i = 0; i < numElevators; i++) {
				
				if(elevators.get(i).getRequest().isEmpty()) {
					elevators.get(i).setElevatorState(OptimalElevator.IDLE);
				}
				else if(elevators.get(i).getElevatorState() == OptimalElevator.UP) {
					
					elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor()+1);
					
					for(int j = 0; j < elevators.get(i).getRequest().size(); j++) {
					
						if(elevators.get(i).getRequest().get(j).getSourceVisited() && 
								elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().get(j).getDestinationFloor()) {
							
							elevators.get(i).getRequest().remove(j);
							
							if(!(elevators.get(i).getRequest().isEmpty())){
								if(elevators.get(i).getRequest().get(0).getSourceFloor() < elevators.get(i).getCurrentFloor()){
									elevators.get(i).setElevatorState(OptimalElevator.DOWN);
								}
							}
							
						}
						
						else if(elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().get(j).getSourceFloor()) {
							waitTime += simCount - elevators.get(i).getRequest().get(j).getTimeEntered();
							elevators.get(i).getRequest().get(j).setSourceVisited(true);
							
							if(elevators.get(i).getRequest().get(j).getDestinationFloor() == elevators.get(i).getCurrentFloor()) {
								elevators.get(i).getRequest().remove(j);
								
								if(!(elevators.get(i).getRequest().isEmpty())){
									if(elevators.get(i).getRequest().get(0).getSourceFloor() < elevators.get(i).getCurrentFloor()){
										elevators.get(i).setElevatorState(OptimalElevator.DOWN);
									}
								}
							}
							
							else if(elevators.get(i).getRequest().get(j).getDestinationFloor() < elevators.get(i).getCurrentFloor()) {
								elevators.get(i).setElevatorState(OptimalElevator.DOWN);
							}
								
						}
						
						if(elevators.get(i).getRequest().isEmpty())
							elevators.get(i).setElevatorState(OptimalElevator.IDLE);
					}
				}
				else if(elevators.get(i).getElevatorState() == OptimalElevator.DOWN) {
					
					elevators.get(i).setCurrentFloor(elevators.get(i).getCurrentFloor()-1);
					
					for(int j = 0; j < elevators.get(i).getRequest().size(); j++) {
						
						if(elevators.get(i).getRequest().get(j).getSourceVisited() && 
								elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().get(j).getDestinationFloor()) {
							
							elevators.get(i).getRequest().remove(j);
							
							if(!(elevators.get(i).getRequest().isEmpty())){
								if(elevators.get(i).getRequest().get(0).getSourceFloor() > elevators.get(i).getCurrentFloor()){
									elevators.get(i).setElevatorState(OptimalElevator.UP);
								}
							}
						}
					
						else if(elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().get(j).getSourceFloor()) {
							waitTime += simCount - elevators.get(i).getRequest().get(j).getTimeEntered();
							elevators.get(i).getRequest().get(j).setSourceVisited(true);
						
							if(elevators.get(i).getRequest().get(j).getDestinationFloor() == elevators.get(i).getCurrentFloor()) {
								elevators.get(i).getRequest().remove(j);
								
								if(!(elevators.get(i).getRequest().isEmpty())){
									if(elevators.get(i).getRequest().get(0).getSourceFloor() > elevators.get(i).getCurrentFloor()){
										elevators.get(i).setElevatorState(OptimalElevator.UP);
									}
								}	
							}
							
							else if(elevators.get(i).getRequest().get(j).getDestinationFloor() > elevators.get(i).getCurrentFloor()) {
								elevators.get(i).setElevatorState(OptimalElevator.UP);
							}
								
						}
						
						if(elevators.get(i).getRequest().isEmpty())
							elevators.get(i).setElevatorState(OptimalElevator.IDLE);
					}
					
				}
				if(elevators.get(i).getCurrentFloor() == floors && elevators.get(i).getElevatorState() == OptimalElevator.UP) {
					for(int j = 0; j < elevators.get(i).getRequest().size(); j++) {
						if(elevators.get(i).getRequest().get(j).getSourceVisited() 
								&& elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().get(j).getDestinationFloor())
							elevators.get(i).getRequest().remove(j);
					}
					if(elevators.get(i).getRequest().isEmpty()) {
						elevators.get(i).setElevatorState(OptimalElevator.IDLE);
					}
					else
						elevators.get(i).setElevatorState(OptimalElevator.DOWN);
				}
				else if(elevators.get(i).getCurrentFloor() == 1 && elevators.get(i).getElevatorState() == OptimalElevator.DOWN) {
					for(int j = 0; j < elevators.get(i).getRequest().size(); j++) {
						if(elevators.get(i).getRequest().get(j).getSourceVisited() 
								&& elevators.get(i).getCurrentFloor() == elevators.get(i).getRequest().get(j).getDestinationFloor())
							elevators.get(i).getRequest().remove(j);
					}
					
					if(elevators.get(i).getRequest().isEmpty()) {
						elevators.get(i).setElevatorState(OptimalElevator.IDLE);
					}else
						elevators.get(i).setElevatorState(OptimalElevator.UP);
				}
				
				if(DEBUG) {
					elevators.get(i).toString();
				}
				
				
			}
			if(DEBUG)
				System.out.println();
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
