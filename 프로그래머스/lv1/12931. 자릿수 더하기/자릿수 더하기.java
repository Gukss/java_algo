import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        
        String str = Integer.toString(n);
        int len = str.length();
        for(int i=0;i<len;i++){
            answer += str.charAt(i) - '0';
        }
        
        return answer;
    }
}