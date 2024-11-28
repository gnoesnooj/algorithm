import java.io.*;
import java.util.*;


public class p1806_부분합 {

    static int S, N;

    static int answer = Integer.MAX_VALUE;

    static int[] numbers;

    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        // 총 숫자 수
        N = Integer.parseInt(inputs[0]);

        // 목표 숫자
        S = Integer.parseInt(inputs[1]);

        numbers = new int[N + 1];
        sum = new int[N + 1];

        inputs = br.readLine().split(" ");

        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(inputs[i - 1]);
            sum[i] = numbers[i] + sum[i - 1];
        }

        int right = 1;
        for (int i = 0; i <= N ; i++) {
            while (true) {
                if(right > N){
                    break;
                }
                if(sum[right] - sum[i] < S){
                    right++;
                } else {
                    answer = Math.min(answer, right - i);
                    break;
                }
            }
        }
        if (answer == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
