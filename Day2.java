import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        int MAX = 0;
        String string = """ 
Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
""";

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int cnt = 1;
        for (String str : br.lines().toList()) {
            for (String s : str.substring(8,str.length()).lines().toList()) {
                boolean flag = checkString(s, cnt);
                if (flag && cnt <= 99) {
                    MAX += cnt;
                    System.out.println(cnt);
                }
            }
            ++cnt;
        }
        System.out.println("max: " + MAX);
        br.close();
    }

    public static boolean checkString(String s, int cnt) {
        int red = 0;
        int green = 0;
        int blue = 0;
        boolean flag = true;
        for (String s1 : s.split(";"))  {
            for (String s2 : s1.split(",")) {
                String s3 = s2.trim();
                String number = s3.substring(0,2);
                String color = s3.substring(2,s3.length());
                int n = Integer.parseInt(number.trim());

                if (color.trim().equals("red")) {
                    red = n;
                } else if (color.trim().equals("green")) {
                    green = n;
                } else if (color.trim().equals("blue")) {
                    blue = n;
                }

                if (red > 12 || green > 13 || blue > 14) {
                    flag = false;
                    break;
                }

            }
        }
        return flag;
    }
}
