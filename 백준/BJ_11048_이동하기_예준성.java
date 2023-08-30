import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 102304	
 * 시간 652
 * 
 * dp 배열 생성 후 값을 넣어주고 풀이한다.
 * i j 의 값은 3방향 값중 최대값을 자기자신의 값과 더한 것과 같으므로, 이를 이용해서 점화식을 만든 후 최대값을 구해나간다.
 * */

public class Main {

	static int[][] candy;

	static int[][] dp;

	static int N, M;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs;

		inputs = br.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		candy = new int[N][M];
		dp = new int[N + 1][M + 1]; // dp 배열
		
		// 값 입력 받기
		for (int i = 0; i < N; i++) {
			inputs = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				candy[i][j] = Integer.parseInt(inputs[j]);
			}
		}

		// 최대값 찾기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = candy[i-1][j-1] + Math.max(Math.max(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
			}
		}
		System.out.println(dp[N][M]);
	}
}
