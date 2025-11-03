
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
/**
 * 메모리 229668kb
 * 시간 964ms
 * */
public class BJ_17281_야구_예준성 {
    static int player[][], lineUp[], T, max, count;
    static ArrayDeque<Integer> hit;
    static boolean used[];

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine()); // 이닝 수

        player = new int[T][9];

        // T 이닝 동안 선수가 칠 타석
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 순열 뽑을라고 만들엇구나
        used = new boolean[9];

        // 4번에 넣어줄 값, 무조건 1번타자는 4번이다
        used[0] = true;

        // 순열 담아줄 배열
        lineUp = new int[9];

        max = 0;
        makeList(0);

        System.out.println(max);
    }

    private static void makeList(int cnt) {

        if (cnt == 3) {
            lineUp[cnt] = 0; // 라인업 3번에 그냥 0, 즉 1번타자 박기
            makeList(cnt + 1); // 다음거 찾으러 슛
        }
        if (cnt == 9) {
            playGame(lineUp);
        } else {
            for (int i = 0; i < 9; i++) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                lineUp[cnt] = i;
                makeList(cnt + 1);
                used[i] = false;
            }
        }
    }

    private static void playGame(int[] lineUp) {

        count = 0;

        ArrayDeque<Integer> que = new ArrayDeque<>();

        for (int i = 0; i < lineUp.length; i++) {
            que.add(lineUp[i]); // que 라인업 넣기
        }

        for (int i = 0; i < T; i++) {
            hit = new ArrayDeque<>(); // hit
            int out = 0;
            while (out < 3) {

                int currentPlayer = que.poll();
                int play = player[i][currentPlayer]; // i 이닝, 현재 플레이어
                que.add(currentPlayer); // 일단 반복

                switch (play) {
                    case 1:
                        isHome(1);
                        hit.add(1);
                        break;
                    case 2:
                        isHome(2);
                        hit.add(2);
                        break;
                    case 3:
                        isHome(3);
                        hit.add(3);
                        break;
                    case 4:
                        hit.add(4);
                        isHome(4);
                        break;
                    case 0:
                        out++;
                        break;
                }
            }
        }
        max = Math.max(max, count);

    }

    // 주루 상의 주자들을 큐에 넣고, 수치가 4가 넘으면 점수를 +1 올려준다.
    private static void isHome(int run) {
        int size = hit.size();
        for (int j = 0; j < size; j++) {
            int now = hit.poll();
            now = now + run;
            if (now >= 4) {
                count++;
                continue;
            }
            hit.add(now);
        }

    }

}