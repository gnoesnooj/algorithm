import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 	메모리 16420
 *  실행시간 192
 *  
 *  1. q로 주변 탐색
 *  2. 주변 탐색하면서, 다음 이동할 곳이 미로 범위를 벗어나지 않고, 방문한 적이 없으며, 지나갈 수 있는 1인 경우에
 *  3. 다음 이동 좌표와 이동거리를 +1 해주고 큐에 넣어준다.
 *  4. q가 빌 때 까지 poll 하면서, 만일 해당 좌표가 목표지점이라면 이동거리를 출력해주고 종료한다.
 *   */
public class Main {

	static int N, M;

	static int[][] miro;
	static StringBuilder sb = new StringBuilder();

	// 상 하 좌 우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs;

		inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		// 알파벳 넣어주기
		miro = new int[N][M];
		visited = new boolean[N][M];

//		for (int i = 0; i < N; i++) {
//			inputs = br.readLine().split("");
//			for (int j = 0; j < inputs.length; j++) {
//				miro[i][j] = Integer.parseInt(inputs[j]);
//			}
//		}
		
		// parse int 시간 줄이기
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<input.length();j++) {
				miro[i][j] = input.charAt(j) - '0';
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 1 });

		while (!q.isEmpty()) {
			int[] node = q.poll(); // q 에 있는 것 하나를 가져온다
			visited[node[0]][node[1]] = true; // 방문 처리
			
			if(node[0] == N-1 && node[1] == M-1) {
				System.out.println(node[2]);
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				// 범위를 만족하고 방문하지 않았다면
				int moveX = node[0] + dx[i];
				int moveY = node[1] + dy[i];
//				if(isRange(moveX, moveY) && !visited[moveX][moveY] && miro[moveX][moveY] == 1) {
				if(moveX >= 0 && moveY >= 0 && moveX < N && moveY < M && !visited[moveX][moveY] && miro[moveX][moveY] == 1) {
					q.offer(new int[] {moveX ,moveY, node[2]+1});
					visited[moveX][moveY] = true;
				}
			}
		}
	}

//	private static boolean isRange(int x, int y) {
//		return x >= 0 && y >= 0 && x < N && y < M;
//	}
}
