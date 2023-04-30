/*
현재 라운드를 어떻게든 클리어 했다면, 다음 라운드로 진행한다.
*/
class Solution {
    int answer = 0;
    public int solution(int n, int k, int[] enemy) {
        if(k==enemy.length)
            return k;
        dfs(n, k, enemy, 0);
        return answer;
    }
    
    public void dfs(int n, int k, int[] enemy, int depth){
        int soldiers = n - enemy[depth];
        if(soldiers >= 0){ // 아군 병사를 사용한 경우 + 사용한 병사 수가 유효한 경우
            answer = Math.max(answer, depth+1);
            if(depth+1 < enemy.length)
                dfs(soldiers, k, enemy, depth+1);
        }
        
        int moo = k - 1;
        if(moo >= 0){ // 무적권을 사용한 경우 + 사용한 무적권 수가 유효한 경우 
            answer = Math.max(answer, depth+1);
            if(depth+1 < enemy.length)
                dfs(n, moo, enemy, depth+1);
        }
    }
}
// 너무 큰 값으로 인한 런타임 에러 발생
