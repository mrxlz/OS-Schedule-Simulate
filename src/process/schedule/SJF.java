package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;

import controlblock.PCB;
import dispatcher.Dispatcher;

/**
 * ����ҵ���ȵ���
 *
 * @author wz
 *
 * @date 2015��11��10��
 */
public class SJF{

	protected LinkedList<PCB> processQueue;
	
	public SJF() {
		processQueue = new LinkedList<PCB>();
	}

	public void schedule() {
		sortByArriveTime(processQueue);
		PCB process, tempProcess;
		int arriveTime, needTime, minIndex, minNeedTime, currentTime = 0;

		while (!processQueue.isEmpty()) {

			process = processQueue.pollFirst();
			arriveTime = process.getArriveTime();
			needTime = process.getNeedTime();

			if (currentTime < arriveTime)
				currentTime = arriveTime;

			minIndex = -1;
			minNeedTime = Dispatcher.getMaxNeedTime() + 2;

			// Ҫִ�н���ʱ����ѡ�ѵ������Ҫ��ҵʱ����̵Ľ���
			for (int i = 0; i < processQueue.size(); i++) {
				tempProcess = processQueue.get(i);
				if (tempProcess.getArriveTime() > currentTime + needTime)
					break;
				// ����ʱ����ͬ����ѡ�����ҵΪ��ǰ��ҵ
				if (tempProcess.getArriveTime() == arriveTime && tempProcess.getNeedTime() < needTime) {
					processQueue.set(i, process);
					process = tempProcess;
					tempProcess = processQueue.get(i);
					needTime = process.getNeedTime();
				}
				if (tempProcess.getNeedTime() < minNeedTime) {
					minIndex = i;
					minNeedTime = tempProcess.getNeedTime();
				}
			}
			// �������ҵ�������
			if (minIndex != -1) {
				tempProcess = processQueue.remove(minIndex);
				processQueue.addFirst(tempProcess);
			}

			System.out.print("���̣�" + process.getPid() + ",����ʱ�䣺" + process.getArriveTime() + ",��Ҫʱ��:"
					+ process.getNeedTime() + ",��ʼʱ�䣺" + currentTime + ",");
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
