package process.synchronize;

/**
 * 生产者进程
 *
 * @author wz
 * @date 2015年11月22日
 */
public class Producer implements Runnable {

    private Storage storage;
    private Lock lock;

    public Producer(Storage storage, Lock lock) {
        this.storage = storage;
        this.lock = lock;
    }

    /**
     * 生产产品，放入storage
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
            System.out.println(tName+" :开始生产产品");
            int i, product[] = storage.getProduct();
            for (i = 0; i < product.length; i++) {
                if (product[i]<1){
                    product[i]++;
                    System.out.println(tName+" :生产了"+i+"号产品");
                    break;
                }
            }
            storage.setEmpty(storage.getEmpty()-1);
            System.out.println(tName+" :产品生产完毕");
            lock.ssignal(storage,i);
        }
    }

}
