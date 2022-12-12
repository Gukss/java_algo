package _20221105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj_10610_30 {
    static String str;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        arr = str.toCharArray();
        Arrays.sort(arr);
        int sum = 0;
        String result = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sum += Integer.parseInt(String.valueOf(arr[i]));
            sb.append(arr[i]);
        }
//        for (int i = arr.length-1; i >= 0; i--) {
//            result += arr[i];
//        }
        if(sum % 3 == 0 && arr[0]=='0'){
            System.out.println(sb.reverse());
        }else{
            System.out.println(-1);
        }
    }
}
