import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 * 메모리 17588kb
 * 시간 196ms
 * */
public class p2961_도영이가_만든_맛있는_음식 {
	static int number;
	static int min = 1000000001;
	static boolean isSelected [];
	static int[][] info;
	
	public static void main(String [] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Scanner sc = new Scanner(System.in);

		number = Integer.parseInt(br.readLine());

		// 재료 개수만큼 재료 각각의 신맛, 쓴맛 입력 받기
		info = new int[number][2];

		String[] inputs;
		for (int i = 0; i < number; i++) {
			inputs = br.readLine().split(" ");
			info[i][0] = Integer.parseInt(inputs[0]);
			info[i][1] = Integer.parseInt(inputs[1]);
		}
		
		isSelected = new boolean[number];

		// number 개의 재료 중, 1~number 개수 만큼 고른 것중 최소 차이 구하기

		// 최소값을 비교해줄 수 넣어주기, 또한 신맛 슨맛은 각각 뭘해도 값은 10억보다 작다.
		calc(0);
		System.out.println(min);
	}
	
	public static void calc(int num) {
		if(num == number) {
			// 계산
			int gob = 1;
			int hap = 0;
			for(int i = 0 ;i<number ; i++) {
				if(isSelected[i]) { // 해당 숫자가 골라졌다면
					gob *= info[i][0];
					hap += info[i][1];
					
					min = Math.min(min, Math.abs(gob - hap));
				}
			}
		} else {
			isSelected[num] = true;
			calc(num+1);
			isSelected[num] = false;
			calc(num+1);
		}
	}
}
