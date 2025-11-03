import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 14768	
 * 시간 172
 * dp 배열 생성 후 점화 식 찾고, 목표 값을 찾아준다.
 * */
public class BJ_1010_다리놓기_예준성 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int test_case = sc.nextInt();

		for (int T = 1; T <= test_case; T++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			int [] dp = new int[x+1];
			dp[0] = 1;
			
			for(int i= 1 ; i<=x;i++) {
				dp[i] = dp[i-1] * ( y - (i - 1) ) / i;
			}
			
			System.out.println(dp[x]);
		}
	}
}
