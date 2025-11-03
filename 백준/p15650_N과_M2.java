/**
 * @autho 예준성
 * 15650 N과 M (2)
 * 메모리 18356kb
 * 시간 256ms
 * visited를 확인해줄 필요가 없다
 * */


import java.util.Scanner;

class p15650_N과_M2 {
	static int number; // 숫자 범위
	static int goal; // 목표
	static boolean visited[]; // 방문확인
	static int numbers[]; // 넣어줄 배열
    public static void main(String args[]) throws Exception {
    	
    	Scanner sc = new Scanner(System.in);
    	
    	number = sc.nextInt(); // 숫자 범위
    	goal = sc.nextInt(); // 뽑아낼 개수
    	visited = new boolean[number+1]; // 방문확인 위한 배열인데, 해줄 필요가 없다.
    	numbers = new int[goal];
    	// 범위가 1~n 중, m개 뽑기
    	recur(0, 1);
    	
    }
    
    static void recur(int index, int start) { // 들어갈 인덱스, 종료조건은 목표개수와 같을때
    	if(index == goal) {
    		for(int i : numbers) {
    			System.out.print(i + " ");
    		}System.out.println();
    		// 출력
    	} else {
    		for(int i=start ; i<= number;i++) {
    			/*if(visited[i] == false) {// 방문 안했으면
    				numbers[index] = i;
    				visited[i] = true;
    				recur(index+1, i+1);
    				visited[i] = false;
    			}*/
				numbers[index] = i;
    			recur(index+1, i+1);
    		}
    	}
    }
}

