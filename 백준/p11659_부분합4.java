import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Scanner 를 사용하면 안되는 것과
 * System 함수 호출을 최대한 적게 하는 것이 좋다는 것에 대해 알게 되었습니다.
 * 
 * @author 예준성
 * 메모리 65716KB
 * 시간 892ms*/
public class p11659_부분합4 {

	static int N;
	static int M;
	static int[] numbers;
	static int start;
	static int end;
	static int [] sum;

	public static void main(String[] args) throws IOException {

		String [] inputs;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 수의 갯수
		inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		numbers = new int[N];

		int temp = 0;
		sum = new int[N+1];
		sum[0] = 0;
		inputs = br.readLine().split(" ");
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(inputs[i]);
			temp += numbers[i];
			sum[i+1] = temp;
		} 
		
		StringBuilder sb = new StringBuilder();
	
		for (int i = 0; i < M; i++) {
			inputs = br.readLine().split(" ");
			start = Integer.parseInt(inputs[0]);
			end = Integer.parseInt(inputs[1]);
			sb.append(sum[end] - sum[start-1]+"\n");
		}

		System.out.println(sb.toString());
	}
}

