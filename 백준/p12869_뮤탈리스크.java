import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p12869_뮤탈리스크 {

    static int N, answer;

    static int[] numbers;

    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        answer = Integer.MAX_VALUE;

        String[] inputs = br.readLine().split(" ");
        numbers = new int[3];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(inputs[i]);
        }

        visited = new boolean[61][61][61];

        calc(numbers[0], numbers[1], numbers[2], 0);

        System.out.println(answer);
    }

    private static void calc(int a, int b, int c, int depth) {
        if (a <= 0 && b <= 0 && c <= 0) {
            answer = Math.min(answer, depth);
            return;
        }

        a = Math.max(a, 0);
        b = Math.max(b, 0);
        c = Math.max(c, 0);

        int[] tmp = new int[]{a, b, c};

        Arrays.sort(tmp);

        // 정답
        c = tmp[0];
        b = tmp[1];
        a = tmp[2];

        // 틀림
//        a = tmp[0];
//        b = tmp[1];
//        c = tmp[2];
        
        /**
         * tmp를 오름차순으로 하면
         * 만약 input 이
         * 1
         * 60
         * 인 경우, -1을 9번 호출된 후 계산되는 (51,0,0) 의 경우가 먼저 방문 체크 되기 때문에
         * 첫번째 calc() 호출의 여섯번째 재귀호출인  (-9,-3,-1) 의 경우로 (51,0,0) 이 호출될 때
         * 이미 방문이 되어있기 때문에 계산을 하지 않고 최적의 해를 찾지 못하게 된다.
         * */
        
        if (visited[a][b][c]) {
            return;
        }

        visited[a][b][c] = true;

        if (depth > answer) {
            return;
        }

        calc(a - 9, b - 3, c - 1, depth + 1);
        calc(a - 9, c - 3, b - 1, depth + 1);
        calc(b - 9, a - 3, c - 1, depth + 1);
        calc(b - 9, c - 3, a - 1, depth + 1);
        calc(c - 9, a - 3, b - 1, depth + 1);
        calc(c - 9, b - 3, a - 1, depth + 1);
    }
}
