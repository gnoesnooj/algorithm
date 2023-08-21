import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test_case = Integer.parseInt(br.readLine());
		
		String [] inputs;
		int sum = 0;
		Stack<int[]> stack = new Stack<>();
		for(int T=0; T<test_case; T++) {
			inputs = br.readLine().split(" "); // 값 입력받기
			
			int [] tmp = new int[2];
			if(Integer.parseInt(inputs[0]) == 1) { // 1이라면
				tmp = new int[]{Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])};
				stack.add(tmp);
				stack.peek()[1]--; // 분 줄이기
				if(stack.peek()[1] == 0) {// 해당 요소의 소요시간이 0이 되었다면
					sum += stack.peek()[0]; // 점수 더해주기
					stack.pop(); // 그리고 버리기
				}
			} else { // 첫 입려값이 0
				if(!stack.isEmpty()) { // 스택이 비어있지 않다면
					stack.peek()[1]--; // 분 줄이기
					if(stack.peek()[1] == 0) { // 해당 요소의 소요시간이 0이 되었다면
						sum += stack.peek()[0]; // 점수 더해주기
						stack.pop(); // 그리고 버리기
					}
				}
			}
		}
		System.out.println(sum);
	}

}
