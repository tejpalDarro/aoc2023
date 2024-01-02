import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


// stack over flow will happen if you compile it normal
// use -Xss515m to give more memory to stack
// $javac Day10.java
// $java -Xss515m Day10
enum Pos {
    NORTH, SOUTH, EAST, WEST;
}

public class Day10_part2 {
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
        // System.out.println("Valid pos: " + matrix[startingPos[0]][startingPos[1]]);
        // System.out.println("Starting pos: " + startingPos[0] + "," + startingPos[1]);
        // System.out.println("Row: " + row);
        // System.out.println("Col: " + col);
        br.close();
    }

    private static void solve(String[][] matrix, int[] startingPos) {
        int[][] list = new int[matrix.length][matrix[1].length];
        list[startingPos[0]][startingPos[1]] = 1;
        // for (int i=0; i<flag.length; i++) {
        //     for (int j=0; j<flag[1].length; j++) {
        //         System.out.print(flag[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        int cnt = 1;
        // for WEST
        // System.out.println("Starting the arr1");
        // int arr1 = dfs(matrix, startingPos[0], startingPos[1]-1, Pos.WEST, list, cnt, new boolean[matrix.length][matrix[0].length]);
        // System.out.println("arr1: " + arr1);

        // for EAST
        // System.out.println("Starting the arr2");
        // int arr2 = dfs(matrix, startingPos[0], startingPos[1]+1, Pos.EAST, list, cnt, new boolean[matrix.length][matrix[0].length]);
        // System.out.println("arr2: " + arr2);

        // for SOUTH
        System.out.println("Starting the arr3");
        dfs(matrix, startingPos[0]+1, startingPos[1], Pos.SOUTH, list, cnt, new boolean[matrix.length][matrix[0].length]);

        // for NORTH
        // System.out.println("Starting the arr4");
        // int arr4 = dfs(matrix, startingPos[0]-1, startingPos[1], Pos.NORTH, list, cnt,new boolean[matrix.length][matrix[0].length]);
        // System.out.println("arr4: " + arr4);


        
        printTheMatrix(list);


        Queue<Integer> q = new LinkedList<>();
        boolean[][] flag = new boolean[list.length][list[0].length];

        int idx = 0;
        for (int i=1; i<list.length-1; i++) {
            for (int j=1; j<list[1].length-1; j++) {
                if (list[i][j] == 0) {
                    q.add(list[i][j]);
                    if (bfs(list, i, j, q, flag)) {
                        cnt++;
                    }
                }   
            }
        }
        System.out.println("ANS: " + idx);
    }
   // bfs   dfs
   // queue stack 

    private static boolean bfs(int[][] list, int i, int j, Queue<Integer> q, boolean[][] flag) {
        if (i < 1 || i > list.length-1 || j < 1 || j > list[0].length-1 || list[i][j] == 1) {
            return false;
        }

        while (!q.isEmpty()) {
            if (flag[i][j] == false) {
                flag[i][j] = true;

                for (int x=0; x<4; x++) {
                    bfs(list, i, j, q, flag);
                }
            }
        }

        return false;
    }

    private static int dfs(String[][] matrix, int i, int j,  Pos dir, int[][] list, int cnt, boolean[][] flag) {
        if (i < 0 || i > matrix.length || j < 0 || j > matrix[i].length || matrix[i][j].equals(".") || flag[i][j] == true) {
            return 0;
        }
        if (matrix[i][j].equals("S") ) {
            System.out.println("found S");
            return 1;
        }
        flag[i][j] = true;
        list[i][j] = 1;
        // System.out.println("Points Before: " + i + ", " + j);
        String[] res = switch(matrix[i][j]) {
            case "F" ->  getCoord("F", dir, i, j);
            case "7" ->  getCoord("7", dir, i, j); 
            case "J" ->  getCoord("J", dir, i, j);
            case "L" ->  getCoord("L", dir, i, j);
            case "|" ->  getCoord("|", dir, i, j);
            case "-" ->  getCoord("-", dir, i, j);
            default -> {
                throw new RuntimeException("invalid inputs inside switch statement");
            }
        };
        i += Integer.parseInt(res[0]);
        j += Integer.parseInt(res[1]);
        dir = getPos(res[2]);
        System.out.println("Points After: " + i + ", " + j);
        // try {
        //     Thread.sleep(Duration.ofMillis(500));
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        cnt += dfs(matrix, i, j, dir, list, cnt, flag);
        return cnt;
    }


    private static Pos getPos(String string) {
        if (string.equals("NORTH")) return Pos.NORTH;
        if (string.equals("SOUTH")) return Pos.SOUTH;
        if (string.equals("EAST")) return Pos.EAST;
        if (string.equals("WEST")) return Pos.WEST;
        return null;
    }

    private static String[] getCoord(String string, Pos dir, int i, int j) {
        if (string.equals("F")) {
            System.out.println("Inside F command" + ", Dir: " + dir);
           if (dir == Pos.NORTH) {
                return new String[] { "0", "1", "EAST" };
           } else if (dir == Pos.WEST) {
                return new String[] { "1", "0", "SOUTH" };
            } 
        } 
        if (string.equals("7")) {
            System.out.println("Inside 7 command" + ", Dir: " + dir);
           if (dir == Pos.NORTH) {
                return new String[] { "0", "-1", "WEST"};
           } else if (dir == Pos.EAST) {
                return new String[] { "1", "0", "SOUTH"};
            } 
        } 
        if (string.equals("J")) {
            System.out.println("Inside J command" + ", Dir: " + dir);
           if (dir == Pos.SOUTH) {
                return new String[] { "0", "-1", "WEST"};
           } else if (dir == Pos.EAST) {
                return new String[] { "-1", "0", "NORTH" };
            } 
        } 
        if (string.equals("L")) {
            System.out.println("Inside L command" + ", Dir: " + dir);
           if (dir == Pos.SOUTH) {
                return new String[] { "0", "1", "EAST" };
           } else if (dir == Pos.WEST) {
                return new String[] { "-1", "0", "NORTH" };
            } 
        } 
        if (string.equals("|")) {
            System.out.println("Inside | command" + ", Dir: " + dir);
           if (dir == Pos.NORTH) {
                return new String[] { "-1", "0", "NORTH"};
           } else if (dir == Pos.SOUTH) {
                return new String[] { "1", "0", "SOUTH" };
            } 
        } 
        if (string.equals("-")) {
            System.out.println("Inside - command" + ", Dir: " + dir);
           if (dir == Pos.WEST) {
                return new String[] { "0", "-1", "WEST"};
           } else if (dir == Pos.EAST) {
                return new String[] { "0", "1", "EAST" };
            } 
        }
        return new String[] {};
    }

    private static void printTheMatrix(int[][] matrix) {
        for (int i=1; i<matrix.length-1; i++) {
            for (int j=1; j<matrix[i].length-1; j++) {
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
