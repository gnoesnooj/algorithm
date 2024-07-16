import java.io.*;
import java.util.*;

class Solution {
    // RR, Rr, rr

    static Map<String, Integer> forms = new HashMap<>();

    static String[][] childs = new String[][]{
            {"RR", "RR", "RR", "RR"},
            {"RR", "Rr", "Rr", "rr"},
            {"rr", "rr", "rr", "rr"}
    };

    static String[] answer;

    public String[] solution(int[][] queries) {
        forms.put("RR", 0);
        forms.put("Rr", 1);
        forms.put("rr", 2);

        answer = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            answer[i] = find(queries[i][0], queries[i][1]);
        }
        return answer;
    }

    private static String find(int generation, int index) {
        if (generation == 1) {
            return "Rr";
        } else {
            int parentIndex = (index - 1) / 4;
            int myIndex = (index - 1) % 4;
//            System.out.println(childs[forms.get(find(generation - 1, parentIndex+1))][myIndex]);
            return childs[forms.get(find(generation - 1, parentIndex+1))][myIndex];
        }
    }

}
