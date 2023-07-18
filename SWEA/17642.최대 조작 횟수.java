import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			long a = sc.nextLong();
			long b = sc.nextLong();
			
			long gap = Math.abs(b-a);
			
			long count = 0;

			if(gap == 1 || a > b) {
				count = -1;
			} else {
				count = gap/2;
			}
			System.out.println("#"+test_case+" "+count);
		}
	}
}
