package _20221127;

import java.util.Arrays;
import java.util.Comparator;

public class PG_tangerine {
    public static int N;
    public static int[] map;
    public static int result;
    public static Tan[] store;


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
        for (int i = 0; i < N; i++) {
            int count=0;
            if(count >= N){
                break;
            }
            count += store[i].count;
            result += 1;
        }
    }

    public static void main(String[] args) {
        int answer = 0;
        map = new int[]{1, 3, 2, 5, 4, 5, 2, 3};
        N = 6;
        store = new Tan[10000001];
        for (int i = 0; i < 10000001; i++) {
            store[i] = new Tan(0,0);
        }
        start();

        result = Integer.MAX_VALUE;
    }

    public static class Tan{
        int count, weight;
        public Tan(int count, int weight){
            this.count = count;
            this.weight = weight;
        }
    }
}
