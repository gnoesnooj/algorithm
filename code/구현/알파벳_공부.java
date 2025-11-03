package 구현;

import java.util.Scanner;

class 알파벳_공부 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        String alpha = "abcdefghijklmnopqrstuvwxyz";
        String[] input_alpha = new String[T];
        int[] answer = new int[T];

        for (int test_case = 1; test_case <= T; test_case++) {
            input_alpha[test_case - 1] = sc.next();
        }

        for (int i = 0; i < input_alpha.length; i++) {
            for (int j = 0; j < input_alpha[i].length(); j++) {
                int ops = 0;
                if (input_alpha[i].charAt(j) == alpha.charAt(j)) {
                    answer[i]++;
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println("#" + (i + 1) + " " + answer[i]);
        }
    }
}
