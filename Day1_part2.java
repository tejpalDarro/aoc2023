import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class Day1_part2 {
    
    public static int MAX = 0;
    public static String[] str = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static int convertNum(String s) {
        int res = switch(s) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(s);
        };
        return res;
    }

    public static String charCharSeq(CharSequence c) {
        if (c.length()>2) {
            for (String ss : str) {
                String s = c.toString();
                if (s.indexOf(ss) != -1) {
                    // System.out.println(ss);
                    return ss;
                }
            }
        }
        return null;
    }
    public static String charCharSeqBack(CharSequence c) {
        StringBuilder sb = new StringBuilder(c);
        String s = sb.reverse().toString();

        if (c.length()>2) {
            for (String ss : str) {
                if (s.indexOf(ss) != -1) {
                    // System.out.println(ss);
                    return ss;
                }
            }
        }
        return null;
    }

    public static void checkString(String s) {
        StringBuilder sb = new StringBuilder(s);
        String ss = sb.reverse().toString();

        char c[] = s.toCharArray();
        char c2[] = ss.toCharArray();

        String s1 = "";
        String s2 = "";

        int a = 0;
        int b = 0;

        for (int i=0; i<s.length(); i++) {
            if (Character.isDigit(c[i])) {
                s1 = "" + c[i]; 
                // System.out.println(c[i]);
                break;
            } else {
                CharSequence cc = s.subSequence(0,i+1);
                String str = "";
                if ((str = charCharSeq(cc) ) !=null) {
                    s1 = "" + str; 
                    break;
                }
            }
        }


        for (int i=0; i<ss.length(); i++) {
            if (Character.isDigit(c2[i])) {
                s2 = "" + c2[i]; 
                // System.out.println(c2[i]);
                break;
            } else {
                CharSequence cc = ss.subSequence(0,i+1);
                String str2 = "";
                if ((str2 = charCharSeqBack(cc)) != null) {
                    s2 = "" + str2;
                    break;
                }
            }
        }

        // System.out.println(s1 + " " + s2);
        
        a = convertNum(s1);
        b = convertNum(s2);
        int res = (a*10) + b;
        System.out.println(res);
        MAX += res;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String str = "";

        while((str = br.readLine()) != null) {
            checkString(str);
        }

        System.out.println(MAX);
        br.close();
    }
}
