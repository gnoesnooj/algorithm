package 구현;


import java.util.HashSet;
import java.util.Set;

class 둘만의_암호{
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();

        Set<Character> skipSet = new HashSet<>();
        for (char c : skip.toCharArray()) {
            skipSet.add(c);
        }

        for (char c : s.toCharArray()) {
            int count = 0;

            while (count < index) {
                c = (char) ('a' + (c - 'a' + 1) % 26);
                if (!skipSet.contains(c)) {
                    count++;
                }
            }

            answer.append(c);
        }

        return answer.toString();
    }
}