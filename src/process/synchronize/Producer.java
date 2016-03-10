package process.synchronize;

/**
 * �����߽���
 *
 * @author wz
 * @date 2015��11��22��
 */
public class Producer implements Runnable {

    private Storage storage;
    private Lock lock;

    public Producer(Storage storage, Lock lock) {
        this.storage = storage;
        this.lock = lock;
    }

    /**
     * ������Ʒ������storage
     */
    @Override
    public void run() {
        while (true){
            try {
                lock.swait(storage,null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String tName = Thread.currentThread().getName();
            System.out.println(tName+" :��ʼ������Ʒ");
            int i, product[] = storage.getProduct();
            for (i = 0; i < product.length; i++) {
                if (product[i]<1){
                    product[i]++;
                    System.out.println(tName+" :������"+i+"�Ų�Ʒ");
                    break;
                }
            }
            storage.setEmpty(storage.getEmpty()-1);
            System.out.println(tName+" :��Ʒ�������");
            lock.ssignal(storage,i);
        }
    }

}
