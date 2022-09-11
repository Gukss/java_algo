package _20220909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_11866_Josephus {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> map = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            map.add(i);
        }

        int idx = K;
        StringBuilder sb = new StringBuilder();
        sb.append('<');

        int cnt = 1;
        while(map.size()!=1){
            if(cnt % K==0){
                sb.append(map.poll()+ ", ");
                cnt+= 1;
                continue;
            }
            cnt+=1;
            int temp = map.poll();
            map.add(temp);
        }

        sb.append(map.poll());
        sb.append('>');
        System.out.println(sb);
    }
}
