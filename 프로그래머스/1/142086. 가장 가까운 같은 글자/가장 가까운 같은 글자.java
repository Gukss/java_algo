import java.util.*;

class Solution {
    public int[] solution(String s) {
        //map에 저장
        //반복문 돌면서 1씩 증가시키기
        //contains로 판별
        int len = s.length();
        int[] answer = new int[len];
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0;i<len;i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                answer[i] = map.get(c) + 1;
                map.put(c, -1);
            } else { //처음 나온 경우
                answer[i] = -1;
                map.put(c, -1);
            }
            for (char x: map.keySet()) {
                int num = map.get(x);
                map.put(x, num+1);
            }
        }
        return answer;
    }
}