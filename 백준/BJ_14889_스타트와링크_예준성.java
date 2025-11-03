import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리 14872kb
 * 시간 352ms
 *
 * 1. N/2 개 만큼 조합 뽑기
 * 2. 각각 팀 능력치 뽑기
 * 3. 최소 비교 후 결과 리턴
 * */

public class BJ_14889_스타트와링크_예준성 {

    static int N;
    static boolean[] selected;

    static int [][] graph;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 팀 뽑을 때 쓸거
        selected = new boolean[N];

        // 전체 능력치 배열
        graph = new int[N][N];

        String [] inputs;
        for(int i=0; i<N;i++){
            inputs = br.readLine().split(" ");
            for(int j=0; j<N;j++){
                graph[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        combi(0, 0);

        System.out.println(answer);
    }

    private static void combi(int start, int cnt) {
        if (cnt == N / 2) {
            int a = 0, b = 0;

            for (int i = 0; i < N-1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if(selected[i] && selected[j]){ // true 팀
                        a += graph[i][j] + graph[j][i];
                    } else if(!selected[i] && !selected[j]){ // false 팀
                        b += graph[i][j] + graph[j][i];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(a - b));
        } else {
            // true 팀, false 팀
            for (int i = start; i < N; i++) {
                selected[i] = true;
                combi(i + 1, cnt + 1);
                selected[i] = false;
            }
        }
    }
}