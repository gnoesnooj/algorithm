package lgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 메모리	13404kb
 * 시간 116ms
 * 
 * 시작점부터 편의점들, 그리고 목표지점까지 bfs를 해준다.
 * 편의점에 갈 때마다 20개씩 채워지므로 최대 1000까지 갈 수 있게 된다.
 * 따라서 다음 편의점으로 이동할때마다 갈 수 있는 모든 편의점을 큐에 넣어서 bfs를 한다.
 * 매번 편의점에서 페스티벌에 도달할 수 있는지를 체크하고, 체크에 따라 결과를 출력해준다.
 * */
public class Main {

	static int [] home;
	static List<int[]> markets;
	static int [] festival;
	static boolean [] visited;
	static boolean isOver;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] inputs;
		String input;
		
		int test_case = Integer.parseInt(br.readLine());
		
		for(int t=0;t<test_case;t++) {
			// 편의점 개수
			int market_number =  Integer.parseInt(br.readLine());
			
			// 상근이 집
			inputs = br.readLine().split(" ");
			home = new int [] {Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])};
			
			// 편의점 개수
			markets = new ArrayList<int[]>();
			for(int i=0; i<market_number;i++) {
				inputs = br.readLine().split(" ");
				markets.add(new int [] {Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), i});
			}
			visited = new boolean[market_number+1];
			// 페스티벌
			inputs = br.readLine().split(" ");
			festival = new int [] {Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])};
			
			isOver = false;
			bfs();
			if(!isOver)
				System.out.println("sad");
		}
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		if(isFestival(home[0], home[1])) {
			return;
		}
		for(int [] market : markets) {
			if(visited[market[2]] == false && Math.abs(home[0] - market[0]) + Math.abs(home[1] - market[1]) <= 1000) {
				q.add(market);
				visited[market[2]] = true;
			}
		}
		while(!q.isEmpty()) {
			int [] next = q.poll();
			int x = next[0];
			int y = next[1];
			if(isFestival(x, y)) {
				return;
			}
			for(int [] market : markets) {
				if(visited[market[2]] == false && Math.abs(x - market[0]) + Math.abs(y - market[1]) <= 1000) {
					visited[market[2]] = true;
					q.add(market);
				}
			}
			
		}
	}
	
	private static boolean isFestival(int x, int y) {
		if((x==festival[0] && y == festival[1]) || (Math.abs(x - festival[0]) + Math.abs(y - festival[1]) <= 1000)) {
			System.out.println("happy");
			isOver = true;
			return true;
		}
		return false;
	}
}