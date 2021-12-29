package operation;

import java.util.List;

import inputprocessers.*;

public class Move {
	private Terminal oldTerminal;
	private Terminal newTerminal;
	
	public Move(String[] instance,List<Partition> partitionStatus) {
		double xCoor1=Double.parseDouble(instance[1]);
		double yCoor1=Double.parseDouble(instance[2]);
		int numOfPartition1=Integer.parseInt(instance[3]);
		
		double xCoor2=Double.parseDouble(instance[4]);
		double yCoor2=Double.parseDouble(instance[5]);
		int numOfPartition2=Integer.parseInt(instance[6]);
		
		this.oldTerminal=new Terminal(xCoor1, yCoor1, numOfPartition1, partitionStatus.get(numOfPartition1));
		this.newTerminal=new Terminal(xCoor2, yCoor2, numOfPartition2, partitionStatus.get(numOfPartition2));
	}
	
	public List<Terminal> doMove(List<Terminal> terminalStatus) {
		
		return terminalStatus;
	}
}
