package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import controlblock.PCB;

/**
 * ����Ȩ����(��ռʽ)
 *
 * @author wz
 *
 * @date 2015��11��10��
 */
public class PSA {

	protected LinkedList<PCB> processQueue;

	public PSA() {
		processQueue = new LinkedList<>();
	}

	public void schedule() {
		sortByArriveTime(processQueue);
		PCB process, tempProcess;
		int arriveTime, needTime, runTime, currentTime = 0;
		while (!processQueue.isEmpty()) {
			process = processQueue.pollFirst();
			arriveTime = process.getArriveTime();

			if (currentTime < arriveTime)
				currentTime = arriveTime;

			for (int i = 0; i < processQueue.size(); i++) {
				needTime = process.getNeedTime();
				tempProcess = processQueue.get(i);
				if (tempProcess.getArriveTime() > currentTime + needTime)
					break;
				// ��ǰ����ִ�����������ȼ�������ռ
				if (tempProcess.getPriority() < process.getPriority()) {
					if (tempProcess.getArriveTime() != currentTime) {
						processQueue.remove(i);
						System.out.print("���̣�" + process.getPid() + ",���ȼ���" + (int) process.getPriority() + ",����ʱ�䣺"
								+ process.getArriveTime() + ",��Ҫʱ��:" + process.getNeedTime() + ",��ʼʱ�䣺" + currentTime
								+ ",");
						runTime = tempProcess.getArriveTime() - currentTime;
						process.setNeedTime(needTime - runTime);
						currentTime += runTime;
						System.out.println("�����ж�ʱ�䣺" + currentTime);
						processQueue.addFirst(process);
						process = tempProcess;
					} else {
						processQueue.set(i, process);
						process = tempProcess;
						tempProcess = processQueue.get(i);
						// needTime = process.getNeedTime();
					}

				} else {
					subSortByPriority(processQueue, 0, i + 1);
				}
			}

			System.out.print("���̣�" + process.getPid() + ",���ȼ���" + (int) process.getPriority() + ",����ʱ�䣺"
					+ process.getArriveTime() + ",��Ҫʱ��:" + process.getNeedTime() + ",��ʼʱ�䣺" + currentTime + ",");
			currentTime += process.getNeedTime();
			System.out.println("����ʱ�䣺" + currentTime);
		}
	}

	public void addProcess(int pid, int priority, int arriveTime, int needTime) {
		processQueue.push(new PCB(pid, priority, arriveTime, needTime));
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

	/**
	 * ��ָ���Ӷ��а����ȼ�����
	 * 
	 * @param processQueue
	 * @param fromIndex
	 * @param toIndex
	 */
	private void subSortByPriority(LinkedList<PCB> processQueue, int fromIndex, int toIndex) {
		List<PCB> subQueue = processQueue.subList(fromIndex, toIndex);
		subQueue.sort(new Comparator<PCB>() {
			@Override
			public int compare(PCB p1, PCB p2) {
				Double p1Priority = p1.getPriority();
				Double p2Priority = p2.getPriority();
				return p1Priority.compareTo(p2Priority);
			}
		});
	}

}
