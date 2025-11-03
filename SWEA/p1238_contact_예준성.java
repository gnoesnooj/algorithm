import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 19,328 kb 메모리
 * 115 ms 실행시간
 * bfs를 통해서 주변 노드들을 탐색하고, 넣어주는 걸 반복한다.
 * */
public class p1238_contact_예준성 {
	static int N;
	static List<Integer>[] calls;
	static boolean[] visited;
	static int answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String[] inputs;
		for (int T = 1; T <= 10; T++) {
			inputs = br.readLine().split(" ");

			N = Integer.parseInt(inputs[0]);
			int start_V = Integer.parseInt(inputs[1]);
			
			// 테스트케이스 별 사용 ㅟ한 초기화
			visited = new boolean[101];
			answer = 0;
			calls = new ArrayList[101];

			for (int i = 0; i < 101; i++) {
				calls[i] = new ArrayList<>();
			}

			
			// from 과 to 가 N/2 만큼 반복되어 입력된다.
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				calls[from].add(to);
			}

			// 처음 시작 정점을 true 로 해놓고
			visited[start_V] = true;
			// find
			bfs(calls[start_V]);
			sb.append(String.format("#%d %d\n", T, answer));
		}
		System.out.println(sb);
	}

	// numbers -> 이번 bfs에서 탐색해야할 배열만 들고 들어오게 된다.
	private static void bfs(List<Integer> numbers) {
		// start 들어오고
		int max = 0;
		List<Integer> next = new LinkedList<>();

		for (int i : numbers) {
			if (!visited[i]) {
				visited[i] = true;
				max = Math.max(max, i);
				for (int n : calls[i]) {
					if (!visited[n])
						next.add(n);
				}
			}
		}

		// 다음 배열이 비어있게 된다면 더이상 찾아갈 노드가 없다는 뜻이다.
		if (!next.isEmpty()) {
			bfs(next);
		}
		else answer = max;
	}
}
