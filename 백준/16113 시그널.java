import java.io.*;
import java.util.*;

/*
 * 1. N개를 5개로 나눈 x 값 찾기
 * 2. 2차원배열 [5][x]에 담기
 * 3. 숫자찾기 -> 세로 한줄씩 읽으며, if 이전열이 모두 빈칸이고 혹은 배열밖이고,
 *    현재열이 모두 빈칸이 아닐 때, 3열에 대해 숫자 판독 시작
 * 4. 숫자판독이후 3열 ++된 위치에서 재탐색 시작, 배열 밖이라면 종료
 * */
public class Main {

    static int N;

    static String answer = "";

    static int[][] signal;

    static Map<String, Integer> numbers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int x = N / 5;

        signal = new int[5][x];

        String[] input = br.readLine().split("");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < x; j++) {
                signal[i][j] = input[i * x + j].equals("#") ? 1 : 0;
            }
        }

        putMap();

        for (int i = 0; i < x; i++) { // 첫번째 열이 숫자로 시작하거나, 이전 열이 숫자가아니고 현재열이 숫자로 시작한다면
            if ((isRange(i - 1) || isAllZero(i - 1)) && !isAllZero(i)) {
                if (isOne(i)) {
                    answer += 1;
                    i += 1;
                } else {
                    String generated = genNumber(i);
//                    System.out.println(generated);
                    answer += numbers.get(generated);
                    i += 2;
                }
            }
        }

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < x; j++) {
//                System.out.print(signal[i][j]);
//            }
//            System.out.println();
//        }
        System.out.println(answer);

    }

    private static boolean isRange(int x) { // 배열 범위를 벗어나는가
        return x < 0;
    }

    private static boolean isAllZero(int index) { // 전부 빈칸이면 true, 숫자라면 false
        for (int i = 0; i < 5; i++) {
            if (signal[i][index] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOne(int index) {
        String generated = "";
        if(index == signal[0].length){
            if(!isAllZero(index)){
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if(index + j < N/5) {
                    generated += signal[i][index + j];
                }
            }
        }
        try {
            if (numbers.get(generated) == 1) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
//
    private static String genNumber(int index) {
        String generated = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                generated += signal[i][index + j];
            }
        }

        return generated;
    }

    private static void putMap() {
        numbers.put("111101101101111", 0);
        numbers.put("1010101010", 1);
        numbers.put("111001111100111", 2);
        numbers.put("111001111001111", 3);
        numbers.put("101101111001001", 4);
        numbers.put("111100111001111", 5);
        numbers.put("111100111101111", 6);
        numbers.put("111001001001001", 7);
        numbers.put("111101111101111", 8);
        numbers.put("111101111001111", 9);
    }
}
