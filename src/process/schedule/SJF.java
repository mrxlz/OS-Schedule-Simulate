package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;

import controlblock.PCB;
import dispatcher.Dispatcher;

/**
 * 短作业优先调度
 *
 * @author wz
 *
 * @date 2015年11月10日
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

			// 要执行进程时，挑选已到达的需要作业时间最短的进程
			for (int i = 0; i < processQueue.size(); i++) {
				tempProcess = processQueue.get(i);
				if (tempProcess.getArriveTime() > currentTime + needTime)
					break;
				// 到达时间相同，挑选最短作业为当前作业
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
			// 将最短作业放入队首
			if (minIndex != -1) {
				tempProcess = processQueue.remove(minIndex);
				processQueue.addFirst(tempProcess);
			}

			System.out.print("进程：" + process.getPid() + ",到达时间：" + process.getArriveTime() + ",需要时间:"
					+ process.getNeedTime() + ",开始时间：" + currentTime + ",");
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
