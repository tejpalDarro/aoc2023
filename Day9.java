import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// work in progress.....
public class Day9 { 
    static int SUM_MAX = 0;
    public static void main(String[] args) throws IOException {
        String str = """
10 13 16 21 30 45
                    """;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        for (String s : br.lines().toList()) {

            List<Integer> list = new ArrayList<>();
            String[] st = s.split(" ");  
            for (String ss : st) {
                list.add(Integer.parseInt(ss));
            }
            printList(list);

            int temp = calZero(list);
            SUM_MAX += temp;
            System.out.println("SUM: " + temp);
        }

        System.out.println(SUM_MAX);
        br.close();
    }

    public static int calZero(List<Integer> l) {
        int sum = 0;
        for (Integer i : l) {
            sum+=i; 
        }
        if (sum == 0) return 0;

        List<Integer> newList = new ArrayList<>();
        for (int i =1; i < l.size(); i++) {
            newList.add(l.get(i) - l.get(i-1));
        }
        printList(newList);
        int res = calZero(newList);
        return l.get(l.size()-1) + res;
    }

    public static void printList(List<Integer> l) {
        l.forEach((x) -> System.out.print(x + " "));
        System.out.println();
    }
}
