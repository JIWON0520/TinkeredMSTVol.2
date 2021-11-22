package tinkeredmst;

import inputprocessers.*;
import mst.CalculatorOfDistance;

import java.util.ArrayList;
import java.util.List;

enum DividedState { HORIZONTAL,VERTICAL }

public class CalculateForDistanceTwoPartitions extends CalculatorOfDistance {

	private Partition partition;
	private Partition adjPartition;
	private AdjPartition adjInfo;
	private Point dividedPointOne;
	private Point dividedPointTwo;
	private DividedState dividedState;
	
	public CalculateForDistanceTwoPartitions(Partition partition, Partition adjparition, AdjPartition partitionTwo) {
		this.partition=partition;
		this.adjPartition=adjparition;
		this.adjInfo=partitionTwo;
		this.dividedPointOne=adjInfo.getDividedPoint1();
		this.dividedPointTwo=adjInfo.getDividedPoint2();
		
		if(dividedPointOne.getxCoor() == dividedPointTwo.getxCoor()) {
			this.dividedState=DividedState.HORIZONTAL;
			if(dividedPointOne.getyCoor() > dividedPointTwo.getyCoor()) {
				this.dividedPointOne=adjInfo.getDividedPoint2();
				this.dividedPointTwo=adjInfo.getDividedPoint1();
			}
		else {
			this.dividedState=DividedState.VERTICAL;
			if(dividedPointOne.getxCoor() > dividedPointTwo.getxCoor())
				this.dividedPointOne=adjInfo.getDividedPoint2();
				this.dividedPointTwo=adjInfo.getDividedPoint1();
			}
		}
	}
	
	public double getDistance() {
		List<Point> portals=makePortals();
		
		checkPrintOne(portals);
		
		List<Double> distanceForUsingEachPortals=new ArrayList<>();
		
		for(Point p: portals) {
			distanceForUsingEachPortals.add(getPortalDistance(p));
		}
		
		double minValue=Double.MAX_VALUE;
		for(double distancePortal: distanceForUsingEachPortals) {
			if(minValue>distancePortal)
				minValue=distancePortal;
		}
		
		return minValue;
	}
	
	private Double getPortalDistance(Point portal) {
		
		double distanceOfPartition=Double.MAX_VALUE;
		int indexOfPartition=0;
		for(int i=0; i < partition.getTerminalStatus().size();i++) {
			double disBox=calculateDistance(portal, partition.getTerminalStatus().get(i));
			if(distanceOfPartition>disBox) {
				distanceOfPartition = disBox;
				indexOfPartition=i;
			}
		}
		
		double distanceOfAdjPartition=Double.MAX_VALUE;
		int indexOfAdjParition=0;
		for(int i=0; i < adjPartition.getTerminalStatus().size();i++) {
			double disBox=calculateDistance(portal, adjPartition.getTerminalStatus().get(i));
			if(distanceOfAdjPartition>disBox) {
				distanceOfAdjPartition=disBox;
				indexOfAdjParition=i;
			}
		}
		
		return calculateDistance(partition.getTerminalStatus().get(indexOfPartition), adjPartition.getTerminalStatus().get(indexOfAdjParition));
	}
	
	private void checkPrintOne(List<Point> portals) {
		System.out.println("=============== Partition"+partition.getNumber() +"& Partition"+adjPartition.getNumber()+"==============");
		System.out.println("* Divided Point one: "+dividedPointOne);
		System.out.println("* Divided Point Two: "+dividedPointTwo);
		System.out.println("* Divided State: "+ dividedState.toString());
		System.out.println("* PORTAL LIST: "+portals);
		System.out.println();
	}
	
	private List<Point> makePortals(){
		List<Point> portals=new ArrayList<>();
		double distance=calculateDistance(dividedPointOne, dividedPointTwo);
		int flagOfPortal=(int)(distance/(Consts.NUMOFPORTALS+1));
		
		for(int i =0 ; i<Consts.NUMOFPORTALS;i++) {
			double xCoor=0; double yCoor=0;
			if(dividedState.equals(DividedState.HORIZONTAL)) {
				xCoor=dividedPointOne.getxCoor();
				yCoor=dividedPointOne.getyCoor()+(flagOfPortal*(i+1));
			}
			else {
				xCoor=dividedPointOne.getxCoor()+(flagOfPortal*(i+1));
				yCoor=dividedPointOne.getyCoor();
			}
			Point portal=new Point(xCoor,yCoor);
			portals.add(portal);
		}
		
		return portals;
	}
}
