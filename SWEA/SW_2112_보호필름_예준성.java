package lgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 메모리 18,640 kb 실행시간 110 ms 1.
 */
public class Solution {

	static int D, W, K, answer;
	static int[][] miro;
	static int[][] copied;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		String[] inputs;
		for (int T = 1; T <= tc; T++) {
			inputs = br.readLine().split(" ");

			D = Integer.parseInt(inputs[0]);
			W = Integer.parseInt(inputs[1]);
			K = Integer.parseInt(inputs[2]);
			answer = 999;

			miro = new int[D][W];
			copied = new int[D][W];

			for (int d = 0; d < D; d++) {
				inputs = br.readLine().split(" ");

				for (int w = 0; w < W; w++) {
					miro[d][w] = copied[d][w] = Integer.parseInt(inputs[w]);
				}
			}
			
			dfs(0,0);
			
			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	
	private static void dfs(int cnt, int layer) {
		// 현재 바꿔준 개수가 정답보다 많으면
		if(answer < cnt) {
			return;
		}
		
		if(layer == D) { // 마지막 레이어까지 했다면
			// 결과 보기
			if(check()) {
				answer = Math.min(answer, cnt);
			}
		} else {
			// 선택 X
			dfs(cnt, layer+1);
			
			// 1 바꾸고
			Arrays.fill(copied[layer], 1);
			dfs(cnt+1, layer+1);
			
			// 0으로 바꾸고
			Arrays.fill(copied[layer], 0);
			dfs(cnt+1, layer+1);
			
			// 원상복귀
			for(int i=0; i<W; i++) {
				copied[layer][i] = miro[layer][i];
			}
		}
	}
	
	private static boolean check() {
		for (int i = 0; i < W; i++) {
			int sum = 0;
			boolean status = false;
			// 1. K개 sum 더해놓기
			for (int j = 0; j < K; j++) {
				sum += copied[j][i];
			}
			if (sum == 0 || sum == K) {
				// true 후 다음으로
				status = true;
			} else {
				for (int j = K; j < D; j++) {
					sum -= copied[j - K][i];
					sum += copied[j][i];
					if (sum == 0 || sum == K) {
						status = true;
						break;
					}
				}
			}
			if (!status)
				return false;
		}
		return true;
	}
}
