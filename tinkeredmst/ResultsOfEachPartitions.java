package tinkeredmst;

import java.util.HashMap;
import java.util.Map;

public class ResultsOfEachPartitions {
	private Map<Integer,ResultOfPartition> partitionResultMap;
	
	public ResultsOfEachPartitions() {
		this.partitionResultMap=new HashMap<>();
	}
	
	public void addResultOfEachPartition(ResultOfPartition resultOfPartition) {
		partitionResultMap.put(resultOfPartition.getPartitionNumber(),resultOfPartition);
	}

	public ResultOfPartition getPartitionResultMap(int number) {
		return partitionResultMap.get(number);
	}
}
