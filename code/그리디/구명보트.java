import java.util.Arrays;

/*
 * 구명보트 : https://school.programmers.co.kr/learn/courses/30/lessons/42885
 * ### 처음 생각
 * 1. 주어진 배열들을 크기 순으로 sort() 한다.
 * 2. max 값부터 for문을 돌며, min 값과 함께 구명보트를 타고 나갈 수 있는지 체크한다.
 * 3. 그 다음의 min 값도 같이 나갈 수 있는지 체크한다.
 * 4. 더 이상 같이 타고나갈 수 있는 min 이 없다면, 다음 max 값을 기준으로 다시 탐색한다.
 *
 * 수정 )
 * 3. 한 번에 2명씩만 탑승 가능하므로, max값 한번, min값 한번씩만 체크해준다.*/

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people); // 배열 정렬

        int minIndex = 0; // 무게가 낮은 순서부터 타고간 사람의 위치를 나타내는 인덱스
        for (int i = people.length - 1; i >= minIndex; i--) {   
            if (people[i] + people[minIndex] <= limit) { // 보트에 같이 탈 수 있다면
                answer++;
                minIndex++;
            } else {
                answer++;
            }
        }
        return answer;
    }
}
