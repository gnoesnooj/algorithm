import java.util.Scanner;

/*
 * SWEA Solving Club 난이도 중 - 1961. 숫자 배열 회전
 *
 * 생각)
 * 1. 90도를 회전해주는 메소드를 만든다
 * 1-1. 먼저 x 축 가운데를 기반으로 회전을 해주고
 * 1-2. (0,0) -> (m,m) 을 잇는 선 기반으로 회전을 해주면 90도 회전한 배열의 결과가 된다.
 * 2. 이후 90도, 90도 2번, 90도 3번을 한 배열 3개를 만들어 출력 양식에 맞게 출력해준다.
 * */

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        Solution s = new Solution();
        for (int test_case = 1; test_case <= T; test_case++) {
            // 배열 크기 입력
            int size = sc.nextInt();

            int[][] numbers = new int[size][size];

            // 배열 입력
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    numbers[i][j] = sc.nextInt();
                }
            }

            //90도 회전을 1,2,3회 한 배열을 담아줄 배열을 3개 만들어준다.
            int[][] rotateOne = new int[size][size];
            int[][] rotateTwo = new int[size][size];
            int[][] rotateThree = new int[size][size];
            // 깊은 복사와 얕은 복사에 대해 다시 생각하게 해준 부분.. 잊고 있었는데 다시 기억나게 되어 다행인 것 같다.

            // 초기 배열 값을 복사해준다.
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    rotateOne[i][j] = numbers[i][j];
                    rotateTwo[i][j] = numbers[i][j];
                    rotateThree[i][j] = numbers[i][j];
                }
            }

            // 이후 각각 횟수에 맞게 회전시켜준다.
            rotateOne = rotate90(rotateOne);
            rotateTwo = rotate90(rotate90(rotateTwo));
            rotateThree = rotate90(rotate90(rotate90(rotateThree)));

            // 출력 양식에 맞게 출력해준다
            System.out.println("#" + test_case);
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers.length; j++) {
                    System.out.print(rotateOne[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < numbers.length; j++) {
                    System.out.print(rotateTwo[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < numbers.length; j++) {
                    System.out.print(rotateThree[i][j]);
                }
                System.out.println();
            }
        }
    }

    // 90도 회전하는 메소드를 작성해준다.
    private static int[][] rotate90(int[][] numbers) {
        for (int i = 0; i < numbers.length / 2; i++) {
            for (int j = 0; j < numbers.length; j++) { // 위 아래로 회전
                int tmp = numbers[numbers.length - i - 1][j];
                numbers[numbers.length - i - 1][j] = numbers[i][j];
                numbers[i][j] = tmp;
            }
        }

        for (int i = 0; i < numbers.length; i++) { //  \방향 기준으로 회전
            for (int j = i; j < numbers.length; j++) {
                int tmp = numbers[i][j];
                numbers[i][j] = numbers[j][i];
                numbers[j][i] = tmp;
            }
        }

        return numbers;
    }
}
