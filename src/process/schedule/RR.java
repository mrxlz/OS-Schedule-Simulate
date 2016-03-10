package process.schedule;

import java.util.Comparator;
import java.util.LinkedList;

import controlblock.PCB;

/**
 * 时间片轮转调度
 *
 * @author wz
 *
 * @date 2015年11月10日
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
			System.out.print("进程：" + process.getPid() + ",");
			System.out.print("到达时间：" + process.getArriveTime() + ",");
			System.out.print("还需要时间:" + process.getNeedTime() + ",");

			if (currentTime < arriveTime)
				currentTime = arriveTime;
			System.out.print("开始时间：" + currentTime + ",");

			if (TIME_SLICE < needTime) {
				currentTime += TIME_SLICE;
				System.out.println("进程中断时间：" + currentTime);
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
				System.out.println("结束时间：" + currentTime);
			}
		}

	}

	public void addProcess(int pid, int arriveTime, int needTime) {
		processQueue.push(new PCB(pid, arriveTime, needTime));
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
