import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<Homework> homeworks = new ArrayList<>();

        for (String[] plan : plans) {
            homeworks.add(new Homework(
                    plan[0],
                    convertTime(plan[1]),
                    Integer.parseInt(plan[2])
            ));
        }

        homeworks.sort(Comparator.comparingInt(h -> h.start));

        Stack<Homework> stack = new Stack<>();
        List<String> result = new ArrayList<>();

        int currentTime = homeworks.get(0).start;

        for (int i = 0; i < homeworks.size(); i++) {
            Homework current = homeworks.get(i);

            while (!stack.isEmpty() && currentTime < current.start) {
                Homework top = stack.pop();

                if (currentTime + top.playTime <= current.start) {
                    currentTime += top.playTime;
                    result.add(top.name);
                }
                else {
                    top.playTime -= (current.start - currentTime);
                    stack.push(top);
                    break;
                }
            }

            stack.push(new Homework(current.name, current.start, current.playTime));
            currentTime = current.start;
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }

        return result.toArray(new String[0]);
    }

    private int convertTime(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    static class Homework {
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