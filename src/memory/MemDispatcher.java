package memory;

import java.util.ArrayList;

/**
 * 内存分配
 *
 * @author wz
 * @date 2016年01月13
 */
public class MemDispatcher implements Runnable{

    private Memory memory;
    private ArrayList<String> operators;

    public MemDispatcher(Memory memory, ArrayList<String> operators) {
        this.memory = memory;
        this.operators = operators;
    }

    @Override
    public void run() {
        String []op;
        for (int i = 0; i < operators.size(); i++) {
            op = operators.get(i).split(" ");
            synchronized (memory){
                try {
                    switch (op[0]){
                        case "SAVE":
                            memory.save(Integer.parseInt(op[1]));
                            memory.notify();
                            Thread.sleep(Integer.parseInt(op[2]));
                            break;
                        case "SUBMIT":
                            memory.submit(Integer.parseInt(op[1]));
                            memory.notify();
                            Thread.sleep(Integer.parseInt(op[2]));
                            break;
                        case "RECOVER":
                            memory.recover(Integer.parseInt(op[1]));
                            memory.notify();
                            Thread.sleep(Integer.parseInt(op[2]));
                            break;
                        case "RELEASE":
                            memory.release();
                            memory.notify();
                            return;
                    }
                    System.out.println("等待了"+op[2]+"毫秒");
                } catch (InterruptedException e){

                }
            }
        }
    }
}
