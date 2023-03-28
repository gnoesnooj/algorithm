// DFS 시도

class Solution {
    int[] answer;
    int answer_points = 0;

    public int[] solution(int n, int[] info) {
        answer = new int[info.length]; // 라이언이 쏴야할 점수를 담은 화살 개수
        int[] lion = new int[info.length];

        dfs(0, n, lion, info, 0);
        dfs(0, n - (info[0] + 1), lion, info, info[0] + 1);

        return result();
    }

    public void dfs(int depth, int arrow, int[] lion, int[] info, int value) {
        if (depth >= lion.length) {
            compare(lion, info);
        } else if (arrow <= 0) {
            for (int i = depth; i < lion.length; i++) {
                lion[i] = 0;
            }
            compare(lion, info);
        } else {
            lion[depth] = value;
            dfs(depth + 1, arrow, lion, info, 0);
            if (arrow - (info[depth] + 1) >= 0) {
                dfs(depth + 1, arrow - (info[depth] + 1), lion, info, info[depth] + 1);
            }
        }
    }

    public void compare(int[] lion, int[] info) {
        int lion_point = 0;
        int info_point = 0;
        for (int i = 0; i < lion.length; i++) {
            if (info[i] >= lion[i] && info[i] != 0) { // 어피치의 화살개수가 라이언의 화살보다 같거나 많을 경우
                info_point += 10-i;
            } else {
                lion_point += 10-i;
            }
        }

        if (lion_point - info_point > answer_points) { // 새로운 배열의 점수 차이가 현재 점수보다 클 경우
            for (int i = 0; i < lion.length; i++) {
                answer[i] = lion[i];
            }
            answer_points = lion_point - info_point;
        }
    }

    public int[] result() {
        if (answer_points == 0) {
            int [] tmp = {-1};
            return tmp;
        } else {
            return answer;
        }
    }

    public static void main(String[] args) {
        int[] appeach = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int n = 5;
        Solution s = new Solution();
        int[] result = s.solution(n, appeach);

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
