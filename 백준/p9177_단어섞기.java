import java.util.*;
import java.io.*;

public class p9177_단어섞기 {

    static int N;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= N; tc++) {
            String[] inputs = br.readLine().split(" ");
            if(bfs(inputs[0].toCharArray(), inputs[1].toCharArray(), inputs[2].toCharArray())){
                sb.append("Data set " + tc+": yes\n");
            } else {
                sb.append("Data set " + tc+": no\n");
            }

        }
        System.out.println(sb);
    }

    private static boolean bfs(char[] wordA, char[] wordB, char[] mixed) {
        visited = new boolean[wordA.length + 1][wordB.length + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();

            if (arr[2] == mixed.length) {
                return true;
            }

            if (arr[0] < wordA.length && !visited[arr[0] + 1][arr[1]] && wordA[arr[0]] == mixed[arr[2]]) {
                q.add(new int[]{arr[0] + 1, arr[1], arr[2] + 1});
                visited[arr[0] + 1][arr[1]] = true;
            }

            if (arr[1] < wordB.length && !visited[arr[0]][arr[1] + 1] && wordB[arr[1]] == mixed[arr[2]]) {
                q.add(new int[]{arr[0], arr[1] + 1, arr[2] + 1});
                visited[arr[0]][arr[1] + 1] = true;
            }

        }
        return false;
    }
}

