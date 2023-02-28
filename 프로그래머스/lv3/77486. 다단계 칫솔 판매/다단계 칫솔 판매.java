import java.util.*;

class Solution {
    //referral를 사용해서 노드끼리 연결 한다. => 인접 리스트로 만든다.
    
    public static String[] Enroll, Referral, Seller;
    public static int[] Amount;
    public static List[] listArr;
    public static Map<String, Integer> mapper;
    public static int[] result;
    
    public static void makeList(String name, int idx){
        if(!name.equals("-")){ //제일 위가 아니면
            listArr[idx].add(name); //밑에서 부터 자기 부모의 이름을 가지고 있다. 부모노드는 하나밖에 없다.
        }
    }
    
    public static void sell(String name, int earn){
        int parent = earn/10; //부모에게 줄 10%
        int mine = earn-parent; //내가 가질 수익
        result[mapper.get(name)] += mine; //내 수익을 더한다.
        if(listArr[mapper.get(name)].isEmpty() || parent==0){ //parent가 0이면 더이상 재귀호출할 이유가 없다.=> 백트래킹 || 최상단 노드일 때
            return;
        }
        sell(listArr[mapper.get(name)].get(0).toString(), parent); //부모노드의 부모에게 10%를 준다.
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Enroll = enroll;
        Referral = referral;
        Seller = seller;
        Amount = amount;
        listArr = new List[Enroll.length];
        mapper = new HashMap<>();
        result = new int[Enroll.length];
        
        for(int i=0;i<Enroll.length;i++){ //{이름: idx}인 mapper, listArr초기화
            listArr[i] = new ArrayList<String>();
            mapper.put(Enroll[i], i);
        }
        
        for(int i=0;i<Referral.length;i++){ //인접 리스트를 만든다. 밑에서 부터 자기 부모의 이름을 가지고 있다.
            makeList(Referral[i], i);
        }
        
        for(int i=0;i<Seller.length;i++){ //수익을 계산한다.
            sell(Seller[i], Amount[i]*100);
        }
        
        int[] answer = result;
        return answer;
    }
}

//2시간
