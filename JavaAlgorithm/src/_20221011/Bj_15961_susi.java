package _20221011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_15961_susi {
    static int N,d,k,c;
    static int[] map;
    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //접시 수
        d = Integer.parseInt(st.nextToken()); //초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); //연속 접시수
        c = Integer.parseInt(st.nextToken()); //쿠폰 번호

        map = new int[N];

        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        int count = 1;
        v = new int[d+1];
        v[c] += 1; //쿠폰을 먼저 사용하고
        for (int i = 0; i < k; i++) {
            if(v[map[i]] == 0) {
                count += 1;
            }
            v[map[i]] += 1;
        }
        int maxcount = count;
        for (int i = 0; i < N; i++) { //N을 1부터 했을 때 틀렸다.
            v[map[i]] -= 1;
            if(v[map[i]] == 0){
                count -= 1;
            }
            if(v[map[(i+k)%N]] == 0){
                count += 1;
            }
            v[map[(i+k)%N]] += 1;
            maxcount = Math.max(count, maxcount);
        }
        System.out.println(maxcount);
    }
}
