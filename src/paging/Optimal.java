package paging;

import java.util.LinkedList;

/**
 * Optimal(���)�û��㷨
 *
 * @author wz
 * @date 15/11/30.
 */
//TODO ���ԣ��Ż�
public class Optimal {
    private LinkedList<Integer> memoryBlock;

    void pageReplacement(int[] pageString, int memBlockNum) {
        memoryBlock = new LinkedList<>();
        int maxDistIndex,willVisit,replaceIndex = -1;
        int pageFaultCount = 0, pageReplaceCount = 0;
        for (int i = 0; i < pageString.length; i++) {
            if (memoryBlock.contains(pageString[i]))
                continue;
            if (memoryBlock.size() >= memBlockNum) {
                // �����ʱ���ڲ������ʵ�ҳ
                maxDistIndex = -1;
                for (int j = 0; j < memBlockNum; j++) {
                    willVisit = 0;
                    for (int k = i+1; k < pageString.length; k++) {
                        if (memoryBlock.get(j) == pageString[k]) {
                            if (k > maxDistIndex){
                                maxDistIndex = k;
                                replaceIndex = j;
                            }
                            willVisit = 1;
                            break;
                        }
                    }
                    if (willVisit == 0){
                        replaceIndex = j;
                        break;
                    }
                }
                memoryBlock.set(replaceIndex, pageString[i]);
                pageReplaceCount++;
            } else
                memoryBlock.add(pageString[i]);
            pageFaultCount++;
        }
        System.out.println("ȱҳ�ж���: "+pageFaultCount/(double)pageString.length);
        System.out.println("ҳ���û�����: "+pageReplaceCount);
    }
}
