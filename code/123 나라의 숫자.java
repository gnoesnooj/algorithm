// 220125

class Solution {
    public String solution(int n) {
        StringBuffer sb = new StringBuffer();

        while(n>0){
            if(n == 0) break;
            else if (n<=1 && n<3) {
                sb.append(1);
                break;
            }
            else{
                if(n%3 == 0){
                    sb.append(4);
                    n = (n/3) - 1;
                }
                else if(n%3 == 1){
                    sb.append(1);
                    n = n/3;
                }
                else{
                    sb.append(2);
                    n = n/3;
                }
            }
        }
        String answer = sb.reverse().toString();
    
        return answer;
    }
}
