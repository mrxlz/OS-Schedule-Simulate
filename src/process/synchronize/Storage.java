package process.synchronize;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * 生产物品仓库
 *
 * @author wz
 *
 * @date 2015年11月22日
 */
public class Storage {
    /**
     * 缓冲区产品
     */
    private int[] product;
    /**
     * 等待队列
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
     * 唤醒生产者阻塞进程
     */
    public void wakeupProducer(){
        Thread t;
        while (!producerQueue.isEmpty()){
            t=producerQueue.poll();
            LockSupport.unpark(t);
//            System.out.println("唤醒了"+t.getName());
        }
    }

    /**
     * 唤醒第index号产品的阻塞进程
     * @param index
     */
    public void wakeupConsumer(int index){
        Thread t;
        while (consumerQueue[index]!=null && !consumerQueue[index].isEmpty()){
            t=consumerQueue[index].poll();
            LockSupport.unpark(t);
//            System.out.println("唤醒了"+t.getName());
        }
    }

    /**
     * 判断供应是否充足
     * @param index
     * @return 返回第一个不满足需求的产品号,-1为皆需求满足
     */
    public int supplyEnough(Integer[] index){
        for (int i:index){
            if (product[i]<1)
                return i;
        }
        return -1;
    }

}
