import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 31,048kb
 * 실행시간 291ms
 *
 * 1. 값 다 입력받고
 * 2. 두 좌표의 절대값을 받은 뒤에, 더해준다.
 * 3. 두 좌표 더한 값들 중에, 하나라도 짝수와 홀수 여부가 다른 값이 있다면, -1 출력
 * 4. 이미 다 0이면 0 출력
 * 5. 이후 가장 큰 값만 원점으로 들어오면 다른 친구들은 원점에서 놀고 있을테니
 *    가장 큰 값이 원점에 언제들어오는지만 세서 결과를 리턴해준다.
 */
public class Solution {

    static int N;
    static long[] numbers;
    static int[] status;

    static boolean isAllSame;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        String[] inputs;
        for (int T = 1; T <= tc; T++) {

            N = Integer.parseInt(br.readLine()); // 전체 숫자 개수
            numbers = new long[N]; // 숫자 좌표
            status = new int[N]; // 원점에 도달할 수 있는지
            isAllSame = true; // 도달못하면 -1 출력해줄 boolean

            for (int i = 0; i < N; i++) {
                inputs = br.readLine().split(" ");
                int a = Integer.parseInt(inputs[0]);
                int b = Integer.parseInt(inputs[1]);

                numbers[i] = Math.abs(a) + Math.abs(b);
                status[i] = (int) numbers[i] % 2;
                if (i > 0 && status[i - 1] != status[i]) {
                    isAllSame = false;
                }
            }

            if (!isAllSame) { // 원점에 뭔짓해도 도달 못하는 녀석
                sb.append("#").append(T).append(" ").append(-1).append("\n");
            } else if (isAllZero()) { // 이미 다 0
                sb.append("#").append(T).append(" ").append(0).append("\n");
            } else { // 원점 모이기가 가능한 경우
                int answer = 0;
                long maxValue = 0;
                for (long number : numbers) {
                    maxValue = Math.max(number, maxValue);
                }
                while (true) {
                    answer += 1;
                    long before = maxValue;
                    if (maxValue < 0) {
                        maxValue += answer;
                    } else {
                        maxValue -= answer;
                    }
                    if (maxValue == 0 || (isDifferent(before, maxValue) && isCorrectGap(before, maxValue))) {
                        break;
                    }
                }
                sb.append("#").append(T).append(" ").append(answer).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 원점으로 떨어지고나서 몇번 왔다갔다 해야하는 경우
    private static boolean isDifferent(long a, long b) {
        return (a < 0 && b >= 0) || (a >= 0 && b < 0);
    }

    private static boolean isCorrectGap(long a, long b) {
        long gap = (Math.abs(a) + Math.abs(b)) % 2;
        if (a % 2 == 0) { // a는 짝수
            return gap == 0;
        } else { // a 는 홀수
            return gap == 1;
        }
    }

    // 이미 다 0
    private static boolean isAllZero() {
        for (long n : numbers) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}