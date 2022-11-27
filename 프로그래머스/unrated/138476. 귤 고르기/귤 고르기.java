import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public static int result,N;
    public static int[] map;
    public static Tan[] store;
    public static int maxValue;
    
    public static void start(){
        for(int i=0;i<map.length;i++){
            store[map[i]].count+=1;
            store[map[i]].weight = map[i];
        }
        Arrays.sort(store, new Comparator<Tan>() {
            @Override
            public int compare(Tan o1, Tan o2) {
                if(o1.count== o2.count){
                    return o2.weight - o1.weight;
                }else{
                    return o2.count - o1.count;
                }
            }
        });
        int count=0;
        for (int i = 0; i < N; i++) {
            if(count >= N){
                break;
            }
            count += store[i].count;
            result += 1;
        }
    }
    
    public int solution(int k, int[] tangerine) {
        N = k;
        map = tangerine;
        store = new Tan[10000001];
        for (int i = 0; i < 10000001; i++) {
            store[i] = new Tan(0,0);
        }
        start();
        
        
        return result;
    }
    
    public static class Tan{
        int count, weight;
        public Tan(int count, int weight){
            this.count = count;
            this.weight = weight;
        }
    }
}