package DP;/*
import java.util.Arrays;

class LV3_억억단을_외우자 {

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

*/

class LV3_억억단을_외우자 {
    private final int MAX_SIZE = 5000000;
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        int[] divisor = getDivisor(e);
        int[][] store = getMaxNumStore(e, divisor);
        
        for (int i=0; i<starts.length; i++) {
            answer[i] = store[starts[i]][1];
        }
        
        return answer;
    }
    
    private int[][] getMaxNumStore(int e, int[] divisor) {
        int[][] store = new int[e+1][2];
        
        store[e][0] = divisor[e];
        store[e][1] = e;
        for (int i=e-1; i>=1; i--) {
            if (divisor[i] >= store[i+1][0]) {
                store[i][0] = divisor[i];
                store[i][1] = i;
            } else {
                store[i][0] = store[i+1][0];
                store[i][1] = store[i+1][1];
            }
        }
        
        return store;
    }
    
    private int[] getDivisor(int e) {
        int[] divisor = new int[e+1];
        
        for (int i=1; i<=e; i++) {
            for (int j=1; j<=e/i; j++) {
                divisor[i*j] += 1;
            }
        }
        
        return divisor;
    }
}
