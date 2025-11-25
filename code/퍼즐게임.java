import java.io.*;
import java.util.*;

class 퍼즐게임 {    
    public int solution(int[] diffs, int[] times, long limit) {

        return binary_search(diffs, times, limit);

    }

    public int binary_search(int [] diffs, int [] times, long limit) {
        int max = 1; int min = 100000; 
        while (max <= min){
            int level = (max+min)/2; 
            long mid = calcul(diffs,times, level);
            // 제한 시간을 초과함 -> level 상승
            if(mid > limit) max = level + 1; 
            // 제한시간보다 빨리 끝내거나 딱 맞출 때 -> level 하락
            else min = level - 1; 
        }
        return max;
    }

    public long calcul(int [] diffs, int [] times, int level) {
        long ans = 0;
        for(int i = 0; i < diffs.length; i++){
            if(diffs[i] <= level) ans += times[i];
            else ans += (long)(times[i] + times[i-1])* (long)(diffs[i] - level) + times[i];
        }
        return ans;
    }
}
