import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String [] keymap = {"AA"};
        String [] targets = {"BA"};
        Solution s = new Solution();
        int [] answer = s.solution(keymap,targets);
        for(int a : answer){
            System.out.println(a);
        }
    }
}
