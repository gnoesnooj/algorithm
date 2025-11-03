import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 14172kb
 * 실행시간 88ms
 *
 * 1. 오름차순으로 들어온 알파벳들을 정렬 한다.
 * 2. 정렬된 알파벳 배열을 가지고 길이가 L인 순열들을 뽑는다.
 * 3. 자음 2개 이상, 모음 1개 이상인 조건을 맞으면 결과에 리턴해준다.
 * */
public class BJ_1759_암호만들기_예준성 {

    static int L, R;
    static char[] c;  // 문자를 담아놓을 배열
    static char[] tmp;
    static boolean[] visited;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs;
        inputs = br.readLine().split(" ");

        L = Integer.parseInt(inputs[0]);
        R = Integer.parseInt(inputs[1]); //R 개중 L개 뽑기

        c = new char[R]; // 6
        tmp = new char[L]; // 4

        inputs = br.readLine().split(" ");
        visited = new boolean[R];
        for (int i = 0; i < R; i++) {
            c[i] = inputs[i].charAt(0);
        }
        Arrays.sort(c); // 정렬해주기
        make(0,0);
        System.out.println(sb);
    }

    private static void make(int count, int start) {
        if (count == L) { // L개를 뽑았으면
            // 모음 1개 자음 2개 이상인지 확인,
            int jaum = 0;
            int moum = 0;
            for (int i = 0; i < tmp.length; i++) { // 자음과 모음 개수 세기
                if (tmp[i] == 'a' || tmp[i] == 'e' || tmp[i] == 'i' || tmp[i] == 'o' || tmp[i] == 'u') {
                    moum++;
                } else jaum++;
            }
            if (jaum >= 2 && moum >= 1) { // 조건에 맞으면
                for(int i= 0 ; i<tmp.length; i++){ // 결과에 넣어준다.
                    sb.append(tmp[i]);
                }sb.append("\n");
            }
        } else {
            for (int i = start; i < R; i++) { // R개만큼 돌리면
                tmp[count] = c[i]; // 문자열 만들기
                make(count + 1, i + 1);
            }
        }
    }
}