import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Day3 implements Serializable {
    public static int MAX = 0;
    public static char[][] arr;
    public static char[][] char2;
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String str = """
467..114..
...*......
..35..#333
..........
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
""";

        int row = 0;
        int col = 0;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        for (String s : br.lines().toList()) {
            ++row;
            col = s.length();
        }

        br.close();
        BufferedReader brr = new BufferedReader(new FileReader("input.txt"));

        arr = new char[row][col];

        int i = 0;
        for (String ss : brr.lines().toList()) {
            char[] ch = ss.toCharArray();
            for (int a=0; a<ch.length; a++) {
                arr[i][a] = ch[a];
            }
            ++i;
        }



        System.out.println("");
        br.close();
        brr.close();
        int cnt = 0;

        for (char[] cc : arr) {
            findNum(cc, cnt);
            ++cnt;
        }

        System.out.println("Max: " + MAX);
    }

    public static void findNum(char[] c, int row) {

        int start = -1;
        int end = 0;
        for (int i=0; i<c.length; i++) {
            if (Character.isDigit(c[i])  && start == -1) {
                start = i;
            }
            if (Character.isDigit(c[i]) && start != -1){
                end = i;
            } else if ((checkSymbolWithDot(i, row) && start != -1)) {
                // System.out.println("Start: " + start + ", End: " + end + ", row: " + row);
                findAround(start, end, row);
                start = -1;
                end = -1;
            }
        }

    }

    public static boolean checkSymbolWithDot(int x, int row) {
        boolean flag = switch(arr[row][x]) {
            case '@', '#', '$', '%', '&', '*', '+', '-', '.', '/', '=' -> flag = true;
            default -> flag = false;
        };
        return flag;
    }


    public static void findAround(int x, int y, int row) {

        for (int i= x; i <=y; i++) {

            if (dfs(i,row)) {
                addTotal(x,y, row);
                addToList(x,y,row);
                // System.out.println("found it row : " + row + ",  x: " + x + ", y: " + y );
                return;
            }
        }

    }

    public static void addToList(int x, int y, int row) {
        for (int i=x; i<=y; i++) {
            arr[row][i] = '.';
        }
    }

    public static boolean dfs(int x, int row) {


        // System.out.println("x: " + x + ",row: " + row);
        if ((x >= 0 && x < arr[0].length) && (row >= 0 && row < arr.length)) {

            if(checkNum(x-1, row)) {return true;}
            if(checkNum(x+1, row)) {return true;}
            if(checkNum(x, row+1)) {return true;}
            if(checkNum(x, row-1)) {return true;}

            if(checkNum(x-1, row-1)) {return true;}
            if(checkNum(x-1, row+1)) {return true;}
            if(checkNum(x+1, row-1)) {return true;}
            if(checkNum(x+1, row+1)) {return true;}
        }
        return false;
    }

    public static boolean checkNum(int x, int row) {

        // System.out.println("x: " + x + ",row: " + row);

        if ((x >= 0 && x < arr[0].length) && (row >= 0 && row < arr.length)) {
            boolean flag = switch(arr[row][x]) {
                case '@', '#', '$', '%', '&', '*', '+', '-', '/', '=' -> flag = true;
                default -> flag = false;
            };

            if (flag) {
                return true;
            }
        }

        return false;
    }

    public static void addTotal(int x, int y, int row) {
        StringBuilder sb = new StringBuilder("");
        for (int i=x; i<=y; i++) {
            sb.append(arr[row][i]);
        }

        MAX += Integer.parseInt(sb.toString());
    }

}

