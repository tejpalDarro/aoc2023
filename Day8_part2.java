import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

public class Day8_part2 {
    public static Map<String,List<String>> map = new LinkedHashMap<>();
    public static StringBuilder direction = new StringBuilder();
    public static String[] str;
    public static int length;


    public static void makeArrayFromString(String s) {
        str = s.split("");
        length = s.length();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException{
        String str = """
                        LR

                        11A = (11B, XXX)
                        11B = (XXX, 11Z)
                        11Z = (11B, XXX)
                        22A = (22B, XXX)
                        22B = (22C, 22C)
                        22C = (22Z, 22Z)
                        22Z = (22B, 22B)
                        XXX = (XXX, XXX)
                        """;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        for (String s : br.lines().toList()) {
            makeArrayFromString(s);
            break;
        }
        BufferedReader brr = new BufferedReader(new FileReader("input.txt"));

        int cnt = 2;
        for (String s : brr.lines().toList()) {
            if (cnt > 0) {
                cnt--;
                continue;
            } 
            String first = s.substring(0,3); 
            String second = s.substring(7,10);
            String third = s.substring(12,s.length()-1);
            List<String> dir = new ArrayList<>();
            dir.add(second);
            dir.add(third);
            map.put(first, dir);
        }

        int idx = 0;
        int counter = 0;
        StringBuilder sb = new StringBuilder("11A");
        StringBuilder sb2 = new StringBuilder("22A");
        while (!sb.toString().equals("ZZZ")) {
            String dir = Day8.str[idx];
            if (dir.equals("L")) {
                String s = map.get(sb.toString()).get(0); 
                sb.setLength(0);
                sb.append(s);
            } else if (dir.equals("R")) {
                String s = map.get(sb.toString()).get(1); 
                sb.setLength(0);
                sb.append(s);
            }
            if (idx == length-1) {
                idx = -1;
            } 
            idx++;
            counter++;
        }
        br.close();
        brr.close();

        System.out.println(counter);
    }

    public static void printMap() {
        map.forEach((k,v) -> {
            System.out.println("key: " + " " + k + ", value1: " + v.get(0) + ", value2: " + v.get(1));
        });
    }
}


