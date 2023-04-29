/*
* 5인 경우 무조건 내려가는 것으로 선택하여 풀이를 하였는데
* 95인 경우는 올리는게 좋고, 15인 경우에는 버리는게 좋다.
* 따라서 이에 대한 경우의 수를 생각하여 다시 작성해야 한다.*/
/*
class Solution {
    int answer = 0;

    public int solution(int storey) {
        calc(0, storey);
        return answer;
    }

    public void calc(int depth, int storey) {
        int length = Integer.toString(storey).length();
        int number = Integer.parseInt(String.valueOf(Integer.toString(storey).charAt(length - 1 - depth)));
        int new_storey = 0;

        if (number <= 5) { // 5에 대한 예외가 발생하는 경우
            answer += number;
            if(depth +1 < length)
                calc(depth +1, storey);
        } else if (number > 5) {
            answer += 10 - number;
            int index = (int) Math.pow(10, depth) * (10 - number);
            new_storey = storey + index;
        }
        if (depth +1 < Integer.toString(new_storey).length()) {
            calc(depth + 1, new_storey);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(195));
    }
}
*/

class Solution {
    int answer = 0;

    public int solution(int storey) {
        calc(0, storey);
        return answer;
    }

    public void calc(int depth, int storey) {
        int length = Integer.toString(storey).length();
        int number = Integer.parseInt(String.valueOf(Integer.toString(storey).charAt(length - 1 - depth)));
        int new_storey = 0;

        if (number < 5) { // 5에 대한 예외가 발생하는 경우
            answer += number;
            if (depth + 1 < length) {
                calc(depth + 1, storey);
            }
        } else if (number > 5) {
            answer += 10 - number;
            int index = (int) Math.pow(10, depth) * (10 - number);
            new_storey = storey + index;
        } else if (number == 5) { // 5에 대한 예외 추가
            if (depth +1 >= length){ // 현재 위치가 마지막 수인 경우
                answer += number;
            } else { // 마지막 수가 아닐 때
                int number2 = Integer.parseInt(String.valueOf(Integer.toString(storey).charAt(length - 2 - depth)));
                if(number2 <= 5){

                    answer += number;
                    if (depth + 1 < length) {
                        calc(depth + 1, storey);
                    }
                } else {
                    answer += 10 - number;
                    int index = (int) Math.pow(10, depth) * (10 - number);
                    new_storey = storey + index;
                }
            }
        }
        if (depth + 1 < Integer.toString(new_storey).length()) {
            calc(depth + 1, new_storey);
        }
    }
}
