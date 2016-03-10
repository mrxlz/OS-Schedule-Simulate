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
     * �����ź���
     */
    private int mutex;

    public Lock() {
        this.mutex = 1;
    }

    /**
     * P�����������������ѱ�ռ�û������߲��������������������߲���������������������ǰ����
     *
     * @param storage ������������
     * @param reqPro  �������������Ѳ�Ʒ
     */
    public void swait(Storage storage, Integer[] reqPro) throws InterruptedException {
        int index;
        String tName = Thread.currentThread().getName();
        while (true) {
            System.out.println(tName + " :����ʹ�ù�������");
            synchronized (storage) {
                //������̽���ȴ�
                if (mutex == 0) {
                    System.out.println(tName + " :�ȴ������������ѱ�ռ��");
                    storage.wait();
                    continue;
                } else {
                    mutex = 0;
                    System.out.println(tName + " :���뵽��������");
                }

            }

            //������
            if (reqPro == null || reqPro.length == 0) {
                if (storage.getEmpty() != 0)
                    break;

                //empty == 0,������ǰ�����߽���
                synchronized (storage) {
                    System.out.println(tName + " :�޿ջ��������ͷŻ�����ռ��Ȩ���ȴ�����������");
                    mutex = 1;
                    storage.getProducerQueue().add(Thread.currentThread());
                    //���ѻ������
                    storage.notifyAll();
                }
                LockSupport.park();
            } else {//��������������
                index = storage.supplyEnough(reqPro);
                if (index < 0)
                    break;
                //�������������󣬽�����������
                synchronized (storage) {
                    System.out.println(tName + " :" + index + "��û�в�Ʒ���ͷŻ�����ռ��Ȩ��������������");
                    mutex = 1;
                    if (storage.getConsumerQueue()[index] == null)
                        storage.getConsumerQueue()[index] = new LinkedList<Thread>();
                    storage.getConsumerQueue()[index].add(Thread.currentThread());
                    //���ѻ�����̺���������������
                    storage.notifyAll();
                    storage.wakeupProducer();
                }
                LockSupport.park();
            }
        }
    }

    /**
     * V�������ͷ�����������Ĳ�Ʒ�������ռ䣬������Ӧ�����������������еĽ���
     *
     * @param storage      ������������
     * @param productIndex ������Ʒ��
     */
    public void ssignal(Storage storage, int... productIndex) {
        String tName = Thread.currentThread().getName();
        synchronized (storage) {
            System.out.println(tName + " :������ʹ����ϣ��ͷŻ�����ռ��Ȩ");
            storage.notifyAll();
            storage.wakeupProducer();
            //����������ߣ������������Ĳ�Ʒ��Ӧ����������
            if (productIndex != null || productIndex.length != 0) {
                for (int index : productIndex)
                    storage.wakeupConsumer(index);
            }
            mutex = 1;
        }
    }

}
