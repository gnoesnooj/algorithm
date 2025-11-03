import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 14768	
 * 시간 172
 * dp 배열 생성 후 점화 식 찾고, 목표 값을 찾아준다.
 * */
public class BJ_17070_파이프옮기기1_예준성 {
	static int [][] miro;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		miro = new int [N][N];
		String [] inputs;
		for(int i=0;i<N;i++) {
			inputs = br.readLine().split(" ");
			for( int j=0; j<N;j++) {
				miro[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		int [][][] dp = new int[N+1][N+1][3];
		// 처음 것부터 다음 것에 넘겨줄 수 있는 방법을 생각해서 넘겨주기
		// 0 가로
		// 1 대각
		// 2 세로
		dp[0][1][0] = 1; // 처음 시작 상태인 가로와, 파이프의 끝 점을 1 올려주고 시작한다.
		
		for(int i=0 ; i <N;i++) {
			for(int j=1; j<N;j++) {
				if(miro[i][j] == 1) continue;
				// 가로로 도착시키기
				// 1. 본인 [0] 이 0보다 크고, 넘어갈 곳이 1이 아니면
				if(j+1<N && dp[i][j][0] > 0 && miro[i][j+1] != 1) {
					dp[i][j+1][0] += dp[i][j][0]; // 가로로 도착할 다음 구역에 본인이 가진거만큼 넣어주기
				}
				// 2. 본인 [1] 이 0보다 크고, 넘어갈 곳이 1이 아니면
				if(j+1<N && dp[i][j][1] > 0 && miro[i][j+1] != 1 ) {
					dp[i][j+1][0] += dp[i][j][1]; // 가로로 도착할 다음 구역에 본인이 가진거만큼 넣어주기
				}
				// 대각선
				// 1. 본인 [0] 이 0보다 크고, 주변 3곳이 1이 아니면
				if(i+1 < N && j+1<N && dp[i][j][0] > 0 && miro[i][j+1] != 1 && miro[i+1][j+1] != 1 && miro[i+1][j] != 1) {
					dp[i+1][j+1][1] += dp[i][j][0];
				}
				// 2. 본인 [2] 가 0보다 크고, 주변 3곳 1 X
				if(i+1 < N && j+1<N && dp[i][j][2] > 0 && miro[i][j+1] != 1 && miro[i+1][j+1] != 1 && miro[i+1][j] != 1 ) {
					dp[i+1][j+1][1] += dp[i][j][2];
				}
				// 3. 본인 [1] 이 0 ~~~
				if(i+1 < N && j+1<N && dp[i][j][1] > 0 && miro[i][j+1] != 1 && miro[i+1][j+1] != 1 && miro[i+1][j] != 1 ) {
					dp[i+1][j+1][1] += dp[i][j][1];
				}
				// 세로
				// 1. 본인 [2] 0보다크고, 도착할곳 1 X
				if(i+1 < N && dp[i][j][2] > 0 && miro[i+1][j] != 1) {
					dp[i+1][j][2] += dp[i][j][2]; // 가로로 도착할 다음 구역에 본인이 가진거만큼 넣어주기
				}
				if(i+1 < N && dp[i][j][1] > 0 && miro[i+1][j] != 1) {
					dp[i+1][j][2] += dp[i][j][1]; // 가로로 도착할 다음 구역에 본인이 가진거만큼 넣어주기
				}
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}
}
