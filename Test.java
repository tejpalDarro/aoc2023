import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        System.out.println(stringCheck("1Reder1"));

        String s = "(AAA, BBB)";
        List<String> list = Arrays.stream(s
                                    .trim()
                                    .replace("(","" )
                                    .replace(")","")
                                    .split(","))
                                    .map(String::trim)
                                    .toList();


        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    public static boolean stringCheck(String s) {
        final String temp = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        final String res = new StringBuilder(temp).reverse().toString();
        return res.equals(temp);
    }
}
