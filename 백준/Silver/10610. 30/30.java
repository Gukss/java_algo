import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static String str;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        arr = str.toCharArray();
        Arrays.sort(arr);
        int sum = 0;
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            sum += Integer.parseInt(String.valueOf(arr[i]));
        }
        for (int i = arr.length-1; i >= 0; i--) {
            result += arr[i];
        }
        if(sum % 3 == 0 && arr[0]=='0'){
            System.out.println(result);
        }else{
            System.out.println(-1);
        }
    }
}
