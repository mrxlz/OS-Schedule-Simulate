package process.synchronize;

import java.io.*;
import java.util.ArrayList;

/**
 * 进程同步:生产者-消费者问题
 *
 * @author wz
 * @date 15/11/26.
 */
public class Synchronize {

    public static final int CAPACITY = 10;
    public static void main(String[] args) {

        int count = 0;
        Lock lock= new Lock();
        Storage storage = new Storage(CAPACITY);
        Producer producer = new Producer(storage, lock);
        Consumer consumer = new Consumer(storage, lock);
        BufferedReader br = null;
        ArrayList<Integer> require;
        String line,requires[];
        try {
            br = new BufferedReader(new FileReader("info.txt"));
            while ((line = br.readLine())!=null && !line.trim().equals("")){
                require  = new ArrayList<>();
                requires = line.split("\\s");
                for (int i = 0; i < requires.length; i++)
                    require.add(Integer.parseInt(requires[i]));
                consumer.addProcessRequire(require);
                System.out.println(require.toString());
                count++;
            }

            for (int i = 1; i <= count; i++)
                new Thread(consumer,"消费者 "+i).start();

            new Thread(producer,"生产者").start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
