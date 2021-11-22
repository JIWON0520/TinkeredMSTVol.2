package tinkeredmst;

public class CalculateTimeForTinkeredMST {
	
	private long startTime;
	private long endTime;
	
	public CalculateTimeForTinkeredMST() {}
	

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public double getTime() {
		return (endTime-startTime)/1000.0;
	}
	
	

}
