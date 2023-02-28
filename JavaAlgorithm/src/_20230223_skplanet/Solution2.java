package _20230223_skplanet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {

    public static List[] arr;
    public static int size;
    public static int[] answer;
    public static int result;

    public static void main(String[] args) {
        int[] p = {2,2,-1,1,5,-1,5};
        int[] b = {2,5};

        solution(p, b);
    }

    public static void makeArr(int[] p,int[] b, int idx){
        if(p[idx] != -1){
            arr[p[idx]].add(idx);
        }
    }

    public static int[] solution(int[] p, int[] b) {
        size = p.length;
        answer  = new int[b.length];
        arr = new List[size];
        result = 0;

        for (int i = 0; i < size; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        for (int j = 0; j < size; j++) {
            makeArr(p,b,j);
        }


        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < b.length; i++) {
            result = 0;
            for (int j = 0; j < arr[b[i]].size(); j++) {
                int num = (int)arr[b[i]].get(j);
                result += 1;
                count(num, 1);
                answer[i] = result;
            }
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void count(int num, int c){
        for (int i = 0; i < arr[num].size(); i++) {
            int n = (int)arr[num].get(i);
            result += 1;
            count(n, c+1);
        }
    }
}
