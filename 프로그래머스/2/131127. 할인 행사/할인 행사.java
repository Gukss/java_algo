import java.util.*;

class Solution {
    public static String[] Want, Discount;
    public static int[] Number;
    public static HashMap<String, Integer> map;
    public int solution(String[] want, int[] number, String[] discount) {
        //슬라이딩 윈도우로 푼다. 제일 처음 number개수 다 더한 만큼 discount에서 개수 세서 거기-1이 eidx, sidx는 0
        Want = want;
        Discount = discount;
        Number = number;
        map = new HashMap<>();
        int eidx = 0;
        int sidx = 0;
        int result = 0;
        
        for(int i=0;i<want.length;i++){ 
            //처음 Map에 넣기: want에 존재하는지 유무와 개수 세기용
            map.put(Want[i], Number[i]);
            eidx += Number[i];
        }
        eidx -= 1;
        // if(count > discount.length){
        //     return 0;
        // }
        
        for(int i=0;i<=eidx;i++){ //처음 한 번만 다 세면 된다.
            if(map.containsKey(discount[i])){ //map에 포함되어있으면 개수를 빼면된다.
                map.put(discount[i], map.get(discount[i])-1); //개수를 뺀다.
            }
        }
        boolean flag = false;
        for(String x: map.keySet()){
            if(map.get(x) > 0){
                flag = true;
                break;
            }
        }
        if(!flag){
            result += 1;
        }else{
            flag = false;
        }
        // for(String x: map.keySet()){
        //     System.out.println(x+": "+map.get(x));
        // }
        // int ii = Discount.length - eidx + 2;
        // for(int i=ii;i<Discount.length;i++){ //i는 그냥 개수일 뿐이다.
        while(eidx!=Discount.length-1){
            //시작을 map에 있음 더해놓고 sidx증가
            if(map.containsKey(discount[sidx])){
                map.put(discount[sidx], map.get(discount[sidx])+1);
            }
            sidx += 1;
            //끝은 증가시킨 후 map에 있음 더하고 count감소
            eidx += 1;
            if(map.containsKey(discount[eidx])){
                map.put(discount[eidx], map.get(discount[eidx])-1);
            }
            for(String x: map.keySet()){
                if(map.get(x) > 0){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result += 1;
            }else{
                flag = false;
            }
            // System.out.println("---");
            // for(String x: map.keySet()){
            //     System.out.println(x+": "+map.get(x));
            // }
        }
        // System.out.println(result);
        return result;
    }
}
//1시간