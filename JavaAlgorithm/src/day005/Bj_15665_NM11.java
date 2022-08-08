package day005;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Bj_15665_NM11 {
    static Integer[] input;
    static int[] sel;
    static int N,M;
    static StringBuilder sb;

    public static void perm(int idx){
        if(idx == sel.length){
            for (int i = 0; i < sel.length; i++) {
                sb.append(sel[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < input.length; i++) {
            sel[idx] = input[i];
            perm(idx+1);
            //visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        N = sc.nextInt();
        M = sc.nextInt();

        input = new Integer[N];
        sel = new int[M];
        HashSet<Integer> input_set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            input_set.add(sc.nextInt());
        }
        input = input_set.toArray(new Integer[0]);
        Arrays.sort(input);

        perm(0);
        System.out.println(sb);
    }
}
