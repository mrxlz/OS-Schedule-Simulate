package controlblock;

/**
 * ���̿��ƿ�
 *
 * @author wz
 *
 * @date 2015��11��10��
 */
public class PCB {
	private int pid;
	private double priority;
	private int arriveTime;
	private int needTime;
	
	public PCB(int pid,int arriveTime,int needTime){
		this.pid = pid;
		this.arriveTime = arriveTime;
		this.needTime = needTime;
	}

	public PCB(int pid, double priority, int arriveTime, int needTime) {
		super();
		this.pid = pid;
		this.priority = priority;
		this.arriveTime = arriveTime;
		this.needTime = needTime;
	}

	public int getPid() {
		return pid;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public int getArriveTime() {
		return arriveTime;
	}

	public int getNeedTime() {
		return needTime;
	}

	public void setNeedTime(int needTime) {
		this.needTime = needTime;
	}

}
