package lgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution {

	static int N, M;
	static int[] parent;
	static boolean[][] people;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		String[] inputs;
		for (int T = 1; T <= tc; T++) {
			inputs = br.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			M = Integer.parseInt(inputs[1]);

			people = new boolean[N+1][N+1];

			parent = new int[N + 1];
			for (int i = 0; i < N + 1; i++) {
				parent[i] = i;
			}

			for (int m = 0; m < M; m++) { // 그래프 입력받기
				inputs = br.readLine().split(" ");
				int a = Integer.parseInt(inputs[0]);
				int b = Integer.parseInt(inputs[1]);
				people[a][b] = true;
				people[b][a] = true;

				if (a > b) {
					join(b, a);
				} else {
					join(a, b);
				}
			}
			Set<Integer> set = new HashSet<>();
			for(int p : parent) {
				set.add(p);
			}
			
			sb.append("#").append(T).append(" ").append(set.size()-1).append("\n");
		}
		System.out.println(sb);
	}

	private static int find(int a) {
		if (a == parent[a]) {
			return a;
		} else {
			return find(parent[a]);
		}
	}

	private static void join(int a, int b) { // a < b
		int value = find(a);
		int before = find(b);
		if (value < before) {
			for (int i = 1; i <= N; i++) {
				if (parent[i] == before) {
					parent[i] = value;
				}
			}
		}
	}
}
