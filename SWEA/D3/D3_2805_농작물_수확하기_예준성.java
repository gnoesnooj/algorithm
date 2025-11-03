package D3;

import java.util.Scanner;
import java.io.FileInputStream;

class D3_2805_농작물_수확하기_예준성 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            String[][] crops = new String[n][n];

            for (int i = 0; i < n; i++) {
                String str = sc.next(); // n번 만큼 받기
                crops[i] = str.split("");
            }
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(j < Math.abs(n/2-i) || j >= n-(Math.abs(n/2-i))){

                    } else {
                        sum += Integer.parseInt(crops[i][j]);
                    }
                }
            }
            System.out.printf("#%d %d\n", test_case, sum);
        }
    }
}