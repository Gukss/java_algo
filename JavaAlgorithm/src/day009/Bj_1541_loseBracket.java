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

        boolean flag = true;
        Queue<Character> opq = new LinkedList<>();
        for(char x: num.toCharArray()){
            if(x=='+' || x=='-'){
                opq.add(x);
            }
        }
        int sum1 = Integer.parseInt(st.nextToken());
        int sum2 = 0;
        int loop = opq.size();
        boolean check = false;
        for (int i = 0; i < loop; i++) { //연산자 개수만큼 순회
            char op = opq.poll();
            if(op == '-' && !check){ //연산자가 -이고 flag가 트루면 괄호시작
                flag = !flag;
                check = !check;
            }
            if(flag){ //첫 - 만나기 전

                sum1 += Integer.parseInt(st.nextToken());

            }else{ //첫 - 만나고 나서 ( 시작

                sum1 -= Integer.parseInt(st.nextToken());

            }
        }
        System.out.println(sum1 - sum2);


    }
}
/*
* 1-1-1
* 5-0+5
* */
