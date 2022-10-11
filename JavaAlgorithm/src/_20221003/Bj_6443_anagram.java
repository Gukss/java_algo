package _20221003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_6443_anagram {
    static int N;
    static int[] ch;
    static Set<String> set;
    static String inputstr;
    static Stack<Character> st;
    public static void perm(int idx){
        if(idx==inputstr.length()){
            StringBuilder sb = new StringBuilder();
            for(char x: st){
                sb.append(x);
            }
            sb.append("\n");
            set.add(sb.toString());
            return;
        }
        for (int i = 0; i < inputstr.length(); i++) {
            if(ch[inputstr.charAt(i)-'a'] <= 0) continue;
            ch[inputstr.charAt(i)-'a'] -= 1;
            st.push(inputstr.charAt(i));
            perm(idx+1);
            st.pop();
            ch[inputstr.charAt(i)-'a'] += 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder totalsb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            ch = new int[26];
            inputstr = br.readLine();
            for (int j = 0; j < inputstr.length(); j++) {
                ch[inputstr.charAt(j) - 'a'] += 1;
            }
            set = new HashSet<>();
            st = new Stack<>();
            perm(0);
            String[] setarr = set.toArray(new String[0]);
            Arrays.sort(setarr);
            for (int j = 0; j < setarr.length; j++) {
                totalsb.append(setarr[j]);
            }
//            for(String x: set){
//                totalsb.append(x);
//            }
        }
        System.out.println(totalsb);
    }
}
