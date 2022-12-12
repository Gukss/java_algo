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
        for(int i=1;i<str.length();i++){
            String subString = "";
            subString += str.substring(i-1,i+1);
            boolean isOk = true;
            for(int j=0;j<2;j++){
                if(subString.charAt(j)<97 || subString.charAt(j)>122){
                    isOk = false;
                }
            }
            if(isOk){
                set.add(subString);
                if(!map.containsKey(subString)){
                    map.put(subString, 1);            
                }else{
                    map.put(subString, (int)map.get(subString) + 1);
                }
            }
        }
    }
    
    public static int checkSet(){
        max=0;
        min=0;
        for(String x:set){
            // System.out.println(x);
            int map1Num = 0;
            int map2Num = 0;
            if(map1.containsKey(x)){
                map1Num = map1.get(x);
            }
            if(map2.containsKey(x)){
                map2Num = map2.get(x);
            }
            max += Math.max(map1Num, map2Num);
            min += Math.min(map1Num, map2Num);
        }
        double Dresult = 1;
        if(max==0 && min==0){
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
        set = new HashSet<>();
        
        putMap(STR1, map1);
        putMap(STR2, map2);
        
        int result = checkSet();
        return result;
    }
}