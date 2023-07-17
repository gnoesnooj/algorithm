/*
* S/W 문제해결 기본 : 1일차 - Flatten : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh
* 
* 접근 ) 정렬 후, 최대 값 -1, 최소값 -1 의 로직을 dump 가능 횟수 만큼 for문을 돌리고, 
* 만일 평탄화가 dump횟수 전에 끝난다면, for문을 탈출하고 결과값을 print 해준다.
* */

import java.util.Arrays;
import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int count = sc.nextInt();

            int [] boxes = new int [100];

            for(int i=0; i<100; i++){
                boxes[i] = sc.nextInt();
            }

            for(int i=0 ; i<count ; i++){
                Arrays.sort(boxes);
                if(boxes[99] != boxes[0]) {
                    boxes[99] -= 1;
                    boxes[0] += 1;
                } else break;
            }

            System.out.println("#"+test_case+" "+ (boxes[99] - boxes[0]));
        }
    }
}
