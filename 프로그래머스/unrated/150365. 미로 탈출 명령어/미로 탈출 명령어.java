import java.util.*;

class Solution {
    public static int[] dr = {1, 0, 0, -1};//밑, 왼, 오, 위
    public static int[] dc = {0, -1, 1, 0};
    public static String[] term = {"d", "l", "r", "u"};
    public static int[][] map;
    public static String ans;

    public static boolean check(int n, int m, int x, int y, int r, int c, int k, String str, int diff){
        if(k==0 && diff==0){
            ans = str;
            return true;
        }
//        if(k==0){
//            if(diff == 0){
//                ans = str;
//                return true;
//            }else{
//                return false;
//            }
//        }
        int sr = x;
        int sc = y;
        for (int i = 0; i < 4; i++) {
            int nr = sr + dr[i];
            int nc = sc + dc[i];
            if(nr>=0&&nc>=0&&nr<n&&nc<m && !(k<diff)){
                if(check(n,m,nr,nc,r,c,k-1,str+term[i], Math.abs(nr-r)+Math.abs(nc-c))){
                    return true;
                }
            }
        }
        return false;
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        ans = "";
        int diff = Math.abs(x-1 - (r-1))+Math.abs(y-1 - (c-1));
        if((diff % 2 == 0 && k%2==0) || (diff%2==1 && k%2==1)){
            check(n,m,x-1,y-1,r-1,c-1,k, "", diff);
        }
        if(ans.equals("")){
            answer = "impossible";
        }else{
            answer = ans;
        }
//        System.out.println(answer);
        return answer;
    }
}