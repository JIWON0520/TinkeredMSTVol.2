package tinkeredmst;

public class ResultOfTinkeredMST {

	private double time;
	private double usingLength;
	
	public ResultOfTinkeredMST(double[] results) {
		this.time=results[0];
		this.usingLength=results[1];
	}

	public double getTime() {
		return time;
	}

	public double getUsingLength() {
		return usingLength;
	}
}
