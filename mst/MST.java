package mst;

public abstract class MST {
	protected abstract double makeMST();
	
	public double[] getMSTResult() {
		long start=System.currentTimeMillis();
		double resultOfLength=makeMST();
		
		long end=System.currentTimeMillis();
		
		return new double[] {(end-start)/1000.0,resultOfLength};
	}
}
