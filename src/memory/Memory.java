package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Memory
 *
 * @author wz
 * @date 2016年01月14
 */
public class Memory {
    private int physicalMem; //K 起始块号，V 长度
    private int vitrualMem;
    private HashMap<Integer,Integer> pageTable; //K,V  页号，页帧号
    private LinkedList<String> message;

    public Memory(int physicalMem,int vitrualMem) {
        this.physicalMem = physicalMem;
        this.vitrualMem = vitrualMem;
        pageTable = new HashMap<>();
        message = new LinkedList<>();
    }

    public void save(int pageNum) {
        recover(pageNum);
        message.addLast("保留了虚地址空间"+pageNum+",不为其分配物理地址空间");
    }

    public void submit(int pageNum) {
        if (pageTable.containsKey(pageNum) || pageNum >= vitrualMem)
            return;

        Random random = new Random();
        int frame = random.nextInt(physicalMem);
        while (pageTable.containsValue(frame))
            frame = random.nextInt(physicalMem);
        pageTable.put(pageNum,frame);
        message.addLast("page "+pageNum+" 分配了 frame "+frame);
    }

    public void recover(int pageNum) {
        if (pageTable.containsKey(pageNum)){
            int frame = pageTable.get(pageNum);
            pageTable.remove(pageNum);
            message.addLast("回收了"+"page "+pageNum+" 所分配的 frame "+frame);
        }
    }

    public void release() {
        pageTable.clear();
        message.addLast("物理地址与虚拟地址空间全部释放");
    }

    public LinkedList<String> getMessage() {
        return message;
    }
}
