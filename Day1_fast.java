public class Day1_fast {
    public static void main(String[] args) {
        String str = """
1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
                    """;

        int MAX = 0;
        for (String s : str.lines().toList()) {
            String res = s.replaceAll("[a-z]", "");
            if (res.length() > 1) {
                char first = res.charAt(0);
                char second = res.charAt(res.length()-1);
                int num = first - '0'; 
                num *= 10;
                num += second - '0';
                MAX += num;
            } 
            if (res.length() == 1) {
                char first = res.charAt(0);
                int num = first - '0';
                num *= 10;
                num += first - '0';
                MAX += num;
            }
        }
        System.out.println(MAX);
    }
}
