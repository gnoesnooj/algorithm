package DFS;/* DFS 1차 시도

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
}
// 1. 같은 점수 일때 가장 낮은 점수를 많이 맞힌 쪽이 이긴다라는 조건
// 2. 모든 로직을 마치고 나온 값이 0이고, 비겼을때
// 두 조건을 만족하지 못해 위의 로직에서 실패

*/

class 양궁대회 {
    int[] appeach;
    int[] answer = {-1};

    int answer_point = 0;

    public int[] solution(int n, int[] info) {
        appeach = info.clone();
        int[] lion = new int[11];
        dfs(0, 0, lion, n);
        dfs(0, appeach[0] + 1, lion, n);
        return usedAllArrows(answer, n);
    }

    void dfs(int depth, int value, int[] lion, int n) {
        lion[depth] = value;

        if (depth == lion.length - 1) {
            compare(lion, n);

        } else {
            dfs(depth + 1, 0, lion, n);
            dfs(depth + 1, appeach[depth + 1] + 1, lion, n);
        }
    }

    void compare(int[] lion, int n) {
        int lion_point = 0;
        int appeach_point = 0;
        for (int i = 0; i < 11; i++) {
            if(lion[i] == appeach[i]) continue;
            if (lion[i] <= appeach[i] && appeach[i] != 0) { // 어피치가 크거나 같고 0이 아닐때
                appeach_point += 10 - i;
            } else if (lion[i] > appeach[i]) {
                lion_point += 10 - i;
            }
        }
        if (lion_point - appeach_point > answer_point && getUsedArrows(lion, n)) {
            answer = lion.clone();
            answer_point = lion_point - appeach_point;
        }

        if(lion_point - appeach_point == answer_point && getUsedArrows(lion, n) && answer_point != 0){
            answer = getMinScored(answer,lion).clone();
        }
    }

    int [] getMinScored(int [] a, int [] b){
        for(int i = a.length-1; i >=0 ; i--) {
            if(a[i] - b[i] < 0) {
                return b;
            } else if(a[i] - b[i] > 0){
                return a;
            }
        }

        return a;
    }

    boolean getUsedArrows(int[] lion, int n) {
        int sum = 0;
        for (int i : lion) {
            sum += i;
        }
        if (sum > n) { // 너무 많은 화살 사용
            return false;
        } else {
            return true;
        }
    }

    int[] usedAllArrows(int[] lion, int n) {
        if(lion.length > 1) {
            int sum = 0;
            for (int i : lion) {
                sum += i;
            }
            if (sum < n) {
                lion[10] = n - sum;
            }
        }
        return lion;
    }
}
