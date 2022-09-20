package _20220920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj_10844_stepnumber {
    static int N;
    static long stepNo[][];
    public static long step(int idx, int num){
        if(stepNo[idx][num] != 0){ //이미 값이 있으면
            return stepNo[idx][num];
        }else{ //값이 없으면
            if(idx==1){ //한자리 까지 왔으면
                return stepNo[1][num];
            }
            if(num==9){ //9는 10(0)이 계단수가 아니다.
                return stepNo[idx][num] = (step(idx-1, num-1))%1000000000;
            }else if(num==0){ //0은 -1이 계단수가 아니다.
                return stepNo[idx][num] = (step(idx-1, num+1))%1000000000;
            }else{ //나머지는 +-1이 계단수다.
                return stepNo[idx][num] = (step(idx-1, num-1)+step(idx-1, num+1))%1000000000;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stepNo = new long[N+1][10];
        stepNo[1][0] = 0; //0으로 시작하는 수는 0
        long sum=0;

        for (int i = 1; i < 10; i++) { //한자리 숫자 미리 준비
            stepNo[1][i] = 1;
        }
        for (int i = 0; i < 10; i++) { //idx자리수 num으로 끝나는 계단수
            sum += step(N, i);
        }
        System.out.println(sum%1000000000);
    }
}
