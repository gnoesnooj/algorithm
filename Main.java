import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] properties = {2, 1, 3, 2};
        int location = 2;
        Solution s = new Solution();
        int answer = s.solution(properties, location);

        System.out.println("answer : " + answer);
    }
}
