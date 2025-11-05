import java.util.*;
import java.util.stream.Collectors;

class 봉인된주문 {
    static String alpha = "abcedfghijklmnopqrstuvwxyz";

    public String solution(long n, String[] bans) {
        String answer = "";
        // bans 오름차정렬
        List<String> words = Arrays.stream(bans).collect(Collectors.toList());
        words.sort(Comparator.comparing(String::length)
                .thenComparing(Comparator.naturalOrder()));

        String tmp = findWord(n);

        int deleteCount = 0;
        for (String s : words) {
//            System.out.println("S :" +s +"// tmp : "+tmp );
            if (s.length() < tmp.length() || s.length() == tmp.length() && s.compareTo(tmp) <= 0) {
                deleteCount += 1;
                tmp = findWord(n + deleteCount);
            }
        }
//        System.out.println(deleteCount);

        return tmp;
    }

    public String findWord(long n) {
        Stack<Integer> index = new Stack<>();
        long tmp = n;
        while (true) {
            index.add((int) tmp % 26);
            tmp = tmp / 26;
            if (tmp == 0) {
                break;
            }
        }
        StringBuilder word = new StringBuilder();
        while (index.size() > 0) {
//            System.out.println("num :"+index.peek());
            word.append(alpha.charAt(index.pop()-1));
        }
        return word.toString();
    }
}