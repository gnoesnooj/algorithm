import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 249452 kb
 * 시간 644ms
 * 입력을 받으면서 초밥 개수를 세는 것을 동시에 진행한다.
 * 모든 값을 입력을 받고 나서, k-1 개 배열만큼 추가로 계산해준다.
 * -> 0 , 1 , 2 , 3 중 3개를 뽑는다고 할 때, 2 3 0, 3 0 1 과 같은 경우도 계산해야 하기 때문이다.
 * */
public class Main {

    static int N, d, k, c;
    static int answer, max = 0;
    static int [] used_numbers;
    static ArrayDeque<Integer> q;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs;
        inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); // 접시의 수 3,000,000
        d = Integer.parseInt(inputs[1]); // 초밥 가짓수 3,000
        k = Integer.parseInt(inputs[2]); // 연속해서 먹어야할 접시 수 3,000
        c = Integer.parseInt(inputs[3]); // 쿠폰 번호  1 <= c <= d

        int cnt = 0;
        used_numbers = new int [d+1];
        q = new ArrayDeque<>();
        int [] numbers_k = new int[k]; // 나중에 또한번 돌려 줄 배열
        for (int i = 0; i < N; i++) { // 초밥 입력 받기
            int input = Integer.parseInt(br.readLine());

            if(cnt++ < k) { // 우선 k 개 만큼 입력 받기
                numbers_k[i] = input;
                plus(input);
            } else { // 처음 k개 받기가 끝났다면
                // 1개씩 옮겨가며 계산해주기
                minus();
                plus(input);
            }

            // k개 연속으로 먹고 있는 경우니까, 쿠폰 초밥 c 적용해주기
            coupon();
        }

        // first 가 N 이고, 0부터 k-1 까지 다시한번 체크 0 1 2 .. N -> N 0 1 2 3... k-1 까지
        for(int i=0; i<k-1; i++){
            plus(numbers_k[i]);
            minus();
            coupon();
        }
        System.out.println(max);
    }


    private static void plus(int input){
        q.add(input); //나중 것 넣기
        used_numbers[input] += 1; // 사용된 횟수 1 증가
        if(used_numbers[input] == 1){ // 1 더해줫더니 1이 되었따면, 새로운 녀석이 들어온 것
            answer++;
        }
    }

    private static void minus(){
        int out = q.poll(); // 처음꺼 빼주고
        used_numbers[out] -= 1; // 사용된 횟수 1 차감
        if(used_numbers[out] == 0){ // 1 빼줫더니 0이 되었다면, 해당 숫자는 사용하지 않는다는 뜻
            answer--;
        }
    }

    private static void coupon(){
        q.addFirst(c);
        used_numbers[c] += 1;
        if(used_numbers[c] == 1){ // 1 더해줫더니 1이 되었따면, 새로운 녀석이 들어온 것
            answer++;
        }

        max = Math.max(max, answer);

        q.poll(); // 제일 마지막에 넣은 쿠폰 빼주기
        used_numbers[c] -= 1;
        if(used_numbers[c] == 0){ // 1 빼줫더니 0이 되었다면, 해당 숫자는 사용하지 않는다는 뜻
            answer--;
        }
    }
}