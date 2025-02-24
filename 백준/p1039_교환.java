import java.util.*;
import java.io.*;

public class p1039_교환 {

    static int N, K, M;

    static boolean[][] visited;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);

        K = Integer.parseInt(input[1]);

        M = String.valueOf(N).length();

        visited = new boolean[(int) Math.pow(10, M + 1)][K + 1];

        search();

        System.out.println(answer);

    }

    private static void search() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N, 0});
        visited[N][0] = true;
        while (!q.isEmpty()) {
            int[] arr = q.poll();
            if (arr[1] == K) {
                answer = Math.max(answer, arr[0]);
            } else {
                for (int i = 0; i < M - 1; i++) {
                    for (int j = i + 1; j < M; j++) {
                        int value = change(arr[0], i, j);
                        if (value != -1 && !visited[value][arr[1] + 1]) {
                            q.add(new int[]{value, arr[1] + 1});
                            visited[value][arr[1] + 1] = true;
                        }
                    }
                }
            }
        }
    }

    private static int change(int num, int a, int b) {
        char[] arr = String.valueOf(num).toCharArray();

        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        if (arr[0] == '0') {
            return -1;
        }
        return Integer.parseInt(String.valueOf(arr));
    }
}

