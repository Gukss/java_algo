import java.util.*;

class Solution {
    public static LinkedList<Long> list;
    
    public Long[] solution(int n, long left, long right) {
        list = new LinkedList<>();
        // long[] answer = {};
        // System.out.println("sr: "+sr+", sc"+sc+", len: "+answer.length);
        
        for(long i=left;i<=right;i++){
            long sr = i/n;
            long sc = i%n;
            if(sr<sc){
                list.add(sc+1);
            }else{
                list.add(sr+1);
            }
        }
        
        // for(long i=sr;i<n;i++){ //i는 숫자세기용
        //     for(long j=sc;j<n;j++){
        //         if(i<j){
        //             list.add(i+j);
        //         }else{
        //             list.add(i+1);
        //         }
        //     }
        // }
        
        Long[] answer = list.toArray(new Long[0]);
        return answer;
    }
}