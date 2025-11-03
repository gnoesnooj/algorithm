import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_01697_숨바꼭질 {

	/**
	 * bfs 를 통해 K의 최단 경로를 찾아낸다.
	 * 범위를 벗어나거나, 방문 배열을 통해서 가지를 쳐낸다.*/
	static int N, K;
	static int answer;
	static Queue<int[]> q = new ArrayDeque<>();
	static boolean [] visited = new boolean[100001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 수빈
		K = sc.nextInt(); // 동생

		if (N >= K) { // 같거나 동생이 뒤에 있는 경우
			System.out.println(N - K);
			return;
		}
		bfs();
		System.out.println(answer);
	}

	private static void bfs() {
		q.add(new int[] { N, 0 });
		while (!q.isEmpty()) {
			int[] number = q.poll();
			visited[number[0]] = true;
			int[] next = { number[0] + 1, number[0] - 1, number[0] * 2 };
			for (int i = 0; i < 3; i++) {
				if (next[i] == K) {
					answer = number[1] + 1;
					return;
				} else {
					if(next[i] <= 100000 && next[i] >= 0 && visited[next[i]] != true)
						q.add(new int[] { next[i], number[1] + 1 });
				}
			}
		}
	}
}
