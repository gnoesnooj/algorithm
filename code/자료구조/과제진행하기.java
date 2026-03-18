package 자료구조;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

class 과제진행하기 {
    public String[] solution(String[][] plans) {
        List<Homework> homeworks = new ArrayList<>();
        for (String[] plan : plans) {
            homeworks.add(new Homework(plan[0], convertTime(plan[1]), Integer.parseInt(plan[2])));
        }
        homeworks.sort(Comparator.comparingInt(h -> h.start));

        Stack<Homework> s = new Stack<>();
        List<String> endingList = new ArrayList<>();
        int index = 0;
        s.add(homeworks.get(index++));
        while (true) {
            if (s.isEmpty()) {
                if (index >= homeworks.size()) {
                    break;
                } else {
                    s.add(homeworks.get(index++));
                }
            } else {
                if (index >= homeworks.size()) {
                    endingList.add(s.pop().name);
                } else {
                    Homework peek = s.peek();
                    Homework next = homeworks.get(index);
                    if (peek.start + peek.playTime <= next.start) { // 끝났어야함
                        endingList.add(s.pop().name);
                    } else if (peek.start + peek.playTime > next.start) {
                        s.add(next);
                        index++;
                    }
                }
            }
        }

        String[] answer = new String[endingList.size()];
        for (int i = 0; i < endingList.size(); i++) {
            answer[i] = endingList.get(i);
        }
        return answer;
    }

    private int convertTime(String stringTime) {
        String[] splitted = stringTime.split(":");
        int hour = 60 * Integer.valueOf(splitted[0]);
        int minute = Integer.valueOf(splitted[1]);
        return hour + minute;
    }

    class Homework {
        String name;
        int start;
        int playTime;

        Homework(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
    }
}