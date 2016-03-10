package paging;

import java.util.LinkedList;

/**
 * LRU(������δʹ��)ҳ���û��㷨
 *
 * @author wz
 * @date 15/11/30.
 */
//TODO ���ԣ�
public class LRU {
    private LinkedList<Integer> memoryBlock;

    void pageReplacement(int[] pageString, int memBlockNum) {
        memoryBlock = new LinkedList<>();
        int pageFaultCount = 0, pageReplaceCount = 0;
        for (int i = 0; i < pageString.length; i++) {
            if (memoryBlock.contains(pageString[i])){
                memoryBlock.addLast(memoryBlock.remove(memoryBlock.indexOf(pageString[i])));
                continue;
            }else if (memoryBlock.size() >= memBlockNum) {
                memoryBlock.pollFirst();
                pageReplaceCount++;
            }
            memoryBlock.addLast(pageString[i]);
            pageFaultCount++;
        }
        System.out.println("ȱҳ�ж���: "+pageFaultCount/(double)pageString.length);
        System.out.println("ҳ���û�����: "+pageReplaceCount);
    }

}
