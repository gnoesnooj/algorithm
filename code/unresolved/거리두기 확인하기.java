/* dfs
dx dy
위치가 P인 것만 dfs를 진행함.
맨해튼 거리가 2 까지만 확인
1) 
P - 공간 - P 
2) 
P - 공간
공간 - P 
이 두가지만 아니면 된다.
*/
import java.util.*;
 
class Solution {
    
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    
    public int[] solution(String[][] places) {
        int[] result = new int[places.length];
        for(int i = 0; i < places.length; i++){
            result[i] = isCorrext(places[i]);
        }
        return result;
    }
    
    public int isCorrext(String[] board) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length(); j++){
                if(board[i].charAt(j) == 'P') { 
                    if(!bfs(board, i, j)) return 0; 
                }
            }
        }
        return 1;
    }
    
    public boolean bfs(String[] board, int x, int y) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board.length];
        q.offer(new Node(x, y));
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                int manhattan = Math.abs(x - nx) + Math.abs(y - ny);
                
                if(nx < 0 || ny < 0 || nx >= board.length || ny >= board.length) continue;
                if(visited[nx][ny] || manhattan > 2) continue;
                
                visited[nx][ny] = true;
                if(board[nx].charAt(ny) == 'X') continue;
                else if(board[nx].charAt(ny) == 'P') return false;
                else q.offer(new Node(nx, ny));
            }
        }
        return true;
    }
    
    public class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
