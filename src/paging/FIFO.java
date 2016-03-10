package paging;

import java.util.LinkedList;

/**
 * FIFO(�Ƚ��ȳ�)ҳ���û��㷨
 *
 * @author wz
 * @date 15/11/30.
 */
public class FIFO {
    private LinkedList<Integer> memoryBlock;

    void pageReplacement(int[] pageString, int memBlockNum) {
        memoryBlock = new LinkedList<>();
        int pageFaultCount = 0, pageReplaceCount = 0;
        for (int i = 0; i < pageString.length; i++) {
            if (memoryBlock.contains(pageString[i]))
                continue;
            if (memoryBlock.size() >= memBlockNum) {
                memoryBlock.pollFirst();
//                memoryBlock.set(0, pageString[i]);
                pageReplaceCount++;
            }
            memoryBlock.add(pageString[i]);
            pageFaultCount++;
        }
        System.out.println("ȱҳ�ж���: "+pageFaultCount/(double)pageString.length);
        System.out.println("ҳ���û�����: "+pageReplaceCount);
    }
}
