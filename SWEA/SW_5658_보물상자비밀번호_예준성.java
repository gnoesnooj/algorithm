import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 메모리 21,644 kb
 * 실행시간 130 ms
 * 1. map 에 16변환 위한 K, V 저장
 * 2. rotate 진행
 * 3. set에 저장해서 중복 제거
 * 4. list를 통해 크기 역순 정렬
 * 5. 결과리턴
 */
public class SW_5658_보물상자비밀번호_예준성 {

	static int N, K;
	static Map<String, Integer> map = new HashMap<>();;
	static String [] numbers;
	static Set<Long> set;
	static List<Long> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<16;i++) {
			map.put(Integer.toHexString(i).toUpperCase(), i);
		}
		int test_case = Integer.parseInt(br.readLine());
		String [] inputs;
		
		for (int T = 1; T <= test_case; T++) {
			set = new HashSet<>();
			list = new LinkedList<>();
			
			inputs = br.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			K = Integer.parseInt(inputs[1]);
			
			numbers = br.readLine().split("");
			
			// rotate and calc, 0~n/4
			for(int i=0; i<numbers.length;i++) {
				rotate(i);
			}
			// get K
			// 1. sort
			for(Long l : set) {
				list.add(l);
			}
			Collections.sort(list, Collections.reverseOrder());
			sb.append("#").append(T).append(" ").append(list.get(K-1)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void rotate(int start) {
		String [] tmp = new String[N/4];
		
		int cnt = 0;
		for(int i=start; i<start+N/4;i++) {
			if(i>=N)tmp[N/4 - 1 - (cnt++)] = numbers[i%N];
			else tmp[N/4 - 1 - (cnt++)] = numbers[i];
		}
		calc(tmp);
	}
	
	private static void calc(String [] tmp) {
		long number = 0;
		for(int i=0; i<tmp.length;i++) {
			int value = map.get(tmp[i]);
			number += (long) (value * Math.pow(16, i));
		}
		set.add(number);
	}
	
	

}
