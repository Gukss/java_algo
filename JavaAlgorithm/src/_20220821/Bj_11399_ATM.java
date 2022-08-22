package _20220821;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Bj_11399_ATM {
    static Integer[] time;
    static int N;
    static boolean[] v;
    static int[] sel;
    static int result;

    public static void perm(int idx){
        if(idx == sel.length){
            int sum = 0;
            for (int i = 0; i < sel.length; i++) {
                for (int j = i; j < sel.length; j++) {
                    sum += time[sel[j]];
                }
            }
            result = Math.min(result, sum);
            return;
        }
        for (int i = 0; i < time.length; i++) {
            if(v[i]) continue;
            v[i] = true;
            sel[idx] = time[i];
            perm(idx+1);
            v[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        time = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }
//        v = new boolean[N];
//        sel = new int[N];
//        result = Integer.MAX_VALUE;
//
//        perm(0);
        Arrays.sort(time, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sum += time[j];
            }
        }
        System.out.println(sum);
    }
}
//처음 순열로 모든 경우의 수를 구하고 답을 구했다. -> 정렬로 작은 수 부터 누적합 구하면 된다.
//30분