package process.synchronize;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * ConsumerRequire
 *
 * @author wz
 * @date 15/11/29.
 */
public class ConsumerRequire {
    private LinkedList<ArrayList<Integer>> reqProduct;

    public ConsumerRequire() {
        this.reqProduct = new LinkedList<>();
    }

    public void addProcessRequire(ArrayList<Integer> require){
        reqProduct.addLast(require);
    }

    public ArrayList<Integer> getConsumerRequire(){
        return reqProduct.pollFirst();
    }
}
