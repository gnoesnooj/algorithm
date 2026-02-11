package 순열조합;

public class 삼총사 {
    static int number = 0;
    static int[] people;
    static int answer = 0;

    public int solution(int[] numbers) {
        number = numbers.length;
        people = numbers;
        comb(0, 0, 0);

        return answer;
    }

    public void comb(int depth, int start, int sum) {
        if (depth == 3) {
            if (sum == 0) {
                answer += 1;
            }
        } else {
            for (int i = start; i < number; i++) {
                sum += people[i];
                comb(depth + 1, i + 1, sum);
                sum -= people[i];
            }
        }
    }
}
