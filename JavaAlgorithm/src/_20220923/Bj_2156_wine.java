package _20220923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj_2156_wine {
    static int N;
    static int[] wine;
    static int[] data;

    public static int drink(int idx){
        if(data[idx]==-1){
            //oox, oxo, xoo
            data[idx] = Math.max(drink(idx-1), Math.max(drink(idx-2)+wine[idx], drink(idx-3) + wine[idx]+wine[idx-1]));
        }
        return data[idx];
//        for (int i = 1; i < N; i++) {
//            if(i==1){
//                data[1] = data[0] + wine[1];
//                continue;
//            }
//            if(i==2){
//                data[2] = Math.max(data[1], Math.max(wine[2]+wine[0], wine[1] + wine[2]));
//                continue;
//            }
//            data[i] = Math.max(data[i-1], Math.max(data[i-2]+wine[i], data[i-3] + wine[i]+wine[i-1]));
//        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        wine = new int[N];
        data = new int[N];
        Arrays.fill(data, -1);

        for (int i = 0; i < N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        data[0] = wine[0];

        if(N>=2){
            data[1] = data[0] + wine[1];
        }
        if(N>=3){
            data[2] = Math.max(data[1], Math.max(wine[2]+wine[0], wine[1] + wine[2]));
        }
        drink(N-1);
        System.out.println(data[N-1]);

    }
}
/*
https://limkydev.tistory.com/112

2
63
54
ans: 117

10
0
0
10
0
5
10
0
0
1
10
ans: 36
 */