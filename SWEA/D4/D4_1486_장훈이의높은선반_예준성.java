package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 20,484 kb 메모리
 * 175 ms 실행시간
 */
public class D4_1486_장훈이의높은선반_예준성 {

	static int N, B, answer;
	static int people [];
	static boolean [] selected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		String[] inputs;
		for (int T = 1; T <= tc; T++) {
			inputs = br.readLine().split(" ");

			N = Integer.parseInt(inputs[0]);
			B = Integer.parseInt(inputs[1]);
			
			people = new int [N];
			selected = new boolean[N];
			
			inputs = br.readLine().split(" ");
			
			for(int i=0;i<N;i++) {
				people[i] = Integer.parseInt(inputs[i]);
			}
			
			answer = Integer.MAX_VALUE;
			find(0);
			
			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void find(int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i=0; i<N;i++) {
				if(selected[i]) {
					sum += people[i];
					if(sum >= B) {
						answer = Math.min(sum-B, answer);
					}
				}
			}
		} else {
			selected[cnt] = true;
			find(cnt+1);
			selected[cnt] = false;
			find(cnt+1);
		}
	}
}
