public class Day3_part2 {
    public static char[][] ch;
    public static boolean[][] boolChar;
    public static int row = 0;
    public static int col = 0;
    static {
        String str = """ 
                    467..114..
                    ...*......
                    ..35..633.
                    ......#...
                    617*......
                    .....+.58.
                    ..592.....
                    ......755.
                    ...$.*....
                    .664.598..
                    """;
        for (String s : str.lines().toList()) {
            ++row;
            col = s.length();
        }

        ch = new char[row][col];
        boolChar = new boolean[row][col];

        int i=0;
        for (String ss : str.lines().toList()) {
            int j=0;
            for (char c : ss.toCharArray())  {
                ch[i][j] = c;
                boolChar[i][j] = false;
                ++j;
            }
            ++i;
        }

    }
    public static <T> void printElement(T[][] c) {
        for (T[] ch : c) {
            System.out.println(ch.toString());
        }
    }

    public static void main(String[] args) {
        // printElement(ch);
        // printElement(boolChar);

        turnTrue();

        for (boolean[] c : boolChar) {
            for (boolean cc : c) {
                System.out.print(cc + " ");
            }
            System.out.println("");
        }

    }

    public static void turnTrue() {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                dfs(i,j);
            }
            System.out.println(" ");
        }
    }

    public static void dfs(int x, int y) {

        if (isSafe(x,y)) {
            if (Character.isDigit(ch[x][y]) || ch[x][y] == '*') {
                boolChar[x][y] = true;
                dfs(x-1, y);
                dfs(x+1, y);
            }

        } else {
            return;
        }

    }

    public static boolean isChar(int x, int y) {
        boolean flag = switch(ch[x][y]) {
            case '@','#','$','%','&','+','-','.','/','=' -> flag = false;
            default -> flag = true;
        };
        return flag;
    }

    public static boolean isSafe(int x, int y) {
        return (x >= 0) && (x < row) && (y >= 0) && (y < col);
    } 
}
