package 구현;

public class 크기가_작은_부분문자열 {
    static long number = 0;
    static int answer = 0;

    public int solution(String t, String p) {

        number = Long.parseLong(p); // 비교할 p
        int length = p.length(); // p 길이

        String tmp = "";

        for (int i = 0; i <= t.length() - length; i++) {
            tmp = t.substring(i, i + length);
            if (Long.parseLong(tmp) <= number) {
                answer++;
            }
        }
        return answer;
    }
}
