import java.util.*;

class Solution {
    //referral를 사용해서 노드끼리 연결 한다. => 인접 리스트로 만든다.
    //amount 마지막 부터 올라가면서 결과를 만든다.
    
    public static String[] Enroll, Referral, Seller;
    public static int[] Amount;
    public static List[] listArr;
    public static Map<String, Integer> mapper;
    public static int[] result;
    
    public static void makeList(String name, int idx){
        if(!name.equals("-")){ //제일 위가 아니면
            listArr[idx].add(name); //밑에서 부터 자기 부모의 이름을 가지고 있다.
        }
    }
    
    public static void sell(String name, int earn){
        int parent = earn/10;
        int mine = earn-parent;
        result[mapper.get(name)] += mine;
        if(listArr[mapper.get(name)].isEmpty() || parent==0){
            return;
        }
        sell(listArr[mapper.get(name)].get(0).toString(), parent);
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Enroll = enroll;
        Referral = referral;
        Seller = seller;
        Amount = amount;
        listArr = new List[Enroll.length];
        mapper = new HashMap<>();
        result = new int[Enroll.length];
        
        for(int i=0;i<Enroll.length;i++){
            listArr[i] = new ArrayList<String>();
            mapper.put(Enroll[i], i);
        }
        
        for(int i=0;i<Referral.length;i++){
            makeList(Referral[i], i);
        }
        
        for(int i=0;i<Seller.length;i++){
            sell(Seller[i], Amount[i]*100);
        }
        
        int[] answer = result;
        return answer;
    }
}