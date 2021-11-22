package tinkeredmst;

import inputprocessers.Consts;

public class DistanceOfEachPartitions {

	private double[][] distanceOfEachPartitions;
	
	public DistanceOfEachPartitions() {
		this.distanceOfEachPartitions=new double[Consts.NUMOFPARTITIONS][Consts.NUMOFPARTITIONS];
	}

	public double getDistanceOfETwoPartitions(int pNum1,int pNum2) {
		return distanceOfEachPartitions[pNum1][pNum2];
	}

	public void setDistanceOfEachPartitions(int pNum1,int pNum2,double distance) {
		this.distanceOfEachPartitions[pNum1][pNum2] = distance;
	}

	public double[][] getDistanceOfEachPartitions() {
		return distanceOfEachPartitions;
	}	
}
