// 220131

// 처음에 stack 에 넣은 후, 이후 push 하기 전 peek를 통해서 값을 비교해서 같으면 pop, 다르다면 push를 한다.
// for 문 종료 후 스택이 비어있다면 1, 아니라면 0을 리턴한다.

import java.util.*;

class 짝지어제거하기
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0 ; i<s.length(); i++){
            char c = s.charAt(i);
            if(stack.empty() != true && stack.peek() == c){
                stack.pop();
            }
            else stack.push(c);
        }
        
        if(stack.empty())
            return 1;
        else
            return 0;
    }
}
