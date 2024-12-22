import java.util.*;
import java.io.*;

public class p1655_가운데를말해요 {

    static int N;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        // 내림차
        PriorityQueue<Integer> down = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });

        // 오름차
        PriorityQueue<Integer> up = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();

            if(up.size() >= down.size()){
                down.add(num); // 내림차순에 먼저 들어가도록
            } else {
                up.add(num);
            }

            if(!up.isEmpty() && !down.isEmpty()){
                if(down.peek() > up.peek()){
                    int tmpValue = down.poll();
                    down.offer(up.poll());
                    up.offer(tmpValue);
                }
            }
            sb.append(down.peek()+"\n");
        }
        System.out.println(sb);

    }

}
