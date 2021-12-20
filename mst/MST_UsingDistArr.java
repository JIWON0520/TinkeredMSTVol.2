package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import inputprocessers.Terminal;

public class MST_UsingDistArr extends MST{
	private double[][] distance;
	private int numOfComponent;
	private List<Terminal> terminalStatus;
	
	public MST_UsingDistArr(double [][] distance) {
		this.distance=distance;
		this.numOfComponent=distance.length;
	}
	
	public MST_UsingDistArr(List<Terminal> terminalStatus) {
		this.terminalStatus=terminalStatus;
		this.numOfComponent=terminalStatus.size();
		//makeDistance();
	}
	
	private void makeDistance() {
		distance=new double[terminalStatus.size()][terminalStatus.size()];
		CalculatorOfDistance calculatorOfDistance=new CalculatorOfDistance();
		
		for(int i=0;i<numOfComponent;i++) {
			for(int j=i;j<numOfComponent;j++) {
				distance[i][j]=calculatorOfDistance.calculateDistance(terminalStatus.get(i), terminalStatus.get(i));
				distance[j][i]=distance[i][j];
			}
		}
	}
	
	@Override
	public double makeMST() {
		
		//1. Preprocessing
		double result=0;
		boolean[] selected= new boolean[numOfComponent];
		double[] minDist=new double[numOfComponent];
		int[] startPoint=new int[numOfComponent];
		Arrays.fill(selected, false);
		Arrays.fill(minDist, Double.MAX_VALUE);
		
		//2. ������ ����
		int selectedIndex=0;
		selected[selectedIndex]=true;
		
		//3. Prim Algorithm
		CalculatorOfDistance calculatorOfDistance = new CalculatorOfDistance();
		for (int k=1; k< numOfComponent; k++) {
			//1) �Ÿ� �� ������Ʈ
			for(int i=1;i<numOfComponent;i++) {
				double distance;
				if(terminalStatus != null)
					distance=calculatorOfDistance.calculateDistance(terminalStatus.get(selectedIndex), terminalStatus.get(i));
				else distance=this.distance[selectedIndex][i];
				if(minDist[i]>distance && distance != 0.0) {
					minDist[i]=distance;
					startPoint[i]=selectedIndex;
				}
			}
			
			//2) �ּ� �Ÿ� ã��
			double min=Double.MAX_VALUE;
			for(int i=0;i<numOfComponent;i++) {
				if(min>minDist[i] && selected[i]==false) {
					min=minDist[i];
					selectedIndex=i;
				}
			}
			
			//3)�ּ� �Ÿ��� Terminal�� �����ϰ� �Ÿ� ��, ���ÿ��� ������Ʈ
			result+=min;
			selected[selectedIndex]=true;
			terminalStatus.get(startPoint[selectedIndex]).setAdjTerminal(terminalStatus.get(selectedIndex));
			terminalStatus.get(selectedIndex).setAdjTerminal(terminalStatus.get(startPoint[selectedIndex]));
		}
		
		
		return result;
	}

}
