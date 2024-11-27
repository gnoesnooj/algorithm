import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N, K;
    static int[] electronics;
    static List<Integer> plug;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); // 플러그 수
        K = Integer.parseInt(inputs[1]); // 총 전기용품 수

        electronics = new int[K];
        inputs = br.readLine().split(" ");
        for (int i = 0; i < K; i++) {
            electronics[i] = Integer.parseInt(inputs[i]);
        }

        plug = new ArrayList<>();
        int answer = 0;

        for (int i = 0; i < K; i++) {
            int current = electronics[i];

            // 이미 꽂혀 있는 경우
            if (plug.contains(current)) {
                continue;
            }

            // 빈 자리가 있는 경우
            if (plug.size() < N) {
                plug.add(current);
                continue;
            }

            // 교체가 필요한 경우
            int lastUsed = -1;
            int targetIndex = -1;

            for (int j = 0; j < plug.size(); j++) {
                int device = plug.get(j);
                int nextUse = Integer.MAX_VALUE;

                // 앞으로 사용할 인덱스 탐색
                for (int k = i + 1; k < K; k++) {
                    if (electronics[k] == device) {
                        nextUse = k;
                        break;
                    }
                }

                // 가장 늦게 사용되거나 사용되지 않는 전기용품 선택
                if (nextUse > lastUsed) {
                    lastUsed = nextUse;
                    targetIndex = j;
                }
            }

            plug.remove(targetIndex);
            plug.add(current);
            answer++;
        }

        System.out.println(answer);
    }
}
