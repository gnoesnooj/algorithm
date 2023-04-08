// 이진트리접근
// dfs로 나눈다고 생각할 때, 모두 새로운 트리라고 생각
// 필요할땐 인덱스 말고 String 그 자체로 접근하자

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int index = 0;
        for (long number : numbers) {
            String binaryNumber = Long.toBinaryString(number);
            int depth = findDepth(binaryNumber);
            String fullBinary = makeFullBinary(depth, binaryNumber);
            answer[index] = isCorrectTree(fullBinary) ? 1 : 0;
            index++;
        }
        return answer;
    }

    public int findDepth(String binary_numbers) { // 2의 몇배수길이인지 찾기, 즉 해당 트리의 depth가 얼마인지 찾는 메소드
        int depth = 0;
        while (Math.pow(2, depth) <= (long) binary_numbers.length()) {
            depth++;
        }
        //System.out.println(depth);
        return depth;
    }

    public String makeFullBinary(int depth, String binary_numbers) { // 앞에 부족한 0을 채워주기 위한 메소드

        long needZero = (long) Math.pow(2, depth) - 1 - (long) binary_numbers.length();
        for (long i = 1; i <= needZero; i++) {
            binary_numbers = "0" + binary_numbers;
        }
        //System.out.println(binary_numbers);
        return binary_numbers;
    }

    // 문제조건을 만족하는 이진트리인가 ?
    public boolean isCorrectTree(String number) {
        /*
        boolean valid = true;
        int root = fullBinaryNumber.length() / 2 ;
        int left = root / 2;
        int right = root + root / 2; // 모든 경우에 right의 인덱스 접근 방식이 이렇게 되지 않을 것으로 예상된다. String을 잘라서 각각의 상황에서 판단해주는 아래 방식이 옳다.

        if(fullBinaryNumber.charAt(root -1) == '0'){
            if(fullBinaryNumber.charAt(left -1) == '1' || fullBinaryNumber.charAt(right - 1) == '1'){
                return false;
            }
        }

        if(left >= 3){
            valid = isCorrectTree(fullBinaryNumber.substring(0,root));
            if(valid) {
                valid = isCorrectTree(fullBinaryNumber.substring(root+1, fullBinaryNumber.length()));
            }
        }

        return valid;
        */

        boolean valid = true;

        int mid = (number.length() - 1) / 2;
        char root = number.charAt(mid);
        String left = number.substring(0, mid);
        String right = number.substring(mid + 1, number.length());

        if (root == '0' && (left.charAt((left.length() - 1) / 2) == '1'
                || right.charAt((right.length() - 1) / 2) == '1')) {
            return false;
        }

        if (left.length() >= 3) {
            valid = isCorrectTree(left);
            if (valid) {
                valid = isCorrectTree(right);
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        long[] numbers = {7, 42, 5};
        int[] answer = s.solution(numbers);
        for (int a : answer) {
            System.out.println(a);
        }
    }
}
