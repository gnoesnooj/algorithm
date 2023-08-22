import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 56020kbb 시간 432ms
 * 내가 푼 시간 : 2시간 되는듯
 * 
 * 문제 조건에 맞게 구현해준다. 순열+완탐으로 cctv 방향을 뽑아주고 사각지대를 찾은 후 최소값을 리턴해준다.
 */
public class Main {
	static int answer = Integer.MAX_VALUE;
	static boolean[] visited;
	static int[] cctv_direction; // 뽑아줄 cctv 방향 중복순열
	static int[] cctv_range = { 4, 2, 4, 4, 1 }; // 1 2 3 4 5 니까 1씩 빼서 찾기
	static List<int[]> cctv; // 길이 3, cctv 종류, 위치x, 위치y
	static int[][] room;
	static int[][] tmp;

	static int N, M;
	static int min = Integer.MAX_VALUE;

	// 상 우 하 좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs;

		inputs = br.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		// 사무실
		room = new int[N][M];

		// CCTV 위치 기억하기
		cctv = new ArrayList<>();

		// 사무실 채워주기
		for (int i = 0; i < N; i++) {
			inputs = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(inputs[j]);
				if (room[i][j] >= 1 && room[i][j] <= 5) { // cctv 위치 기억해주기
					cctv.add(new int[] { room[i][j], i, j }); // 위치 넣어주고, cctv 종류도 넣을지 생각
				}
			}
		}
		// cctv 개수만큼 범위 4내에서 중복순열 뽑기
		// 만일 cctv 종류도 넣어주면 종류에 따라 나올 수 있는 숫자 범위 다르게 해줄지 고민
		cctv_direction = new int[cctv.size()];
		pick(0);
		System.out.println(answer);
	}

	private static void pick(int count) {
		if (count == cctv.size()) { // K개의 cctv 의 방향을 모두 결정해 줬다면, 계산해준다
			tmp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tmp[i][j] = room[i][j];
				}
			}
			// 주어진 direction에 맞게 계산
			for (int i = 0; i < cctv_direction.length; i++) {
				change(i, cctv_direction[i]);
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (tmp[i][j] == 0)
						cnt++;
				}
			}
			answer = Math.min(answer, cnt);
			
		} else {
			for (int i = 0; i < cctv_range[cctv.get(count)[0] - 1]; i++) {
				cctv_direction[count] = i;
				pick(count + 1);
			}
		}
	}

	private static void change(int i, int direction) {
		int x = cctv.get(i)[1];
		int y = cctv.get(i)[2];
		// direction 방향 cctv.get(i)[0] 종류
		switch (cctv.get(i)[0]) { // cctv 종류에 따라 # 대신 9로 바꿔주는 메소드이다. 1번 제외 상 하 좌 우 4개 모두 코드가 똑같다.
		case 1: // x,y 부터 방향까지, 6을 만나거나 범위 밖까지
			int cnt = 0;
			while (x + dx[direction] * cnt >= 0 && y + dy[direction] * cnt >= 0 && x + dx[direction] * cnt < N && y + dy[direction] * cnt < M) {
				if (tmp[x + dx[direction] * cnt][y + dy[direction] * cnt] == 6) {
					return;
				} else {
					if (tmp[x + dx[direction] * cnt][y + dy[direction] * cnt] == 0) {
						tmp[x + dx[direction] * cnt][y + dy[direction] * cnt] = 9;
					}
				}
				cnt++;
			}
			break;
		case 2: // cctv가 2번, 상하 또는 좌우
			if (direction == 1) {
				up(x, y);
				down(x, y);
			} else if (direction == 0) { // 0일 경우, 좌 우
				left(x, y);
				right(x, y);
			}
			break;
		case 3:
			if (direction == 0) { // 상 우
				up(x, y);
				right(x, y);
			} else if (direction == 1) { // 우 하
				right(x, y);
				down(x, y);
			} else if (direction == 2) { // 하 좌
				down(x, y);
				left(x, y);
			} else if (direction == 3) { // 좌 상
				up(x, y);
				left(x, y);
			}
			break;
		case 4:
			if (direction == 0) { // 좌 상 우
				left(x, y);
				up(x, y);
				right(x, y);
			} else if (direction == 1) { // 상우하
				down(x, y); 
				up(x, y);
				right(x, y);
			} else if (direction == 2) {// 우하좌
				left(x, y);
				down(x, y);
				right(x, y);
			} else if (direction == 3) {// 하좌상
				left(x, y);
				down(x, y);
				up(x, y);
			}
			break;
		case 5:// 위쪽
			up(x, y);// 왼쪽
			left(x, y);// 오른쪽
			right(x, y);
			down(x, y);
			break;
		}
	}

	private static void up(int x, int y) {
		for (int d = x - 1; d >= 0; d--) {
			if (tmp[d][y] == 6) {
				break; // 벽만나면 끝
			} else if (tmp[d][y] == 0) {
				tmp[d][y] = 9; // 아니면 9로 바꿔주기 쭈욱
			}
		}
	}

	private static void down(int x, int y) {
		for (int d = x + 1; d < N; d++) {
			if (tmp[d][y] == 6) {
				break; // 벽만나면 끝
			} else if (tmp[d][y] == 0) {
				tmp[d][y] = 9; // 아니면 9로 바꿔주기 쭈욱
			}
		}
	}

	private static void left(int x, int y) {
		for (int d = y - 1; d >= 0; d--) {
			if (tmp[x][d] == 6)
				break;
			else if (tmp[x][d] == 0) {
				tmp[x][d] = 9;
			}
		}
	}

	private static void right(int x, int y) {
		for (int d = y + 1; d < M; d++) {
			if (tmp[x][d] == 6)
				break;
			else if (tmp[x][d] == 0) {
				tmp[x][d] = 9;
			}
		}
	}
}
