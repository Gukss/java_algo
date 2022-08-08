package day005;

import java.util.*;

public class P_03 {
    public static void main(String[] args) {
        int answer = 0;

        int[] order = {4,3,1,2,5};
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> load = new ArrayList<>();

        int turn = 0;

        for (int i = 1; i <= order.length; i++) {
            if(i == order[turn]){
                load.add(i);
                turn += 1;
            }else{
                if(st.isEmpty()){
                    st.push(i);
                    continue;
                }
                if(order[turn] == st.peek()){
                    load.add(st.pop());
                    turn += 1;
                }
                st.push(i);
            }
        }

        while(!st.isEmpty()){
            int top = st.pop();
            if(order[turn] == top){
                load.add(top);
                turn += 1;
            }else{
                break;
            }
        }
        answer = load.size();

        //return answer;
    }
}
