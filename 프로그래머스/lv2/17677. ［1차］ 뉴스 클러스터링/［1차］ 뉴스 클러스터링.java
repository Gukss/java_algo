import java.util.*;
import java.util.Arrays;

class Solution {
    //문자열을 모두 소문자로 바꿀 필요가 있다.
    //알파벳이 아닌 특수문자, 숫자, 공백은 버린다.
    //Map에 저장한다. => set에 각각 저장한다.
    public static String STR1,STR2;
    public static Set<String> set;
    public static Map<String, Integer> map1, map2;
    public static double max,min;
    
    public static void putMap(String str, Map map){
        for(int i=1;i<str.length();i++){ //index 1부터 끝까지 반복한다.
            String subString = ""; //임시 문자열
            subString += str.substring(i-1,i+1); //자신 하나 앞문자와 자신으로 구성된 문자열을 만든다.
            boolean isOk = true; //특수문자, 숫자가 포함되지 않았는지 확인하는 flag
            for(int j=0;j<2;j++){ //임시 문자열은 길이가 2다. 하나라도 특수문자, 숫자면 false로 바꿔주기
                if(subString.charAt(j)<97 || subString.charAt(j)>122){
                    isOk = false;
                }
            }
            if(isOk){ //특수문자가 없으면
                set.add(subString); //집합에 추가한다. => 두 문자열에 포함된 모든 작은 문자열이 집합에 들어간다.
                if(!map.containsKey(subString)){ //map에 포함되지 않았을 때
                    map.put(subString, 1); //value를 1로 넣어준다.
                }else{ //map에 원래 있으면
                    map.put(subString, (int)map.get(subString) + 1); //value += 1;해준다.
                }
            }
        }
        System.out.println(map);
    }
    
    public static int checkSet(){
        max=0; //합집합의 개수
        min=0; //교집합의 개수
        for(String x:set){ //작은 문자열 전체를 반복한다.
            // System.out.println(x);
            int map1Num = 0; //map1에서 x에 해당하는 value를 저장하는 변수
            int map2Num = 0;
            if(map1.containsKey(x)){ //x에 해당하는 key가 있으면, 없으면 0
                map1Num = map1.get(x);
            }
            if(map2.containsKey(x)){
                map2Num = map2.get(x);
            }
            // {ce=1, nc=1, fr=1, an=1, ra=1}
            // {re=1, nc=1, ch=1, en=1, fr=1}
            // {aa=2}
            // {aa=3}
            max += Math.max(map1Num, map2Num); //max에 두 map의 value중 큰 값을 누적 => 합집합
            min += Math.min(map1Num, map2Num); //min에 두 map의 value중 작은 값을 누적한다. => 교집합
        }
        double Dresult = 1;
        if(max==0 && min==0){ // A,B모두 공집합인 경우 유사도==1
            Dresult = 1;
        }else{
            Dresult = min/max;
        }
         
        Dresult = Dresult*65536;
        return (int)Dresult;
    }
    
    public int solution(String str1, String str2) {
        STR1 = str1.toLowerCase();
        STR2 = str2.toLowerCase(); //모두 소문자로 바꾸기
        
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        set = new HashSet<>(); //초기화
        
        putMap(STR1, map1);
        putMap(STR2, map2); //map에 개수 세서 넣는다.
        
        int result = checkSet();
        return result;
    }
}
//2시간
