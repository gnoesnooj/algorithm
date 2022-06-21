// 내가 풀었떤 방식은
// 1. Stirngbuilder 사용 X
// 2. u v 의 붙이는 순서에 대한 오해
// 이래서 해결에 실패했었다.
// 다시 풀어볼예정
// 아래의 코드는 check() 를 해주는 부분이 인상깊어서 ? 가져오게 되었다.

class Solution {
    public String solution(String p) {
        StringBuilder sb = new StringBuilder();
        
        if(p.length()==0) return sb.toString();
        
        String u=null;
        String v=null;
        int left=0;
        int right=0;
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i)=='(') left++;
            else if(p.charAt(i)==')') right++;
            if(left!=0 && left==right) {
                u=p.substring(0,i+1);
                v=p.substring(i+1);
                break;
            }
        }
        if(check(u)) {
            sb.append(u);
            sb.append(solution(v));
        }else {
            sb.append("(");
            sb.append(solution(v));
            sb.append(")");
            for(int i=1; i<u.length()-1;i++) {
                if(u.charAt(i)=='(') sb.append(")");
                if(u.charAt(i)==')') sb.append("(");
            }
        }
        return sb.toString();
    }

    private boolean check(String u) {
        int left=0;
        int right=0;
        for(int i=0; i<u.length(); i++) {
            if(u.charAt(i)=='(') left++;
            else if(u.charAt(i)==')') right++; 
            if(left<right) return false; // 한순간이라도 오른쪽 갯수가 많아지면 올바른게 아님.
        }
        return true;
    }
}
