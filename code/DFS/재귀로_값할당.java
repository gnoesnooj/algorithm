package DFS;

class 재귀로_값할당 {

    int[] numbers = {0, 1, 2, 3, 4};

    public void solution(int[] arr) {
        for (int i = 0; i < 5; i++) { // 0,1,2,3,4
            dfs(arr, 0);
        }
    }

    public void dfs(int[] arr, int index) {
        if(index >= 4){
            for(int number : arr){
                System.out.print(number +" ");
            }
            System.out.println();
        }
        else {
            for (int number : numbers) {
                arr[index] = number;
                dfs(arr, index + 1);
            }
        }
    }
}
