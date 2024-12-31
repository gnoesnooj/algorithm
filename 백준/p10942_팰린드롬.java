import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class p10942_팰린드롬 {

    static int N;

    static int[] numbers;

    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numbers = new int[N+1];
        dp = new boolean[N+1][N+1];

        String[] inputs = br.readLine().split(" ");

        // 수열 입력
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(inputs[i-1]);
        }

        // dp 배열 채우기
        for (int i = 1; i < N+1; i++) {
            dp[i][i] = true;
            if (i < N && numbers[i] == numbers[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        for(int i=2;i<N;i++){
            for(int j=1;j<=N-i;j++){
                if(numbers[j] == numbers[j+i] && dp[j+1][j+i-1]){
                    dp[j][j+i] = true;
                }
            }
        }

        // 질문 수
        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<M;i++){
            inputs = br.readLine().split(" ");
            sb.append(dp[Integer.parseInt(inputs[0])][Integer.parseInt(inputs[1])] ? 1 +"\n" : 0 +"\n");
        }
        System.out.println(sb);
    }
}
