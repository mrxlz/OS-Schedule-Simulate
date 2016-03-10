package process.schedule;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import controlblock.PCB;

/**
 * 先到先服务进程调度
 *
 * @author wz
 *
 * @date 2015年11月10日
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
			System.out.print("进程：" + process.getPid() + ",");
			System.out.print("到达时间：" + process.getArriveTime() + ",");
			System.out.print("需要时间:" + process.getNeedTime() + ",");

			if (arriveTime > currentTime)
				currentTime = arriveTime;
			System.out.print("开始时间：" + currentTime + ",");

			currentTime += process.getNeedTime();
			System.out.println("结束时间：" + currentTime);
			iter.remove();
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
		processQueue.sort((p1, p2) -> {
            Integer p1Time = p1.getArriveTime();
            Integer p2Time = p2.getArriveTime();
            return p1Time.compareTo(p2Time);
        });

	}

}
