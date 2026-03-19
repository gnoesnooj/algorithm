package 자료구조;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class 이중우선순위큐 {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        List<Integer> numbers = new ArrayList<>();

        for(String operation : operations){
            String [] ops = operation.split(" ");
            if(ops[0].equals("I")){
                numbers.add(Integer.valueOf(ops[1]));
                minPQ.add(Integer.valueOf(ops[1]));
                maxPQ.add(Integer.valueOf(ops[1]));
            } else {
                if(ops[1].charAt(0) == '1' && !numbers.isEmpty()){ // 최댓값
                    int maxValue = maxPQ.poll();
                    numbers.remove(Integer.valueOf(maxValue));
                    minPQ.remove(Integer.valueOf(maxValue));
                } else if(ops[1].charAt(0) != '1' && !numbers.isEmpty()){
                    int minValue = minPQ.poll();
                    numbers.remove(Integer.valueOf(minValue));
                    maxPQ.remove(Integer.valueOf(minValue));
                }
            }
        }

        if(numbers.isEmpty()){
            return new int[]{0,0};
        } else {
            return new int[]{maxPQ.poll(), minPQ.poll()};
        }
    }
}