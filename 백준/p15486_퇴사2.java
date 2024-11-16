import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dp[N] = N일 까지 최대 금액
 *
 * 1. 문제를 나누고
 * - 큰 문제 = 작은 문제 + 분기되는 추가 선택
 * - 지금 상태에서의 최적의 결과는 어떤 작은 상태로 나눌 수 있을까 ?
 * - 지금 상태는 이전 상태와 어떻게 연결이 되는가 ?
 *
 * 2. DP 배열 정의하고
 * 3. 점화식 구하기
 *
 * */
public class p15486_퇴사2 {

    static int N;

    static int[] T;

    static int[] P;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 일 수
        N = Integer.parseInt(br.readLine());

        T = new int[N + 2];
        P = new int[N + 2];
        dp = new int[N + 2];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            T[i + 1] = Integer.parseInt(inputs[0]);
            P[i + 1] = Integer.parseInt(inputs[1]);
        }

        for (int i = 1; i <= N; i++) {
            // 오늘 상담하지 않고 다음날로 가는 경우
            // 미리 갱신된 값이 있을 수 있기 때문에 대소비교를 해줘야한다.
            dp[i+1] = Math.max(dp[i], dp[i+1]);

            // 오늘 상담이 가능할 경우
            int next = i + T[i];
            if (next <= N + 1) {
                dp[next] = Math.max(dp[next], dp[i] + P[i]);
            }
        }
        System.out.println(dp[N + 1]);
    }

}
