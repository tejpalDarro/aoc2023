import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// work in progress.....
public class Day9_part2 { 
    static long SUM_MAX = 0;
    public static void main(String[] args) throws IOException {
        String str = """
10 13 16 21 30 45
                    """;
        int cnt = 1;
        // ArrayList<Long> outputLines = new ArrayList<>();
        // FileWriter writer = new FileWriter("output2.txt");
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        for (String s : br.lines().toList()) {

            // List<Integer> list = new ArrayList<>();
            // String[] st = s.split(" ");  
            // for (String ss : st) {
            //     list.add(Integer.parseInt(ss));
            // }
            List<Long> list = Arrays.stream(s.split(" ")).map(a -> Long.parseLong(a)).toList();
            printList(list);

            long temp = calZero(list);
            // outputLines.add(temp);
            SUM_MAX += temp;
            System.out.println("temp cnt : " + cnt + ", val: " + temp);
            ++cnt;
        }
        // for (Long i : outputLines) {
        //     writer.write(i + System.lineSeparator());
        // }
        // writer.close();

        System.out.println(SUM_MAX);
        br.close();
    }

    public static long calZero(List<Long> l) {
        boolean flag = false;
        for (Long i : l) {
            if (i == 0) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        if (flag) return 0;

        List<Long> newList = new ArrayList<>();
        for (int i =1; i < l.size(); i++) {
            newList.add(l.get(i) - l.get(i-1));
        }
        printList(newList);
        long res = calZero(newList);
        return l.get(0) - res;
    }

    public static void printList(List<Long> l) {
        l.forEach((x) -> System.out.print(x + " "));
        System.out.println();
    }
}
