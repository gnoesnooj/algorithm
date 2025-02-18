import java.util.*;
import java.io.*;

public class p2166_다각형의면적 {

    static int N;
    static int[][] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] inputs;

        edge = new int[N][2];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            edge[i][0] = Integer.parseInt(inputs[0]);
            edge[i][1] = Integer.parseInt(inputs[1]);
        }

        // n번째의 0과 n+1의 1값을 곱한값들을 더하고, n번째의 1과 n+1의 0값들을 곱한 값들을 더한다.
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (double) edge[i][0] * edge[(i + 1) % N][1];
            sum -= (double) edge[i][1] * edge[(i + 1) % N][0];
        }

        System.out.printf("%.1f", Math.abs(sum/2));
    }
}

