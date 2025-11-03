package 구현;
// 시간 관련
// 시간을 hour * 60 + minute + 필요한 만큼 더하는 방법으로 접근
// 기존 분이 시로 넘어가면 +1 해주는게 번거롭기도하고, 코드가 지저분해지기도 했는데, 좋은 접근 방법을 알게 되었다.

// 또한 누적합에 대해 알게되었다.

class 호텔_대실 {

    int[] answers = new int[1450];
    int answer = 0;

    public int solution(String[][] book_time) {
        for (String[] s : book_time) {
            getTime(s);
        }
        return answer;
    }

    public void getTime(String[] time) {
        String[] startTime = time[0].split(":");
        int calcStartTime = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
        String[] endTime = time[1].split(":");
        int calcEndTime = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]) + 10;

        for (int i = calcStartTime; i < calcEndTime; i++) {
            answers[i]++;
            answer = Math.max(answer, answers[i]);
        }
    }
}
