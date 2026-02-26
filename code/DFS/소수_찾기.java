package DFS;

import java.util.HashSet;
import java.util.Set;

class 소수_찾기 {
    static char[] papers;
    static boolean[] visited;
    static Set<Integer> set;

    public int solution(String numbers) {
        int answer = 0;
        papers = numbers.toCharArray();
        visited = new boolean[papers.length];
        set = new HashSet<>();

        dfs("", 0);
        for (Integer number : set) {
            if (isPrime(number)) {
                answer++;
            }
        }
        return answer;
    }

    private void dfs(String word, int depth) {
        if (depth > word.length()) {
            return;
        }
        for (int i = 0; i < papers.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                set.add(Integer.parseInt(word + papers[i]));
                dfs(word + papers[i], depth + 1);
                visited[i] = false;
            }
        }
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}