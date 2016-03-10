package process.schedule;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import controlblock.PCB;

/**
 * �ȵ��ȷ�����̵���
 *
 * @author wz
 *
 * @date 2015��11��10��
 */
public class FCFS {

	protected LinkedList<PCB> processQueue;

	public FCFS() {
		processQueue = new LinkedList<>();
	}

	public void schedule() {
		sortByArriveTime(processQueue);
		int currentTime = 0;
		int arriveTime;
		PCB process;
		Iterator<PCB> iter = processQueue.iterator();
		while (iter.hasNext()) {
			process = iter.next();
			arriveTime = process.getArriveTime();
			System.out.print("���̣�" + process.getPid() + ",");
			System.out.print("����ʱ�䣺" + process.getArriveTime() + ",");
			System.out.print("��Ҫʱ��:" + process.getNeedTime() + ",");

			if (arriveTime > currentTime)
				currentTime = arriveTime;
			System.out.print("��ʼʱ�䣺" + currentTime + ",");

			currentTime += process.getNeedTime();
			System.out.println("����ʱ�䣺" + currentTime);
			iter.remove();
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
		processQueue.sort((p1, p2) -> {
            Integer p1Time = p1.getArriveTime();
            Integer p2Time = p2.getArriveTime();
            return p1Time.compareTo(p2Time);
        });

	}

}
