package benchmarkmodel;

public class ResultOfBenchmarkModel {
	private double time;
	private double usingLenght;
	
	public ResultOfBenchmarkModel(double[] results) {
		this.time=results[0];
		this.usingLenght=results[1];
	}

	public double getTime() {
		return time;
	}

	public double getUsingLenght() {
		return usingLenght;
	}
}
