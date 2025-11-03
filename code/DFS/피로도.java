package DFS;

class 피로도 {
    int answer = 0;
    boolean visited[] ;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0,dungeons, k);
        return answer;
    }
    
    public void dfs(int depth, int [][] dungeons, int k){
        for(int i=0; i<dungeons.length; i++){
            if(!visited[i] && k >= dungeons[i][0]){ // 방문하지 않았고, 던전 최소 피로도 보다 피로도가 높을 경우
                visited[i] = true; // 방문했다고 해주고
                dfs(depth + 1, dungeons, k- dungeons[i][1]); // 방문한 던전+1, 소모한 피로도를 빼준 값을 넣어줘서 다음 dfs 진행
                visited[i] = false; // 다른 분기이므로 false로 바꿔준다
            }
        }
        answer = Math.max(depth, answer);
    }
}
