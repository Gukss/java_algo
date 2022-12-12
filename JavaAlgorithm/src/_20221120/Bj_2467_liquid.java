package _20221120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2467_liquid {
    public static int N;
    public static int[] map;
    public static int rleft,rright,diff;
    public static void start(){
        int left = 0;
        int right = N-1;
        rleft = left;
        rright = right;
        int ldiff;
        int rdiff;
        diff = Math.abs(map[left] + map[right]);
        while(left<right){

            ldiff = Math.abs(map[left]+map[right-1]);
            rdiff = Math.abs(map[right]+map[left+1]);
            if(Math.abs(map[left] + map[right]) < diff){
                diff = Math.abs(map[left] + map[right]);
                rleft = left;
                rright = right;
            }
            if(ldiff > rdiff){ //왼쪽 기준으로 한 절댓값이 더 크면 왼쪽을 당긴다.
                left += 1;
            }else if(ldiff<rdiff){
                right -= 1;
            }else{
                left += 1;
                right -= 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        diff = Integer.MAX_VALUE;
        start();
        System.out.println(map[rleft] + " " +map[rright]);
    }
}
/*
10
-5 -5 -5 1 1 10 10 10 10 10
ans: 1 1

2
999999999 1000000000
ans: 999999999 1000000000
45분
 */