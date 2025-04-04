import java.util.*;

public class LargeK_ABNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        long K = sc.nextLong(); // K가 최대 10^18

        System.out.println(findKthNumber(a, b, K));
    }

    static String findKthNumber(int a, int b, long K) {
        StringBuilder sb = new StringBuilder();
        String binary = Long.toBinaryString(K); // K를 이진수로 변환

        for (int i = 0; i < binary.length(); i++) {
            sb.append(binary.charAt(i) == '0' ? a : b);
        }
        return sb.toString();
    }
}
