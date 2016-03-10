package test;

/**
 * ThreadTest
 *
 * @author wz
 * @date 15/11/27.
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Ttest test = new Ttest();
        for (int i = 0; i < 3; i++) {
            test.setA(i+5);
            new Thread(test).start();
            Thread.sleep(1000);
        }
    }

}
