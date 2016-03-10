package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;

import controlblock.PCB;

/**
 * ʱ��Ƭ��ת����
 *
 * @author wz
 *
 * @date 2015��11��10��
 */
public class RR {
	
	protected LinkedList<PCB> processQueue;

	private static final int TIME_SLICE = 5;

	public RR() {
		processQueue = new LinkedList<PCB>();
	}

	public void schedule() {
		sortByArriveTime(processQueue);

		PCB process;
		int currentTime = 0;
		int arriveTime;
		int needTime;

		while (!processQueue.isEmpty()) {
			process = processQueue.pollFirst();
			needTime = process.getNeedTime();
			arriveTime = process.getArriveTime();
			System.out.print("���̣�" + process.getPid() + ",");
			System.out.print("����ʱ�䣺" + process.getArriveTime() + ",");
			System.out.print("����Ҫʱ��:" + process.getNeedTime() + ",");

			if (currentTime < arriveTime)
				currentTime = arriveTime;
			System.out.print("��ʼʱ�䣺" + currentTime + ",");

			if (TIME_SLICE < needTime) {
				currentTime += TIME_SLICE;
				System.out.println("�����ж�ʱ�䣺" + currentTime);
				process.setNeedTime(needTime - TIME_SLICE);
				for (int i = 0; i < processQueue.size(); i++) {
					if (processQueue.get(i).getArriveTime() > currentTime) {
						processQueue.add(i, process);
						break;
					} else if (i == processQueue.size() - 1) {
						processQueue.add(process);
						break;
					}
				}
			} else {
				currentTime += needTime;
				System.out.println("����ʱ�䣺" + currentTime);
			}
		}

	}

	public void addProcess(int pid, int arriveTime, int needTime) {
		processQueue.push(new PCB(pid, arriveTime, needTime));
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
