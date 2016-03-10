package process.synchronize;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * Lock
 *
 * @author wz
 * @date 15/11/26.
 */
public class Lock {
    /**
     * 互斥信号量
     */
    private int mutex;

    public Lock() {
        this.mutex = 1;
    }

    /**
     * P操作。若共享缓冲区已被占用或生产者不满足生产条件或消费者不满足消费条件则阻塞当前进程
     *
     * @param storage 共享缓冲区对象
     * @param reqPro  消费者请求消费产品
     */
    public void swait(Storage storage, Integer[] reqPro) throws InterruptedException {
        int index;
        String tName = Thread.currentThread().getName();
        while (true) {
            System.out.println(tName + " :申请使用共享缓冲区");
            synchronized (storage) {
                //互斥进程进入等待
                if (mutex == 0) {
                    System.out.println(tName + " :等待，共享缓冲区已被占用");
                    storage.wait();
                    continue;
                } else {
                    mutex = 0;
                    System.out.println(tName + " :申请到共享缓冲区");
                }

            }

            //生产者
            if (reqPro == null || reqPro.length == 0) {
                if (storage.getEmpty() != 0)
                    break;

                //empty == 0,阻塞当前生产者进程
                synchronized (storage) {
                    System.out.println(tName + " :无空缓冲区，释放缓冲区占有权，等待消费者消费");
                    mutex = 1;
                    storage.getProducerQueue().add(Thread.currentThread());
                    //唤醒互斥进程
                    storage.notifyAll();
                }
                LockSupport.park();
            } else {//消费者请求消费
                index = storage.supplyEnough(reqPro);
                if (index < 0)
                    break;
                //不满足消费需求，进入阻塞队列
                synchronized (storage) {
                    System.out.println(tName + " :" + index + "号没有产品，释放缓冲区占有权，进入阻塞队列");
                    mutex = 1;
                    if (storage.getConsumerQueue()[index] == null)
                        storage.getConsumerQueue()[index] = new LinkedList<Thread>();
                    storage.getConsumerQueue()[index].add(Thread.currentThread());
                    //唤醒互斥进程和生产者阻塞进程
                    storage.notifyAll();
                    storage.wakeupProducer();
                }
                LockSupport.park();
            }
        }
    }

    /**
     * V操作，释放消费者请求的产品缓冲区空间，唤醒相应缓冲区的阻塞队列中的进程
     *
     * @param storage      共享缓冲区对象
     * @param productIndex 生产产品号
     */
    public void ssignal(Storage storage, int... productIndex) {
        String tName = Thread.currentThread().getName();
        synchronized (storage) {
            System.out.println(tName + " :缓冲区使用完毕，释放缓冲区占有权");
            storage.notifyAll();
            storage.wakeupProducer();
            //如果是生产者，则唤醒生产出的产品对应的阻塞进程
            if (productIndex != null || productIndex.length != 0) {
                for (int index : productIndex)
                    storage.wakeupConsumer(index);
            }
            mutex = 1;
        }
    }

}
