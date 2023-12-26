import java.util.HashMap;
import java.util.Map;

public class Day6 {
    public static Map<Integer, Integer> map = new HashMap<>();
    public static int[][] a = new int[2][4];
    public static int MAX = 1;
    public static void main(String[] args) {
        String str = """ 
Time:        59     70     78     78
Distance:   430   1218   1213   1276
""";    
        int i=0;
        int j=-1;
        for (String s : str.lines().toList()) {
            for (String ss : s.substring(11, s.length()).lines().toList()) {
                for (String c : ss.trim().split(" ")) {
                    
                    if (isNumber(c)) {
                        ++j;
                        a[i][j] = Integer.parseInt(c);
                    }
                }
                ++i;
                j=-1;
            }

        }
        // for (int x=0; x<a.length; x++) {
        //     for (int y=0; y<a[0].length; y++) {
        //         System.out.print(a[x][y] + " ");
        //     }
        //     System.out.println();
        // }

        for (int m=0; m<a[0].length; m++) {
            getVal(a[0][m], a[1][m]);
            System.out.println();
        }

        System.out.println(MAX);
    }

    public static void getVal(int x, int y) {
        int temp = x;
        int cnt =0;
        for (int i=1; i<=temp; i++) {
            int time = temp - i;
            int distance = i * time;
            if (distance > y) {
                ++cnt;
            }
        }

        MAX *= cnt;
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
