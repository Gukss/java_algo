import java.util.*;

class Solution {
    public static HashMap<String, Integer> map;
    public static int[][] arr;
    public static int[] dir;
    public static int[] ans;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int len = friends.length;
        map = new HashMap<String, Integer>();
        for (int i=0;i<len;i++) {
            map.put(friends[i], i);
        }
        arr = new int[len][len];
        dir = new int[len];
        ans = new int[len];
        
        for (String x: gifts) {
            StringTokenizer st = new StringTokenizer(x, " ");
            String a = st.nextToken();
            String b = st.nextToken();
            // System.out.println("a: "+a+", "+"b: "+b);
            arr[map.get(a)][map.get(b)] += 1;
            dir[map.get(a)] += 1;
            dir[map.get(b)] -= 1;
        }
        
        for (int i=0;i<len;i++) {
            for (int j=i;j<len;j++) {
                if (i==j) continue;
                if (arr[i][j] > arr[j][i]) {
                    ans[i] += 1;
                    // System.out.println("1ans["+i+"]: "+ans[i]+", j: "+j);
                } else if (arr[i][j] < arr[j][i]) {
                    ans[j] += 1;
                    // System.out.println("4ans["+j+"]: "+ans[j]+", i: "+i);
                } else if ((arr[i][j] == arr[j][i]) || ((arr[i][j] == 0) && (arr[j][i] == 0))) { //선물 지수 비교
                    if (dir[i] > dir[j]) {
                        ans[i] += 1;
                        // System.out.println("3ans["+i+"]: "+ans[i]+", j: "+j);
                    } else if (dir[i] < dir[j]) {
                        ans[j] += 1;
                        // System.out.println("4ans["+j+"]: "+ans[j]+", i: "+i);
                    } else {
                        //선물 지수도 같은 경우 아무행동 안함
                    }
                }
            }
        }
        for (int x: ans) {
            // System.out.println(x);
            answer  = Math.max(x, answer);
        }
        return answer;
    }
}