import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 모든 점의 통로를 확인한다.
 * 2. 해당 통로가 이어져 있는지 확인한다.
 * 3. 만일 통로가 나가는 방향의 값이 . 이라면, 끊어져 있다는 뜻이다.
 * 4. 해당 . 좌표로 이동해서. 나가야할 방향을 찾는다.
 * 5. 올바른 값을 넣어준다.
 * */
public class Main {

	static String [][] miro;
	static int X, Y;
	static List<int [][]> command = new ArrayList<>();
	static int cutX, cutY; // 끊어진 곳의 좌표
	static boolean [] b;
	static String answer; // 끊어진 곳에 넣어줄 좌표
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 순서에 맞게, 탐색할 방향을 담아서 command 에 넣어주기
		command.add(new int[][]{{-1,0},{1,0}});
		command.add(new int[][]{{0,-1},{0,1}});
		command.add(new int[][]{{-1,0},{1,0},{0,1},{0,-1}});
		command.add(new int[][]{{0,1},{1,0}});
		command.add(new int[][]{{-1,0},{0,1}});
		command.add(new int[][]{{0,-1},{-1,0}});
		command.add(new int[][]{{0,-1},{1,0}});
		
		String [] inputs;
		inputs = br.readLine().split(" ");
		X = Integer.parseInt(inputs[0]);
		Y = Integer.parseInt(inputs[1]);
		miro = new String[X][Y];

		// 통로 상태 입력 받기
		for(int i=0; i<X;i++) {
			miro[i] = br.readLine().split("");
		}
		
		// 연결 되지 않은 것 찾기
		cutX = 0; cutY = 0;
		find();
//			System.out.println(cutX+" "+cutY);
		// 해당좌표로 이동해서, 나가야할 부분 찾기
		// 상 하 좌우
		b = new boolean[4];
		where();
		
		makePath();
		System.out.println((cutX+1)+" "+(cutY+1)+" "+answer);
		
	}
	
	// 끊어진 통로 찾기
	// 0~6 번의 요소까지 각 통로일 경우 확인해야할 좌표의 값이 2차원 배열로 list에 담겨 있다.
	// 이동 좌표의 값이 . 이라면 끊어져 있다는 뜻이므로, 끊어진 곳을 찾으면 그 즉시 cutX 와 cutY에 결과를 담아주고 메소드를 return 한다.
	private static void find() {
		for(int i=0;i<X;i++) {
			for(int j=0;j<Y;j++) {
				if(isPath(miro[i][j])) { // 통로라면
					int [][] com;
					switch(miro[i][j]) { // 각 주변확인, . 이면 좌표 기억하고 종료
					case"|":
						com = command.get(0);
						for(int c = 0 ; c < com.length; c++) {
							int moveX = i+com[c][0];
							int moveY = j+com[c][1];
							if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) {
								if(miro[moveX][moveY].equals(".")) {
									cutX = moveX;
									cutY = moveY;
									return;
								}
							}
						}
						break;
					case"-":
						com = command.get(1);
						for(int c = 0 ; c < com.length; c++) {
							int moveX = i+com[c][0];
							int moveY = j+com[c][1];
							if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) {
								if(miro[moveX][moveY].equals(".")) {
									cutX = moveX;
									cutY = moveY;
									return;
								}
							}
						}break;
					case"+":
						com = command.get(2);
						for(int c = 0 ; c < com.length; c++) {
							int moveX = i+com[c][0];
							int moveY = j+com[c][1];
							if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) {
								if(miro[moveX][moveY].equals(".")) {
									cutX = moveX;
									cutY = moveY;
									return;
								}
							}
						}break;
					case"1":
						com = command.get(3);
						for(int c = 0 ; c < com.length; c++) {
							int moveX = i+com[c][0];
							int moveY = j+com[c][1];
							if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) {
								if(miro[moveX][moveY].equals(".")) {
									cutX = moveX;
									cutY = moveY;
									return;
								}
							}
						}break;
					case"2":
						com = command.get(4);
						for(int c = 0 ; c < com.length; c++) {
							int moveX = i+com[c][0];
							int moveY = j+com[c][1];
							if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) {
								if(miro[moveX][moveY].equals(".")) {
									cutX = moveX;
									cutY = moveY;
									return;
								}
							}
						}break;
					case"3":
						com = command.get(5);
						for(int c = 0 ; c < com.length; c++) {
							int moveX = i+com[c][0];
							int moveY = j+com[c][1];
							if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) {
								if(miro[moveX][moveY].equals(".")) {
									cutX = moveX;
									cutY = moveY;
									return;
								}
							}
						}break;
					case"4":
						com = command.get(6);
						for(int c = 0 ; c < com.length; c++) {
							int moveX = i+com[c][0];
							int moveY = j+com[c][1];
							if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) {
								if(miro[moveX][moveY].equals(".")) {
									cutX = moveX;
									cutY = moveY;
									return;
								}
							}
						}break;
					}
				}
			}
		}
	}
	
	// 끊어진 좌표 이동, 나가야할 부분 찾기
	private static void where() {
		// 상0 하1 좌2 우3 보면서, 통로가 이어지는 부분 찾기
		int [] dx = {-1,1,0,0};
		int [] dy = {0,0,-1,1};
		for(int i=0;i<4;i++) {
			int moveX = cutX+dx[i];
			int moveY = cutY+dy[i];
			if(moveX>=0 && moveY>=0 && moveX <X && moveY < Y) { // 범위를 만족하고
				if(i==0) { // 위쪽
					if((miro[moveX][moveY].equals("|") || miro[moveX][moveY].equals("4") || miro[moveX][moveY].equals("1") || miro[moveX][moveY].equals("+"))) {
						b[0] = true;
					}
				} else if(i==1) { // 아래쪽
					if((miro[moveX][moveY].equals("|") || miro[moveX][moveY].equals("2") || miro[moveX][moveY].equals("3") || miro[moveX][moveY].equals("+"))) {
						b[1] = true;
					}
				} else if(i==2) { // 왼쪽
					if((miro[moveX][moveY].equals("-") || miro[moveX][moveY].equals("2") || miro[moveX][moveY].equals("1") || miro[moveX][moveY].equals("+"))) {
						b[2] = true;
					}
				} else if(i==3) { // 오른
					if((miro[moveX][moveY].equals("-") || miro[moveX][moveY].equals("4") || miro[moveX][moveY].equals("3") || miro[moveX][moveY].equals("+"))) {
						b[3] = true;
					}
				}
			}
		}
	}

	// 끊어진 곳에 올바른 통로 넣어주기
	private static void makePath() {
		// 상 0 하 1 좌 2 우 3
		if(b[0] && b[1] && b[2] && b[3]) {
			answer = "+";
			return;
		} else if(b[1] && b[0]) { // 상 하
			answer = "|";
		} else if(b[2] && b[3]) { // 좌 우
			answer = "-";
		} else if(b[1] && b[3]) { // 하 우
			answer = "1";
		} else if(b[3] && b[0]) { // 상 우
			answer = "2";
		} else if(b[2] && b[0]) { // 좌 상
			answer = "3";
		} else if(b[2] && b[1]) { // 좌 하
			answer = "4";
		}
	}
	
	// 해당 좌표가 통로인지
	private static boolean isPath(String str) {
		return str.equals("|") || str.equals("-") || str.equals("+") || str.equals("1")|| str.equals("2")|| str.equals("3")|| str.equals("4");
	}

}
