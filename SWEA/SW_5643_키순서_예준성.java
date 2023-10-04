import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 97,376 kb
 * 메모리
 * 2,178 ms
 * 실행시간
 * 학생 기준으로 위쪽 탐색, 아래쪽 탐색 후 두 탐색의 결과 합이 N-1 이라면 본인의 위치를 알 수 있음.
 * */
public class Solution {

    static int N, M;
    static int[][] students;

    static int answer, upCnt, downCnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());
        for (int T = 1; T <= test_case; T++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            students = new int[N + 1][N + 1];

            String[] inputs;
            for (int i = 0; i < M; i++) {
                inputs = br.readLine().split(" ");
                int left = Integer.parseInt(inputs[0]);
                int right = Integer.parseInt(inputs[1]);

                students[left][right] = 1;
            }

            answer = 0;
            // 위쪽 탐색
            for (int i = 1; i <= N; i++) {
                upCnt = 0;
                downCnt = 0;
                // 위
                upBfs(i);
                // 아래
                downBfs(i);

                if (upCnt + downCnt == N - 1) {
                    answer++;
                }
            }
            System.out.println("#" + T + " " + answer);
        }

    }

    private static void upBfs(int student) {
        // 큐에 시작 학생 넣고
        // 방문처리
        // 큐 진행 -> student -> x 가 1인 건 전부 큐, 끝까지 탐색, poll 할떄마다 +1
        Queue<Integer> q = new LinkedList<>();
        q.add(student);
        boolean[] visited = new boolean[N + 1];
        visited[student] = true;
        while (!q.isEmpty()) {
            int next = q.poll();
            for (int i = 1; i <= N; i++) {
                if (visited[i] == false && students[next][i] == 1) {
                    q.add(i);
                    visited[i] = true;
                    upCnt++;
                }
            }
        }
    }

    private static void downBfs(int student) {
        Queue<Integer> q = new LinkedList<>();
        q.add(student);
        boolean[] visited = new boolean[N + 1];
        visited[student] = true;
        while (!q.isEmpty()) {
            int next = q.poll();
            for (int i = 1; i <= N; i++) {
                if (visited[i] == false && students[i][next] == 1) {
                    q.add(i);
                    visited[i] = true;
                    downCnt++;
                }
            }
        }
    }


}