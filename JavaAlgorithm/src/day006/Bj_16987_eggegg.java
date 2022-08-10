package day006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Bj_16987_eggegg {
    static int[][] e;
    static int N;
    static Queue<int[][]> qu;
    static int max;
    static int result;
    //0번 부터 시작해서 1번을 쳤을 때, 2번을 쳤을 때... 마지막 을 쳤을 때
    //그다음 1번을 들고 0번이 깨지지 않았으면 0번부터 시작해야 한다.
    public static void egg(int idx){
        if(idx == N){
            result = Math.max(max, result);
            return;
        }
        for (int i = 0; i < N; i++) {
            //들고있는 계란이랑 같을 때 or 계란이 깨졌을 때 -> 다음계란 깨러
            if(i==idx || e[i][0] <= 0)continue;
            //들고있는 계란이 깨진 계란일 때
            if(e[idx][0] <= 0) {
                //idx+1
                egg(idx+1);
            }else{
                //계란 깨기
                e[i][0] -= e[idx][1];
                e[idx][0] -= e[i][1];
                if(e[i][0] <= 0){
                    max++;
                }
                if(e[idx][0] <= 0){
                    max++;
                }
                egg(idx+1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        //N번째 계란, 내구도, 무게
        e = new int [N][2];
        result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            e[i][0] = Integer.parseInt(st.nextToken());
            e[i][1] = Integer.parseInt(st.nextToken());
        }
        max = 0;
        egg(0);
        System.out.println(result);

    }
}

// 종료 조건에서 몇 개의 계란이 깨졌는지 확인해야 한다.
// 계란을 깨고 다음 단계로 들어갔으면 그 단계에서 탈출했을 때 다음 반복을 위해 깬계란을 돌려놔야 한다.