package _20230223_skplanet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1 {

    public static int curInt;
    public static int[] timeIntArr;
    public static int[] numArr;
    public static int remain;
    public static int stopTimeIdx;

    public static void main(String[] args) {
        String[] arr = {"09:05 10", "12:20 5", "13:25 6", "14:24 5"};
        solution(arr, "12:05", 10);
    }

    public static int solution(String[] bakery_schedule, String current_time, int k) {
        int answer = -2;
        int size = bakery_schedule.length;
        curInt = 0;
        timeIntArr = new int[size];
        numArr = new int[size];
        remain = k;
        stopTimeIdx = -1;

        StringTokenizer st = null;
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(bakery_schedule[i], ": ");
            int time = Integer.parseInt(st.nextToken());
            int minute = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            timeIntArr[i] = time*60 + minute; //분으로 바꿔서 저장
            numArr[i] = num; //개수 저장
        }
        st = new StringTokenizer(current_time, ": ");
        int curTime = Integer.parseInt(st.nextToken());
        int curMinute = Integer.parseInt(st.nextToken());

        curInt = curTime*60 + curMinute;

        for (int i = 0; i < size; i++) {
            if(timeIntArr[i] - curInt < 0){ //현재 시간보다 뒤면 패스, 같으면 팔아야한다.
                continue;
            }
            remain -= numArr[i];
            if(remain <= 0){ //빵이 다 팔리면 현재 시간을 기록하고, 정지
                stopTimeIdx = i;
                break;
            }
        }
        if(stopTimeIdx != -1){
            answer = timeIntArr[stopTimeIdx] - curInt;
        }else{
            answer = -1;
        }

        return answer;
    }
}
