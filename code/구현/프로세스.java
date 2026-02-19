package 구현;

import java.util.LinkedList;
import java.util.Queue;

class 프로세스 {
    static int max = 0;
    static boolean[] visited;

    public int solution(int[] priorities, int location) {
        int answer = 0;
        visited = new boolean[priorities.length];

        Queue<Integer> queue = new LinkedList<>();

        //queueing
        for (int number = 0; number < priorities.length; number++) {
            queue.add(number);
        }

        getMaxValue(priorities);
        // peek
        while (!queue.isEmpty()) {
            int number = queue.poll();
            if (priorities[number] < max) { // 실행이 안될때
                queue.add(number);
            } else { // 실행될때
                answer++;
                visited[number] = true;
                if (number == location) {
                    break;
                }
                getMaxValue(priorities);
            }
        }
        return answer;
    }

    private void getMaxValue(int[] values) {
        max = 0;
        for (int i = 0; i < values.length; i++) {
            if (!visited[i]) {
                max = Math.max(max, values[i]);
            }
        }
    }
}