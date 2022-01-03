package operation;

import java.util.List;
import java.util.Map;

import inputprocessers.*;
import mst.CalculatorOfDistance;
import mst.MST;
import mst.MST_UsingDistArr;
import tinkeredmst.TinkeredInfo;

public class Delete {
	private int numOfPartition;
	private Terminal deleteTerminal;
	private InputProcesser inputProcesser;
	private List<Partition> partitionStatus;
	private List<Terminal> terminalStastus;
	private Terminal tinkeredTerminal;
	private Point portal;
	private Map<Integer,TinkeredInfo> tinkeredInfos;
	
	public Delete(Terminal deleteTerminal, InputProcesser inputProcesser) {
		this.numOfPartition=deleteTerminal.getNumOfPartition();
		this.inputProcesser=inputProcesser;
		this.deleteTerminal=deleteTerminal;
		this.partitionStatus=inputProcesser.getPartitionStatus();
		this.terminalStastus=partitionStatus.get(numOfPartition).getTerminalStatus();
		this.tinkeredInfos=partitionStatus.get(deleteTerminal.getNumOfPartition()).getTinkeredInfo();
	}
	
	public double doDelete() {
		double usingLength=0;
		CalculatorOfDistance calculatorOfDistance = new CalculatorOfDistance();
		for(Map.Entry<Integer, TinkeredInfo> elem : tinkeredInfos.entrySet()) {
			int numOfAdjPartition=elem.getKey();
			TinkeredInfo tinkeredInfo=elem.getValue();
			this.tinkeredTerminal=tinkeredInfo.getTinkeredTerminal();
			this.portal=tinkeredInfo.getPortal();
			
			// Delete Terminal이 Tinkered Terminal인 경우
			int newTinkeredIndex = 0;
			if(deleteTerminal.equals(tinkeredTerminal)) {
				double minDist=Double.MAX_VALUE;
				for(int i=0;i<terminalStastus.size();i++){
					double dist=calculatorOfDistance.calculateDistance(terminalStastus.get(i), portal);
					if(minDist>dist) {
						minDist=dist;
						newTinkeredIndex=i;
						}
					}
				 usingLength=minDist;
				//Tinkered Terminal 정보 업데이트
				tinkeredInfo.setTinkeredTerminal(terminalStastus.get(newTinkeredIndex));
			}
			
			//Delete Terminal과 연결되어있던 Terminal들끼리 MST구축
			int deleteTerminalIndex=terminalStastus.indexOf(deleteTerminal);
			MST mst=new MST_UsingDistArr(terminalStastus.get(deleteTerminalIndex).getAdjTerminal());
			double[] result=mst.getMSTResult();
			usingLength+=result[1];
			
			//Delete Terminal과 연결되있던 Terminal들의 adjTerminalInfo 업데이트
			for(Terminal t: terminalStastus.get(deleteTerminalIndex).getAdjTerminal()) {
				t.getAdjTerminal().remove(deleteTerminal);
			}
		}
		
		//Terminal목록에서 Delete Terminal 삭제
		terminalStastus.remove(deleteTerminal);
		inputProcesser.getTerminalStatus().remove(deleteTerminal);
		Consts.NUMOFTERMINALS--;
		
		return usingLength;
	}
}
