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
    private TinkeredInfo tinkeredInfoOfPartition;
    private TinkeredInfo tinkeredInfoOfAdjPartition;

    public CalculateForDistanceTwoPartitions(Partition partition, Partition adjPartition, AdjPartition partitionTwo) {
        this.partition = partition;
        this.adjPartition = adjPartition;
        this.adjInfo = partitionTwo;
        this.dividedPointOne = adjInfo.getDividedPoint1();
        this.dividedPointTwo = adjInfo.getDividedPoint2();
        this.tinkeredInfoOfPartition = new TinkeredInfo();
        this.tinkeredInfoOfAdjPartition=new TinkeredInfo();

        if(dividedPointOne.getxCoor() == dividedPointTwo.getxCoor()) {
            dividedState = DividedState.HORIZONTAL;
            if(dividedPointOne.getyCoor() > dividedPointTwo.getyCoor()) {
                this.dividedPointTwo = adjInfo.getDividedPoint1();
                this.dividedPointOne = adjInfo.getDividedPoint2();
            }
        } else {
            dividedState = DividedState.VERTICAL;
            if(dividedPointOne.getxCoor() > dividedPointTwo.getxCoor()) {
                this.dividedPointTwo = adjInfo.getDividedPoint1();
                this.dividedPointOne = adjInfo.getDividedPoint2();
            }
        }
    }

    public double getDistance() {
        List<Point> portals = makePortals();


        List<Double> distanceForUsingEachPortals = new ArrayList<>();
        for(Point p : portals) {
            distanceForUsingEachPortals.add(getPortalDistance(p));
        }

        double minValue = Double.MAX_VALUE;
        for(int i=0;i<distanceForUsingEachPortals.size();i++) {
        	double distancePortal=distanceForUsingEachPortals.get(i);
            if(minValue > distancePortal) {
            	minValue = distancePortal;
            	tinkeredInfoOfPartition.setPortal(portals.get(i));
            	tinkeredInfoOfAdjPartition.setPortal(portals.get(i));
            }
        }
        
        this.partition.addTinkeredInfo(adjPartition.getNumber(), tinkeredInfoOfPartition);
        this.adjPartition.addTinkeredInfo(partition.getNumber(), tinkeredInfoOfAdjPartition);
        
        checkPrintOne(portals);
        
        return minValue;
    }

    private Double getPortalDistance(Point portal) {
        // 1. Partition���� p�� ���� ����� Terminal ã��
        double distanceOfPartition = Double.MAX_VALUE;
        int indexOfPartition = 0;
        for(int i = 0; i < partition.getTerminalStatus().size(); i++) {
            double distBox = calculateDistance(partition.getTerminalStatus().get(i), portal);
            if(distanceOfPartition > distBox) {
                distanceOfPartition = distBox;
                indexOfPartition = i;
            }
        }

        // 2. AdjParition���� p�� ���� ����� Terminal ã��
        double distanceOfAdjPartition = Double.MAX_VALUE;
        int indexOfAdjPartition = 0;
        for(int i = 0; i < adjPartition.getTerminalStatus().size(); i++) {
            double distBox = calculateDistance(adjPartition.getTerminalStatus().get(i), portal);
            if(distanceOfAdjPartition > distBox) {
                distanceOfAdjPartition = distBox;
                indexOfAdjPartition = i;
            }
        }
        
        //3. TinkeredTerminal�� Termianl ����
        tinkeredInfoOfPartition.setTinkeredTerminal(partition.getTerminalStatus().get(indexOfPartition));
        tinkeredInfoOfAdjPartition.setTinkeredTerminal(adjPartition.getTerminalStatus().get(indexOfAdjPartition));

        return calculateDistance(partition.getTerminalStatus().get(indexOfPartition), adjPartition.getTerminalStatus().get(indexOfAdjPartition));
    }

    private void checkPrintOne(List<Point> portals) {
        System.out.println("================== Partition " + partition.getNumber() + " & Partition " + adjInfo.getNumber() + " ==================");
        System.out.println("* Divided Point One: " + dividedPointOne);
        System.out.println("* Divided Point Two: " + dividedPointTwo);
        System.out.println("* Divided State: " + dividedState.toString());
        System.out.println("* PORTAL LIST: " + portals);
        System.out.println("* Tinkered result:"+partition.getTinkeredInfo().entrySet());
        System.out.println();
    }

    private List<Point> makePortals() {
        List<Point> portals = new ArrayList<>();
        double distance = calculateDistance(dividedPointOne, dividedPointTwo);
        int flagOfPortal = (int) (distance / (Consts.NUMOFPORTALS + 1));

        for(int i = 0; i < Consts.NUMOFPORTALS; i++) {
            double xCoor = 0; double yCoor = 0;
            if(dividedState.equals(DividedState.HORIZONTAL)) {
                xCoor = dividedPointOne.getxCoor();
                yCoor = dividedPointOne.getyCoor() + (flagOfPortal * (i+1));
            } else {
                xCoor = dividedPointOne.getxCoor() + (flagOfPortal * (i+1));
                yCoor = dividedPointOne.getyCoor();
            }
            Point portal = new Point(xCoor, yCoor);
            portals.add(portal);
        }

        return portals;
    }
}