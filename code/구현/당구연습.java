package 구현;

class 당구연습 {
    public int[] solution(int startX, int startY, int[][] balls, int m, int n) {
        int[] answer = new int [balls.length];
        for (int i = 0; i < balls.length; i++) {
            answer[i] = getMinDistance(startX, startY, m, n, balls[i][0], balls[i][1]);
        }
        return answer;
    }

    public int getMinDistance(int x, int y, int m, int n, int gx, int gy) {
        int up = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;

        if(!(y==gy && gx < x)) left = calcDistance((-1) * x, y, gx, gy);
        if(!(x==gx && y > gy)) down = calcDistance(x, (-1) * y, gx, gy);
        if(!(x==gx && y < gy)) up = calcDistance(x, 2 * m - y, gx, gy);
        if(!(y==gy && x < gx)) right = calcDistance(2 * n - x, y, gx, gy);

        return Math.min(Math.min(up, down), Math.min(right, left));
    }

    public int calcDistance(int startX, int startY, int goalX, int goalY) {
        int distanceX = (startX - goalX) * (startX - goalX);
        int distanceY = (startY - goalY) * (startY - goalY);
        return distanceX + distanceY;
    }
}
