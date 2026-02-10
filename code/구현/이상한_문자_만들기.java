package 구현;

class 이상한_문자_만들기 {
    public String solution(String s) {

        StringBuffer sb = new StringBuffer();

        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((int) s.charAt(i) == 32) {
                cnt = 0;
                sb.append(s.charAt(i));
            } else {
                if (cnt % 2 == 0) {
                    String str = Character.toString(s.charAt(i)).toUpperCase();
                    sb.append(str);
                } else {
                    String str = Character.toString(s.charAt(i)).toLowerCase();
                    sb.append(str);
                }
                cnt++;
            }
        }
        String answer = sb.toString();

        return answer;
    }
}
