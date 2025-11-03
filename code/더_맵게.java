// 220129

// 처음 코드를 짤 때
// Arrays.sort() 를 사용해서 하려고 했는데, 효율성테스트 통과를 못함.
// 해결방법을 찾던 중 우선순위 큐에 대해 알게되는 계기가 되었음

import java.util.*;

class 더_맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : scoville){ // 다넣기
            pq.offer(i);
        }
        
        while(pq.peek() < K){
            if(pq.size() == 1 && pq.peek() < K) return -1;
            int a = pq.poll();
            int b = pq.poll();
            int num = a + (b*2);
            
            pq.offer(num);
            answer++;
        }
        return answer;
    }
}
