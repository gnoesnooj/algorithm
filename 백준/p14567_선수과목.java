import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

/**
 * 1. answer[] -> 정답 2. need[] -> [index] <- 들어야 하는 과목 수 3. list<>[] -> [index]
 * -> 보내줄 과목
 */
public class p14567_선수과목 {

	static int N, M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs;

		inputs = br.readLine().split(" ");

		N = Integer.parseInt(inputs[0]); // 과목 수
		M = Integer.parseInt(inputs[1]);

		int[] answer = new int[N + 1];
		List<Integer>[] list = new ArrayList[N + 1];

		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}

		int[] need = new int[N + 1];

		for (int i = 0; i < M; i++) { // 선수과목 관계 입력받기
			inputs = br.readLine().split(" ");

			int a = Integer.parseInt(inputs[0]); // 과목 수
			int b = Integer.parseInt(inputs[1]); // b를 듣기 위해선 a가 이수 되어야함.

			// 1. 리스트a에 b넣고
			// 2. 필요한 개수b+1
			list[a].add(b);
			need[b]++;
		}

		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (need[i] == 0) { // 첫학기에 이수하는 거면
				answer[i] = 1;
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int before = q.poll();

			// 얘가 넣어줄 과목들 빼주기
			for (int i : list[before]) {
				need[i] -= 1;

				if (need[i] == 0) {
					answer[i] = answer[before] + 1;
					q.add(i);
				}
			}
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 1; i < answer.length; i++) {
			sb.append(answer[i] + " ");
		}
		System.out.println(sb);

	}
}
