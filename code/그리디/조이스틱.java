package 그리디;
// 시도 ) 가장 긴 A문자열을 찾은 후, Start 와 end 포인트를 기억
// 글자를 맞춰주는 것과, 글자를 방문하는 건 따로 계산한다.
// 긴 문자열의 위치에 따라 바로가는 것, 돌았다가 가는 방법 등 모든 방법 중 최소 값을 더한다.

class 조이스틱 {
    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1;
        
        for(int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A'));
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
                int endA = i + 1;
                while(endA < name.length() && name.charAt(endA) == 'A')
                    endA++;
                move = Math.min(move, i * 2 + (name.length() - endA));
                move = Math.min(move, i + (name.length() - endA) * 2);
            }
        }
        return answer + move;
    }
}
