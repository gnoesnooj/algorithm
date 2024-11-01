import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] inputs = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        int answer = 0;
        int value = 1;
        for(int i=0; i<inputs.length; i++){
            if(inputs[i] == '('){
                stack.add(inputs[i]);
                value *= 2;
            }
            else if(inputs[i] == '['){
                stack.add(inputs[i]);
                value *= 3;
            }
            else if(inputs[i] == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    answer = 0;
                    break;
                }
                if(inputs[i-1] == '('){
                    answer += value;
                }
                value /= 2;
            }
            else if(inputs[i] == ']'){
                if(stack.isEmpty() || stack.pop() != '['){
                    answer = 0;
                    break;
                }
                if(inputs[i-1] == '['){
                    answer += value;
                }
                value /= 3;
            }
        }
        if(!stack.isEmpty()){
            answer = 0;
        }
        System.out.println(answer);
    }
}
