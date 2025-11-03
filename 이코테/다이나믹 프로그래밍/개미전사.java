import java.util.*;

public class 개미전사 {

    public int solution(int n, int[] food) {
        int answer[] = new int[n];

        answer[0] = food[0];
        answer[1] = Math.max(food[0], food[1]);

        for (int i = 2; i < n; i++) {
            answer[i] = Math.max(answer[i - 2] + food[i], answer[i - 1]);
        }

        return answer[n - 1];
    }
}
