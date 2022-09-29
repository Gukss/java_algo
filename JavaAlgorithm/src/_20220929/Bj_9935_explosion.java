package _20220929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bj_9935_explosion {
    static String str, ex;
    static Stack<Character> st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        ex = br.readLine();
        st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            st.add(temp);
            if(temp == ex.charAt(ex.length()-1)){ //마지막 문자가 같으면
                Stack<Character> tempst = new Stack<>();

                if(ex.length()<=st.size()){
                    for (int j = 0; j < ex.length(); j++) {
                        tempst.add(st.pop()); //폭탄 문자열 길이만큼 tempst에 저장
                    }
                }
                StringBuilder sb = new StringBuilder();
                int size = tempst.size();
                for (int j = 0; j < size; j++) {
                    sb.append(tempst.pop());
                }
                if(!ex.equals(sb.toString())) { //폭탄 문자열과 tempst에 저장된 문자열이 같으면 넘어가고 같지 않으면 다시 돌려놓기
                    for (int j = 0; j < sb.toString().length(); j++) {
                        st.add(sb.toString().charAt(j));
                    }
                }

            }
        }
        StringBuilder sb = new StringBuilder();
        int size = st.size();
        for (int j = 0; j < size; j++) {
            sb.append(st.pop());
        }
        sb.reverse();
        if(sb.toString().equals("")){
            System.out.println("FRULA");
        }else{
            System.out.println(sb);
        }
    }
}
