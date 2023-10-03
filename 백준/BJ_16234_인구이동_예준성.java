import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 메모리 295488
 * 시간 628
 * bfs 로 탐색하고 -> 인구이동 시켜주고 -> 인구이동을 안했으면 결과 리턴 후 종료
 * bfs에서 처음 좌표를 방문처리만하고 인구이동에 넣어주지 않아서 한시간넘게 날렸다
 * */

public class Main {
    static int N, L, R, answer;

    static int[][] countries;

    static boolean isOver;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        L = Integer.parseInt(inputs[1]);
        R = Integer.parseInt(inputs[2]);

        countries = new int[N][N];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        while(true) {
            isOver = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == false) { // 방문하지 않은 곳이라면 bfs로 연합찾아보기
                        bfs(i, j);
                    }
                }
            }
            if(!isOver)
                break;
            answer++;
        }

        System.out.println(answer);

    }

    private static void bfs(int x, int y){
        // 해당 좌표에서 연합찾기
        Queue<int []> q = new LinkedList<>();
        // 시작
        q.add(new int[]{x,y});
        // 시작 방문
        visited[x][y] = true;

        // 인구이동리스트
        List<int []> movings = new LinkedList<>();

        movings.add(new int[]{x,y}); // 이거 때문에 시간 너무 날림

        while(!q.isEmpty()){
            int [] next = q.poll();
            for(int i=0 ;i<4; i++){
                int nx = next[0] + dx[i];
                int ny = next[1] + dy[i];
                // 범위고, 간적없고, 이동할수있는 차이면
                if(isRange(nx, ny) && visited[nx][ny] == false && isMovable(countries[next[0]][next[1]], countries[nx][ny])){
                    isOver = true;
                    movings.add(new int[]{nx,ny});
                    visited[nx][ny]= true;
                    q.add(new int[] {nx, ny});
                }
            }
        }

        if(!movings.isEmpty()) {
            int sum = 0;
            int cnt = 0;
            for (int[] moving : movings) {
                sum += countries[moving[0]][moving[1]];
                cnt++;
            }

            int avg = sum / cnt;
            for (int[] moving : movings) {
                countries[moving[0]][moving[1]] = avg;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static boolean isMovable(int px, int py) {
        int gap = Math.abs(px - py);
        return gap >= L && gap <= R;
    }
}