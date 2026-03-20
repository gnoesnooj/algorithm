import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1011_FlyMeToTheAlphaCentauri {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < test_case; tc++) {
            String[] numbers = br.readLine().split(" ");
            int distance = Integer.parseInt(numbers[1]) - Integer.parseInt(numbers[0]);

            int n = (int) Math.sqrt(distance);

            if (distance == n * n) {
                System.out.println(n * 2 - 1);
            } else if (n * n < distance && distance <= n * n + n) {
                System.out.println(n * 2);
            } else {
                System.out.println(n * 2 + 1);
            }
        }
    }
}
