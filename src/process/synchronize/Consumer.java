package process.synchronize;

/**
 * �����߽���
 *
 * @author wz
 * @date 2015��11��22��
 */
public class Consumer extends ConsumerRequire implements Runnable{

    private Storage storage;
    private Lock lock;


    public Consumer(Storage storage, Lock lock) {
        this.storage = storage;
        this.lock = lock;
    }

    /**
     * ��storage��ȡ����Ʒ
     */
    @Override
    public void run() {
        Integer[] require;
        require = getConsumerRequire().toArray(new Integer[0]);
        try {
            lock.swait(storage,require);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String tName = Thread.currentThread().getName();
        System.out.println(tName+" :��ʼ���Ѳ�Ʒ");
        int[] product = storage.getProduct();
        for (int i:require){
            product[i]--;
            storage.setEmpty(storage.getEmpty()+1);
            System.out.println(tName+" :������"+i+"�Ų�Ʒ");
        }
        System.out.println(tName+" :��Ʒ�������");
        lock.ssignal(storage);
    }


    
}
