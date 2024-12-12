import java.util.*;
import java.io.*;

public class p3197_백조의호수 {

    static int R, C;

    static char[][] miro;

    static Queue<int[]> waters = new LinkedList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static boolean[][] visited;

    static List<int[]> swans = new ArrayList<>();

    static Queue<int[]> swanMovings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);

        miro = new char[R][C];

        String input;
        for (int i = 0; i < R; i++) { // 1500 * 1500
            input = br.readLine();
            for (int j = 0; j < C; j++) {
                miro[i][j] = input.charAt(j);
                if (miro[i][j] == '.' || miro[i][j] == 'L') {
                    waters.add(new int[]{i, j});
                }
                if (miro[i][j] == 'L') {
                    swans.add(new int[]{i, j});
                }
            }
        }

        int cnt = 0;
        while (!waters.isEmpty()) {
            if (isReachable()) {
                System.out.println(cnt);
                return;
            }
            melt();
            cnt++;
        }
    }

    private static void melt() {
        int size = waters.size();

        for (int i = 0; i < size; i++) {
            int[] water = waters.poll();
            for (int d = 0; d < 4; d++) {
                int nx = water[0] + dx[d];
                int ny = water[1] + dy[d];

                if (isRange(nx, ny) && miro[nx][ny] == 'X') { // 물로 만들어주고 다음에 넣어준다.
                    miro[nx][ny] = '.';
                    waters.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isReachable() {
        visited = new boolean[R][C];
        swanMovings = new LinkedList<>();

        swanMovings.add(swans.get(0));
        while (!swanMovings.isEmpty()) {
            int[] moving = swanMovings.poll();
            for (int d = 0; d < 4; d++) {
                int nx = moving[0] + dx[d];
                int ny = moving[1] + dy[d];

                if(nx == swans.get(1)[0] && ny == swans.get(1)[1]){
                    return true;
                }
                if (isRange(nx, ny) && miro[nx][ny] == '.' && !visited[nx][ny]) { // 주변이동할 수 있다면
                    visited[nx][ny] = true;
                    swanMovings.add(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }
}
