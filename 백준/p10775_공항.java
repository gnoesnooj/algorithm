import java.util.*;
import java.io.*;

public class p10775_공항 {

    static int[] parent;
    static int G, P, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        parent = new int[G + 1];
        for (int i = 0; i < G + 1; i++) {
            parent[i] = i;
        }

        P = Integer.parseInt(br.readLine());

        for (int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine());

            int n = find(gi);

            if (n == 0) {
                break;
            }
            answer++;

            union(n, n - 1);
        }

        System.out.println(answer);

    }

    private static int find(int n) {
        if (parent[n] == n) {
            return n;
        } else {
            return parent[n] = find(parent[n]);
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }
}

