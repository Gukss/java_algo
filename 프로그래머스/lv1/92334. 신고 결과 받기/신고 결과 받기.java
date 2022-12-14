import java.util.*;

class Solution {
    //전체 id_list 길이만큼 신고를 저장할 map을 원소로 가지는 리스트 ban_list을 만든다.
    //=> 한 사람에 대해 한 번의 신고만 유효하기 때문에 set으로 해도된다.
    //set_list를 만들어서 해당하는 idx에 신고를 저장하고, 신고한 것도 동일하게 저장한다.
    //신고당한 개수가 k보다 큰 
    
    public static int K, size;
    public static Set[] banList, setList;
    public static String[] idList;
    public static String[] Report;
    public static Map<String, Integer> mapper;
    public static int[] result, banResult;
    
    public static void ban(){
        for(String x:Report){
            StringTokenizer st = new StringTokenizer(x);
            // System.out.println(st.nextToken());
            // System.out.println(st.nextToken());
            // System.out.println("==========");
            // System.out.println(mapper.get(st.nextToken()));
            String one = st.nextToken(); //one->two
            String two = st.nextToken();
            banList[mapper.get(two)].add(one); //내가 신고 당한것 => banList[i] 조회하면 idx가 누구한테 신고당했는지 적혀있다.
            setList[mapper.get(one)].add(two); //내가 신고 한 것
        }
    }
    
    public static void check(){
        for(int i=0;i<size;i++){
            if(banList[i].size()>=K){ //신고한 사람이 정지횟수 이상이면 => 정지메일을 발송한다.
                for(Object x: banList[i]){
                    result[mapper.get((String) x)] += 1;
                }
                // System.out.println(banList[i]);
            }
            // System.out.println("=======");
        }
    }
    
    public int[] solution(String[] id_list, String[] report, int k) {
        K = k; //전역으로 바꿔주기
        idList = id_list;
        Report = report;    
        
        size = idList.length; //초기화
        result = new int[size];
        banResult = new int[size]; //신고 누적횟수 저장
        banList = new HashSet[size];
        setList = new HashSet[size];
        mapper = new HashMap<>();
        for(int i=0;i<size;i++){
            banList[i] = new HashSet<String>();
            setList[i] = new HashSet<String>();
            mapper.put(idList[i], i);
        }
        
        
        ban(); //신고
        
        check(); //신고확인
        
        int[] answer = {};
        answer = result;
        return answer;
    }
}