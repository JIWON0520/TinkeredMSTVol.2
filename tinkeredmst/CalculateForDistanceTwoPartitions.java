package tinkeredmst;

import inputprocessers.*;
import mst.CalculatorOfDistance;

import java.util.ArrayList;
import java.util.List;

enum DividedState { HORIZONTAL,VERTICAL }

public class CalculateForDistanceTwoPartitions extends CalculatorOfDistance {
    private Partition partition;
    private List<Terminal> terminalStatus;
    private List<Terminal> adjTerminalStatus;
    private Partition adjPartition;
    private AdjPartition adjInfo;
    private Point dividedPointOne;
    private Point dividedPointTwo;
    private DividedState dividedState;
    private TinkeredInfo tinkeredInfoOfPartition;
    private TinkeredInfo tinkeredInfoOfAdjPartition;

    public CalculateForDistanceTwoPartitions(Partition partition, Partition adjPartition, AdjPartition partitionTwo) {
        this.partition = partition;
        this.terminalStatus=partition.getTerminalStatus();
        this.adjPartition = adjPartition;
        this.adjTerminalStatus=adjPartition.getTerminalStatus();
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
        List<Integer> indexOfterminal = new ArrayList<>();
        List<Integer> indexOfAdjterminal = new ArrayList<>();
        
        for(Point p : portals) {
            distanceForUsingEachPortals.add(getPortalDistance(p,indexOfterminal,indexOfAdjterminal));
        }

        double minValue = Double.MAX_VALUE;
        for(int i=0;i<distanceForUsingEachPortals.size();i++) {
        	double distancePortal=distanceForUsingEachPortals.get(i);
            if(minValue > distancePortal) {
            	minValue = distancePortal;
            	tinkeredInfoOfPartition.setPortal(portals.get(i));
            	tinkeredInfoOfAdjPartition.setPortal(portals.get(i));
            	tinkeredInfoOfPartition.setTinkeredTerminal(terminalStatus.get(indexOfterminal.get(i)));
            	tinkeredInfoOfAdjPartition.setTinkeredTerminal(adjTerminalStatus.get(indexOfAdjterminal.get(i)));
            }
        }
        
        this.partition.addTinkeredInfo(adjPartition.getNumber(), tinkeredInfoOfPartition);
        this.adjPartition.addTinkeredInfo(partition.getNumber(), tinkeredInfoOfAdjPartition);
        
        //checkPrintOne(portals);
        
        return minValue;
    }

    private Double getPortalDistance(Point portal,List<Integer> indexOfTerminal,List<Integer> indexOfAdjTerminal) {
        // 1. Partition에서 p와 가장 가까운 Terminal 찾기
        double distanceOfPartition = Double.MAX_VALUE;
        int indexOfPartition = 0;
        for(int i = 0; i < terminalStatus.size(); i++) {
            double distBox = calculateDistance(partition.getTerminalStatus().get(i), portal);
            if(distanceOfPartition > distBox) {
                distanceOfPartition = distBox;
                indexOfPartition = i;
            }
        }

        // 2. AdjParition에서 p와 가장 가까운 Terminal 찾기
        double distanceOfAdjPartition = Double.MAX_VALUE;
        int indexOfAdjPartition = 0;
        for(int i = 0; i < adjTerminalStatus.size(); i++) {
            double distBox = calculateDistance(adjPartition.getTerminalStatus().get(i), portal);
            if(distanceOfAdjPartition > distBox) {
                distanceOfAdjPartition = distBox;
                indexOfAdjPartition = i;
            }
        }
        
        //3. TinkeredTerminal인 Terminal index 저장
        indexOfTerminal.add(indexOfPartition);
        indexOfAdjTerminal.add(indexOfAdjPartition);

        return calculateDistance(partition.getTerminalStatus().get(indexOfPartition), adjPartition.getTerminalStatus().get(indexOfAdjPartition));
    }

    /*private void checkPrintOne(List<Point> portals) {
        System.out.println("================== Partition " + partition.getNumber() + " & Partition " + adjInfo.getNumber() + " ==================");
        System.out.println("* Divided Point One: " + dividedPointOne);
        System.out.println("* Divided Point Two: " + dividedPointTwo);
        System.out.println("* Divided State: " + dividedState.toString());
        System.out.println("* PORTAL LIST: " + portals);
        System.out.println("* Tinkered result:"+partition.getTinkeredInfo().entrySet());
        System.out.println();
    }*/

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