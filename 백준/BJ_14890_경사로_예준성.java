import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 12888kb 시간 108ms
 * 조건에 맞게 구현해준다.
 * 경사로가 이미 있는지에 대해선 boolean 배열을 사용한다.
 */

public class BJ_14890_경사로_예준성 {
    static int N, length;
    static int answer;
    static int[][] roads;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        length = Integer.parseInt(inputs[1]);
        roads = new int[N][N];

        // 맵 입력받기
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                roads[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        // 가로 판단
        for (int i = 0; i < N; i++) {
            if (check(roads[i])) {
                answer++;
            }
        }

        // 세로 판단
        for (int i = 0; i < N; i++) {
            int[] tmp = new int[N];
            for (int j = 0; j < N; j++) {
                tmp[j] = roads[j][i];
            }
            if (check(tmp)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int[] arr) {
        visited = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (arr[i] != arr[i + 1]) { // 두 값이 다를 때
                if (Math.abs(arr[i] - arr[i + 1]) > 1) {
                    return false;
                }
                if (!(arr[i] < arr[i + 1] ? left(arr, i) : right(arr, i + 1))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean left(int[] arr, int index) {
        int cnt = 0; // length 개 만큼 경사로를 만들기 위해서 cnt 선언
        int value = arr[index]; // 비교를 위해서 값 할당
        for (int i = index; i >= 0; i--) {
            cnt++;

            // 같은 값이 아니거나, 이미 경사로가 있다면
            if (arr[i] != value || visited[i] == true) {
                return false;
            }

            // length 만큼만 보면 되니까, cnt가 length 가 되면 멈춘다.
            if (cnt == length) {
                break;
            }
        }

        // 경사로도 없고, 같은 값도 맞지만, cnt가 length를 충족시키지 못할 경우이다.
        if (cnt != length) {
            return false;
        }

        // 위에서 false가 되지 않았다면, 경사로를 만들 수 있는 경우이므로
        // 경사로를 만들었다는 걸 나타내기 위해 visited를 true 로 바꿔준다.
        for (int i = index; i > index - length; i--) {
            visited[i] = true;
        }
        return true;
    }

    private static boolean right(int[] arr, int index) {
        // left 와 같은 구조, 탐색 조건만 다르다.
        int cnt = 0;
        int value = arr[index];
        for (int i = index; i < N; i++) {
            cnt++;
            if (arr[i] != value || visited[i] == true) {
                return false;
            }
            if (cnt == length) {
                break;
            }
        }
        if (cnt != length) {
            return false;
        }
        for (int i = index; i < index + length; i++) {
            visited[i] = true;
        }
        return true;
    }
}