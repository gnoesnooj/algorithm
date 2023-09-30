import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 메모리 24960 실행 시간 200
 * 문제에 나온대로 구현한다.
 */
public class Main {

    // 전체 맵
    static int[][] miro;
    static int N, M;

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    // 이동 정보
    static Queue<int[]> movings = new LinkedList<>();

    // 물복사버그
    static List<int[]> bugs = new LinkedList<>();
    // 현재 구름
    static List<int []> clouds = new LinkedList<>();
    // 구름생성 방문배열
    static boolean [][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);

        M = Integer.parseInt(inputs[1]);

        miro = new int[N][N];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                miro[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            movings.add(new int[]{Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])});
        }
        // 비바라기
        clouds.add(new int[]{N-1, 0});
        clouds.add(new int[]{N-1, 1});
        clouds.add(new int[]{N-2, 0});
        clouds.add(new int[]{N-2, 1});

        while(!movings.isEmpty()){
            int [] moving = movings.poll();
            moveClouds(moving[0], moving[1]);
            copyWater();
            makeClouds();
        }
        System.out.println(getAllWaters());
    }

    private static void moveClouds(int d, int s){
        bugs.clear();
        visited = new boolean[N][N];
        for(int [] cloud : clouds){
            cloud[0] = (cloud[0] + (dx[d-1] * s)) % N;
            cloud[1] = (cloud[1] + (dy[d-1] * s)) % N;
            if(cloud[0] < 0) cloud[0] += N;
            if(cloud[1] < 0) cloud[1] += N;

            miro[cloud[0]][cloud[1]] += 1;

            bugs.add(new int[]{cloud[0], cloud[1]}); // 물복사버그에 할곳에 넣기

            visited[cloud[0]][cloud[1]] = true;
        }
    }

    private static void copyWater(){
        for(int [] bug : bugs){
            for(int i=1; i<=7; i+=2){
                int mx = bug[0] + dx[i];
                int my = bug[1] + dy[i];
                if(isRange(mx,my) && miro[mx][my] > 0){
                    miro[bug[0]][bug[1]] += 1;
                }
            }
        }
    }

    private static void makeClouds(){
        clouds.clear();
        for(int i=0 ;i<N; i++){
            for(int j=0; j<N; j++){
                if(miro[i][j] >= 2 && visited[i][j] == false){
                    clouds.add(new int[]{i,j});
                    miro[i][j] -=2;
                }
            }
        }
    }

    private static int getAllWaters(){
        int result = 0;
        for(int i=0 ;i<N; i++){
            for(int j=0; j<N; j++){
                result += miro[i][j];
            }
        }
        return result;
    }

    private static boolean isRange(int x, int y){
        return x >= 0 && y >= 0 && x < N && y < N;
    }

}