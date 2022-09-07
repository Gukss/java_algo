package _20220904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.Stack;
import java.util.StringTokenizer;

public class Bj_9012_bracket {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            boolean flag = true;
            st = new Stack<>();
            for (int j = 0; j < temp.length(); j++) {
                if(temp.charAt(j) == '('){
                    st.push('(');
                }else{
                    if(!st.isEmpty()){
                        st.pop();
                    }else{
                        flag = false;
//                        System.out.println("NO");
                        break;
                    }
                }
            }
            if(!flag){
                System.out.println("NO");
                continue;
            }
            if(!st.isEmpty()){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }


        }


    }
}
