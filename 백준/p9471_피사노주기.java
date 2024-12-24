import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class p9471_피사노주기 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] inputs;

        for (int tc = 1; tc <= N; tc++) {
            inputs = br.readLine().split(" ");

            int goal = Integer.parseInt(inputs[1]);

            int a = 1;
            int b = 1;

            int count = 0;

            while (true) {
                int next = (a + b) % goal;
                a = b;
                b = next;
                count++;

                if (a == 1 && b == 1) {
                    break;
                }
            }
            System.out.println(tc + " " + count);

        }
    }
}
