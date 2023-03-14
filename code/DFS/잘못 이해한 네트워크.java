// 프로그래머스 문제 잘못이해하고 품 ㅋㅋ
/*
- 문제 조건을 그래프가 아닌 영역 탐색을 하는 것으로 이해
- 이보다 더 쉽게 풀 수 있을 것 같긴 하다..
*/

/* 풀이
1. for문을 전체 탐색
2. 탐색하면서, 방문하지 않았고, 1이라면 answer ++ 를 해준다.
  2-1. 그 자리에서, 상하좌우 DFS를 진행한다.
  2-2. DFS를 진행할 때, 범위를 벗어나면 진행하지 않는다.
  2-3. 0을 만나면, true로 바꿔준다. 
  2-4. 1을 만나면, 붙어있는 영역이므로 true로 바꿔준 후 dfs를 다시 진행한다.
  2-5. 이렇게 되면, 0으로 둘러싸여있는 1의 영역만을 탐색할 수 있다.
3. 방문을 했다면 다음 for 문을 진행한다.
4. 만일 방문하지 않았고, 해당 영역이 0이라면 true로 바꿔준 후 다음 for문을 진행한다. 
5. answer를 리턴하고 종료한.*/

class Solution {

    boolean[][] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n][n]; // false 로 초기화

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false && computers[i][j] == 1) { // 방문하지 않았고, 해당 값이 1이라면
                    answer++;
                    dfs(i, j, computers, n);
                    visited[i][j] = true;
                }
                if (computers[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        return answer;
    }

    public void dfs(int x, int y, int[][] computers, int n) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        if (isRangeOut(x, y, n) && !visited[x][y]) { // 범위를 벗어나지 않고, 방문하지 않았다면
            if (computers[x][y] == 1) {
                visited[x][y] = true;
                for (int i = 0; i < 4; i++) {
                    dfs(x + dx[i], y + dy[i], computers, n);
                }
            } else if (computers[x][y] == 0) {
                visited[x][y] = true;
            }
        }

    }

    public boolean isRangeOut(int x, int y, int n) {
        if (x >= n || y >= n || x < 0 || y < 0) { // 범위를 벗어나면
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] com = {
                {1, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}};
        int answer = s.solution(4, com);
        System.out.println("answer = " + answer);
    }
}
