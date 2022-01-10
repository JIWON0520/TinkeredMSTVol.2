package benchmarkmodel;

public class ResultOfBenchmarkModel {
	private double time;
	private double usingLenght;
	
	public ResultOfBenchmarkModel(double[] results) {
		this.time=results[0];
		this.usingLenght=results[1];
	}
	
	public ResultOfBenchmarkModel() {
		this.time=0;
		this.usingLenght=0;
	}

	public double getTime() {
		return time;
	}

	public double getUsingLength() {
		return usingLenght;
	}
	
	public void addResultOfBenchmarkModel(double[] results) {
		this.time+=results[0];
		this.usingLenght+=results[1];
	}
}
