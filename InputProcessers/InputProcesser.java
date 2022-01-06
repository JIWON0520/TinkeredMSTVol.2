package inputprocessers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputProcesser implements Cloneable{
    private String instance;
    private List<Terminal> terminalStatus;
    private List<Partition> partitionStatus;

    public InputProcesser(String path) {
        terminalStatus = new ArrayList<>();
        partitionStatus = new ArrayList<>();
        Consts.setFILENAME(path.split("\\\\")[path.split("\\\\").length - 1].substring(0, path.split("\\\\")[path.split("\\\\").length - 1].length() - 4));

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            byte[] readBuffer = new byte[fileInputStream.available()];
            while(fileInputStream.read(readBuffer) != -1) {}
            this.instance = new String(readBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        readInstance();
    }

    private void readInstance() {
        String[] fullInstance = instance.split(System.getProperty("line.separator"));
        Consts.setNUMOFPARTITIONS(Integer.parseInt(fullInstance[0]));
        Consts.setNUMOFTERMINALS(Integer.parseInt(fullInstance[1]));
        Consts.setNUMOFPORTALS(Integer.parseInt(fullInstance[2]));

        String[] splitInstance = Arrays.copyOfRange(fullInstance, 3, fullInstance.length);

        // 1. Partition의 정보 저장
        for(int i = 0; i < Consts.NUMOFPARTITIONS; i++) {
            partitionStatus.add(makePartition(splitInstance[i]));
        }

        // 2. Terminal의 정보와 Partition 내 terminal의 정보 저장
        int cnt = 0;
        for(int i = Consts.NUMOFPARTITIONS; i < Consts.NUMOFPARTITIONS + Consts.NUMOFTERMINALS; i++) {
            Terminal terminal = makeTerminal(splitInstance[i], cnt);
            terminalStatus.add(terminal);
            partitionStatus.get(terminal.getNumOfPartition()).addTerminalStatus(terminal);
        }
    }

    private Terminal makeTerminal(String terminalInfo, int num) {
        String[] tInfoArr = terminalInfo.split(" ");
        double xCoor = Double.parseDouble(tInfoArr[0]);
        double yCoor = Double.parseDouble(tInfoArr[1]);
        int numOfPartition = (int) Double.parseDouble(tInfoArr[2]);

        Terminal terminal = new Terminal(xCoor, yCoor, numOfPartition, partitionStatus.get(numOfPartition));
        return terminal;
    }

    private Partition makePartition(String patitionInfo) {
        String[] pInfoArr = patitionInfo.split(" ");
        Partition partition = new Partition(Integer.parseInt(pInfoArr[0]));

        int cnt = 2;
        for(int i = 0; i < Integer.parseInt(pInfoArr[1]); i++) {
            int adjNum = Integer.parseInt(pInfoArr[cnt++]);
            Point adjPoint1 = new Point(Double.parseDouble(pInfoArr[cnt++]), Double.parseDouble(pInfoArr[cnt++]));
            Point adjPoint2 = new Point(Double.parseDouble(pInfoArr[cnt++]), Double.parseDouble(pInfoArr[cnt++]));

            AdjPartition adjPartition = new AdjPartition(adjNum, adjPoint1, adjPoint2);
            partition.setAdjPartition(adjPartition);
            
        }

        return partition;
    }

    public void setTerminalStatus(List<Terminal> terminalStatus) {
        this.terminalStatus = terminalStatus;
    }

    public void setPartitionStatus(List<Partition> partitionStatus) {
        this.partitionStatus = partitionStatus;
    }

    public void addTerminal(Terminal terminal) {
        this.terminalStatus.add(terminal);
    }

    public void addPartititon(Partition partition) {
        this.partitionStatus.add(partition);
    }

    public List<Terminal> getTerminalStatus() {
        return terminalStatus;
    }

    public List<Partition> getPartitionStatus() {
        return partitionStatus;
    }

	@Override
	public InputProcesser clone() throws CloneNotSupportedException {
		InputProcesser obj=(InputProcesser)super.clone();
		ArrayList<Terminal> terminalStatus_Clone=new ArrayList<>();
		for(Terminal t:terminalStatus) {
			terminalStatus_Clone.add((Terminal) (t.clone()));
		}
		obj.setTerminalStatus(terminalStatus_Clone);
		return obj;
	}
    
    
}
