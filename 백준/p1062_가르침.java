import java.io.*;
import java.util.*;

/*
 * 1. N개를 5개로 나눈 x 값 찾기
 * 2. 2차원배열 [5][x]에 담기
 * 3. 숫자찾기 -> 세로 한줄씩 읽으며, if 이전열이 모두 빈칸이고 혹은 배열밖이고,
 *    현재열이 모두 빈칸이 아닐 때, 3열에 대해 숫자 판독 시작
 * 4. 숫자판독이후 3열 ++된 위치에서 재탐색 시작, 배열 밖이라면 종료
 * */
public class p1062_가르침 {

    static int N, K, answer;
    static String[] words;

    static boolean[] used;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs;
        String input = "";
        inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);
        answer = Integer.MIN_VALUE;

        words = new String[N];
        used = new boolean[26];

        for (int i = 0; i < N; i++) {
            input = br.readLine();
            input = input.replace("anta", "");
            input = input.replace("tica", "");
            words[i] = input;
        }

        // a n t i c 는 무조건 사용
        used['a' - 'a'] = true;
        used['n' - 'a'] = true;
        used['t' - 'a'] = true;
        used['i' - 'a'] = true;
        used['c' - 'a'] = true;

        // 5개 미만이면 읽을 수 있는게 없음, 26개면 모든 알파벳 가능이니까 다읽기 가능
        if (K < 5) {
            System.out.println("0");
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        solve(0, 0);
        System.out.println(answer);
    }

    // 사용한 알파벳, cnt는 사용한 알파벳 길이
    private static void solve(int index, int cnt) {
        // k-5개중 필요한 알파벳을 조합 후
        // 최대 읽을 수 있는 단어 개수 찾기
        if (cnt == K - 5) {
            int sum = 0;
            for (int i = 0; i < words.length; i++) {
                boolean isReadable = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if (!used[words[i].charAt(j) - 'a']) {
                        isReadable = false;
                        break;
                    }
                }
                if (isReadable) {
                    sum += 1;
                }
            }
            answer = Math.max(answer, sum);
        } else {
            for (int i = index; i < 26; i++) {
                if (used[i] == false) {
                    used[i] = true;
                    solve(i + 1, cnt + 1);
                    used[i] = false;
                }
            }
        }

    }
}
