package results;

import benchmarkmodel.ResultOfBenchmarkModel;
import tinkeredmst.ResultOfTinkeredMST;

public class CompareTwoResults {

	private ResultOfBenchmarkModel benchmarkModel;
	private ResultOfTinkeredMST tinkeredMST;
	
	public CompareTwoResults(ResultOfBenchmarkModel benchmarkModel, ResultOfTinkeredMST tinkeredMST) {
		this.benchmarkModel=benchmarkModel;
		this.tinkeredMST=tinkeredMST;
	}
	
	//��ȯ�� �������: ��밪 100%���� ũ���� 100%�� ����
	public double getResultOfLength() {
		return (tinkeredMST.getUsingLength()/benchmarkModel.getUsingLength())*100.0;
	}
	
	//��밪: 100%���� �ξ� ���� ��
	public double getResultOfTime() {
		return (tinkeredMST.getTime()/benchmarkModel.getTime())*100.0;
	}
}
