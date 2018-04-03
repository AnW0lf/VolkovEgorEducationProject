package task2;

import java.util.LinkedList;

public class Application {
    public static void main(String[] args) {
        Grep grep = new Grep(args);
        System.out.println("____________________________________________________________");
        System.out.print(grep.toString());
        System.out.println("____________________________________________________________");
        LinkedList<String> result = grep.search();
        for (int i = 0; i < result.size(); ++i)
            System.out.println((i + 1) + ": " + result.get(i));
        System.out.println("____________________________________________________________");
    }
}
