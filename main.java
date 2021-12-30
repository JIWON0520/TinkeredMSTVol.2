import benchmarkmodel.ResultOfBenchmarkModel;
import inputprocessers.*;
import mst.CalculatorOfDistance;
import mst.MST;
import mst.MST_UsingDistArr;
import operation.Operation;
import results.CompareTwoResults;
import tinkeredmst.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class main {

	public static void main(String[] args) {
		
		//1. INSTANCE 받기
		InputProcesser inputProcesser = makeInstance();
		
		//2. Benchmark Model만들기
		ResultOfBenchmarkModel resultOfBenchmarkModel= makeBenchmarkModel(inputProcesser.getTerminalStatus());
		
		//3. 각 Partition 별로 Sub MST를 만든다.
		ResultsOfEachPartitions resultsOfEachParitions = makeResultsOfEachPartitions(inputProcesser.getPartitionStatus());
		
		//본 알고리즘을 위한 시간 측정 시작
		CalculateTimeForTinkeredMST calculateTimeForTinkeredMST = makeStartTimeForTinkeredMST(); 
		
		//4. 각 Partition 별 거리를 저장한다.
		DistanceOfEachPartitions distanceOfEachPartitions= makeDistanceOfEachPartitions(inputProcesser);
		
		//5. Partition 사이 MST를 구축한다.
		double totalLengthOfEachPartitions=makeTotalLengthOfEachPartitions(resultsOfEachParitions);
		double connectingLengthOfTinkeredMST=makeResultOfTinkeredMST(distanceOfEachPartitions);
		
		//본 알고리즘 시간 측정 끝
		calculateTimeForTinkeredMST=makeEndTimeForTinkeredMST(calculateTimeForTinkeredMST);
		
		ResultOfTinkeredMST resultOfTinkeredMST=makeResultOfTinkeredMST(calculateTimeForTinkeredMST.getTime(),totalLengthOfEachPartitions+connectingLengthOfTinkeredMST);
	
		//6. Benchmark model과 TinkeredMST의  Result를 통합관리
		CompareTwoResults compareTwoResults=new CompareTwoResults(resultOfBenchmarkModel,resultOfTinkeredMST);
		
		//7. 결과 출력
		//printForCheckFinal(compareTwoResults,resultOfBenchmarkModel,totalLengthOfEachPartitions,connectingLengthOfTinkeredMST,resultOfTinkeredMST);
        makeFileOutput(compareTwoResults, resultOfBenchmarkModel, totalLengthOfEachPartitions, connectingLengthOfTinkeredMST, resultOfTinkeredMST);
        
        System.out.println("##########중간 테스트#########");
        System.out.println(inputProcesser.getTerminalStatus().size());
        Operation operation= new Operation(inputProcesser);
        operation.doOperation();
        System.out.println(inputProcesser.getTerminalStatus().size());
	}

	private static void makeFileOutput(CompareTwoResults compareTwoResults,ResultOfBenchmarkModel resultOfBenchmarkModel, double totalLengthOfEachPartitions,double connectingLengthOfTinkeredMST, ResultOfTinkeredMST resultOfTinkeredMST) {
		 BufferedOutputStream bs = null;
	        try {
	            bs = new BufferedOutputStream(new FileOutputStream(Consts.FILENAME + "_result.txt"));
	            String s = "============================== RESULT ==============================";
	            bs.write(s.concat("\n").getBytes());
	            s = "* FILE INFO: " + Consts.FILENAME;
	            bs.write(s.concat("\n").getBytes());
	            s = "* NUM OF TERMINALS: " + Consts.NUMOFTERMINALS;
	            bs.write(s.concat("\n").getBytes());
	            s = "* NUM OF PARTITIONS: " + Consts.NUMOFPARTITIONS;
	            bs.write(s.concat("\n").getBytes());
	            s = "* NUM OF PORTALS: " + Consts.NUMOFPORTALS;
	            bs.write(s.concat("\n").getBytes());
	            s = "* RESULT OF TIME: " + compareTwoResults.getResultOfTime();
	            bs.write(s.concat("\n").getBytes());
	            s = "* RESULT OF LENGTH: " + compareTwoResults.getResultOfLength();
	            bs.write(s.concat("\n").getBytes());
	            s = "\n============================ DETAIL RESULT ============================";
	            bs.write(s.concat("\n").getBytes());
	            s = "* LENGTH OF TWO ALGORITHMS...";
	            bs.write(s.concat("\n").getBytes());
	            s = "   - length of Benchmark model: " + resultOfBenchmarkModel.getUsingLength();
	            bs.write(s.concat("\n").getBytes());
	            s = "   - length of Tinkered MST: " + resultOfTinkeredMST.getUsingLength();
	            bs.write(s.concat("\n").getBytes());
	            s = "* TIME OF TWO ALGORITHMS...";
	            bs.write(s.concat("\n").getBytes());
	            s = "   - length of Benchmark model: " + resultOfBenchmarkModel.getTime();
	            bs.write(s.concat("\n").getBytes());
	            s = "   - length of Tinkered MST: " + resultOfTinkeredMST.getTime();
	            bs.write(s.concat("\n").getBytes());
	            s = "* DETAIL OF TINKERED MST...";
	            bs.write(s.concat("\n").getBytes());
	            s = "    - total length for partitions(not connecting each partitions): " + totalLengthOfEachPartitions;
	            bs.write(s.concat("\n").getBytes());
	            s = "    - connecting length for partitions: " + connectingLengthOfTinkeredMST;
	            bs.write(s.concat("\n").getBytes());
	        } catch (Exception e) {
	            e.getStackTrace();
	        }
	        finally {
	            try {
	                bs.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}


	private static void printForCheckFinal(CompareTwoResults compareTwoResults,ResultOfBenchmarkModel resultOfBenchmarkModel, double totalLengthOfEachPartitions,double connectingLengthOfTinkeredMST, ResultOfTinkeredMST resultOfTinkeredMST) {
		System.out.println();
        System.out.println("============================== RESULT ==============================");
        System.out.println("* FILE INFO: " + Consts.FILENAME);
        System.out.println("* NUM OF TERMINALS: " + Consts.NUMOFTERMINALS);
        System.out.println("* NUM OF PARTITIONS: " + Consts.NUMOFPARTITIONS);
        System.out.println("* NUM OF PORTALS: " + Consts.NUMOFPORTALS);
        System.out.println("* RESULT OF TIME: " + compareTwoResults.getResultOfTime());
        System.out.println("* RESULT OF LENGTH: " + compareTwoResults.getResultOfLength());
        System.out.println();
        System.out.println("============================ DETAIL RESULT ============================");
        System.out.println("* LENGTH OF TWO ALGORITHMS...");
        System.out.println("   - length of Benchmark model: " + resultOfBenchmarkModel.getUsingLength());
        System.out.println("   - length of Tinkered MST: " + resultOfTinkeredMST.getUsingLength());
        System.out.println("* TIME OF TWO ALGORITHMS...");
        System.out.println("   - length of Benchmark model: " + resultOfBenchmarkModel.getTime());
        System.out.println("   - length of Tinkered MST: " + resultOfTinkeredMST.getTime());
        System.out.println("* DETAIL OF TINKERED MST...");
        System.out.println("    - total length for partitions(not connecting each partitions): " + totalLengthOfEachPartitions);
        System.out.println("    - connecting length for partitions: " + connectingLengthOfTinkeredMST);
		
	}


	private static ResultOfTinkeredMST makeResultOfTinkeredMST(double timeOfTinkerdMST, double finalResultLengthForTinkeredMST) {
		return new ResultOfTinkeredMST(timeOfTinkerdMST, finalResultLengthForTinkeredMST);
		
	}

	private static CalculateTimeForTinkeredMST makeEndTimeForTinkeredMST(CalculateTimeForTinkeredMST calculateTimeForTinkeredMST) {
		calculateTimeForTinkeredMST.setEndTime(System.currentTimeMillis());
		
		return calculateTimeForTinkeredMST;
	}

	private static double makeResultOfTinkeredMST(DistanceOfEachPartitions distanceOfEachPartitions) {
		MST mst=new MST_UsingDistArr(distanceOfEachPartitions.getDistanceOfEachPartitions());
		return mst.getMSTResult()[1];
	}

	private static double makeTotalLengthOfEachPartitions(ResultsOfEachPartitions resultofEachPartitions) {
		double totalLengthOfEachPartitions=0;
		for(int i=0;i<Consts.NUMOFPARTITIONS;i++) {
			totalLengthOfEachPartitions+=resultofEachPartitions.getPartitionResultMap(i).getUsingLength();
		}
		return totalLengthOfEachPartitions;
	}

	private static DistanceOfEachPartitions makeDistanceOfEachPartitions(InputProcesser inputProcesser) {
		DistanceOfEachPartitions distanceOfEachPartitions = new DistanceOfEachPartitions();
		for(Partition p:inputProcesser.getPartitionStatus()) {
			for(AdjPartition adjP:p.getAdjPartitions()) {
				if(distanceOfEachPartitions.getDistanceOfETwoPartitions(p.getNumber(), adjP.getNumber()) == 0.0) {
					CalculateForDistanceTwoPartitions distanceTwoPartitions = new CalculateForDistanceTwoPartitions(p,inputProcesser.getPartitionStatus().get(adjP.getNumber()),adjP);
					double distance= distanceTwoPartitions.getDistance();
					distanceOfEachPartitions.setDistanceOfEachPartitions(p.getNumber(), adjP.getNumber(), distance);
					distanceOfEachPartitions.setDistanceOfEachPartitions(adjP.getNumber(),p.getNumber(), distance);
				}
			}
		}
		return distanceOfEachPartitions;
	}

	private static CalculateTimeForTinkeredMST makeStartTimeForTinkeredMST() {
		CalculateTimeForTinkeredMST calculateTimeForTinkeredMST= new CalculateTimeForTinkeredMST();
		calculateTimeForTinkeredMST.setStartTime(System.currentTimeMillis());
		
		return calculateTimeForTinkeredMST;
	}

	private static ResultsOfEachPartitions makeResultsOfEachPartitions(List<Partition> partitionStatus) {
		MST_UsingDistArr mst;
		ResultsOfEachPartitions resultsOfEachPartition = new ResultsOfEachPartitions();
		for(Partition p: partitionStatus) {
			mst=new MST_UsingDistArr(p.getTerminalStatus());
			ResultOfPartition resultOfPartition=new ResultOfPartition(p,mst.getMSTResult());
			resultsOfEachPartition.addResultOfEachPartition(resultOfPartition);
		}
		return resultsOfEachPartition;
	}

	private static ResultOfBenchmarkModel makeBenchmarkModel(List<Terminal> terminalStatus) {
		MST mst=new MST_UsingDistArr(terminalStatus);
		return new ResultOfBenchmarkModel(mst.getMSTResult());
	}
	
	private static InputProcesser makeInstance() {
		Scanner input=new Scanner(System.in);
		String path=input.nextLine();
		InputProcesser inputProcesser=new InputProcesser(path);
		
		return inputProcesser;
	}
}
