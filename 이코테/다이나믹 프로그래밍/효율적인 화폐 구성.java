// 이거 푸는데 너무 오래걸림
// 접근하는게 어려웠음. SK ICT 였나 프로그래머스 백엔드코스 였나.. 그때 그문제랑 유사함
/*
import java.io.*;
import java.util.*;

public class Solution {
    public int solution(int num, int k, int [] money){
        int [] dp = new int [k+1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for(int i=0 ; i<num; i++){
            for(int j=money[i] ; j<=k; j++){
                dp[j] = Math.min(dp[j], dp[j - money[i]] +1);
            }
        }
        if(dp[k] == 10001)
            return -1;
        else
            return dp[k];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int num = 2;
        int k = 7;
        int [] money = {2,3};
        int answer = s.solution(num, k, money);
        System.out.println("answer = " + answer);
    }
}*/

import java.util.*;

public class Solution {

    public int[] solution(int[] money, int goal) {
        int dp[] = new int[goal + 1];

        Arrays.fill(dp, 10001);

        dp[0] = 0;
        for (int i = money[0]; i <= goal; i++) {
            for (int j : money) {
                if ( i - j >= 0 && dp[i - j] != 10001) {
                    dp[i] = Math.min(dp[i], dp[i - j] + 1);
                }
            }
        }
        return dp;
    }
}
