package InputProcessers;

public class AdjPartition {
	private int number;
	private Point dividedPoint1;
	private Point dividedPoint2;
	
	public AdjPartition(int number,Point dividedPoint1, Point dividedPoint2) {
		this.number=number;
		this.dividedPoint1=dividedPoint1;
		this.dividedPoint2=dividedPoint2;
	}
	
	public int getNumber() {
		return number;
	}
	
	public Point getDividedPoint1() {
		return dividedPoint1;
	}

	public Point getDividedPoint2() {
		return dividedPoint2;
	}
	
	@Override
	public String toString() {
		return "AdjPartition{"+
				"number="+number+
				", DividedPoinr1=" + dividedPoint1 +
				", DividedPoint2=" + dividedPoint2 + 
				"}";
	}
}
