package dispatcher;

import java.util.Random;

import process.schedule.FCFS;
import process.schedule.HRRN;
import process.schedule.PSA;
import process.schedule.RR;
import process.schedule.SJF;

/**
 * ���ɳ���
 *
 * @author wz
 *
 * @date 2015��11��10��
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
	 * ����Ӧ�����ȵ���
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
	 * ����Ȩ����(��ռʽ)
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
	 * ʱ��Ƭ��ת����
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
	 * �ȵ��ȷ���
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
	 * ����ҵ���ȵ���
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
