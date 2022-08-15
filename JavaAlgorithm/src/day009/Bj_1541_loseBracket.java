package day009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_1541_loseBracket {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        StringTokenizer st = new StringTokenizer(num, "-+");

        Queue<Character> opq = new LinkedList<>();
        for(char x: num.toCharArray()){
            if(x=='+' || x=='-'){
                opq.add(x);
            }
        }

        int sum1 = Integer.parseInt(st.nextToken());
        int loop = opq.size();
        boolean flag = true;
        boolean check = false;
        for (int i = 0; i < loop; i++) { //연산자 개수만큼 순회
            char op = opq.poll();
            if(op == '-' && !check){ //연산자가 -이고 check가 false면 첫 '-'등장
                flag = !flag;
                check = !check;
            }
            if(flag){
                sum1 += Integer.parseInt(st.nextToken());
            }else{
                sum1 -= Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(sum1);


    }
}
/*
* 1-1-1
* 5-0+5
* */
