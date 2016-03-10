package dispatcher;

import java.util.Random;

import process.schedule.FCFS;
import process.schedule.HRRN;
import process.schedule.PSA;
import process.schedule.RR;
import process.schedule.SJF;

/**
 * 分派程序
 *
 * @author wz
 *
 * @date 2015年11月10日
 */
public class Dispatcher {

	private static final int MAX_ARRIVE_TIME = 5;
	private static final int MAX_NEED_TIME = 10;
	private static final int MAX_PROCESS_NUM = 5;
	private static final int MAX_PRIORITY = 10;
	private final static int MAX_PID = 10000;

	public static void main(String[] args) {
		FCFSSchedule();
//		SJFSchedule();
//		RRShedule();
//		PSAShedule();
//		HRRNShedule();
	}

	/**
	 * 高响应比优先调度
	 */
	private static void HRRNShedule() {
		HRRN hrrn = new HRRN();
		HRRN ps = hrrn;
		Random random = new Random();

		for (int i = 0; i < MAX_PROCESS_NUM; i++) {
			ps.addProcess(random.nextInt(MAX_PID), random.nextInt(MAX_ARRIVE_TIME), random.nextInt(MAX_NEED_TIME) + 1);
		}

		ps.schedule();
	}

	/**
	 * 优先权调度(抢占式)
	 */
	private static void PSAShedule() {
		PSA ps = new PSA();
		Random random = new Random();

		for (int i = 0; i < MAX_PROCESS_NUM; i++) {
			ps.addProcess(random.nextInt(MAX_PID), random.nextInt(MAX_PRIORITY), random.nextInt(MAX_ARRIVE_TIME),
					random.nextInt(MAX_NEED_TIME) + 1);
		}

		ps.schedule();
	}

	/**
	 * 时间片轮转调度
	 */
	private static void RRShedule() {
		RR ps = new RR();
		Random random = new Random();

		for (int i = 0; i < MAX_PROCESS_NUM; i++) {
			ps.addProcess(random.nextInt(MAX_PID), random.nextInt(MAX_ARRIVE_TIME), random.nextInt(MAX_NEED_TIME) + 1);
		}

		ps.schedule();
	}

	/**
	 * 先到先服务
	 */
	private static void FCFSSchedule() {
		FCFS ps = new FCFS();
		Random random = new Random();

		for (int i = 0; i < MAX_PROCESS_NUM; i++) {
			ps.addProcess(random.nextInt(MAX_PID), random.nextInt(MAX_ARRIVE_TIME), random.nextInt(MAX_NEED_TIME) + 1);
		}

		ps.schedule();
	}

	/**
	 * 短作业优先调度
	 */
	private static void SJFSchedule() {
		SJF ps = new SJF();
		Random random = new Random();

		for (int i = 0; i < MAX_PROCESS_NUM; i++) {
			ps.addProcess(random.nextInt(MAX_PID), random.nextInt(MAX_ARRIVE_TIME), random.nextInt(MAX_NEED_TIME) + 1);
		}

		ps.schedule();
	}

	public static int getMaxNeedTime() {
		return MAX_NEED_TIME;
	}
}
