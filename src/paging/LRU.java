package paging;

import java.util.LinkedList;

/**
 * LRU(最近最久未使用)页面置换算法
 *
 * @author wz
 * @date 15/11/30.
 */
//TODO 测试！
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
        System.out.println("缺页中断率: "+pageFaultCount/(double)pageString.length);
        System.out.println("页面置换次数: "+pageReplaceCount);
    }

}
