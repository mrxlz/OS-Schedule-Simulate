package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import controlblock.PCB;

/**
 * 优先权调度(抢占式)
 *
 * @author wz
 *
 * @date 2015年11月10日
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
				// 当前进程执行至被高优先级进程抢占
				if (tempProcess.getPriority() < process.getPriority()) {
					if (tempProcess.getArriveTime() != currentTime) {
						processQueue.remove(i);
						System.out.print("进程：" + process.getPid() + ",优先级：" + (int) process.getPriority() + ",到达时间："
								+ process.getArriveTime() + ",需要时间:" + process.getNeedTime() + ",开始时间：" + currentTime
								+ ",");
						runTime = tempProcess.getArriveTime() - currentTime;
						process.setNeedTime(needTime - runTime);
						currentTime += runTime;
						System.out.println("进程中断时间：" + currentTime);
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

			System.out.print("进程：" + process.getPid() + ",优先级：" + (int) process.getPriority() + ",到达时间："
					+ process.getArriveTime() + ",需要时间:" + process.getNeedTime() + ",开始时间：" + currentTime + ",");
			currentTime += process.getNeedTime();
			System.out.println("结束时间：" + currentTime);
		}
	}

	public void addProcess(int pid, int priority, int arriveTime, int needTime) {
		processQueue.push(new PCB(pid, priority, arriveTime, needTime));
	}

	/**
	 * 对进程队列按到达时间排序
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
	 * 对指定子队列按优先级排序
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
