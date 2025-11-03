import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1987_알파벳 {

	static int R, C;

	static String[][] alphas;
	static StringBuilder sb = new StringBuilder();
	static boolean[][] visited;

	static int max;
	// 상 하 좌 우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs;

		inputs = br.readLine().split(" ");
		C = Integer.parseInt(inputs[0]);
		R = Integer.parseInt(inputs[1]);

		// 알파벳 넣어주기
		alphas = new String[C][R];
		for (int i = 0; i < C; i++) {
			alphas[i] = br.readLine().split("");
		}

		// 방문했거나, visited 거나, 범위 밖으로 벗어나거나
		// 방문 배열 초기화
		visited = new boolean[C][R];
		max = 0;
		
		dfs(alphas[0][0], 0, 0);
		
		System.out.println(max);
	}

	private static void dfs(String route, int x, int y) {
		max = Math.max(route.length(), max);
		for (int i = 0; i < 4; i++) {
			if (isRange(x + dx[i], y + dy[i])) { // 범위를 만족하고, 새로운 알파벳이며 방문하지 않았다면
				if (!route.contains(alphas[x + dx[i]][y + dy[i]]) && !visited[x + dx[i]][y + dy[i]]) {
					visited[x + dx[i]][y + dy[i]] = true;
					dfs(route + alphas[x + dx[i]][y + dy[i]], x + dx[i], y + dy[i]);
					visited[x + dx[i]][y + dy[i]] = false;
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && y >= 0 && x < C && y < R;
	}
}
