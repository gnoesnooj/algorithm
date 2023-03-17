
class Solution {

    int answer = 0;
    boolean[][] visited;

    public int solution(int n, int[][] computers) {
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(computers[i][j] == 1 && visited[i][j] == false){
                    answer++;
                    visited[i][j] = true;
                    dfs(computers, j);
                }
            }
        }
        return answer;
    }

    public void dfs(int[][] computers, int x) {
        for(int i=0;i<computers[x].length;i++){
            if(computers[x][i] == 1 && visited[x][i] == false) {
                visited[x][i] = true;
                dfs(computers,i);
            }
        }
    }
}
