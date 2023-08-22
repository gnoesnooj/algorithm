import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 13792 kb
 * 실행시간 236 ms
 * 1. 리스트 배열을 통해서 모든 간선들을 넣어놓는다. 단, 넣을 때 무향 그래프이므로 1->4 라면 4->1도 넣어놓는다.
 * 2. 시작노드가 모두가 될 수 있도록, 첫 시작 dfs도 0 ~ N 까지로 스타트 하게 해준다 (그렇지 않으면 2 - 0 - 1 - 3 - 4 와 같은 테스트케이스를 통과하지 못함)
 * 3. 해당 노드에 들어오면, 리스트를 탐색하여 연결된 모든 노드중, 방문하지 않는 노드만을 찾아서 dfs를 진행한다.
 * 4. depth가 5가 되면 가능한 경우이므로 answer 을 1로 바꿔준다.
 * 5. 가능하면 1, 불가능하면 0을 출력하므로, answer 가 0일때만 dfs를 진행하게 하여 가지치기를 해준다.
 * 
 * */
public class Main {
	
	static ArrayList<Integer>[] friends;
	static int answer = 0;
	static boolean [] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String [] inputs;
		
		inputs = br.readLine().split(" ");
		
		int N = Integer.parseInt(inputs[0]); // 사람 2000
		int M = Integer.parseInt(inputs[1]); // 간선 2000
		
		friends = new ArrayList[N];
		for(int i=0; i<N;i++) {
			friends[i] = new ArrayList<>();
		}
		
		for(int line = 0 ; line < M ; line ++) {
			inputs = br.readLine().split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);

			friends[a].add(b);
			friends[b].add(a);
		}
		// dfs
		visited = new boolean[N];

		for(int i = 0; i< N; i++) {
			visited[i] = true;
			dfs(i,1);
			visited[i] = false;
		}
		
		System.out.println(answer);
	}
	private static void dfs(int node, int depth) {
		// 1. 노드가 들어오면
		// 2. list[노드].size 만큼 for문 돌면서 get 하고, 해당 get으로 dfs 진행
		// 만일 depth가 5가되면, 가능하다는 뜻이므로 결과 리턴
		if(depth == 5)
			answer = 1;
		else {
			if(answer != 1) { // 백트래킹 - 자영쓰 아이디어
				for(int i=0;i < friends[node].size() ; i++) {
					int next = friends[node].get(i);
					if(!visited[next]) {
						visited[next] = true;
						dfs(next, depth+1);
						visited[next] = false;
					}
				}
			}
		}
	}
}
