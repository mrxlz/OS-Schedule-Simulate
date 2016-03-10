package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;

import controlblock.PCB;

/**
 * ����Ӧ�����ȵ���
 *
 * @author wz
 *
 * @date 2015��11��10��
 */
public class HRRN {
	
	protected LinkedList<PCB> processQueue;
	
	public HRRN() {
		processQueue = new LinkedList<PCB>();
	}

	public void schedule() {
		sortByArriveTime(processQueue);
		PCB process, tempProcess;
		int arriveTime, needTime, maxIndex, currentTime = 0;
		double respRatio, maxPriority = 0;

		while (!processQueue.isEmpty()) {

			process = processQueue.pollFirst();
			arriveTime = process.getArriveTime();
			needTime = process.getNeedTime();

			if (currentTime < arriveTime)
				currentTime = arriveTime;

			maxIndex = -1;
			maxPriority = -1;
			// ��ǰ����ִ�������ѡ�ѵ������Ӧ����ߵĽ���
			for (int i = 0; i < processQueue.size(); i++) {
				tempProcess = processQueue.get(i);
				if (tempProcess.getArriveTime() > currentTime + needTime)
					break;
				respRatio = (currentTime + needTime - tempProcess.getArriveTime()) / (double) tempProcess.getNeedTime() + 1;
				tempProcess.setPriority(respRatio);
				if (respRatio > maxPriority) {
					maxIndex = i;
					maxPriority = respRatio;
				}
			}
			// ����Ӧ����ߵĽ��̷������
			if (maxIndex != -1) {
				tempProcess = processQueue.remove(maxIndex);
				processQueue.addFirst(tempProcess);
			}

			System.out.print("���̣�" + process.getPid() + ",��Ӧ�ȣ�" + process.getPriority() + ",����ʱ�䣺"
					+ process.getArriveTime() + ",��Ҫʱ��:" + process.getNeedTime() + ",��ʼʱ�䣺" + currentTime + ",");
			currentTime += needTime;
			System.out.println("����ʱ�䣺" + currentTime);
		}
	}

	public void addProcess(int pid, int arriveTime, int needTime) {
		PCB process = new PCB(pid, arriveTime, needTime);
		processQueue.push(process);
	}

	/**
	 * �Խ��̶��а�����ʱ������
	 * 
	 * @param processQueue
	 */
	private <T> void sortByArriveTime(LinkedList<PCB> processQueue) {
		processQueue.sort(new Comparator<PCB>() {
			@Override
			public int compare(PCB p1, PCB p2) {
				Integer p1Time = p1.getArriveTime();
				Integer p2Time = p2.getArriveTime();
				return p1Time.compareTo(p2Time);
			}
		});
	}

}
