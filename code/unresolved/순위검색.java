// 1차
/*
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {

    HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    int[] answer;

    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];

        makeMap(info);
        findByQuery(query);
        return answer;
    }

    // dfs 로 info에 대한 모든 상황만들고 Map에 넣기
    void makeMap(String[] info) {
        for (String str : info) {
            StringBuffer sb = new StringBuffer();
            String[] s = str.split(" ");
            dfs(s, 0, sb);
        }
    }

    // dfs
    void dfs(String[] str, int index, StringBuffer sb) {
        if (index == 4) {
            if (!map.containsKey(sb.toString())) {
                ArrayList<Integer> list = new ArrayList<>();
                System.out.println(sb.toString());
                map.put(sb.toString(), list);
                map.get(sb.toString()).add(Integer.parseInt(str[4]));
            }
        } else {
            dfs(str, index + 1, sb.append(str[index]));
            dfs(str, index + 1, sb.append("-"));
        }
    }

    void findByQuery(String[] query) {
        int cnt = 0;
        for (String q : query) {
            StringBuffer sb = new StringBuffer();
            q.replaceAll(" and ", " ");
            String[] s = q.split(" ");
            for (int i = 0; i < s.length - 1; i++) {
                sb.append(s[i]);
                //System.out.println(s[i]);
            }
            ArrayList<Integer> list = map.get(sb.toString());
            Collections.sort(list);
            int result = binarySearch(Integer.parseInt(s[4]), 0, list.size(), list);
            answer[cnt] = result;
            cnt++;
        }
    }

    // query로 검색 후 조건에 맞는 점수에 따라 이분탐색하기
    int binarySearch(int goal, int start, int end, ArrayList<Integer> list) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) <= goal) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210",
                "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
                "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250", "- and backend and senior and - 150",
                "- and - and - and chicken 100", "- and - and - and - 150"};
        Solution s = new Solution();
        int[] result = s.solution(info, query);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
*/
