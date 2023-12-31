import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class Day10 {
    public static void main(String[] args) throws IOException {
        String str = """        
                    ..F7.
                    .FJ|.
                    SJ.L7
                    |F--J
                    LJ...
                    """;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringBuilder sb = new StringBuilder();

        for (String s : br.lines().toList()) {
            sb.append(s);
            sb.append("\n"); 
        }
        int[] startingPos = new int[2];
        int row = 0;
        int col = 0;
        for (String s : sb.toString().lines().toList()) {
            int cnt = 0;
            for (String p : s.split("")) {
                if (p.equals("S")) {
                    startingPos[0] = row;
                    startingPos[1] = cnt;
                }
                ++cnt;
            }
            col = s.length();
            ++row;
        }
        String[][] matrix = new String[row][col];
        fillTheMatrix(sb.toString(), matrix);
        // printTheMatrix(matrix);
        System.out.println("create the matrrix");
        solve(matrix, startingPos);
        System.out.println("Valid pos: " + matrix[startingPos[0]][startingPos[1]]);
        System.out.println("Starting pos: " + startingPos[0] + "," + startingPos[1]);
        System.out.println("Row: " + row);
        System.out.println("Col: " + col);
        br.close();
    }

    private static void solve(String[][] matrix, int[] startingPos) {
        int[][] list = new int[matrix.length][matrix[1].length];
        boolean[][] flag = new boolean[matrix.length][matrix[1].length];
        // for (int i=0; i<flag.length; i++) {
        //     for (int j=0; j<flag[1].length; j++) {
        //         System.out.print(flag[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        int cnt = 1;
        int[][] arr1 = dfs(matrix, startingPos[0], startingPos[1]-1, list, cnt, flag);
        int[][] arr2 = dfs(matrix, startingPos[0], startingPos[1]+1, list, cnt, flag);
        int[][] arr3 = dfs(matrix, startingPos[0]+1, startingPos[1], list, cnt, flag);
        int[][] arr4 = dfs(matrix, startingPos[0]-1, startingPos[1], list, cnt, flag);

        System.out.println("arr1: " + arr1.length);
        System.out.println("arr2: " + arr2.length);
        System.out.println("arr3: " + arr3.length);
        System.out.println("arr4: " + arr4.length);
        // for (int i=0; i<arr1.length; i++) {
        //     for (int j=0; j<arr1[1].length; j++) {
        //         System.out.print(arr1[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }

    private static int[][] dfs(String[][] matrix, int i, int j, int[][] list, int cnt, boolean[][] flag) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[i].length || matrix[i][j].equals(".") || flag[i][j] == true) {
            return new int[][] {};
        }
        if (matrix[i][j].equals("S") ) {
            System.out.println("found S");
            return list;
        }
        list[i][j] = cnt++;
        flag[i][j] = true;
        int[] res = switch(matrix[i][j]) {
            case "F" ->  new int[] { i, j+1, i+1, j };
            case "7" ->  new int[] { i, j-1, i+1, j };
            case "J" ->  new int[] { i-1, j, i, j-1 };
            case "L" ->  new int[] { i, j+1, i-1, j };
            case "|" ->  new int[] { i+1, j, i-1, j };
            case "-" ->  new int[] { i, j+1, i, j-1 };
            default -> {
                throw new RuntimeException("invalid inputs inside switch statement");
            }
        };
        dfs(matrix, res[0], res[1], list, cnt, flag);
        dfs(matrix, res[2], res[3], list, cnt, flag);
        return list;
    }

    private static void printTheMatrix(String[][] matrix) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void fillTheMatrix(String str, String[][] matrix) {
        int i = 0;
        for (String s : str.lines().toList()) {
            int j = 0;
            for (String p : s.split("")) {
                matrix[i][j] = p;
                ++j;
            }
            ++i;
        }
    }
    
}
