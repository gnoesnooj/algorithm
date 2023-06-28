import java.util.*;

public class Solution {

    public int solution(int n, int[] food) {
        int answer[] = new int[n];

        answer[0] = food[0];
        answer[1] = Math.max(food[0], food[1]);

        for (int i = 2; i < n; i++) {
            answer[i] = Math.max(answer[i - 2] + food[i], answer[i - 1]);
        }

        return answer[n - 1];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] food = {1, 3, 1, 5, 10};

        int answer = s.solution(5, food);
        System.out.println(answer);
    }
}
