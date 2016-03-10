package process.synchronize;

/**
 * 消费者进程
 *
 * @author wz
 * @date 2015年11月22日
 */
public class Consumer extends ConsumerRequire implements Runnable{

    private Storage storage;
    private Lock lock;


    public Consumer(Storage storage, Lock lock) {
        this.storage = storage;
        this.lock = lock;
    }

    /**
     * 从storage中取出物品
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
        System.out.println(tName+" :开始消费产品");
        int[] product = storage.getProduct();
        for (int i:require){
            product[i]--;
            storage.setEmpty(storage.getEmpty()+1);
            System.out.println(tName+" :消费了"+i+"号产品");
        }
        System.out.println(tName+" :产品消费完毕");
        lock.ssignal(storage);
    }


    
}
