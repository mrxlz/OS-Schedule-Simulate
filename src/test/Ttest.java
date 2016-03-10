package test;

/**
 * Ttest
 *
 * @author wz
 * @date 15/11/29.
 */
public class Ttest implements Runnable{
    private int a;


    public Ttest() {
        this.a = 0;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":" + a);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
