/*
* dfs 로 풀이
* dfs 내에서 for문을 통해서 문자열을 나타내는 int 정수배열을 만들고
* 그러고나서 조건 메소드를 통해서 참 거짓을 판단한 후 answer++를 해주고 리턴해주는 방식이었는데
* 도저히 몰라서 찾아본 풀이방법에서
* 문자열을 만든 배열에서, 나는 대부분 배열의 인덱스가 위치, 배열의 값이 해당 문자열이라고 생각하고, 약간 고정관념처럼 틀에 박혀서 생각했는데
* 내가본 방식은 인덱스가 문자열, 배열의 값이 위치를 나타내는 방식이었다.
* 프레임을 깨버린거같아서 되게 신선하고 충격적? 으로 다가왔던 것 같다.
*/
import java.util.*;

class Solution {
    static String[] d;
    static HashMap<Character,Integer> map ;
    static boolean[] visited;
    static int[] ch;
    static int answer;
    
    public int solution(int n, String[] data) {
        d = data;
        map = new HashMap<>();
        visited = new boolean[8];
        ch = new int[8];
        answer = 0;
        map.put('A',0);
        map.put('C',1);
        map.put('F',2);
        map.put('J',3);
        map.put('M',4);
        map.put('N',5);
        map.put('R',6);
        map.put('T',7);
        dfs(0);
        return answer;
    }
    
    public static void dfs(int idx){
        if(idx == 8){
            if(check()) answer++;
        }
        else{
            for(int i=0;i<8;i++){
                if(!visited[i]){
                    visited[i] = true;
                    ch[idx] = i;
                    dfs(idx + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
    public static boolean check(){
        int a,b,res;
        char op;
        for(String s : d){
            a = ch[map.get(s.charAt(0))];
            b = ch[map.get(s.charAt(2))];
            op = s.charAt(3);
            res = s.charAt(4)-'0' + 1;
            
            if(op == '='){ if(Math.abs(a-b)!=res) return false;}
            else if(op == '>'){ if(Math.abs(a-b) <= res) return false;}
            else {if(Math.abs(a-b) >= res) return false;}
        }
        return true;
    }
}
