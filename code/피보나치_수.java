/*
재귀로 처음에 풀었는데 시간초과가 계속 발생했다.

1. long 타입으로도 10만번의 피보나치의 수는 담아낼 수 없다.
2. 10만번이상의 재귀로 인해 시간초과가 발생한다.
3. (A + B) % C = ( (A % C) + (B % C) ) % C 의 성질을 이용한다.

*/

class 피보나치_수 {
    public int solution(int n) {
        int fibo[] = new int [n+1];
        fibo[0] = 0;
        fibo[1] = 1;
        
        for(int i=2 ; i <= n ; i++){
            fibo[i] = (fibo[i-2] + fibo[i-1]) % 1234567;
        }
        return fibo[n];
    }
}
