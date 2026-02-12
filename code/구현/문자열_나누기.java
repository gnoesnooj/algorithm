package 구현;

class 문자열_나누기 {
    public int solution(String s) {
        int answer = 0;

        char prev = 'A';
        int same = 0;
        int differ = 0;
        for (int i = 0; i < s.length(); i++) {
            if (prev == 'A') {
                prev = s.charAt(i);
                same++;
            } else {
                if (prev != s.charAt(i)) {
                    differ++;
                    if (same == differ) {
                        answer++;
                        prev = 'A';
                        same = 0;
                        differ = 0;
                    }
                } else if (prev == s.charAt(i)) {
                    same++;
                }
            }
        }
        if (same != 0) {
            answer++;
        }

        return answer;
    }
}