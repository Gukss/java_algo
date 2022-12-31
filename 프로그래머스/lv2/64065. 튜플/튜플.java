import java.util.*;

class Solution {
    public static String S;
    public static Set<Integer> result1111;
    public static Map<Integer, Integer> map;
    public static int size;
    public static PriorityQueue<Pos> pq;
    public static int[] result;
    
    public static void check(){
        StringTokenizer st = new StringTokenizer(S, "{},");
        
        while(st.hasMoreTokens()){
            int key = Integer.parseInt(st.nextToken());
            if(map.get(key) != null){
                map.put(key, map.get(key)+1);
            }else{
                map.put(key, 1);              
            }
        }
        for(int x: map.keySet()){
            pq.add(new Pos(x, map.get(x)));
        }
        result = new int[map.size()];
        for(int i=0;i<result.length;i++){
            Pos target = pq.poll();
            result[i] = target.num;
        }
            // System.out.println(map.keySet());
            // System.out.println(map.values().size());
    }
    
    public int[] solution(String s) {
        S = s;
        // result = new HashSet<>();    
        size = S.length();
        map = new HashMap<>();
        pq = new PriorityQueue<>(new Comparator<Pos>(){
            public int compare(Pos o1, Pos o2){
                return o2.count-o1.count;
            }
        });
        check();
        
        int[] answer = result;
        return answer;
    }
    
    public static class Pos{
        int num,count;
        
        Pos(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
}