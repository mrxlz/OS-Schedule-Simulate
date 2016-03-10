package paging;

import java.util.LinkedList;

/**
 * ��Clock�û��㷨
 *
 * @author wz
 * @date 15/11/30.
 */
//TODO ����!
public class Clock {
    private LinkedList<Integer> memoryBlock;
    private int[] accessed;

    void pageReplacement(int[] pageString, int memBlockNum) {
        memoryBlock = new LinkedList<>();
        accessed = new int[memBlockNum];
        int pageFaultCount = 0, pageReplaceCount = 0;
        for (int i = 0; i < pageString.length; i++) {
            if (memoryBlock.contains(pageString[i])){
                accessed[memoryBlock.indexOf(pageString[i])] = 1;
                continue;
            }else if (memoryBlock.size() >= memBlockNum) {
                for (int j = 0; j < accessed.length;j++) {
                    accessed[j] ^= 1;   //ȡ��
                    if(accessed[j]==1){
                        memoryBlock.set(j,pageString[i]);
                        break;
                    }
                    if (j == accessed.length-1)
                        j = -1;
                }
                pageReplaceCount++;
            } else{
                memoryBlock.addLast(pageString[i]);
                accessed[memoryBlock.size()-1] = 1;
            }
            pageFaultCount++;
        }
        System.out.println("ȱҳ�ж���: "+pageFaultCount/(double)pageString.length);
        System.out.println("ҳ���û�����: "+pageReplaceCount);
    }
}
