import java.util.Arrays;

class Solution { // 학생들의 학점을 하나의 문자열로 만들어서 return

    public String solution(int [][] scores){
        String answer= "";
        for(int i=0 ; i<scores.length; i++){
            answer += solution2(i,scores);
        }
        return answer;
    }

    public String solution2(int index, int[][] scores) {
        int max = 0;
        int min = 200;
        int sum = 0;
        double avg = 0.0;
        for (int i = 0; i < scores[index].length; i++) {
            max = Math.max(max, scores[i][index]); // 최고점 찾기

            min = Math.min(min, scores[i][index]); // 최저점 찾기

            sum += scores[i][index]; // 모든 점수 더해주기
        }
        if (max == scores[index][index] && isUnique(index, max, scores)) { // 자기자신이 최고점이고 유일하다면
            sum -= max;
            avg = sum / (scores.length - 1);
        } else if (min == scores[index][index] && isUnique(index, min, scores)) { // 자기 자신이 최저점이고 유일하다면
            sum -= min;
            avg = sum / (scores.length - 1);
        } else {
            avg = sum / scores.length;
        }
        String grade = getGrade(avg);

        return grade;
    }

    public boolean isUnique(int index, int value, int[][] scores) {
        int cnt = 0;
        for (int i = 0; i < scores[index].length; i++) {
            if (scores[i][index] == value) {
                cnt++;
            }
        }
        return cnt == 1;
    }

    private String getGrade(double avg) {
        if (avg < 50) {
            return "F";
        } else if (50 <= avg && avg < 70) {
            return "D";
        } else if (70 <= avg && avg < 80) {
            return "C";
        } else if (80 <= avg && avg < 90) {
            return "B";
        } else if (90 <= avg) {
            return "A";
        }
        return "";
    }

}
