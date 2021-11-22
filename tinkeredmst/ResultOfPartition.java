package tinkeredmst;

import inputprocessers.Partition;

public class ResultOfPartition {
	private int partitionNumber;
	private Partition partitionInfo;
	private double usingLength;
	private double time;
	
	public ResultOfPartition(Partition partitionInfo,double[] results) {
		this.partitionInfo=partitionInfo;
		this.partitionNumber=partitionInfo.getNumber();
		this.time=results[0];
		this.usingLength=results[1];
	}

	public int getPartitionNumber() {
		return partitionNumber;
	}

	public Partition getPartitionInfo() {
		return partitionInfo;
	}

	public double getUsingLength() {
		return usingLength;
	}

	public double getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return "ResultOfEachPartition{" +
				"partitionNumber="+partitionNumber+
				", partitionInfo="+partitionInfo+
				", usingLength="+usingLength+
				", time="+time+"}";
	}

}
