package mst;

import inputprocessers.Point;
import inputprocessers.Terminal;

public class CalculatorOfDistance {
	
	public double calculateDistance(Terminal t1, Terminal t2) {
		return Math.sqrt(Math.pow(Math.abs(t2.getxCoor()-t1.getxCoor()),2) + Math.pow(Math.abs(t2.getyCoor()-t1.getyCoor()), 2));
	}
	
	public double calculateDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(Math.abs(p2.getxCoor()-p1.getxCoor()),2) + Math.pow(Math.abs(p2.getyCoor()-p1.getyCoor()), 2));
	}
	
	public double calculateDistance(Terminal t1, Point p2) {
		return Math.sqrt(Math.pow(Math.abs(p2.getxCoor()-t1.getxCoor()),2) + Math.pow(Math.abs(p2.getyCoor()-t1.getyCoor()), 2));
	}
	
	public double calculateDistance(Point p1, Terminal t2) {
		return Math.sqrt(Math.pow(Math.abs(t2.getxCoor()-p1.getxCoor()),2) + Math.pow(Math.abs(t2.getyCoor()-p1.getyCoor()), 2));
	}
}
