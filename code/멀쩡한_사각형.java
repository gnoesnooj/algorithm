// 220124
// 주어지는 W와 H를 W/H 기울기로 생각한 후, 기울기 아래 정상적인 사각형을 구한 후 X2
// y = H/W x 라고 생각하면, x를 0 ~ W-1 까지 대입했을 때 나오는 정수부분이 멀쩡한 사각형의 갯수이다.

class 멀쩡한_사각형 {
    
    public long solution(int w, int h) {
        long answer =0;
        for(long x=1  ; x<w ; x++){
            long tmp = (long)((h*x)/w);
            answer += tmp;
        }
        return answer*2;
    }
}
