
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 12712kb 실행시간 100ms
 * <p>
 * 값을 입력받으면서, dp 배열을 완성시켜 나간다.
 * i 번째의 최소 비용은, 당연히 R G B 각각의 값들 중 최소 값이 최소 비용이 될 것이고,
 * R G B 각각 의 최소비용은
 * i 번째의 Red 값이 x 라고 했을 때, i번째 red를 사용한 최소비용은,
 * i-1 번쨰의 Green를 사용한 값과 Blue를 사용한 최소비용 중 더 작은 값에 i 번째 red의 비용을 더한 값이 된다.
 */

public class BJ_1149_RGB거리_예준성 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int home = Integer.parseInt(br.readLine()); // 집 수

        int[][] dp = new int[home + 1][3]; // dp 배열, 0번째는 자동 초기화됨

        String[] inputs;

        for (int i = 1; i <= home; i++) {
            inputs = br.readLine().split(" "); // RGB 입력받기

            int red = Integer.parseInt(inputs[0]);
            int blue = Integer.parseInt(inputs[1]);
            int green = Integer.parseInt(inputs[2]);

            // i 번째 최소값은 이전 단계의 다른색 2개를 사용한 값들 중 최소값이다.
            // 즉 , i번째의 Red를 사용한 최소값은, 이전 단계의 blue와 green 값 2개 중 최소 값 + i번째 red 비용이 된다.
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + red;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + blue;
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + green;
        }

        int answer = Math.min(dp[home][2], Math.min(dp[home][0], dp[home][1]));
        System.out.println(answer);
    }
}