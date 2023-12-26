import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Day4 {
    public static Map<String, String> map = new TreeMap<>(); 
    public static int[][] arr = new int[6][10];
    public static long MAX = 0;
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String str = """
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
""";

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int cnt = 1;
        for (String s : br.lines().toList()) {
            for (String ss : s.substring(10, 39).lines().toList()) { //substring(10, 39) //(8,22)
                // System.out.println(ss);
                map.put("Game" + cnt, ss);
            } 
            for (String ss : s.substring(42, s.length()).lines().toList()) { //substring(42, s.length()) // (25, s.len)
                // System.out.println(ss);
                countWinning(ss,cnt);
            }
            ++cnt;
        }

        System.out.println("MAX VAL:" + MAX);
        br.close();

        // for (int i=0; i<5; i++) {
        //     for (int j=0; j<5; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println("");
        // }

        // map.forEach((k,v)-> {System.out.println("k: " + k + ", v: " + v);});
    }

    public static void countWinning(String s, int cnt) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (String ss : s.split(" ")) {
            if (isChar(ss)) {
                set.add(Integer.parseInt(ss));
            }
        }

        String test = map.get("Game" + cnt);
        for (String ss : test.split(" ")) {
            if (isChar(ss)) {
                int res = Integer.parseInt(ss);
                if (set.contains(res)) {
                    list.add(res);
                }
            }

        }

        calTotal(list);


    }

    public static void calTotal(List<Integer> list) {

        int res = 1;
        list.forEach((x)-> {System.out.print(x + " ");});
        System.out.println("");

        if (list.size() == 0) {
            return;
        }

        if (list.size() == 1) {
            MAX += 1;
            return;
        }

        if (list.size() > 1) {
            for (int i=1; i<list.size(); i++) {
                System.out.println("yes");
                res *= 2;
            }
            MAX += res;
        }
            System.out.println(res);


    }

    public static boolean isChar(String s) {

        if (s.equals("") || s.equals(" ")) return false;

        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
