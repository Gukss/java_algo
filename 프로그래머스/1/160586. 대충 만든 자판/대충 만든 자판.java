import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        //map을 만들어서 알파벳을 key로 두고, 
        //keymap String을 돌면서 가장 작은 숫자를 value로 저장해놓는다.
        int[] answer = new int[targets.length];
        Map<Character, Integer> map = new HashMap<>();
        for(String x: keymap){
            int len = x.length();
            for(int i=0;i<len;i++){
                char ch = x.charAt(i);
                if(!map.containsKey(ch)){
                    map.put(ch, i+1);
                }else{
                    int index = map.get(ch);
                    if(index > i+1){
                        map.put(ch, i+1);
                    }
                }
            }
        }
        
        for(int j=0;j<targets.length;j++){
            int len = targets[j].length();
            int result = 0;
            for(int i=0;i<len;i++){
                char ch = targets[j].charAt(i);
                if(map.containsKey(ch)){
                     result += map.get(ch);
                }else{
                    result = -1;
                    break;
                }
            }
            answer[j] = result;
        }
        return answer;
    }
}