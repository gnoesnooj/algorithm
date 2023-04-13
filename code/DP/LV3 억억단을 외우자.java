import java.util.Arrays;

class Solution {

    int[] dp;

    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        dp = new int[e + 1];
        findDp(e);
        for (int i = 0; i < starts.length; i++) {
            answer[i] = findMax(e, starts[i]);
        }
        return answer;
    }

    public void findDp(int e) {
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                dp[i * j] += 1;
            }
        }
    }

    public int findMax(int e, int start) {
        int max = 0;
        int index = 0;
        for (int i = start; i <= e; i++) {
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        return index;
    }
}
