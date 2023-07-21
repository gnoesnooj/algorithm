// 1244. [S/W 문제해결 응용] 2일차 - 최대 상금
package practice;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

// 그리디로 풀면 X
// 32888에 대한 테스트케이스를 풀수가 없음
// dfs로 각 회차에 맞는 최댓값을 구해야 할듯 -> 최악의 경우 2^15
// 현재 로직 -> 각 회차에 맞는 최대값을 찾기 (그리디)
// 바꿔야 할것 -> 각 회차, n회차에 최적으로 뽑을 수 있는 최대값이 다름.

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			String num = sc.next();
			int count = sc.nextInt();
			int value = Integer.parseInt(num);
			
			for (int cnt = 0; cnt < count; cnt++) {
				int max = 0;
				for (int i = 0; i < num.length() - 1; i++) {
					for (int j = i + 1; j < num.length(); j++) {
						// method
						if (Integer.toString(value).length() != num.length()) {
							max = Math.max(max, calc(i, j, ("0" + Integer.toString(value)).split("")));
						} else
							max = Math.max(max, calc(i, j, Integer.toString(value).split(""))); // number를 매 순간마다 다시
																								// 바꿔줘야함
					}
				}
				value = max;
			}
			System.out.println("#" + test_case + " " + value);
		}
	}

	private static int calc(int x, int y, String[] number) {
		String[] tmp = new String[number.length];
		for (int i = 0; i < number.length; i++) {
			tmp[i] = number[i];
		}

		String ttmp = tmp[x];
		tmp[x] = tmp[y];
		tmp[y] = ttmp;

		// str 에는 자리를 바꿔준 수가 들어오게 된다
		String str = "";
		for (String s : tmp) {
			str += s;
		}

		return Integer.parseInt(str);
	}

}
