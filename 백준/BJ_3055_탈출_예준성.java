import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 메모리 12336
 * 실행 시간 104
 * 물 확산을 먼저 시켜 준 후, 도치를 이동시켜 준다.
 * */
public class BJ_3055_탈출_예준성 {

    static char[][] miro;
    static boolean[][] visited;

    static Queue<int[]> waters = new LinkedList<>();
    static Queue<int[]> movings = new LinkedList<>();
    static int[] goal;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int R, C;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);

        miro = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            inputs = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                miro[i][j] = inputs[j].charAt(0);

                if (miro[i][j] == 'D') {
                    goal = new int[]{i, j};
                } else if (miro[i][j] == 'S') {
                    movings.add(new int[]{i, j, 0});
                } else if (miro[i][j] == '*') {
                    waters.add(new int[]{i, j});
                }
            }
        }

        bfs();

        System.out.println(result == 0 ? "KAKTUS" : result);
    }

    private static void bfs() {
        while (!movings.isEmpty()) {
            // 1. 물 확산
            int size = waters.size();
            for (int i = 0; i < size; i++) {
                int[] water = waters.poll();

                for (int j = 0; j < 4; j++) {
                    int nextX = water[0] + dx[j];
                    int nextY = water[1] + dy[j];

                    if(isRange(nextX, nextY) && miro[nextX][nextY] == '.'){
                        miro[nextX][nextY] = '*';
                        waters.add(new int[]{nextX, nextY});
                    }
                }
            }

            // 2. 도치 도망가기
            size = movings.size();

            for(int i=0;i<size;i++){
                int [] moving = movings.poll();

                for (int j = 0; j < 4; j++) {
                    int nextX = moving[0] + dx[j];
                    int nextY = moving[1] + dy[j];

                    if(isAnswer(nextX,nextY)){
                        result = moving[2]+1;
                        return;
                    }

                    if(isRange(nextX, nextY) && miro[nextX][nextY] == '.' && !visited[nextX][nextY]){
                        visited[nextX][nextY] = true;
                        movings.add(new int[]{nextX, nextY, moving[2]+1});
                    }
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }

    private static boolean isAnswer(int x, int y){
        return x == goal[0] && y == goal[1];
    }
}