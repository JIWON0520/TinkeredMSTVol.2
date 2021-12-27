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
	
	public void doDelete() {
		CalculatorOfDistance calculatorOfDistance = new CalculatorOfDistance();
		for(Map.Entry<Integer, TinkeredInfo> elem : tinkeredInfos.entrySet()) {
			int numOfAdjPartition=elem.getKey();
			TinkeredInfo tinkeredInfo=elem.getValue();
			this.tinkeredTerminal=tinkeredInfo.getTinkeredTerminal();
			this.portal=tinkeredInfo.getPortal();
			
			// Delete Terminal�� Tinkered Terminal�� ���
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
				
				//Tinkered Terminal ���� ������Ʈ
				tinkeredInfo.setTinkeredTerminal(terminalStastus.get(newTinkeredIndex));
			}
			
			//Delete Terminal�� ����Ǿ��ִ� Terminal�鳢�� MST����
			int terminalIndex=terminalStastus.indexOf(deleteTerminal);
			MST mst=new MST_UsingDistArr(terminalStastus.get(terminalIndex).getAdjTerminal());
			double[] result=mst.getMSTResult();
			
			//Delete Terminal�� ������ִ� Terminal���� adjTerminalInfo ������Ʈ
			int index=terminalStastus.indexOf(deleteTerminal);
			
			for(Terminal t: terminalStastus.get(index).getAdjTerminal()) {
				t.getAdjTerminal().remove(deleteTerminal);
			}
		}
		
		//Terminal��Ͽ��� Delete Terminal ����
		terminalStastus.remove(deleteTerminal);
		inputProcesser.getTerminalStatus().remove(deleteTerminal);
		Consts.NUMOFTERMINALS--;
		
	}
}
