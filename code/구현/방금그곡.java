// 구현
// 브루트포스
// 조건에 맞게 구현
// str = str.replaceAll("바뀌기전","바뀌기후") 처럼 사용 안한 실수
// 메소드 구현 후 제대로 사용 안한 실수
// splitInfo를 더 분리할 수 있을 것 같다.

import java.util.ArrayList;
import java.util.List;

class Solution {
    static String answer;
    static int playedTime;

    public String solution(String m, String[] musicinfos) {

        answer = "";
        playedTime = 0;

        for(String s : musicinfos){
            splitInfo(m,s);
        }
        if(answer.equals(""))
            return "(None)";

        return answer;
    }

    private void splitInfo(String m, String musicInfo) { // ex 12:00,12:14,HELLO,CDEFGAB
        String[] info = musicInfo.split(","); // 0 - 12:00 , 1- 12:14, 2-HELLO, 3-CDEFGAB
        String[] startTime = info[0].split(":"); // 0 - 12, 1- 00
        String[] endTime = info[1].split(":"); // 0 - 12, 1 - 14

        int repeated = getRepeatTime(getHour(startTime[0], endTime[0]),
                getMinute(startTime[1], endTime[1])); // 재생된 시간의 길이

        String transed = transShop(info[3]); // # 붙은거 떼서 넣어주기

        String playedMusic = "";

        while(playedMusic.length() < repeated){
            playedMusic += transed;
        }

        playedMusic = playedMusic.substring(0,repeated);
        playedMusic = transShop(playedMusic);
        m = transShop(m);

        if(playedMusic.contains(m)){
            if(answer.equals("")) {
                answer = info[2];
                playedTime = repeated;
            } else if(!answer.equals("") && playedTime < repeated){
                answer = info[2];
                playedTime = repeated;
            }
        }
    }

    private int getRepeatTime(int hour, int minute) {
        return hour * 60 + minute;
    }

    private int getHour(String start, String end) {
        int hour = Integer.parseInt(end) - Integer.parseInt(start);
        if (hour < 0) {
            return hour + 24;
        }
        return hour;
    }

    private int getMinute(String start, String end) {
        int minute = Integer.parseInt(end) - Integer.parseInt(start);
        return minute;
    }

    private String transShop(String str){
        str = str.replaceAll("A#","H");
        str = str.replaceAll("C#","J");
        str = str.replaceAll("D#","K");
        str = str.replaceAll("F#","M");
        str = str.replaceAll("G#","N");

        return str;
    }

    public static void main(String[] args) {
        String m = 	"ABC";
        String [] info = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        Solution s = new Solution();
        String answer = s.solution(m,info);

        System.out.println(answer);
    }
}
