import java.io.*;
import java.util.*;

public class 2933_미네랄 {
    static List<String> list = new LinkedList<>();

    static int N, M;

    static String top;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);

        M = Integer.parseInt(inputs[1]);

        // 위에서 채워줄 타일 만들기
        top = "";
        for (int i = 0; i < M; i++) {
            top += '.';
        }

        // 미네랄 입력
        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }
        br.readLine();
        // 던지는 위치 입력
        inputs = br.readLine().split(" ");

        for (int i = 0; i < inputs.length; i++) {
            if (i % 2 == 0) {
                throwFromLeft(Integer.parseInt(inputs[i]));
            } else {
                throwFromRight(Integer.parseInt(inputs[i]));
            }
        }

        for(String mineral : list){
            System.out.println(mineral);
        }
    }

    private static void throwFromLeft(int index) {
        char[] mineral = list.get(index - 1).toCharArray();
        for (int i = 0; i < mineral.length; i++) {
            if (mineral[i] == 'x') {
                mineral[i] = '.';
                break;
            }
        }
        boolean flag = true;
        for (int i = 0; i < mineral.length; i++) {
            if (mineral[i] == 'x') {
                flag = false;
                break;
            }
        }
        list.remove(index - 1);
        if (flag) {
            list.add(0, top);
        } else {
            list.add(index - 1, mineral.toString());
        }
    }

    private static void throwFromRight(int index) {
        char[] mineral = list.get(index - 1).toCharArray();
        for (int i = mineral.length - 1; i >= 0; i--) {
            if (mineral[i] == 'x') {
                mineral[i] = '.';
                break;
            }
        }
        boolean flag = true;
        for (int i = mineral.length - 1; i >= 0; i--) {
            if (mineral[i] == 'x') {
                flag = false;
                break;
            }
        }
        list.remove(index - 1);
        if (flag) {
            list.add(0, top);
        } else {
            String newMineral="";
            for(char c : mineral){
                newMineral+=c;
            }
            list.add(index - 1, newMineral);
        }
    }
}
