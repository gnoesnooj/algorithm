import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 17804
 * 시간 208
 *
 * 분할탐색을 해나가면서, 모든 값의 총합이 해당 size^2와 같다면, 1을 출력해주고, 0이라면 0을 출력해준다.
 * 둘다 아닌 경우에는, 1과 0이 섞여있는 부분이므로, 재귀를 통해 분할탐색을 다시 해준다.
 * 만일 size가 2인데 sum의 값이 0 또는 4 가 아닌 경우에는, 4개의 값을 모두 출력해준다.
 * */

public class Main {

    static int N;

    static int numbers[][];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numbers = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                numbers[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        search(N, 0, 0);

        System.out.print(sb.toString());
    }

    private static void search(int n, int sx, int sy) {
        int sum = 0;
        for (int i = sx; i < sx+n; i++) {
            for (int j = sy; j < sy+n; j++) {
                sum += numbers[i][j];
            }
        }

        if (sum == 0) { // 모든 좌표가 0
            sb.append(0+"");
            return;
        }

        if (sum == n * n) { // 모든 좌표가 1
            sb.append(1+"");
            return;
        }

        if (n == 2) { // 현재 n의 범위가 2
            sb.append("("+numbers[sx][sy] + numbers[sx][sy + 1] + numbers[sx + 1][sy] + numbers[sx + 1][sy + 1]+")");
            return;
        }

        sb.append("(");
        search(n / 2, sx, sy);
        search(n / 2, sx, sy + n / 2);
        search(n / 2, sx + n / 2, sy);
        search(n / 2, sx + n / 2, sy + n / 2);
        sb.append(")");
    }
}