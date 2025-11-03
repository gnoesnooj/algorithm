package D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 나무들 중 최고 높이를 구한다.
 * 2. 최고 높이를 가지기 위한 나무 각각의 필요 수치를 구한다.
 * 3. 필요 수치만큼 1 과 2로 나눠서 필요한 날짜를 계산한다.
 * -> 2일은 1일 2개로 나눠서 자라게 할 수 있고, 홀수 1, 짝수 3인 경우 3 2 로 나눠서 자라게 하는게 좋지만
 * -> 홀수 1, 짝수 2인 경우 3 1 로 나눠서 자라게 하는 것 보다 1일 1, 2일 2, 3일 X, 4일 2 만큼 자라게 하는게 더 좋다.
 * 4. 이후 조건에 맞게 계산해준다.
 *
 * 메모리 19,376 kb
 * 시간 110 ms
 */

public class D2_나무높이_예준성 {

    static int[] trees;

    static int answer;

    static int odd;
    static int even;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        String[] inputs;
        StringBuilder sb = new StringBuilder();
        for (int T = 1; T <= test_case; T++) {
            int N = Integer.parseInt(br.readLine());

            odd = 0;
            even = 0;
            answer = 0;

            trees = new int[N];
            inputs = br.readLine().split(" ");

            // 큰값 찾기
            int max = 0;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(inputs[i]);
                if (max < trees[i]) {
                    max = trees[i];
                }
            }

            // 필요한 물 확인하기
            for (int i = 0; i < N; i++) {
                int gap = max - trees[i];
                even += gap / 2;
                odd += gap % 2;
            }

            while (odd + 1 < even) { // 2는 1 2개가 될 수 있음
                odd += 2;
                even -= 1;
            }

            if (odd == even) { // 둘이 똑같은 경우
                answer += even + odd;
            } else if (odd > even) { // odd 가 1이라도 더 크면
                answer += even * 2;
                answer += (odd - even) + (odd - even - 1);
            } else if (even > odd) { // even 이 1 더 크면
                answer += odd * 2 + 2;
            }

            sb.append("#" + T + " " + answer + "\n");
        }
        System.out.println(sb);
    }

}
