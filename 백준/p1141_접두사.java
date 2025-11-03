import java.io.*;
import java.util.*;

public class p1141_접두사 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words);

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].startsWith(words[i])) {
                    words[i] = "";
                }
            }
        }

        int answer = 0;

        for(String word : words){
            if(!word.equals("")){
                answer++;
            }
        }
        System.out.println(answer);
    }
}
