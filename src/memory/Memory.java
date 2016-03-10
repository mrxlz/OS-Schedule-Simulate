package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Memory
 *
 * @author wz
 * @date 2016��01��14
 */
public class Memory {
    private int physicalMem; //K ��ʼ��ţ�V ����
    private int vitrualMem;
    private HashMap<Integer,Integer> pageTable; //K,V  ҳ�ţ�ҳ֡��
    private LinkedList<String> message;

    public Memory(int physicalMem,int vitrualMem) {
        this.physicalMem = physicalMem;
        this.vitrualMem = vitrualMem;
        pageTable = new HashMap<>();
        message = new LinkedList<>();
    }

    public void save(int pageNum) {
        recover(pageNum);
        message.addLast("���������ַ�ռ�"+pageNum+",��Ϊ����������ַ�ռ�");
    }

    public void submit(int pageNum) {
        if (pageTable.containsKey(pageNum) || pageNum >= vitrualMem)
            return;

        Random random = new Random();
        int frame = random.nextInt(physicalMem);
        while (pageTable.containsValue(frame))
            frame = random.nextInt(physicalMem);
        pageTable.put(pageNum,frame);
        message.addLast("page "+pageNum+" ������ frame "+frame);
    }

    public void recover(int pageNum) {
        if (pageTable.containsKey(pageNum)){
            int frame = pageTable.get(pageNum);
            pageTable.remove(pageNum);
            message.addLast("������"+"page "+pageNum+" ������� frame "+frame);
        }
    }

    public void release() {
        pageTable.clear();
        message.addLast("�����ַ�������ַ�ռ�ȫ���ͷ�");
    }

    public LinkedList<String> getMessage() {
        return message;
    }
}
