package inputprocessers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tinkeredmst.TinkeredInfo;

public class Partition {
	private int number;
	private List<AdjPartition> adjPartitions;
	private List<Terminal> terminalStatus;
	private Map<Integer,TinkeredInfo> tinkeredInfo;
	
	public Partition(int number) {
		this.number=number;
		this.adjPartitions=new ArrayList<>();
		this.terminalStatus=new ArrayList<>();
		this.tinkeredInfo=new HashMap<>();
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
	
	public void addTinkeredInfo(int numOfAdjPartition, TinkeredInfo tinkeredInfo) {
		this.tinkeredInfo.put(numOfAdjPartition,tinkeredInfo);
	}
	
	public Map<Integer, TinkeredInfo> getTinkeredInfo() {
		return tinkeredInfo;
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
