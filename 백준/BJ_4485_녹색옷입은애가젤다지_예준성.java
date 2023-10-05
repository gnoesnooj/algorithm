package lgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 메모리 31252
 * 시간	256
 * 
 * bfs로 탐색하면서 길뚫어주기 -> 작은값 생길떄마다 새로운길 찾은거니까 값 바꿔주면서 bfs 진행하기
 * */
public class Main {
	
	static int N;
	static int [][] miro;
	static int [][] roads;
	static int [] dx = {-1,0,1,0};
	static int [] dy = {0,1,0,-1};
	static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int problem = 0;
		while(true) {
			problem++;
			N =Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			q = new LinkedList<>();
			miro = new int[N][N];
			roads = new int[N][N];
			
			// 길뚫 최대로 만들어주기
			for(int i=0 ;i<N;i++) {
				Arrays.fill(roads[i], 9999999);
			}
			
			String [] inputs;
			for(int i=0;i<N;i++) {
				inputs = br.readLine().split(" ");
				for(int j=0;j<N;j++) {
					miro[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			roads[0][0] = miro[0][0];
			//bfs로 탐색하면서 값 갱신해주기
			q.add(new int[] {0,0});
			bfs();
			sb.append("Problem ").append(problem).append(": ").append(roads[N-1][N-1]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			int [] now = q.poll();
			for(int i=0;i<4;i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				// 범위가 맞고, 이전 로드값 + 현재 미로값보다 현재 로드값이 더 크면, 값을 새로 바꿔주고 큐에 넣는다.
				if(isRange(nx, ny)) {
					if(roads[now[0]][now[1]] + miro[nx][ny] < roads[nx][ny]) {
						roads[nx][ny] = roads[now[0]][now[1]] + miro[nx][ny];
						q.add(new int [] {nx,ny});
					}
				}
			}
		}
	}
	
	
	// 범위를 만족하는지
	private static boolean isRange(int x, int y) {
		return x >=0 && y>=0 && x <N && y < N;
	}
}