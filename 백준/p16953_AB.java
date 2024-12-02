import java.io.*;
import java.util.*;


public class p16953_AB {

    static int A, B;

    static long answer = -1;
    static long max = 1_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        A = Integer.parseInt(inputs[0]);
        B = Integer.parseInt(inputs[1]);

        Queue<long[]> numbers = new LinkedList<>();

        numbers.add(new long[]{A, 0});
        while (!numbers.isEmpty()) {
            long[] number = numbers.poll();

            long twice = number[0] * 2;
            long rightPlusOne = number[0] * 10 + 1;

            if (twice == B || rightPlusOne == B) {
                answer = number[1] + 2;
                break;
            } else {
                if (twice < max) {
                    numbers.add(new long[]{twice, number[1] + 1});
                }
                if (rightPlusOne < max) {
                    numbers.add(new long[]{rightPlusOne, number[1] + 1});
                }
            }
        }

        System.out.println(answer);
    }
}
