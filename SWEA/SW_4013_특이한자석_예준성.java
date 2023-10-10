package lgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 메모리 18,640 kb 실행시간 110 ms
 * 톱니바퀴가 회전하는 것에 맞게 가중치를 통해서 필요한 인덱스를 비교하고, 그에 맞게 새로운 가중치를 부여해나간다.
 */
public class Solution {

	static int K, answer;
	static int[][] magnets;
	static List<int[]> orders;
	static int[] d;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		String[] inputs;
		for (int T = 1; T <= tc; T++) {
			K = Integer.parseInt(br.readLine());

			d = new int[4];
			magnets = new int[4][8];
			for (int i = 0; i < 4; i++) {
				inputs = br.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(inputs[j]);
				}
			}

			orders = new LinkedList<int[]>();

			for (int i = 0; i < K; i++) {
				inputs = br.readLine().split(" ");
				orders.add(new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) });
			}

			for (int[] order : orders) {
				visited = new boolean[4];
				int wheelNo = order[0] - 1;
				int v = order[1];
				rotate(wheelNo, v);
			}
			sb.append("#").append(T).append(" ").append(calc()).append("\n");
		}
		System.out.println(sb);
	}

	private static int calc() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if (magnets[i][calcIndex(0, d[i] * (-1))] == 1) {
				sum += Math.pow(2, i);
			}
		}
		return sum;
	}

	private static void rotate(int wheelNo, int v) {
		visited[wheelNo] = true;
		// 범위만족, !visited 본인 기준, 왼쪽2 자신6, 오른쪽6 자신2
		// 왼쪽
		if (isRange(wheelNo - 1) && !visited[wheelNo - 1]) { // 왼쪽이 범위를 만족하고, 방문하지 않았다면
			if (magnets[wheelNo - 1][calcIndex(2, d[wheelNo - 1] * (-1))] != magnets[wheelNo][calcIndex(6,d[wheelNo] * (-1))]) { // 두 위치가 다르다면
				rotate(wheelNo - 1, v * (-1));
			}
		}
		// 오른쪽
		if (isRange(wheelNo + 1) && !visited[wheelNo + 1]) { // 오른쪽이 범위를 만족하고, 방문하지 않았다면
			if (magnets[wheelNo + 1][calcIndex(6, d[wheelNo + 1] * (-1))] != magnets[wheelNo][calcIndex(2,d[wheelNo] * (-1))]) { // 두 위치가 다르다면
				rotate(wheelNo + 1, v * (-1));
			}
		}
		// 가중치 더해주기
		d[wheelNo] += v;

	}

	private static boolean isRange(int n) {
		return n >= 0 && n < 4;
	}

	private static int calcIndex(int index, int d) {
		if (index + d < 0) {
			return index + d + 8;
		} else if (index + d >= 8) {
			return (index + d) % 8;
		} else
			return index + d;
	}
}
