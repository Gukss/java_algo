package _20220908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Bj_1062_teaching {
    static int N,K;
    static ArrayList<String> fixlist;
    static ArrayList<Character> alpalist;
    static boolean[] v;
    static char[] sel;
    static HashSet<Character> alpa;
    static int result;

    public static void comb(int idx, int start){
        if(idx == K ||idx==alpalist.size()){
            int sum = 0;
            for (int i = 0; i < fixlist.size(); i++) {
                String checkstr = fixlist.get(i);
                int check = 0;
                for (int j = 0; j < checkstr.length(); j++) {
                    for (int k = 0; k < sel.length; k++) {
                        if(checkstr.charAt(j) == sel[k]){
                            check += 1;
                            break;
                        }
                    }
                }
                if(check == checkstr.length()){
                    sum += 1;
                }
            }
            result = Math.max(result, sum);
            return;
        }
        for (int i = start; i < alpalist.size(); i++) {
            sel[idx] = alpalist.get(i);
            comb(idx+1, i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fixlist = new ArrayList<>();
        v = new boolean[27];
        sel = new char[K];
        alpalist = new ArrayList<>();
        result = Integer.MIN_VALUE;
        //조합문제
        //모든 알파벳 가지고 있는 리스트를 하나 만든다. -> set사용
        //고정 문자 미리 넣어놓은 조합 만든다.
        //앞 뒤 고정 문자열 뺀 문자열 돌면서 가능한지 수 세기
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            fixlist.add(temp);
//            if(temp.length() != 8){
//
//                fixlist.add(temp.substring(4, temp.length()-4)); //앞뒤 고정 문자열 뺀 문자열 => 그냥 문자열 다 넣기
//            }
        }
        if(K<5){
            System.out.println(0);
            return;
        }

        alpa = new HashSet<>();
        alpalist.add('a');
        alpalist.add('n');
        alpalist.add('t');
        alpalist.add('i');
        alpalist.add('c');

        for (int i = 0; i < fixlist.size(); i++) {
            for (int j = 0; j < fixlist.get(i).length(); j++) {
                char temp = fixlist.get(i).charAt(j);
                alpa.add(temp);
            }
        }

        for(char x:alpa){
            if(alpalist.indexOf(x) == -1){
                alpalist.add(x);
            }
        }

        sel[0] = 'a';
        sel[1] = 'n';
        sel[2] = 't';
        sel[3] = 'i';
        sel[4] = 'c';

        comb(5, 5);
        System.out.println(result==Integer.MIN_VALUE ? 0 : result);
    }
}
//1시간 30분
/*
1 5
antatica
ans: 1

3 7
antawxtica
antaytica
antaztica
ans: 2

2 25
antatica
antaztica
ans: 2
 */