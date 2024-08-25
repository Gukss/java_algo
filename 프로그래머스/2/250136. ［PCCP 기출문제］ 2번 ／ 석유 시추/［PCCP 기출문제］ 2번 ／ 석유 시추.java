import java.util.*;

class Solution {
    public static int n,m;
    public static int[][] arr;
    public static int[] dr = {-1, 0, 0, 1}; //위오왼밑
    public static int[] dc = {0, 1, -1, 0}; //위오왼밑
    public static Queue<Pos> qu;
    public static int max;
    public static boolean[][] v;
    public static int result;
    public static boolean[] vc;
    public static int[] resultc;
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        v = new boolean[n][m];
        vc = new boolean[m];
        resultc = new int[m];
        arr = land;
        qu = new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j] != 0 && v[i][j] == false){
                    max = 1;
                    vc = new boolean[m];
                    v[i][j] = true;
                    vc[j] = true;
                    dfs(i, j);
                    numbering();
                    // System.out.println(Arrays.toString(resultc));
                }
            }
        }
        // for(int i=0;i<n;i++){
        //     System.out.println(Arrays.toString(arr[i]));
        // }
        
        for(int i=0;i<m;i++){
            result = Math.max(result, resultc[i]);
        }
        return result;
    }
    
    public static void dfs(int r, int c){
        for(int i=0;i<4;i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr>=0&&nc>=0&&nr<n&&nc<m && arr[nr][nc]!=0 && v[nr][nc]==false){
                v[nr][nc] = true;
                vc[nc] = true;
                max += 1;
                dfs(nr,nc);
            }
        }
    }
    
    public static void numbering(){
        for(int i=0;i<m;i++){
            if(vc[i]){
                resultc[i]+=max;
            }
        }
    }
    
    public static class Pos{
        int r, c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}