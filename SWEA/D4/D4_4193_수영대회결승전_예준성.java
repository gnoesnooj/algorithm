package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 메모리 18,876 kb
 * 시간 111 ms
 * q에서 poll 후 bfs 할 때, q.size 만큼 하면 원하는 시간 n초에 탐색을 할 수있다.
 * 또한 poll 후 bfs 할 때, 소용돌이처럼 조건에 맞지 않다면 원하는 시간에 탐색이 될 수 있게 다시 큐에 넣어준다.
 */
public class D4_4193_수영대회결승전_예준성 {

	static int N, answer;
	static int[][] miro;
	static boolean[][] visited;

	static int[] start, end;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		String[] inputs;
		for (int T = 1; T <= tc; T++) {
			N = Integer.parseInt(br.readLine());
			answer = Integer.MAX_VALUE;
			miro = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				inputs = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					miro[i][j] = Integer.parseInt(inputs[j]);
				}
			}

			inputs = br.readLine().split(" ");
			start = new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) };

			inputs = br.readLine().split(" ");
			end = new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) };

			bfs();
			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		int time = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { start[0], start[1] });
		visited[start[0]][start[1]] = true;
		
		while (!q.isEmpty()) {
			int size = q.size();
			int[] point;
			for (int i = 0; i < size; i++) {
				point = q.poll();
				if(isAnswer(point[0], point[1])) {
					answer = time;
					return;
				}
				
				for(int k=0;k<4;k++) {
					int nx = point[0] + dx[k];
					int ny = point[1] + dy[k];
					
					if(isRange(nx, ny)) { // 범위를 만족하고
						if(!visited[nx][ny] && miro[nx][ny] == 0) {
							visited[nx][ny] = true;
							q.add(new int[] {nx,ny});
						} else if(!visited[nx][ny] && miro[nx][ny] == 2) {
							if(time % 3 != 2) {
								q.add(point);
							} else {
								visited[nx][ny] = true;
								q.add(new int[] {nx, ny});
							}
						}
					}
				}
			}
			time++;
		}
		
		answer = -1;

	}

	private static boolean isAnswer(int x, int y) {
		return x == end[0] && y == end[1];
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}
