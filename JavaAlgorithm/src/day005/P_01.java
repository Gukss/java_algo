package day005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P_01 {
    static int[] sArr;
    static int[] sel;
    static boolean[] visited;
    static ArrayList<String> output;

    public static void perm(int idx){
        if(idx == sArr.length){
            String temp = "";
            for (int i = 0; i < sArr.length; i++) {
                temp+=sel[i];
            }
            output.add(temp);
            return;
        }
        for (int i = 0; i < sArr.length; i++) {
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            sel[idx] = sArr[i];
            perm(idx+1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        String answer = "";
        String X, Y;
        X = "100";
        Y = "2345";
        int[] xArr = new int[X.length()];
        int[] yArr = new int[Y.length()];

        
        ArrayList<Integer> same = new ArrayList<>();
        Queue<Integer> qu = new LinkedList<>();
        
        for (int i = 0; i < xArr.length; i++) {
            qu.add(Integer.parseInt(String.valueOf(X.charAt(i))));
//            xArr[i] = Integer.parseInt(String.valueOf(X.charAt(i)));
        }
        for (int i = 0; i < yArr.length; i++) {
            yArr[i] = Integer.parseInt(String.valueOf(Y.charAt(i)));
        }
        
        while(!qu.isEmpty()){
            int num = qu.poll();
            for (int i = 0; i < yArr.length; i++) {
                if(num == yArr[i]){
                    same.add(yArr[i]);
                    yArr[i] = -1;
                }
            }
        }


        sArr = new int[same.size()];
        for (int i = 0; i < same.size(); i++) {
            sArr[i] = same.get(i);
        }
        Arrays.sort(sArr);
        sel = new int[sArr.length];
        visited = new boolean[sArr.length];
        output = new ArrayList<>();
        perm(0);

        int hi = Integer.MIN_VALUE;

        if(output.get(0).equals("")){
            //return "-1";
        }
        for(String str: output){
            hi = Math.max(hi, Integer.parseInt(str));
        }
        System.out.println(Integer.toString(hi));

    }
}
