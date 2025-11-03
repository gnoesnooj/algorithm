import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 메모리 70660kb
 * 시간	472ms
 *
 * KMP 알고리즘 사용
 */
public class BJ_1786_찾기_예준성 {

    static String parent;
    static String part;

    static int[] table;

    static int count;

    static List<Integer> numbers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 찾을 곳
        parent = br.readLine();

        // 찾을 녀석
        part = br.readLine();

        // 테이블 만들기
        makeTable(part); // 부분 문자열 입력받고 테이블 만들기

        find();

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        for(int i:numbers){
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    // 테이블 만들고
    public static void makeTable(String part_str) {
        int length = part_str.length();
        table = new int[length];

        int index = 0;
        for (int i = 1; i < length; i++) {
            while (index > 0 && part_str.charAt(i) != part_str.charAt(index)) {
                index = table[index - 1];
            }

            if (part_str.charAt(i) == part_str.charAt(index)) {
                index += 1;
                table[i] = index;
            }
        }
    }

    // 찾기
    // 다찾으면 다찾은거로 인덱스 바꿔주기
    public static void find() {
        int part_length = part.length();
        int parent_length = parent.length();

        int index = 0;

        for (int i = 0; i < parent_length; i++) {
            while (index > 0 && parent.charAt(i) != part.charAt(index)) {
                index = table[index - 1];
            }

            if (parent.charAt(i) == part.charAt(index)) {
                if (index == part_length - 1) {
                    count++;
                    numbers.add((i + 1) - (part_length - 1));
                    index = table[index];
                } else {
                    index++;
                }
            }
        }
    }
}