import java.io.*;
import java.util.*;

public class p9935_문자열_폭발 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String words = br.readLine();

        String bomb = br.readLine();

        Stack<Character> s = new Stack<>();

        StringBuilder sb;

        for (int i = 0; i < words.length(); i++) {
            s.push(words.charAt(i));
            if (s.size() >= bomb.length()) {
                boolean isBomb = true;
                for(int j=0;j<bomb.length();j++){
                    if(s.get(s.size()-bomb.length()+j) != bomb.charAt(j)){
                        isBomb = false;
                    }
                }
                if(isBomb){
                    for(int j=0;j<bomb.length();j++){
                        s.pop();
                    }
                }
            }
        }

        sb = new StringBuilder();
        for (int i = 0; i < s.size(); i++) {
            sb.append(s.get(i));
        }
        System.out.println(sb.toString().equals("") ? "FRULA" : sb);
    }
}
