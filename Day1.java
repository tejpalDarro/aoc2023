import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    public static int MAX = 0;
    public static void checkString(String s) {

        char c[] = s.toCharArray();
        String s1 = "";
        String s2 = "";

        for(int i=0; i<s.length(); i++) {
            if(Character.isDigit(c[i])) {
                s1 = "" + c[i];
                break;
            }
        }

        for(int i=s.length()-1; i>=0; i--) {
            if(Character.isDigit(c[i])) {
                s2 = "" + c[i];
                break;
            }
        }

        int res = Integer.parseInt(s1 + s2);
        MAX += res;
        System.out.println(res);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String str = "";
        while((str = br.readLine()) != null) {
            checkString(str);
            // break;
        }
        System.out.println(MAX);
        br.close();
    }
}
