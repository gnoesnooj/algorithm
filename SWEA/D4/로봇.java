package D4;/*
 * 1. x y 의 절댓값 max 값 찾기
 * 2. 해당 값보다 큰, 제일 가까운 3의 제곱수를 찾는다 (Math.pow 로 가까운거 찾기)
 * 3. 제곱수가 3의 X 제곱일 경우, X부터 0까지 FOR문을 돈다
 * 4. 각각의 FOR문마다, X와 Y중 최대 절댓값을 갖는 녀석을 찾고, 음수면 더해주고 양수면 빼준다.
 * 5. 이후 0까지 했을때 0,0 이면 YES, 아니면 NO*/
import java.util.Scanner;
import java.io.FileInputStream;

class 로봇 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			long x = sc.nextLong();
			long y = sc.nextLong();

			long maxValue = Math.max(Math.abs(x), Math.abs(y)); // x와 y중 최대값을 찾는다

			if(x== y) {
				System.out.println("#" + test_case + " no");
				continue;
			}
				
			// 해당 최댓값보다 크고, 가장 가까운 3의 제곱수를 찾는다.
			int pow = 0;
			for (int i = 0; i<40 ; i++) {
				if(maxValue == 0) {
					pow = 0;
					break;
				}
				else if (maxValue > (long) Math.pow(3, i - 1) && maxValue <= (long) Math.pow(3, i)) {
					pow = i;
					break;
				}
			}
			// 해당 pow 값 만큼 for문을 돌면서, 각 회차마다 더 큰 절대값을 가진 좌표값을 더하거나 빼나간다.
			for (int i = pow; i >= 0; i--) {
				// 최대 절댓값, 음수 양수판단
				long bigNumber = Math.max(Math.abs(x), Math.abs(y));

				if (Math.abs(x) == bigNumber) {
					x = x < 0 ? x + (long)Math.pow(3, i) : x - (long) Math.pow(3, i);
				} else if (Math.abs(y) == bigNumber) {
					y = y < 0 ? y + (long)Math.pow(3, i) : y - (long) Math.pow(3, i);
				}
			}

			if (x == 0 && y == 0) {
				System.out.println("#" + test_case + " yes");
			} else
				System.out.println("#" + test_case + " no");
		}
	}
}
