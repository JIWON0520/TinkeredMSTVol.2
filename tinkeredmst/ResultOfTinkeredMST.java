package tinkeredmst;

public class ResultOfTinkeredMST {

	private double time;
	private double usingLength;
	
	public ResultOfTinkeredMST(double time,double usingLength) {
		this.time=time;
		this.usingLength=usingLength;
	}

	public double getTime() {
		return time;
	}

	public double getUsingLength() {
		return usingLength;
	}
}
