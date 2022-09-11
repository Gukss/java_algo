package _20220908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bj_10866_deque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> qu = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            switch(op){
                case "push_front":
                    int temp = Integer.parseInt(st.nextToken());
                    qu.addFirst(temp);
                    break;
                case "push_back":
                    int temp2 = Integer.parseInt(st.nextToken());
                    qu.addLast(temp2);
                    break;
                case "pop_front":
                    if(!qu.isEmpty()){
                        System.out.println(qu.pollFirst());
                    }else{
                        System.out.println(-1);
                    }
                    break;
                case "pop_back":
                    if(!qu.isEmpty()){
                        System.out.println(qu.pollLast());
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
