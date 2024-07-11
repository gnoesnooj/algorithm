import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static int[] usedCounts = new int[26];
    static String[] alphas = new String[26];
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("eeddee"));
    }

    public String solution(String input_string) {
        String answer = "";

        for (int i = 0; i < alphas.length; i++) {
            alphas[i] = String.valueOf((char) ('a' + i));
            map.put(alphas[i], i);
        }

        String[] inputs = input_string.split("");
        usedCounts[map.get(inputs[0])]++;
        for (int i = 1; i < inputs.length; i++) {
            // 이전 값이랑 비교해서 다르면 카운트 올려주면됨
            if (!inputs[i - 1].equals(inputs[i])) {
                usedCounts[map.get(inputs[i])]++;
            }
        }

        for (int i = 0; i < usedCounts.length; i++) {
            if (usedCounts[i] >= 2) {
                answer += alphas[i];
            }
        }

        return answer != "" ? answer : "N";
    }
}
