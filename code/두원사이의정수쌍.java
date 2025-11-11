class 두원사이의정수쌍 {
    public long solution(int r1, int r2) {
        long answer = 0;
        for (int x = 0; x <= r2; x++) {
            for (int y = 0; y <= r2; y++) {
                if (x + y >= r1 && x + y <= r1 + r2) {
                    if (x * x + y * y >= r1 * r1 && x * x + y * y <= r2 * r2) {
                        if (x == 0 || y == 0) {
                            answer += 2;
                        } else {
                            answer += 4;
                        }
                    }
                }
            }
        }
        return answer;
    }
}
