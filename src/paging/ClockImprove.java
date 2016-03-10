package paging;

import java.util.LinkedList;

/**
 * 改进的Clock置换算法
 *
 * @author wz
 * @date 15/11/30.
 */
//TODO 测试!
public class ClockImprove {
    private LinkedList<Integer> memoryBlock;
    private int[] accessed;
    private int[] modified;

    void pageReplacement(int[] pageString, int[] modifyStatus, int memBlockNum) {
        int index;
        memoryBlock = new LinkedList<>();
        accessed = new int[memBlockNum];
        modified = new int[memBlockNum];
        int pageFaultCount = 0, pageReplaceCount = 0;
        for (int i = 0; i < pageString.length; i++) {
            if (memoryBlock.contains(pageString[i])){
                index = memoryBlock.indexOf(pageString[i]);
                accessed[index] = 1;
                if (modified[index]==0)
                    modified[index]=modifyStatus[i];
                continue;
            } else if (memoryBlock.size() >= memBlockNum) {
                index=-1;
                while (true){
                    for (int j = 0; j < accessed.length; j++) {
                        if (accessed[j] == 0 && modified[j]==0){
                            index=j;
                            break;
                        }
                    }
                    if (index >= 0)
                        break;
                    for (int j = 0; j < accessed.length; j++) {
                        if (accessed[j] == 0 && modified[j]==1){
                            index = j;
                            break;
                        }
                        accessed[j]=0;
                    }
                    if (index >= 0)
                        break;
                }
                memoryBlock.set(index,pageString[i]);
                pageReplaceCount++;
            } else{
                memoryBlock.addLast(pageString[i]);
                index = memoryBlock.size()-1;
            }
            accessed[index] = 1;
            modified[index]=modifyStatus[i];
            pageFaultCount++;
        }
        System.out.println("缺页中断率: "+pageFaultCount/(double)pageString.length);
        System.out.println("页面置换次数: "+pageReplaceCount);
    }
}
