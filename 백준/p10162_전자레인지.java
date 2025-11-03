import java.util.Scanner;

public class p10162_전자레인지 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] button_value = { 300, 60, 10 }; // 버튼의 값
		int[] button_pushed = { 0, 0, 0 }; // 버튼을 누른 횟수를 담아줄 배열

		int N = sc.nextInt(); // 요리시간 입력받기

		if (N % 10 != 0) { // 0 이 아니라면 -1을 리턴하고 종료한다.
			System.out.println(-1);
		} else {
			// 그리디로 최대한 적은 버튼 수 구하기
			for (int i = 0; i < 3; i++) {
				button_pushed[i] = N / button_value[i]; // i 번째로 나눈게 i 번이 들어갈 횟수고
				N = N % button_value[i]; // 다음에는 i 로 나눈 나머지를 넘겨준다.
			}

			System.out.printf("%d %d %d", button_pushed[0], button_pushed[1], button_pushed[2]);
		}

	}

}
