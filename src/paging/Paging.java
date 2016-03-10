package paging;

/**
 * Paging
 *
 * @author wz
 * @date 15/12/1.
 */
public class Paging {
    public static void main(String[] args) {
//        FIFO fifo = new FIFO();
//        fifo.pageReplacement(new int[]{1, 2, 3, 4, 1, 3, 2},3);
//        Optimal optimal = new Optimal();
//        optimal.pageReplacement(new int[]{1, 2, 3, 4, 1, 3, 2},3);
//        LRU lru = new LRU();
//        lru.pageReplacement(new int[]{4,7,0,7,1,0,1,2,1,2,6},5);
//        Clock clock=new Clock();
//        clock.pageReplacement(new int[]{1, 2, 3, 4, 1, 3, 2},3);
        ClockImprove clockImprove=new ClockImprove();
        clockImprove.pageReplacement(new int[]{1, 2, 3, 4, 1, 3, 2},new int[]{0, 1, 0, 0, 1, 0, 0},3);
    }
}
