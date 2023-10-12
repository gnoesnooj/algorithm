package lgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 20,484 kb 메모리 175 ms 실행시간
 */
public class Solution {

	static int N, M, K, answer;
	static int[][] cell;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<int[]> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		String[] inputs;
		for (int T = 1; T <= tc; T++) {
			inputs = br.readLine().split(" ");
			N = Integer.parseInt(inputs[0]); // 셀 크기
			M = Integer.parseInt(inputs[1]); // 시간
			K = Integer.parseInt(inputs[2]); // 미생물 수
			answer = 0;
			cell = new int[N][N];
			list = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				inputs = br.readLine().split(" ");
				int x = Integer.parseInt(inputs[0]);
				int y = Integer.parseInt(inputs[1]);
				int number = Integer.parseInt(inputs[2]);
				int direction = Integer.parseInt(inputs[3]) - 1;

				list.add(new int[] { x, y, number, direction });
			}

			// 1. 큐안에 전체 이동
			// 2. 가장자리 판단
			// 3. 합쳐질 경우 판단
			while (M >= 0) {
				M--;
				int[][] newArr = new int[N][N];
				int[][] d = new int[N][N];

				int size = list.size();
				list.sort((l1, l2) -> {
					return l1[2] - l2[2];
				}); // 미생물 오름차로 정렬

				for (int i = 0; i < size; i++) {
					int[] arr = list.get(i); // 1. 리스트에서 가져와서
					// 2. 이동좌표에 이동
					int direction = arr[3];
					int nx = arr[0] + dx[direction];
					int ny = arr[1] + dy[direction];

					if (isEdge(nx, ny)) { // 약품칠해진 곳이면 방향바꿔서 리스트에 넣어놓기
						int newD = changeDirection(direction);
						newArr[nx][ny] = arr[2] / 2;
						d[nx][ny] = newD;
					} else {
						newArr[nx][ny] += arr[2];
						d[nx][ny] = arr[3];
					}
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (newArr[i][j] != 0) {
							list.add(new int[] { i, j, newArr[i][j], d[i][j] });
						}
					}
				}
				if (M == 0) {
					copy(newArr);
				}
				for (int i = 0; i < size; i++) {
					list.remove(0);
				}
			}
			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static int changeDirection(int a) {
		if (a < 2) {
			return (a + 1) % 2;
		} else {
			return (a + 1) % 2 + 2;
		}
	}

	private static boolean isEdge(int x, int y) {
		return x == 0 || y == 0 || x == N - 1 || y == N - 1;
	}

	private static void copy(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cell[i][j] = arr[i][j];
				answer += arr[i][j];
			}
		}
	}
}
