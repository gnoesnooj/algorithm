
import java.io.*;
import java.util.*;

public class p1038_감소하는수 {

    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(br.readLine());

        if (input < 11) {
            System.out.println(input);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            combi(i);
        }

        if (input >= list.size()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(list);
        System.out.println(list.get(input));
    }


    private static void combi(long n) {
        list.add(n);

        long x = n % 10;

        if (x != 0) {
            for (long i = x - 1; i >= 0; i--) {
                long value = n * 10 + i;
                combi(value);
            }
        }
    }

}
