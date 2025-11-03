import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 161740 kb
 * 시간 1624 ms
 * 2차원 배열의 각 행에 해당되는 배열마다 누적합을 따로 구해준다.
 * 이후 범위에 맞게 탐색하면서 각 행마다 조건에 맞는 합을 구해주고, 모든 행의 값을 더한뒤 리턴해준다.
 * 시간을 줄이는 다른 방법에 대해 생각해봐야겟다.*/
public class p11660_부분합5 {

	static int N;
	static int M;
	static int[][] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] commands;
		String[][] inputs;

		commands = br.readLine().split(" ");
		N = Integer.parseInt(commands[0]); // 배열의 크기
		M = Integer.parseInt(commands[1]); // 연산 진행 횟수

		inputs = new String[N][N];

		int[][] sum = new int[N][N]; // 누적합을 넣어줄 배열
		numbers = new int [N][N];
		for (int i = 0; i < N; i++) {
			commands = br.readLine().split(" ");
			int temp = 0;
			for (int j = 0; j < N; j++) {
				numbers[i][j] = Integer.parseInt(commands[j]); // 숫자 각각을 넣어주고
				temp += numbers[i][j];
				sum[i][j] = temp; // 누적합 넣어주기
			}
		}
		// 100만
		StringBuilder sb =new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			commands = br.readLine().split(" ");
			int sx = Integer.parseInt(commands[0])-1;
			int sy = Integer.parseInt(commands[1])-1;
			int ex = Integer.parseInt(commands[2])-1;
			int ey = Integer.parseInt(commands[3])-1;
			int hap = 0;
			for (int gap = 0; gap <= ex - sx; gap++) {
				int temp =0;
				if(sy-1>=0)
					temp = sum[sx+gap][ey]-sum[sx+gap][sy-1];
				else
					temp = sum[sx+gap][ey];
				
				hap+=temp;
			}
			sb.append(String.valueOf(hap)+"\n");
			
		}

		System.out.print(sb.toString());
	}
}
