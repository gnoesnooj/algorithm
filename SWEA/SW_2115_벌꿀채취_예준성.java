import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 25,804 kb 실행시간 144 ms
 */
public class SW_2115_벌꿀채취_예준성 {

	static int N, M, C, answer, A,B;
	static int[][] honey;
	static int[][] copied;
	static boolean [] selected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		String[] inputs;
		for (int T = 1; T <= tc; T++) {
			inputs = br.readLine().split(" ");

			N = Integer.parseInt(inputs[0]);
			M = Integer.parseInt(inputs[1]);
			C = Integer.parseInt(inputs[2]);
			answer = 0;
			honey = new int[N][N];

			for (int i = 0; i < N; i++) {
				inputs = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			
			makePoint();

			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void makePoint() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < N * N; i++) {
			x = i / N;
			y = i % N;
			// y의 좌표에 선택할 수 있는 꿀벌 통 개수를 더한 값이 해당 열의 마지막보다 크다면
			// 해당 좌표에서 채집할 수 없으므로 다음으로 진행한다
			if (y + M > N) {
				continue;
			}
			// 일꾼A 의 벌꿀을 구한다
			A = getHoney(x,y);
			
			// x, y변환 전의 i를 일꾼 A의 좌표로 삼고, 해당 좌표에 따라 일꾼 B의 좌표를 완탐 후 최댓값을 구한다.
			calc(i+M);
		}
	}

	// 들어온 x 좌표에 따라서, y 좌표의 값을 구하고 계산한다.
	private static void calc(int yi) {
		int sum = 0;
		int bx = yi / N;
		int by = yi % N;
		
		// 전부 탐색 했거나, 범위를 벗어난다면
		if(yi >= N*N) {
			return;
		}
		
		if(by + M > N) {
			calc(yi+1);
		} else {
			sum = getHoney(bx, by) + A;
			answer = Math.max(sum, answer);
			calc(yi+1);
		}
	}
	
	private static int getHoney(int x, int y) { // 이거는 좌표
		int sum = 0;
		B=0;
		selected = new boolean [M];
		combi(0,x,y);
		return B;
	}
	
	private static void combi(int cnt, int x, int y) {
		if(cnt == M) {
			int sum = 0;
			int hap = 0;
			for(int i=0; i<M;i++) {
				int ny = y + i;
				if(selected[i] == true) {
					sum += honey[x][ny];
					if(sum > C) {
						break;
					} else {
						hap += honey[x][ny] * honey[x][ny];
						B = Math.max(B, hap);
					}
				}
			}
			return;
		}  else {
			selected[cnt] = true;
			combi(cnt +1, x, y);
			selected[cnt] = false;
			combi(cnt +1, x, y);
		}
	}
}
