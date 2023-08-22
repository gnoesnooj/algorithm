import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 진입차수가 0인 정점을 큐에 넣기.
 * 2. 이후 반복문을 돌면서, 큐가 빌때까지
 * 3. 큐에서 1개를 가져온다 (전에 넣어뒀던 진입차수 0인 정점)
 * 4. 가져온 것을 노드A라고 할때, A가 진출하는 다른 노드들을 찾는다.
 * 5. 해당 노드 A가 진출 from 이고, 도착 노드 N을 to라고 하면, N의 진입차수를 1개 빼준다.(A-N 간선을 제거하는 것)
 * 6. 이후 N의 진입차수가 0이 되면, 해당 N을 큐에 다시 넣어준다.
 * 60360	792
 * */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String inputs[];

		inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]); // 학생 수 1 ~ N
		int M = Integer.parseInt(inputs[1]); // 간선 수

		Queue<Integer> q = new ArrayDeque<>(); // 정점 넣어줄 큐
		
		List<Integer>[] li = new ArrayList[N+1];
		
		for(int i=0; i<N+1;i++) {
			li[i] = new ArrayList<>();
		}
		
		int [] visit = new int[N+1]; // 진입 차수 
		
		for (int i = 0; i < M; i++) { // 간선 입력 받기
			inputs = br.readLine().split(" ");
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			
			li[from].add(to);
			
			visit[to]++;
		}
		
		for(int i=1;i<visit.length;i++) {
			if(visit[i] == 0) { // 진입 차수가 0이면
				q.add(i); // 해당 정점 큐에 넣어주기
			}
		}
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			System.out.print(tmp + " ");
			for(int i=0; i<li[tmp].size(); i++) {
				int index = li[tmp].get(i);
				visit[index]--;
				if(visit[index] == 0)
					q.add(index);
			}
		}
		
	}
}
