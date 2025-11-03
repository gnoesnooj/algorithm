import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리	299308	
 * 시간 1644
 * 들어갈 수 있는 수를 넣으면서 가로, 세로, 네모가 1~9 중 하나씩 들어가 있는지를 체크하며 dfs를 한다.
 * 처음으로 나온 정답에 대해서 결과를 출력하고 나머지는 출력하지 않는다.
 * */
public class BJ_2239_스도쿠 {

	static int[][] miro = new int[9][9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			char[] c = br.readLine().toCharArray();

			for (int j = 0; j < 9; j++) {
				miro[i][j] = c[j] - '0';
			}
		}

		dfs(0);
	}

	static int over = 0;
	private static void dfs(int cnt) {
		if(over == 1) 
			return;
		if(cnt == 81) {
			// 결과 리턴
			over = 1;
			printMiro();
		} else {
			int x = cnt / 9;
			int y = cnt % 9;
			
			if(miro[x][y] != 0) {
				dfs(cnt+1);
			} else { // 0이면
				for(int i=1; i<=9; i++) {
					miro[x][y] = i;
					if(checkGrid(x,y) && checkH(x) && checkV(y)) {
						dfs(cnt+1);
					}
					miro[x][y] = 0;
				}
			}
		}
	}
	
	// 작은 네모
	private static boolean checkGrid(int x, int y) {
		int gx = x / 3 * 3;
		int gy = y / 3 * 3;
		int[] check = new int[10];

		for (int dx = gx; dx < gx + 3; dx++) {
			for (int dy = gy; dy < gy + 3; dy++) {
				check[miro[dx][dy]] += 1;
				if (miro[dx][dy] != 0 && check[miro[dx][dy]] > 1)
					return false;
			}
		}
		return true;
	}

	// 세로
	private static boolean checkV(int y) {
		int[] check = new int[10];
		for (int i = 0; i < 9; i++) {
			check[miro[i][y]] += 1;
			if (miro[i][y] != 0 && check[miro[i][y]] > 1)
				return false;
		}
		return true;
	}

	// 가로
	private static boolean checkH(int x) {
		int[] check = new int[10];
		for (int i = 0; i < 9; i++) {
			check[miro[x][i]] += 1;
			if (miro[x][i] != 0 && check[miro[x][i]] > 1)
				return false;
		}
		return true;
	}

	private static void printMiro() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(miro[i][j]);
			}System.out.println();
		}
	}
}
