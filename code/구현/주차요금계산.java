package 구현;

import java.util.*;

class 주차요금계산 {

    static Map<String, Integer> money = new HashMap<>();
    static int basicTime;
    static int basicFee;
    static int addTime;
    static int extraCharge;

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> history = new HashMap<>();
        basicTime = fees[0];
        basicFee = fees[1];
        addTime = fees[2];
        extraCharge = fees[3];

        for (String record : records) {
            String[] parsedRecord = record.split(" ");
            if (parsedRecord[2].equals("IN")) {
                history.put(parsedRecord[1], calcTime(parsedRecord[0]));
            } else {
                int start = history.get(parsedRecord[1]);
                int end = calcTime(parsedRecord[0]);
                makeMoneyMap(parsedRecord[1], end - start);
                history.remove(parsedRecord[1]);
            }
        }

        if(!history.isEmpty()){
            List<String> list = new ArrayList<>(history.keySet());
            for(String key : list){
                makeMoneyMap(key, calcTime("23:59") - history.get(key));
            }
        }
        // 정렬
        List<String> cars = new ArrayList<>(money.keySet());
        Collections.sort(cars);

        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            answer[i] = money.get(cars.get(i));
        }

        return answer;
    }

    private static int calcTime(String record) {
        String[] time = record.split(":");
        int hour = Integer.parseInt(time[0]) * 60;
        int minute = Integer.parseInt(time[1]);
        return hour + minute;
    }

    private static void makeMoneyMap(String carNumber, int parkingTime) {
        int sum = 0;
        if (parkingTime <= basicTime) { // 기본시간일 경우
            sum += basicFee;
        } else { // 초과일 경우
            sum += basicFee + ((parkingTime - basicTime) / addTime) * extraCharge;
            if (parkingTime % addTime != 0) {
                sum += extraCharge;
            }
        }
        if (money.containsKey(carNumber)) {
            money.replace(carNumber, money.get(carNumber) + sum);
        } else {
            money.put(carNumber, sum);
        }
    }
}