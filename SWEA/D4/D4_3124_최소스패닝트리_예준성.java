import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 125,988 kb 메모리 2,006 ms 실행시간
 */
public class Solution {

	static int[] parents;
	static int V, E;
	static List<int[]> edges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] inputs;

		for (int T = 1; T <= test_case; T++) {
			sb.append("#" + T + " ");
			inputs = br.readLine().split(" ");
			V = Integer.parseInt(inputs[0]);
			E = Integer.parseInt(inputs[1]);

			parents = new int[V + 1];
			edges = new ArrayList<>();

			for (int i = 0; i < V + 1; i++) {
				parents[i] = i;
			}

			for (int i = 0; i < E; i++) {
				inputs = br.readLine().split(" ");
				edges.add(new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]),
						Integer.parseInt(inputs[2]) });
			}
			edges.sort((e1, e2) -> (e1[2] - e2[2])); // 정렬을 해줬기 때문에, 다음 for문에서 union을 할때 최소의 값을 찾아서 넣어줄 수 있는 것이다.

			long sum = 0;
			int cnt = 0;
			for (int[] arr : edges) {
				if (union(arr[0], arr[1])) { // union , 즉 새로운 정점이 트리에 합류하게 된다면, 최소 정점을 찾아서 값을 더해주어야 하는 상황이다.
					sum += arr[2];
					if (++cnt == V) {
						break;
					}
				}
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb);

	}

	private static int find(int a) {
		if (a == parents[a])
			return a;
		else
			return parents[a] = find(parents[a]); // 재귀를 통해서 root 까지 올라간다
	}

	private static boolean union(int a, int b) {
		int x = find(parents[a]);
		int y = find(parents[b]); // 각각의 루트를 찾아서 비교

		if (x == y) // 같으면 이미 두 정점은 같은 트리에 있다는 뜻
			return false;

		parents[y] = parents[x]; // false 가 리턴 되지 않는다면, 다른 트리인 것이므로 다른 하나의 정점의 트리 루트값을 합쳐주기 위해서 바꿔준다.

		return true;
	}

}
