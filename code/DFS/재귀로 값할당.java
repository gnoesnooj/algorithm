class Solution {

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

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[4]);
    }
}
