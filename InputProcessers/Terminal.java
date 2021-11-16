package InputProcessers;

public class Terminal {
	private double xCoor;
	private double yCoor;
	private int numofPartition;
	private Partition patition;
	
	public Terminal(double xCoor,double yCoor,int numOfPartition, Partition partition) {
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		this.numofPartition=numOfPartition;
		this.patition=partition;
	}

	public double getxCoor() {
		return xCoor;
	}

	public double getyCoor() {
		return yCoor;
	}

	public int getNumofPartition() {
		return numofPartition;
	}

	public Partition getPatition() {
		return patition;
	}
	
	@Override
	public String toString() {
		return "Termianl{"+
				"("+xCoor+","+yCoor+") numOfPartition="+numofPartition+"}";
	}

}
