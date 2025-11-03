import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 13852KB 실행시간 388ms
 * 순열로 뽑아준 다음, 그래프가 이어져있는지, 순회가 되는지 확인 후 결과를 계산해준다.
 */
public class BJ_10971_외판원순회2_예준성 {
	static int N;
	static int[][] graph;
	static boolean[] visited;
	static int[] city;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		String[] inputs;

		// 필요한 녀석들 초기화
		graph = new int[N][N];
		visited = new boolean[N];
		city = new int[N];
		answer = Integer.MAX_VALUE;

		// 도시 입력 받기
		for (int i = 0; i < N; i++) {
			inputs = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(inputs[j]);
			}
		}

		// 순열로하고
		// 이어지는지, 값을 더하는데 해당 시점의 min 값보다 커지면 stop
		find(0);
		System.out.println(answer);
	}

	// 순열로 뽑기
	private static void find(int cnt) {
		if (cnt == N) {
			// ㄱㅖ산
			calc();
		} else {
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					visited[i] = true;
					city[cnt] = i;
					find(cnt + 1);
					visited[i] = false;
				}
			}
		}
	}

	// 그래프 이어져 있는지 + 최소 비용 계산하기
	private static void calc() {
		int sum = 0;
		for (int i = 1; i < N; i++) {
			int x = city[i - 1]; // 이전 녀석
			if (graph[x][city[i]] == 0 || graph[city[N - 1]][city[0]] == 0)
				return;
			sum += graph[x][city[i]];
		}

		sum += graph[city[N - 1]][city[0]];
		answer = Math.min(sum, answer);
	}
}
