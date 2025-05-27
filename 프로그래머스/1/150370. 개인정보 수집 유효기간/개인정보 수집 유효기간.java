import java.util.*;

class Solution {
    public static Map<String, Integer> map;
    public int[] solution(String today, String[] terms, String[] privacies) {
        map = new HashMap<String, Integer>();
        for (String x: terms) {
            StringTokenizer mapSt = new StringTokenizer(x, " ");
            String key = mapSt.nextToken();
            int value = Integer.parseInt(mapSt.nextToken());
            map.put(key, value);
        }
        StringTokenizer ss = new StringTokenizer(today, ".");
        int monthToday = Integer.parseInt(ss.nextToken()) * 12;
        monthToday += Integer.parseInt(ss.nextToken());
        int dayToday = Integer.parseInt(ss.nextToken());
        
        ArrayList<Integer> list = new ArrayList<>();
        int idx = 1;
        
        for (String x: privacies) {
            StringTokenizer st = new StringTokenizer(x, " ");
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), ".");
            String type = st.nextToken();
            int month = Integer.parseInt(st2.nextToken()) * 12;
            month += Integer.parseInt(st2.nextToken());
            int day = Integer.parseInt(st2.nextToken());
            
            int addNum = map.get(type);
            if (monthToday > month + addNum) { //파기
                list.add(idx);
            } else if (monthToday == month + addNum) { //파기
                 if (dayToday >= day) {
                     list.add(idx);
                 }
            } else {
                //do nothing
            }
            idx += 1;
        }
        int size = list.size();
        int[] arr = new int[size];
        for (int i=0;i<size;i++) {
            arr[i] = list.get(i);
        }
//        int[] arr = list.toArray(new int[size]);
        return arr;
    }
}