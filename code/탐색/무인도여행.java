package 탐색;
import java.util.*;

class 무인도여행 {
    static char[][] miro;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int[] solution(String[] maps) {

        visited = new boolean[maps.length][maps[0].length()];
        miro = new char[maps.length][maps[0].length()];

        makeMaze(maps);

        List<Integer> numbers = new ArrayList<>();

        for (int x = 0; x < miro.length; x++) {
            for (int y = 0; y < miro[0].length; y++) {

                if (!visited[x][y] && miro[x][y] != 'X') {

                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{x, y});
                    visited[x][y] = true;

                    int sum = 0;

                    while (!q.isEmpty()) {

                        int[] pos = q.poll();
                        sum += miro[pos[0]][pos[1]] - '0';

                        for (int i = 0; i < 4; i++) {

                            int nx = pos[0] + dx[i];
                            int ny = pos[1] + dy[i];

                            if (nx >= 0 && nx < miro.length &&
                                    ny >= 0 && ny < miro[0].length &&
                                    !visited[nx][ny] &&
                                    miro[nx][ny] != 'X') {

                                visited[nx][ny] = true;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }

                    numbers.add(sum);
                }
            }
        }

        if (numbers.isEmpty()) {
            return new int[]{-1};
        }

        Collections.sort(numbers);

        return numbers.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void makeMaze(String[] maps) {
        for (int i = 0; i < maps.length; i++) {
            miro[i] = maps[i].toCharArray();
        }
    }
}