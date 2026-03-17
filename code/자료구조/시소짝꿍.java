package 자료구조;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class 시소짝꿍 {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        Map<Double, Integer> map = new HashMap<>();

        for (int w : weights) {
            double a = (double) w;
            double b = (double) (w * 2) / 4;
            double c = (double) (w * 2) / 3;
            double d = (double) (w * 3) / 4;

            if(map.containsKey(a)) answer += map.get(a);
            if(map.containsKey(b)) answer += map.get(b);
            if(map.containsKey(c)) answer += map.get(c);
            if(map.containsKey(d)) answer += map.get(d);

            map.put(a, map.containsKey(a) ? map.get(a)+1 : 1);

        }
        return answer;
    }
}