package process.synchronize;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * ������Ʒ�ֿ�
 *
 * @author wz
 *
 * @date 2015��11��22��
 */
public class Storage {
    /**
     * ��������Ʒ
     */
    private int[] product;
    /**
     * �ȴ�����
     */
    private LinkedList<Thread>[] consumerQueue;
    private LinkedList<Thread> producerQueue;
    private int empty;

    public Storage(int capacity) {
        this.product = new int[capacity];
        this.consumerQueue = new LinkedList[capacity];
        producerQueue = new LinkedList<>();
        empty = capacity;
    }

    public int[] getProduct() {
        return product;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public LinkedList<Thread> getProducerQueue() {
        return producerQueue;
    }

    public LinkedList<Thread>[] getConsumerQueue() {
        return consumerQueue;
    }

    /**
     * ������������������
     */
    public void wakeupProducer(){
        Thread t;
        while (!producerQueue.isEmpty()){
            t=producerQueue.poll();
            LockSupport.unpark(t);
//            System.out.println("������"+t.getName());
        }
    }

    /**
     * ���ѵ�index�Ų�Ʒ����������
     * @param index
     */
    public void wakeupConsumer(int index){
        Thread t;
        while (consumerQueue[index]!=null && !consumerQueue[index].isEmpty()){
            t=consumerQueue[index].poll();
            LockSupport.unpark(t);
//            System.out.println("������"+t.getName());
        }
    }

    /**
     * �жϹ�Ӧ�Ƿ����
     * @param index
     * @return ���ص�һ������������Ĳ�Ʒ��,-1Ϊ����������
     */
    public int supplyEnough(Integer[] index){
        for (int i:index){
            if (product[i]<1)
                return i;
        }
        return -1;
    }

}
