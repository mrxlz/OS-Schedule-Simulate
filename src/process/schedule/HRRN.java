package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;

import controlblock.PCB;

/**
 * 高响应比优先调度
 *
 * @author wz
 *
 * @date 2015年11月10日
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
			// 当前进程执行完后，挑选已到达的响应比最高的进程
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
			// 将响应比最高的进程放入队首
			if (maxIndex != -1) {
				tempProcess = processQueue.remove(maxIndex);
				processQueue.addFirst(tempProcess);
			}

			System.out.print("进程：" + process.getPid() + ",响应比：" + process.getPriority() + ",到达时间："
					+ process.getArriveTime() + ",需要时间:" + process.getNeedTime() + ",开始时间：" + currentTime + ",");
			currentTime += needTime;
			System.out.println("结束时间：" + currentTime);
		}
	}

	public void addProcess(int pid, int arriveTime, int needTime) {
		PCB process = new PCB(pid, arriveTime, needTime);
		processQueue.push(process);
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

}
