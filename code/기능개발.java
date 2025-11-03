// 220126
// 구현으로 그냥 짰는데, 제출하고나서 보니 개발 완료를 위한 day를 기억해뒀다가 다음 단계에서 day 만큼 speeds[i] 를 더해줬을 때, 100이
// 넘으면 completed ++ 을 해주는 방법으로 접근했으면 좀 더 코드가 간단했을 것 같다.

import java.util.*;

class 기능개발 {
    
    public int[] getProgressesByDay(int[] progresses, int[] speeds, int day){
        for(int i=0 ; i<progresses.length ; i++){
            progresses[i]+= speeds[i]*day;
        }
        return progresses;
    }
    
    public int[] solution(int[] progresses, int[] speeds) {
        int day = 0;
        int completed = 0;
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for(int i=0 ; i<progresses.length ; i++){
            if(progresses[i] >= 100){
                day = 0;
                completed++;
            }else{
                if(completed != 0){
                    arrList.add(completed);
                    completed = 0;
                    while(progresses[i] < 100){
                        progresses[i] += speeds[i];
                        day++;
                    }
                    completed++;
                    progresses = getProgressesByDay(progresses, speeds, day);
                    day = 0;
                }else{
                    while(progresses[i] < 100){
                        progresses[i] += speeds[i];
                        day++;
                    }
                    completed++;
                    progresses = getProgressesByDay(progresses, speeds, day);
                    day = 0;
                }
            }
            if(i == progresses.length -1){
                    arrList.add(completed);
            }
        }
        int cnt = 0;
        int [] answer = new int [arrList.size()];
        for(int i : arrList){
            answer[cnt] = i;
            cnt++;
        }

        return answer;
    }
}
