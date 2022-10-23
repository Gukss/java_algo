package 순조부;

import java.util.Arrays;

public class pcp {
    static int[] sel;
    static int[] arr = {1,3,5};
    static boolean[] v;

    public static void overlapperm(int idx){
        if(idx==sel.length){
            System.out.println(Arrays.toString(sel));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
//            if(v[i]) continue;
//            v[i] = true;
            sel[idx] = arr[i];
            perm(idx+1);
//            v[i] = false;
        }
    }

    public static void perm(int idx){
        if(idx==sel.length){
            System.out.println(Arrays.toString(sel));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if(v[i]) continue;
            v[i] = true;
            sel[idx] = arr[i];
            perm(idx+1);
            v[i] = false;
        }
    }

    public static void comb(int idx, int start){
        if(idx==sel.length){
            System.out.println(Arrays.toString(sel));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            sel[idx] = arr[i];
            comb(idx+1, i+1);
        }
    }

    public static void overlabcomb(int idx, int start){
        if(idx==sel.length){
            System.out.println(Arrays.toString(sel));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            sel[idx] = arr[i];
            overlabcomb(idx+1, i);
        }
    }

    public static void powerset(int idx){
        if(idx==v.length){
            for (int i = 0; i < v.length; i++) {
                if(v[i]){
                    System.out.print(arr[i]+" ");
                }
            }
            System.out.println();
            return;
        }
        v[idx] = true;
        powerset(idx+1);
        v[idx] = false;
        powerset(idx+1);
    }

//    public static void cumperm(int idx, ){
//
//    }

    public static void main(String[] args) {
        sel = new int[2];
        v = new boolean[3];

//        perm(0);
//        System.out.println("==============");
//        overlapperm(0);
//        System.out.println("==============");
//        comb(0,0);
//        System.out.println("==============");
//        overlabcomb(0,0);
//        System.out.println("==============");
//        powerset(0);
//        System.out.println("==============");
//        cumperm(0);
    }
}