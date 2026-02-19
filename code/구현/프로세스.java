package 구현;

import java.util.LinkedList;
import java.util.Queue;

class 프로세스 {

    static class Document {
        int index;
        int priority;

        Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public int solution(int[] priorities, int location) {
        Queue<Document> queue = new LinkedList<>();

        // 큐 초기화
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Document(i, priorities[i]));
        }

        int answer = 0;

        while (!queue.isEmpty()) {
            Document current = queue.poll();

            if (hasHigherPriority(queue, current.priority)) {
                queue.offer(current);
            } else {
                answer++;
                if (current.index == location) {
                    return answer;
                }
            }
        }

        return answer;
    }

    private boolean hasHigherPriority(Queue<Document> queue, int priority) {
        for (Document doc : queue) {
            if (doc.priority > priority) {
                return true;
            }
        }
        return false;
    }
}
