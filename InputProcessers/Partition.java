package InputProcessers;

import java.util.ArrayList;
import java.util.List;

public class Partition {
	private int number;
	private List<AdjPartition> adjPartitions;
	private List<Terminal> terminalStatus;
	
	public Partition(int number) {
		this.number=number;
		this.adjPartitions=new ArrayList<>();
		this.terminalStatus=new ArrayList<>();
	}
	
	public void setAdjPartition(AdjPartition adjPartition) {
		this.adjPartitions.add(adjPartition);
	}
	
	public void setTerminalStatus(Terminal terminal) {
		terminalStatus.add(terminal);
	}
	
	public int getNumber() {
		return number;
	}
	
	public List<AdjPartition> getAdjPartitions(){
		return adjPartitions;
	}
	
	public List<Terminal> getTerminalStatus(){
		return terminalStatus;
	}
	
	@Override
	public String toString() {
		return "Partition"+number+"INFO =====================" +
				"number=" + number +
				", adjPartitions=" + adjPartitions +
				", terminalStatus="+terminalStatus+
				"}";
	}
}
