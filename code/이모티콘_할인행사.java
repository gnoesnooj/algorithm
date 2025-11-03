// DFS
// 모든경우의 수를 계산했을 때, 최대 1억 이하면 시도해볼만 하다는 것을 알게되었다 !
// 중복이 가능한 경우의수를 찾는 문제였다.
// 필요한 경우의 수를 담는 배열을 인자로 삼고, 재귀를 통해서 해당 배열에 필요한 것을 담아주는 방식을 택했다.
// 다시 풀어보는 게 좋을듯 하다.

class 이모티콘_할인행사 {

    int[] sale = {10, 20, 30, 40};

    int[] answer = {0, 0};

    public int[] solution(int[][] users, int[] emoticons) {

        dfs(users,emoticons,0,new int[emoticons.length]);

        return answer;
    }

    public void dfs(int[][] users, int[] emoticons, int depth, int[] rates) {
        // 할인율 배정
        // depth가 emoticon 개수만큼 진행이 되었따면, dfs 진행을 멈추고 값을 계산하여 비교해야한다.
        if (depth == emoticons.length) {
            int[] result = calculate(users, emoticons, rates);
            getBestAnswer(result);
        } else {
            for (int rate : sale) {
                rates[depth] = rate;
                dfs(users, emoticons, depth + 1, rates);
            }
        }
    }

    public int[] calculate(int[][] users, int[] emoticons, int[] rates) {
        int price = 0;
        int plus = 0;
        for (int[] user : users) {
            int sum = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (user[0] <= rates[i]) {
                    int a = emoticons[i] * (100 - rates[i]) / 100;
                    sum += a;
                }
            }
            if (sum >= user[1]) {
                plus++;
            } else {
                price += sum;
            }
        }
        int[] result = {plus, price};
        return result;
    }

    public void getBestAnswer(int[] result) {
        if (answer[0] < result[0]) { // 새로운 값이 더 크면
            answer[0] = result[0];
            answer[1] = result[1];
        }
        if (answer[0] == result[0]) {
            answer[1] = Math.max(answer[1], result[1]);
        }
    }
}
