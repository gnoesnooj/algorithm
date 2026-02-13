package 구현;
import java.util.*;

class 대충_만든_자판 {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        Map<Character, Integer> dict = new HashMap<>();

        // make dict
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                if (dict.containsKey(key.charAt(i))) {
                    dict.replace(key.charAt(i), Math.min(dict.get(key.charAt(i)), i));
                } else {
                    dict.put(key.charAt(i), i);
                }
            }
        }

        //find answer
        for (int i = 0; i < targets.length; i++) {
            for (int j = 0; j < targets[i].length(); j++) {
                if (dict.get(targets[i].charAt(j)) == null) {
                    answer[i] = -1;
                    break;
                } else {
                    answer[i] += dict.get(targets[i].charAt(j)) + 1;
                }
            }
        }
        return answer;
    }
}