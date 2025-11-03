package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D4_3289_서로소집합_예준성 {

	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] inputs;
		for (int T = 1; T <= test_case; T++) {
			sb.append("#" + T + " ");
			inputs = br.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int M = Integer.parseInt(inputs[1]);

			// N 개의 배열 만들기
			parent = new int[N + 1];
			for (int i = 1; i < N + 1; i++) {
				parent[i] = i; // 처음 집합 만들기
			}

			for (int comm = 0; comm < M; comm++) {
				inputs = br.readLine().split(" ");
				int order = Integer.parseInt(inputs[0]);
				int num1 = Integer.parseInt(inputs[1]);
				int num2 = Integer.parseInt(inputs[2]);

				if (order == 0) {
					union(num1, num2);
				} else if (order == 1) {
					// 같은 집합 여부 리턴
					sb.append(isInclude(num1, num2));
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int find(int a) {
		if (a == parent[a])
			return a;
		else
			return parent[a] = find(parent[a]); // 재귀를 통해서 root 까지 올라간다
	}

	private static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		parent[y] = x;
	}

	private static int isInclude(int a, int b) {
		return find(a) == find(b) ? 1 : 0;
	}

}
