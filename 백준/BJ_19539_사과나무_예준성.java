import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 1뿌리개랑 2뿌리개 둘다 동시에 사용해야 하므로, 모든 수의 합은 3의 배수여야한다.
 * 2. 1 3 1 3 1 과 같이, 1을 해결하려면 1뿌리개를 사용해야 하는데,
 *    그러려면 1뿌리개만큼 횟수를 다른 곳에서 2뿌리개 횟수를 가지고 있어야함.
 * 3. 위의 두 조건을 만족하면 답이 될 듯 하다
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 사과나무 개수

        String [] inputs = br.readLine().split(" ");

        int[] trees = new int[inputs.length];

        int sum = 0;
        int one_count = 0;
        int two_count = 0;
        for (int i = 0; i < inputs.length; i++) {
            trees[i] = Integer.parseInt(inputs[i]);

            sum += trees[i];
            if (trees[i] % 2 == 1) {
                one_count++;
            }
            two_count += trees[i] / 2;
        }
        if (sum % 3 == 0 && two_count >= one_count) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
