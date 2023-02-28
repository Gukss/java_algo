package _20230223_skplanet;

import java.util.Arrays;

public class Solution3 {

    public static int[][] map;
    public static int[] dr = {-1, 0, 1, 0}; //위오밑왼
    public static int[] dc = {0, 1, 0, -1}; //위오밑왼
    public static int sr = -1;
    public static int sc = -1;
    public static int N;
    public static boolean[][] v;

    public static void main(String[] args) {
        String[][] boards = {{"00011", "01111", "21001", "11001", "01111" }, {"00011", "00011", "11111", "12101", "11111"}};
        solution(boards);
    }

    public static int[] solution(String[][] boards) {
        int[] answer = new int[boards.length];
        for (int i = 0; i < boards.length; i++) {
            makeMap(boards[i]);
            v = new boolean[N][N];
            for (int dir = 0; dir < 4; dir++) {
                v[sr][sc] = true;
                map[sr][sc] = -1;
                if(dfs(sr,sc,dir, 1)){
                    answer[i] = 1;
                }
            }
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
    
    public static void makeMap(String[] strArr){
        N = strArr.length;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = strArr[i].charAt(j)-'0';
                if(map[i][j] == 2){
                    sr = i;
                    sc = j;
                }
            }
        }
    }

    public static boolean dfs(int sr, int sc, int dir, int depth){
        if(check()){
            return true;
        }
        int nr = sr + dr[dir];
        int nc = sc + dc[dir];
//        if(!(nr>=0&&nc>=0&&nr<N&&nc<N)){
//            return false;
//        }
        if(nr>=0&&nc>=0&&nr<N&&nc<N && !v[nr][nc] && map[nr][nc]==1){ //방문 안한곳
            v[nr][nc] = true;
            map[nr][nc] = -1;
            for (int i = 0; i < N; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
                System.out.println("=========");
            for (int i = 0; i < 4; i++) {
                if(dfs(nr, nc, i, depth+1)){
                    return true;
                }
            }
            v[nr][nc] = false;
            map[nr][nc] = 1;

        }else{
            return false;
        }
        return false;
    }

    public static boolean check(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }
}
