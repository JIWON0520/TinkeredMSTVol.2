package operation;

import java.util.List;
import java.util.Map;

import inputprocessers.*;
import mst.CalculatorOfDistance;
import tinkeredmst.TinkeredInfo;

public class Insert {
	private InputProcesser inputProcesser;
	private List<Partition> partitionStatus;
	private List<Terminal> terminalStastus;
	private Terminal tinkeredTerminal;
	private Terminal insertTerminal;
	private Point portal;
	private Map<Integer,TinkeredInfo> tinkeredInfos;
	
	public Insert(Terminal insertTerminal,InputProcesser inputProcesser) {
		this.inputProcesser=inputProcesser;
		this.insertTerminal=insertTerminal;
		this.partitionStatus=inputProcesser.getPartitionStatus();
		this.terminalStastus=inputProcesser.getTerminalStatus();
		this.tinkeredInfos=partitionStatus.get(insertTerminal.getNumOfPartition()).getTinkeredInfo();
	}
	
	public void doInsert() {
		CalculatorOfDistance calculatorOfDistance = new CalculatorOfDistance();
		for(Map.Entry<Integer, TinkeredInfo>elem : tinkeredInfos.entrySet()) {
			int numOfAdjPartition=elem.getKey();
			TinkeredInfo tinkeredInfo=elem.getValue();
			this.tinkeredTerminal=tinkeredInfo.getTinkeredTerminal();
			this.portal=tinkeredInfo.getPortal();
			
			double DistOfTinkeredTermianlToPortal=calculatorOfDistance.calculateDistance(tinkeredTerminal,portal);
			double DistOfInsertTerminalToPortal=calculatorOfDistance.calculateDistance(insertTerminal,portal);
			
			// Tinkered Terminal과 Portal사이의 거리가 Insert Terminal과 Portal사이의 거리보다 가까운 경우
			if(DistOfInsertTerminalToPortal>DistOfTinkeredTermianlToPortal) {
				double min_value=Double.MAX_VALUE;
				for(Terminal terminal:partitionStatus.get(insertTerminal.getNumOfPartition()).getTerminalStatus()) {
					double distance = calculatorOfDistance.calculateDistance(terminal, insertTerminal);
					if(min_value>distance) {
						min_value=distance;
					}
					terminal.setAdjTerminal(insertTerminal);
					insertTerminal.setAdjTerminal(terminal);
				}
			}
			else {
				tinkeredInfo.setTinkeredTerminal(insertTerminal);
				partitionStatus.get(insertTerminal.getNumOfPartition()).setTinkeredInfo(numOfAdjPartition, tinkeredInfo);
				
				tinkeredTerminal.setAdjTerminal(insertTerminal);
				insertTerminal.setAdjTerminal(tinkeredTerminal);
			}
		}
		
		partitionStatus.get(insertTerminal.getNumOfPartition()).addTerminalStatus(insertTerminal);
		terminalStastus.add(insertTerminal);
		Consts.NUMOFTERMINALS++;
		
	}


}
