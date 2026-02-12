package 구현;

class 시저암호 {
    public String solution(String s, int n) {
        char[] arr = new char[s.length()];
        arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if ((int) arr[i] != 32) {
                int tmp = (int) arr[i] + n;
                if (91 <= tmp && (int) arr[i] <= 90) {
                    tmp = tmp - 26;
                } else if (123 <= tmp) {
                    tmp = tmp - 26;
                }
                arr[i] = (char) tmp;
            }
        }
        String answer = new String(arr);
        return answer;
    }
}