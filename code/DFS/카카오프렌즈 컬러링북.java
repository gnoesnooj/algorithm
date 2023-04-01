// 전역변수를 solution 내에서 초기화해주기
// int 배열의 크기인 첫 매개변수 pciture를 long으로 복사해주기
// 왜 이것들을 해야 통과가 되는지 모르겠지만, 이를 해줬더니 통과가 된다.

class Solution {
    boolean[][] visited;
    int numberOfArea ;
    int maxSizeOfOneArea ;

    int[] moveX = {-1, 1, 0, 0};
    int[] moveY = {0, 0, -1, 1};

    public int[] solution(int m, int n, int[][] pictures) {
        visited = new boolean[m][n];
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        long[][] picture = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                picture[i][j] = (long) pictures[i][j];
                visited[i][j] = false;
            }
        }

        //dfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false && picture[i][j] != 0) { // 0이아니고 방문한 곳이 아니라면
                    numberOfArea++;
                    System.out.println(i + "," + j + "," + numberOfArea);
                    dfs(i, j, m, n, picture[i][j], picture, 0);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    void dfs(int x, int y, int m, int n, long before_value, long[][] picture, int count) {
        if (x < 0 || y < 0 || x >= m || y >= n || picture[x][y] == 0
                || picture[x][y] != before_value || visited[x][y] == true) { // 범위를 벗어나면, 또는 0이면
            // 암것도안한다
        } else {
            if (before_value == picture[x][y]) { // 이전값과 같다면
                visited[x][y] = true;
                count++;
                for (int i = 0; i < 4; i++) {
                    dfs(x + moveX[i], y + moveY[i], m, n, picture[x][y], picture, count);
                }
            }
        }
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
    }

    public static void main(String[] args) {
        int[][] picture = {{0, 1, 0}, {1, 1, 0}, {0, 0, 0}};
        Solution s = new Solution();
        int[] answer = s.solution(3, 3, picture);
        System.out.println("답 : " + answer[0] + "," + answer[1]);
    }
}

//
/*
class Solution {
    // 변수 접근을 위한 전역 변수들.
    static int numberOfArea;
    static int maxSizeOfOneArea;
    // 한 영역의 수를 저장하는 변수.
    static int temp_cnt = 0;
    // 좌표에서의 상,하,좌,우 탐색을 위한 배열.
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    // DFS 메소드
    public static void dfs(int x,int y, int[][] picture, boolean[][] check){
        // 6. 방문한 적 있는 좌표라면 DFS 종료.
        if(check[x][y]) return;
        
        // 7. 처음 방문 시 방문처리.
        check[x][y] = true;
        // 8. 영역의 수 증가.
        temp_cnt++;
        
        // 9. 한 좌표에서 상,하,좌,우 탐색.
        for(int i =0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 10. picture 배열의 범위를 벗어나면 continue.
            if(nx<0 || nx>=picture.length || ny<0 || ny>=picture[0].length) continue;
            
            // 11. 현 좌표의 색 == 상,하,좌,우 좌표의 색 && 방문한적 없는 상,하,좌,우 좌표라면.
            if(picture[x][y] == picture[nx][ny] && !check[nx][ny]){                
                // 12. DFS를 위한 재귀호출.
                dfs(nx,ny,picture,check);
            }            
        }        
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        // 1. 초기화 꼭! 하기.
        numberOfArea =0;
        maxSizeOfOneArea=0;
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        // 2. DFS시 방문여부를 체크 할 배열.
        boolean[][] check = new boolean[m][n];
        
        // 3. 주어진 picture 배열을 탐색.
        for(int i =0;i<m;i++){
            for(int j=0;j<n;j++){
                // 4. 원소가 0이 아니고, 방문한 적이 없다면.
                if(picture[i][j] != 0 && !check[i][j]){
                    // 5. 영역의 수가 1개 증가하며 DFS 탐색 수행.
                    numberOfArea++;
                    dfs(i,j,picture,check);
                }
                // 13. 한 영역의 탐색이 모두 끝났다면, 조건에 따라 최대 영역의 수를 변경.
                if(temp_cnt > maxSizeOfOneArea) maxSizeOfOneArea = temp_cnt;
                // 14. 한 영역의 수는 다시 초기화.
                temp_cnt = 0;
            }
        }
        
        // 15. 각 값을 answer 배열에 담아주고 끝.
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
}
*/
