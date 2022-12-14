import java.util.*;

class Solution {
    //전체 id_list 길이만큼 신고를 저장할 map을 원소로 가지는 리스트 ban_list을 만든다.
    //=> 한 사람에 대해 한 번의 신고만 유효하기 때문에 set으로 해도된다.
    //set_list를 만들어서 해당하는 idx에 신고를 저장하고, 신고한 것도 동일하게 저장한다. => mapper가 있어서 필요없다.
    //신고당한 개수가 k보다 큰 banList[]를 반복하면서 answer[] += 1 해준다.
    
    public static int K, size;
    public static Set[] banList;//, setList;
    public static String[] idList;
    public static String[] Report;
    public static Map<String, Integer> mapper;
    public static int[] result;
    
    public static void ban(){ //신고를 반복하면서 banList에 저장한다.
        for(String x:Report){
            StringTokenizer st = new StringTokenizer(x);
            String one = st.nextToken(); //one->two 이 방향으로 신고한다.
            String two = st.nextToken();
            banList[mapper.get(two)].add(one); //내가 신고 당한것 => banList[i] 조회하면 idx가 누구한테 신고당했는지 적혀있다.
            // setList[mapper.get(one)].add(two); //내가 신고 한 것 => 내가 신고한 사람을 저장해서 결과 출력할 때 사용하려 했으나 
        }
    }
    
    public static void check(){ //banList를 반복하면서 신고를 확인한다.
        for(int i=0;i<size;i++){
            if(banList[i].size()>=K){ //신고한 사람(신고횟수)이 정지횟수 이상이면 => 정지메일을 발송한다.
                for(Object x: banList[i]){ //나를 신고한 사람은 메일을 받아야 한다.
                    result[mapper.get((String) x)] += 1;
                }
            }
        }
    }
    
    public int[] solution(String[] id_list, String[] report, int k) {
        K = k; //전역으로 바꿔주기
        idList = id_list;
        Report = report;    
        
        size = idList.length; //유저 숫자
        result = new int[size]; //메일 받을 횟수 저장할 배열
        banList = new HashSet[size]; //idx에 해당하는 유저가 신고 당한 것, 신고를 한 유저가 저장돼있다. => 신고당한 횟수를 알 수 있다.
        // setList = new HashSet[size]; //idx에 해당하는 유저가 신고 한 것, 신고를 당한 유저를 저장한다. => 사용하지 않았다.
        mapper = new HashMap<>(); //String을 int로 mapping한다.
        for(int i=0;i<size;i++){ //banList[]에 set을 초기화하고, 매퍼도 초기화한다.
            banList[i] = new HashSet<String>();
            // setList[i] = new HashSet<String>();
            mapper.put(idList[i], i);
        }
        
        
        ban(); //신고를 반복하면서 banList에 저장한다.
        check(); //banList를 반복하면서 신고를 확인한다.
        
        int[] answer = {};
        answer = result;
        return answer;
    }
}

//1시간
