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

    public CalculateForDistanceTwoPartitions(Partition partition, Partition adjPartition, AdjPartition partitionTwo) {
        this.partition = partition;
        this.adjPartition = adjPartition;
        this.adjInfo = partitionTwo;
        this.dividedPointOne = adjInfo.getDividedPoint1();
        this.dividedPointTwo = adjInfo.getDividedPoint2();

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

        checkPrintOne(portals);

        List<Double> distanceForUsingEachPortals = new ArrayList<>();
        for(Point p : portals) {
            distanceForUsingEachPortals.add(getPortalDistance(p));
        }

        double minValue = Double.MAX_VALUE;
        for(double distancePortal : distanceForUsingEachPortals) {
            if(minValue > distancePortal) minValue = distancePortal;
        }

        return minValue;
    }

    private Double getPortalDistance(Point portal) {
        // 1. Partition에서 p와 가장 가까운 Terminal 찾기
        double distanceOfPartition = Double.MAX_VALUE;
        int indexOfPartition = 0;
        for(int i = 0; i < partition.getTerminalStatus().size(); i++) {
            double distBox = calculateDistance(partition.getTerminalStatus().get(i), portal);
            if(distanceOfPartition > distBox) {
                distanceOfPartition = distBox;
                indexOfPartition = i;
            }
        }

        // 2. AdjParition에서 p와 가장 가까운 Terminal 찾기
        double distanceOfAdjPartition = Double.MAX_VALUE;
        int indexOfAdjPartition = 0;
        for(int i = 0; i < adjPartition.getTerminalStatus().size(); i++) {
            double distBox = calculateDistance(adjPartition.getTerminalStatus().get(i), portal);
            if(distanceOfAdjPartition > distBox) {
                distanceOfAdjPartition = distBox;
                indexOfAdjPartition = i;
            }
        }

        return calculateDistance(partition.getTerminalStatus().get(indexOfPartition), adjPartition.getTerminalStatus().get(indexOfAdjPartition));
    }

    private void checkPrintOne(List<Point> portals) {
        System.out.println("================== Partition " + partition.getNumber() + " & Partition " + adjInfo.getNumber() + " ==================");
        System.out.println("* Divided Point One: " + dividedPointOne);
        System.out.println("* Divided Point Two: " + dividedPointTwo);
        System.out.println("* Divided State: " + dividedState.toString());
        System.out.println("* PORTAL LIST: " + portals);
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