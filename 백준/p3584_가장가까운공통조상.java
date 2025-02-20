import java.util.*;
import java.io.*;

public class p3584_가장가까운공통조상 {

    static int N;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        String[] inputs;

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());

            // parent 배열 초기화
            parent = new int[N + 1];
            visited = new boolean[N + 1];

            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            // 노드 입력
            for (int i = 0; i < N - 1; i++) {
                inputs = br.readLine().split(" ");

                int a = Integer.parseInt(inputs[0]);
                int b = Integer.parseInt(inputs[1]);

                parent[b] = a;
            }
            // 공통 부모를 확인할 두 노드를 입력
            inputs = br.readLine().split(" ");

            int n1 = Integer.parseInt(inputs[0]);
            int n2 = Integer.parseInt(inputs[1]);

            checkParent(n1);
            System.out.println(find(n2));
        }
    }

    private static int checkParent(int x){
        visited[x] = true;
        if(parent[x] == x){
            return x;
        } else {
            visited[checkParent(parent[x])] = true;
            return parent[x];
        }
    }

    private static int find(int x){
        int p = x;
        while(true){
            if(visited[p] == true){
                return p;
            } else {
                p = parent[p];
            }
        }
    }
}

