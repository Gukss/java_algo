package _20220815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_9019_DSLR {
    static int A, B;
    static Queue<String[]> qu;
    static StringBuilder sb;
    static boolean visited[];
    public static void bfs(){
        qu.add(new String[] {Integer.toString(A), ""});
        visited[A] = true;
        while(!qu.isEmpty()){
            String[] cur = qu.poll();
            for (int i = 0; i < 4; i++) {
                int n = Integer.parseInt(cur[0]);
                String temp = cur[1];
                sb = new StringBuilder();

                if(i==0){
                    n = (n * 2) % 10000;
                    sb.append(temp+"D");
                }
                else if(i==1){
                    if (n==0){
                        n=9999;
                    }else{
                        n-=1;
                    }
                    sb.append(temp+"S");
                }
                else if(i==2){
                    int nn = (n%1000)*10 + (n/1000);
                    n = nn;
                    sb.append(temp+"L");
                }
                else if(i==3) {
                    int nnn = ((n % 10) * 1000) + (n / 10);
                    n = nnn;
                    sb.append(temp + "R");
                }
                if(!visited[n]){
                    visited[n] = true;
                    if(n == B){
                        System.out.println(sb.toString());
                        return;
                    } else{
                        qu.add(new String[] {Integer.toString(n), sb.toString()});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            qu = new LinkedList<>();
            visited = new boolean[10000];
            bfs();
        }
    }
}

//메모리 초과 -> visited 배열을 사용해서 방문했던 숫자는 다시 방문하지 않게 했다.
//312 같은 숫자 0312로 생각해야 한다.
//s 연산은 n-1이 0보다 작거나 같아질 때가 아니라 n이 0일 때 9999가 된다.

/*
12
5 0
0 7
9 0
47 63
26 60
60 50
32 38
98 366
5310 6
7519 2006
7579 4005
2720 8031

정답
RD
SDRDRDRDDSDR
SDSRSD
RSDLS
RSSRR
RSL
RSLSSSD
SRDLSD
DRSSR
SDDRS
SRDDSDR
DDRSRDDS
*/