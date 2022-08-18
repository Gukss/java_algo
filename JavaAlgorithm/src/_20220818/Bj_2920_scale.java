package _20220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2920_scale {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[8];

        int[] as = {1,2,3,4,5,6,7,8};
        int[] ds = {8,7,6,5,4,3,2,1};
        for (int i = 0; i < 8; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(Arrays.equals(arr, as)){
            System.out.println("ascending");
        }else if(Arrays.equals(arr, ds)){
            System.out.println("descending");
        }else{
            System.out.println("mixed");
        }
    }
}
