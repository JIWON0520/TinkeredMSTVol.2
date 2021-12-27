package operation;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import inputprocessers.*;

enum OperationType { INSERT,DELETE,MOVE}

public class Operation {
	private String instance;
	private InputProcesser inputProcesser ;
	
	public Operation(InputProcesser inputProcesser) {
		this.inputProcesser=inputProcesser;
		String path=Consts.FILENAME+"_Operation.txt";
		
		FileInputStream fileInputStream=null;
		try {
			fileInputStream=new FileInputStream(path);
			byte[] readBuffer=new byte[fileInputStream.available()];
			while(fileInputStream.read(readBuffer) != -1) {}
			this.instance=new String(readBuffer);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Terminal> doOperation() {
		List<Terminal> terminalStatus=inputProcesser.getTerminalStatus();
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
				Insert insert=new Insert(insertTerminal,inputProcesser);
				inputProcesser=insert.doInsert();
			}
			else if (operationType.equals(OperationType.DELETE)){
				double xCoor=Double.parseDouble(splitInstance[1]);
				double yCoor=Double.parseDouble(splitInstance[2]);
				int numOfPartition=Integer.parseInt(splitInstance[3]);
				Terminal insertTerminal=new Terminal(xCoor, yCoor, numOfPartition, partitionStatus.get(numOfPartition));
			}
			else {
	
			}
		}
		return terminalStatus;
	}
}