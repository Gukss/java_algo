package _20220911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bj_1259_palindrome {
    static Deque<Integer> dq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            boolean flag = false;
            dq = new ArrayDeque<>();
            String str = br.readLine();
            if(str.equals("0")){
                break;
            }
            for (int i = 0; i < str.length(); i++) {
                int temp = str.charAt(i) - '0';
                dq.add(temp);
            }
            while(!dq.isEmpty()){
                int first = -1;
                int last = 0;
                if(dq.size() != 1){
                    first = dq.pollFirst();
                    last = dq.pollLast();
                }else{
                    break;
                }
                if(first != last){
                    flag = true;
                    break;
                }
            }
            if(flag){
                System.out.println("no");
            }else{
                System.out.println("yes");
            }
        }
    }
}
