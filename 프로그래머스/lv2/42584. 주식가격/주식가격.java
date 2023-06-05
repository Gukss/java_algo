import java.util.*;

class Solution {
    /*
    스택에 가격과 idx를 같이 클래스로 만들어서 넣고,
    이전 가격을 저장하고 있다가 떨어지면 push 전에 pop을 먼저 한다. (같은 가격이 나올 때 까지)
    pop하면 클래스의 idx와 현재 순서를 비교해서 몇초간 스택에 들어있었는지 확인해서 배열에 넣어준다.
    모두 pop하면 push 를 한다.
    배열을 모두 반복했으면 스택을 pop하면서 비운다. 이때는 length-idx해서 배열을 채워준다.
    */
    public static int size;
    public static Stack<P> st;
    
    public int[] solution(int[] prices) {
        size = prices.length;    
        int[] answer = new int[size];
        st = new Stack<>();
        int before = 0;
        for(int i=0;i<size;i++){ //idx: 0부터
            if(before > prices[i]){ //이전 가격 보다 떨어지면
                //while 돌릴때 조건 확인 잘하기(반대로 생각한다.)
                //&& != 조건으로 돌렸는데, > 조건이었다. => 조건 확인 잘하기
                while(!st.isEmpty() && st.peek().p > prices[i]){
                    P a = st.pop();
                    answer[a.idx] = i - a.idx; //todo: 숫자 확인하기
                }
            }
            st.push(new P(prices[i], i));
            before = prices[i];
        }
        while(!st.isEmpty()){
            P a = st.pop();
            answer[a.idx] = size - a.idx-1; //todo: 숫자 확인하기
        }
        return answer;
    }
    
    public static class P{
        int p;
        int idx;
        public P(int p, int idx){
            this.p = p;
            this.idx = idx;
        }
    }
}