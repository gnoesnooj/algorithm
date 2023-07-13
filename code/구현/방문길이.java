/*
 * 처음 생각
 * - 1. 도착 지점 좌표 + 들어온 방향을 String 화한 후, 이를 set에 저장
 * - 2. set 길이 리턴
 * - 3. 탐색은 dirs 의 길이만큼 for 진행
 * # 유효성 검사
 * - 도착 지점의 좌표가 범위를 만족하는지
 *
 * 다시 생각
 * * 문제점 ) 10L과 00D가 동일한 거리인데, 잡아내지 못함
 * start + end String, end + start String 각각 생성 후 set 에 add
 * 이후 return set.size() / 2; (같은거리에 대해 2번 카운팅했으므로)
 *
 * 코드가 너무 길어서 짜증나서 다시 생각
 * U, R 의 경우 ) 출발지점 + 도착지점으로 String 생성
 * L, D 의 경우 ) 도착 + 출발로 String 생성
 *  * */


import java.util.HashSet;
import java.util.Set;

class Solution {
    Set<String> visited = new HashSet<>();
    int x = 0;
    int y = 0;
    
    public int solution(String dirs) {

        String[] splitDirs = dirs.split("");

        for (String direction : splitDirs) {
            String moved = "";
            int newX = x;
            int newY = y;
            if (direction.equals("U")) { // 출 + 도 1 0
                newX += 1;
                moved += x;
                moved += y;
                moved += newX;
                moved += y;
                if(check(newX, moved)){ // true 면 성공했으므로 x와 y를 갱신해줘야한다
                    x = newX;
                }
            }
            if (direction.equals("R")) { // 출 + 도 0 1
                newY += 1;
                moved += x;
                moved += y;
                moved += x;
                moved += newY;
                if(check(newY, moved)){
                    y = newY;
                }
            }
            if (direction.equals("L")) { // 도 + 출 0 -1
                newY += -1;
                moved += x;
                moved += newY;
                moved += x;
                moved += y;
                if(check(newY, moved)){
                    y = newY;
                }
            }
            if (direction.equals("D")) { // 도 + 출 -1 0
                newX += -1;
                moved += newX;
                moved += y;
                moved += x;
                moved += y;
                if(check(newX, moved)){
                    x = newX;
                }
            }
        }

        return visited.size();
    }

    private boolean check(int newOne, String moved){
        if(newOne <= 5 && newOne >= -5){
            visited.add(moved);
            return true;
        } return false;
    }

}
