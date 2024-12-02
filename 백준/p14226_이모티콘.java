import java.io.*;
import java.util.*;


public class p14226_이모티콘 {

    static int S;

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        visited = new boolean[1001][1001];

        Queue<int[]> orders = new LinkedList<>();
        // int[]{화면, 클립보드, 시간};

        visited[2][1] = true;
        orders.add(new int[]{2, 1, 2});
        while (!orders.isEmpty()) {
            int[] order = orders.poll();

            if (order[0] == S) {
                System.out.println(order[2]);
                break;
            }

            // 복사
            if (order[0] != order[1] && !visited[order[0]][order[0]]) {
                // 화면과 클립보드가 다르면 복사를 하는 경우의 수를 넣어준다.
                visited[order[0]][order[0]] = true;
                orders.add(new int[]{order[0], order[0], order[2] + 1});
            }

            // 붙여넣기
            if (order[0] + order[1] < 1001 && !visited[order[0] + order[1]][order[1]]) {
                visited[order[0] + order[1]][order[1]] = true;
                orders.add(new int[]{order[0] + order[1], order[1], order[2] + 1});
            }

            // 삭제
            if (order[0] > 2 && !visited[order[0] - 1][order[1]]) {
                visited[order[0] - 1][order[1]] = true;
                orders.add(new int[]{order[0] - 1, order[1], order[2] + 1});
            }
        }
    }
}
