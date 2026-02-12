package 구현;

class 최소직사각형 {
    public int solution(int[][] sizes) {
        int tmp = 0;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                tmp = sizes[i][1];
                sizes[i][1] = sizes[i][0];
                sizes[i][0] = tmp;
            }
        }
        int maxLeft = sizes[0][0];
        int maxRight = sizes[0][1];
        for (int i = 0; i < sizes.length; i++) {
            if (maxLeft <= sizes[i][0]) {
                maxLeft = sizes[i][0];
            }
            if (maxRight <= sizes[i][1]) {
                maxRight = sizes[i][1];
            }
        }
        int answer = maxLeft * maxRight;
        return answer;
    }
}