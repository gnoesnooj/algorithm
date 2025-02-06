import java.util.*;
import java.io.*;

public class p9177_단어섞기 {
    static int indexA, indexB;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= TC; tc++) {
            String[] input = br.readLine().split(" ");
            String wordA = input[0];
            String wordB = input[1];
            String mixed = input[2];

            indexA = 0;
            indexB = 0;
            for (int i = 0; i < mixed.length(); i++) {
                if (indexA < wordA.length() && wordA.charAt(indexA) == mixed.charAt(i)) {
                    indexA += 1;
                }
                if (indexB < wordB.length() && wordB.charAt(indexB) == mixed.charAt(i)) {
                    indexB += 1;
                }
            }

            if (wordA.length() == indexA && wordB.length() == indexB) {
                sb.append("Data set " + tc + ": " + "yes\n");
            } else {
                sb.append("Data set " + tc + ": " + "no\n");

            }
        }

        System.out.println(sb);
    }
}

