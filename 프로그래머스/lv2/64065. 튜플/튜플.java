import java.util.*;

class Solution {
    public static String S;
    // public static Set<Integer> result1111; 처음 중복제거를 위해 set으로 접근했다. but 정렬을 위해 빈도를 저장할 필요가 있어 map으로 숫자와 빈도를 저장하기로 했다.
    public static Map<Integer, Integer> map;
    public static int size;
    public static PriorityQueue<Pos> pq; //정렬을 위한 pq
    public static int[] result;
    
    public static void check(){
        StringTokenizer st = new StringTokenizer(S, "{},"); //{},로 구분하면 숫자만 문자열로 나온다.
        
        while(st.hasMoreTokens()){ //다음 토큰이 있을 때 까지 반복문 돌면서 map에 숫자와 빈도를 저장한다.
            int key = Integer.parseInt(st.nextToken());
            if(map.get(key) != null){
                map.put(key, map.get(key)+1);
            }else{
                map.put(key, 1);              
            }
        }
        
        for(int x: map.keySet()){ //정렬을 위해 pq에 값을 넣어준다.
            pq.add(new Pos(x, map.get(x)));
        }
        
        result = new int[map.size()]; //정답배열
        for(int i=0;i<result.length;i++){ //pq에서 하나씩 빼면서 정답배열에 저장한다.
            Pos target = pq.poll();
            result[i] = target.num;
        }
    }
    
    public int[] solution(String s) {
        S = s;
        size = S.length();
        map = new HashMap<>();
        pq = new PriorityQueue<>(new Comparator<Pos>(){ //내림차순 정렬하는 pq
            public int compare(Pos o1, Pos o2){
                return o2.count-o1.count;
            }
        });
        check();
        
        int[] answer = result;
        return answer;
    }
    
    public static class Pos{ //pq에 넣을 때 정렬하기 위한 클래스
        int num,count;
        
        Pos(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
}
