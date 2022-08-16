package _20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bj_5430_AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String p = br.readLine();
            int n = Integer.parseInt((br.readLine()));
            st = new StringTokenizer(br.readLine(), "[,]");
            Deque<Integer> dq = new ArrayDeque<>();
            boolean error = false;
            boolean first = true;

            for (int j = 0; j < n; j++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            for(char x: p.toCharArray()){
                if(x=='R'){ //뒤집기
                    first = !first;
                }else if(x=='D'){ //버리기
                    if(dq.size() == 0){
                        error = true;
                        break;
                    }
                    if(first){
                        dq.pollFirst();
                    }else{
                        dq.pollLast();
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            if(error){
                sb.append("error");
            }else{
                sb.append("[");
                if(first){
                    while(dq.size()>1){
                        sb.append(dq.pollFirst() + ",");
                    }
                    if(dq.size()>0){
                        sb.append(dq.pollFirst());
                    }
                }else{
                    while(dq.size()>1){
                        sb.append(dq.pollLast() + ",");
                    }
                    if(dq.size()>0){
                        sb.append(dq.pollLast());
                    }
                }
                sb.append("]");
            }
            System.out.println(sb.toString());
        }
    }
}
/*
1
D
1
[10]

*/

