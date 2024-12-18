import java.util.*;
import java.io.*;

public class p2151_거울설치 {

    static int N;

    static int[] dx = {-1, 0, 1, 0}; // 0 1 2 3 상, 우, 하, 좌
    static int[] dy = {0, 1, 0, -1};

    static char[][] miro;

    static int[][] visited;

    static List<int[]> doors = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        miro = new char[N][N];
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            miro[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (miro[i][j] == '#') {
                    doors.add(new int[]{i, j});
                }
            }
        }

        Queue<int[]> movings = new LinkedList<>();

        // queueing
        for (int i = 0; i < 4; i++) {
            int x = doors.get(0)[0];
            int y = doors.get(0)[1];
            for (int d = 0; d < N; d++) {
                int nx = x + dx[i] * d;
                int ny = y + dy[i] * d;
                if(nx == doors.get(1)[0] && ny == doors.get(1)[1]){
                    System.out.println(0);
                    return;
                }
                if (!isRange(nx, ny) || miro[nx][ny] == '*') { // 범위를 벗어나거나 벽이면
                    break;
                }
                if (miro[nx][ny] == '!') { // 거울 설치가 가능하면
                    movings.add(new int[]{nx, ny, (i + 4 + 1) % 4, 1});
                    movings.add(new int[]{nx, ny, (i + 4 - 1) % 4, 1}); // 90도 좌우로 꺾은거 넣어주기
                    visited[nx][ny] = 1;
                }
            }
        }

        while (!movings.isEmpty()) {
            int[] moving = movings.poll();
            int x = moving[0];
            int y = moving[1];
            int dir = moving[2];
            int cnt = moving[3];

            for (int i = 1; i < N; i++) {
                int nx = x + dx[dir] * i;
                int ny = y + dy[dir] * i;
                if(nx == doors.get(1)[0] && ny == doors.get(1)[1]){
                    System.out.println(cnt);
                    return;
                }
                if (!isRange(nx, ny) || miro[nx][ny] == '*') { // 범위를 벗어나거나 벽이면
                    break;
                }
                if (miro[nx][ny] == '!' && visited[nx][ny] > cnt + 1) {
                    // 거울 설치가 가능하고, 현재 진입 할 때의 거을 설치 수가 더 적으면
                    visited[nx][ny] = cnt + 1;
                    movings.add(new int[]{nx, ny, (dir + 4 + 1) % 4, cnt + 1});
                    movings.add(new int[]{nx, ny, (dir + 4 - 1) % 4, cnt + 1}); // 90도 좌우로 꺾은거 넣어주기
                }
            }
        }

    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
