package inputprocessers;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
	private double xCoor;
	private double yCoor;
	private int numofPartition;
	private Partition patition;
	private List<Terminal> adjTerminal;
	
	public Terminal(double xCoor,double yCoor,int numOfPartition, Partition partition) {
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		this.numofPartition=numOfPartition;
		this.patition=partition;
		this.adjTerminal=new ArrayList<Terminal>();
	}

	public double getxCoor() {
		return xCoor;
	}

	public double getyCoor() {
		return yCoor;
	}

	public int getNumOfPartition() {
		return numofPartition;
	}

	public Partition getPatition() {
		return patition;
	}
	
	public void setAdjTerminal(Terminal terminal) {
		this.adjTerminal.add(terminal);
	}
	
	@Override
	public String toString() {
		return "Termianl{"+
				"("+xCoor+","+yCoor+") numOfPartition="+numofPartition+"}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(xCoor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yCoor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terminal other = (Terminal) obj;
		if (Double.doubleToLongBits(xCoor) != Double.doubleToLongBits(other.xCoor))
			return false;
		if (Double.doubleToLongBits(yCoor) != Double.doubleToLongBits(other.yCoor))
			return false;
		return true;
	}

	
}
