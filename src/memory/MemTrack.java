package memory;

import java.util.LinkedList;

/**
 * �ڴ����
 *
 * @author wz
 * @date 2016��01��13
 */
public class MemTrack implements Runnable{

    private Memory memory;

    public MemTrack(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void run() {
        LinkedList<String> message;
        while (true){
            synchronized (memory){
                message = memory.getMessage();
                for (String msg:message){
                    System.out.println(msg);
                }
                message.clear();
                try {
                    memory.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
