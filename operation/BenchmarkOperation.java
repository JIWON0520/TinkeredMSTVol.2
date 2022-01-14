package operation;

import java.util.ArrayList;
import java.util.List;

import inputprocessers.InputProcesser;
import inputprocessers.Partition;
import inputprocessers.Terminal;
import mst.MST;
import mst.MST_UsingDistArr;

public class BenchmarkOperation extends Operation{
	private List<Terminal> terminalStatus=new ArrayList<>();
	
	public BenchmarkOperation(InputProcesser inputProcesser) {
		super(inputProcesser);
		this.terminalStatus=inputProcesser.getTerminalStatus();
	}

	@Override
	public double[] doOperation() {
		double usingLength=0;
		
		long start=System.currentTimeMillis();
		
		List<Partition> partitionStatus=inputProcesser.getPartitionStatus();
		
 		String[] fullInstance=instance.split(System.getProperty("line.separator"));
		int operationNum=Integer.parseInt(fullInstance[0]);
		
		for(int i=1;i<=operationNum;i++) {
			String[] splitInstance=fullInstance[i].split(" ");
			OperationType operationType=OperationType.valueOf(splitInstance[0]);
			
			if (operationType.equals(OperationType.INSERT)){
				double xCoor=Double.parseDouble(splitInstance[1]);
				double yCoor=Double.parseDouble(splitInstance[2]);
				int numOfPartition=Integer.parseInt(splitInstance[3]);
				Terminal insertTerminal=new Terminal(xCoor, yCoor, numOfPartition, partitionStatus.get(numOfPartition));
				terminalStatus.add(insertTerminal);
				MST mst=new MST_UsingDistArr(terminalStatus);
				usingLength+=mst.getMSTResult()[1];
			}
			else if (operationType.equals(OperationType.DELETE)){
				double xCoor=Double.parseDouble(splitInstance[1]);
				double yCoor=Double.parseDouble(splitInstance[2]);
				int numOfPartition=Integer.parseInt(splitInstance[3]);
				Terminal deleteTerminal=new Terminal(xCoor, yCoor, numOfPartition, partitionStatus.get(numOfPartition));
				terminalStatus.remove(deleteTerminal);
				MST mst=new MST_UsingDistArr(terminalStatus);
				usingLength+=mst.getMSTResult()[1];
			}
			else {
				double xCoor=Double.parseDouble(splitInstance[1]);
				double yCoor=Double.parseDouble(splitInstance[2]);
				int numOfPartition=Integer.parseInt(splitInstance[3]);
				Terminal deleteTerminal=new Terminal(xCoor, yCoor, numOfPartition, partitionStatus.get(numOfPartition));
				terminalStatus.remove(deleteTerminal);
				MST mst=new MST_UsingDistArr(terminalStatus);
				usingLength+=mst.getMSTResult()[1];
				
				xCoor=Double.parseDouble(splitInstance[4]);
				yCoor=Double.parseDouble(splitInstance[5]);
				numOfPartition=Integer.parseInt(splitInstance[6]);
				Terminal insertTerminal=new Terminal(xCoor, yCoor, numOfPartition, partitionStatus.get(numOfPartition));
				terminalStatus.add(insertTerminal);
				usingLength+=mst.getMSTResult()[1];
			}
		}
		
		long end=System.currentTimeMillis();
		
		return new double[] {(end-start)/1000.0,usingLength};
	}

}
