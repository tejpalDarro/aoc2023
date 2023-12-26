import java.util.HashMap;
import java.util.Map;

public class Day6_part2 {
    public static Map<Integer, Integer> map = new HashMap<>();
    public static long[] a = new long[2];
    public static int MAX = 1;
    public static void main(String[] args) {
        String str = """ 
Time:       59707878
Distance:   430121812131276
""";
        int i=0;
        for (String s : str.lines().toList()) {
            for (String ss : s.substring(11, s.length()).lines().toList()) {
                if (isNumber(ss.trim())) {
                    a[i] = Long.parseLong(ss.trim());
                }
            }
            ++i;
        }
        getVal(a[0], a[1]);

    }

    public static void getVal(long x, long y) {
        long temp = x;
        long cnt =0;
        for (int i=1; i<=temp; i++) {
            long time = temp - i;
            long distance = i * time;
            if (distance > y) {
                ++cnt;
            }
        }

        System.out.println(cnt);

    }


    public static boolean isNumber(String s) {
        if (s.equals(" ")) return false;
        if (s.equals("")) return false;
        char[] c = s.toCharArray();
        for (int i=0; i<c.length; i++) {
            if (!Character.isDigit(c[0])) return false;
        } 
        return true;
    }

}
