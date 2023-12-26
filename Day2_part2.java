import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day2_part2 {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        int MAX = 0;
        String string = """ 
Game 100: 12 green, 8 blue, 2 red; 7 blue, 14 red, 8 green; 14 red, 1 blue, 4 green
""";

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        for (String str : string.lines().toList()) {
            for (String s : str.substring(9,str.length()).lines().toList()) {
                int var = checkString(s);
                MAX += var;
            }
        }
        System.out.println("max: " + MAX);
        br.close();
    }

    public static int checkString(String s) {
        int red = 0;
        int green = 0;
        int blue = 0;
        for (String s1 : s.split(";"))  {
            for (String s2 : s1.split(",")) {
                String s3 = s2.trim();
                String number = s3.substring(0,2);
                String color = s3.substring(2,s3.length());
                int n = Integer.parseInt(number.trim());

                if (color.trim().equals("red")) {
                    red = Math.max(red, n);
                } else if (color.trim().equals("green")) {
                    green = Math.max(n, green);
                } else if (color.trim().equals("blue")) {
                    blue = Math.max(n, blue);
                }

            }
        }
        System.out.println("red: " + red + " green: " + green + " blue: " + blue);
        return red * green * blue;
    }
}
