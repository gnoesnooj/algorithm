import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int end = targets[0][1]; // 가장 작은 end 값을 가진 value
        
        for(int[] target : targets){
            if(end <= target[0]){ // 현재 end 값이 다음 start 값보다 작다면, 개구간이기에 같은 조건도 포함해야한다.
                answer++;
                end = target[1];
            }
        }
        
        return answer;
    }
}

// Arrays.sort 에 대해서 배움.
// (x,y) -> x[0] - y[0] 이면, target[index][0] 의 값을 기준으로 오름차순 정렬
// -> x[1] - y[1] , target[index][1] 의 값을 기준으로 오름차순 정렬
// -> y[0] - x[0] , target[index][0] 의 값을 기준으로 내림차순 정렬 
// -> y[1] - x[1] , target[index][1] 의 값을 기준으로 내림차순 정렬 
// 다음과 같이 응용도 가능하다.
// -> x[0] != y[0] ? x[0]-y[0] : x[1]-y[1]; // 첫번째 기준 오름차순 > 두번째 기준 오름차순
// -> x[0] != y[0] ? x[0]-y[0] : y[1]-x[1]; // 첫번째 기준 오름차순 > 두번째 기준 내림차순
