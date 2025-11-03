package 구현;

class 최댓값과_최솟값 {

    static String min = "";
    static String max = "";

    public String solution(String s) {
        String answer = "";

        String[] splited = s.split(" ");

        for (String str : splited) {
            if(isEmpty(min))
                min = str;
            if(isEmpty(max))
                max = str;
            checkMax(str);
            checkMin(str);
        }
        answer = min + " " + max;

        return answer;
    }

    boolean isEmpty(String str){
        if(str.equals(""))
            return true;
        return false;
    }

    void checkMin(String str){
        if(Integer.parseInt(min) > Integer.parseInt(str)){
            min = str;
        }
    }

    void checkMax(String str){
        if(Integer.parseInt(max) < Integer.parseInt(str)){
            max = str;
        }
    }
}
