import java.io.*;
import java.util.*;

class Solution {
    static int x, y, max;
    static int[][] abilities;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                        {40}
                })
        );
    }

    public int solution(int[][] ability) {
        abilities = ability;
        max = 0;
        x = abilities.length;
        y = abilities[0].length;
        perm(new int[y], new boolean[x], 0);
        return max;
    }

    private static void perm(int[] subject, boolean[] visited, int index) {
        if (index >= y) {
            // 계산진행
            int sum = 0;
            for (int i = 0; i < y; i++) {
                sum += abilities[subject[i]][i];
            }
            max = Math.max(sum, max);
        } else {
            for (int i = 0; i < x; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    subject[index] = i;
                    perm(subject, visited, index + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
