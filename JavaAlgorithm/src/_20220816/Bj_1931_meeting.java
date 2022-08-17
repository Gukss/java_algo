package _20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Bj_1931_meeting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] time = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time, new Comparator<int[]>() { //배열 끝나는 시간을 기준으로 오름차순 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int result = 1; //첫 회의실 사용가능 숫자는 1
        int et = time[0][1]; //제일 빨리 끝나는 회의의 끝나는 시간을 저장
        for (int i = 1; i < N; i++) { //회의 스케줄을 반복하면서 끝나는 시간보다 시작 시간이 뒤인(겹치지 않는) 회의를 배정
            if(et<=time[i][0]){
                et = time[i][1];
                result += 1;
            }
        }
        System.out.println(result);
    }
}
/*
5
6 7
6 6
5 6
5 5
7 7
 */
//20분