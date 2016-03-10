package memory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main
 *
 * @author wz
 * @date 2016��01��14
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("memop.txt"));
        ArrayList<String> op = new ArrayList<>();
        while (scanner.hasNext()){
            op.add(scanner.nextLine());
        }

        Memory memory = new Memory(2048,4096);

        new Thread(new MemTrack(memory)).start();
        new Thread(new MemDispatcher(memory, op)).start();


    }
}
