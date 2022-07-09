// String 파싱, replacaAll 함수 사용
// set과 map을 이용해서 풀이
// 구현 문제

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution{
    public int[] solution(String s){
        Map<Integer, String[]> map = new HashMap<>();
        Set<String> set =new HashSet<>();

        String str = s.substring(2,s.length()-2);
        str = str.replaceAll("},\\{", "/");
        String [] tuples = str.split("/");

        int [] answer = new int[tuples.length];

        for(int i=0; i< tuples.length; i++){
            String [] parseTuples = tuples[i].split(",");
            map.put(parseTuples.length, parseTuples);
        }

        for(int i=1; i<=map.size(); i++){
            String [] tmp = map.get(i);
            for(String tmpString : tmp){
                if(set.contains(tmpString)){
                    continue;
                } else {
                    answer[i-1] = Integer.parseInt(tmpString);
                    set.add(tmpString);
                }
            }
        }
        return answer;
    }
}
