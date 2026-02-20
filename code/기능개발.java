// 220126
// 구현으로 그냥 짰는데, 제출하고나서 보니 개발 완료를 위한 day를 기억해뒀다가 다음 단계에서 day 만큼 speeds[i] 를 더해줬을 때, 100이
// 넘으면 completed ++ 을 해주는 방법으로 접근했으면 좀 더 코드가 간단했을 것 같다.

import java.util.*;

class 기능개발 {

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();

        int n = progresses.length;
        int prevDay = 0; // 앞 작업의 완료일
        int count = 0; // 함께 배포되는 작업 수

        for (int i = 0; i < n; i++) {
            // 현재 작업 완료까지 필요한 일수
            int day = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);

            if (day > prevDay) {
                // 새로운 배포 시작
                if (count > 0) {
                    result.add(count);
                }
                prevDay = day;
                count = 1;
            } else {
                // 이전 작업과 함께 배포
                count++;
            }
        }

        result.add(count);

        return result.stream().mapToInt(i -> i).toArray();
    }
}