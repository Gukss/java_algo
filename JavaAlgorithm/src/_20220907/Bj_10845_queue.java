package _20220907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_10845_queue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> qu = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            switch(op){
                case "push":
                    int temp = Integer.parseInt(st.nextToken());
                    qu.add(temp);
                    break;
                case "pop":
                    if(!qu.isEmpty()){
                        System.out.println(qu.poll());
                    }else{
                        System.out.println(-1);
                    }
                    break;
                case "size":
                    System.out.println(qu.size());
                    break;
                case "empty":
                    if(qu.isEmpty()){
                        System.out.println(1);
                    }else{
                        System.out.println(0);
                    }
                    break;
                case "top":
                    if(!qu.isEmpty()){
                        System.out.println(qu.peek());
                    }else{
                        System.out.println(-1);
                    }
                    break;
                case "front":
                    if(!qu.isEmpty()){
                        System.out.println(qu.peekFirst());
                    }else{
                        System.out.println(-1);
                    }
                    break;
                case "back":
                    if(!qu.isEmpty()){
                        System.out.println(qu.peekLast());
                    }else{
                        System.out.println(-1);
                    }
                    break;
            }
        }
    }

}
