package _20220823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_13023_ABCDE {
    static int[][] map;
    static int N,M;
    static boolean[] v;
    static boolean result;
    static ArrayList[] list;

    public static void dfs(int i, int depth){
        if(result) return;
        if(depth == 4){
            result = true;
            return;
        }
        v[i] = true;
        for (int j = 0; j < list[i].size(); j++) {
            int cur = (int) list[i].get(j);
            if(!v[cur]){
                v[cur] = true;
                dfs(cur, depth+1);
                v[cur] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][2];
        list = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[i][0] = from;
            map[i][1] = to;
            list[from].add(to);
            list[to].add(from);

        }

        Queue<int[]> qu = new LinkedList<>();

        for (int i = 0; i < M; i++) {
        v = new boolean[N];
            result = false;
                //qu.add(new int[] {map[i][1] , 1});
            dfs(i, 0);
            if(result) break;
        }
        if(result) System.out.println(1);
        else System.out.println(0);

//        while(!qu.isEmpty()){
//            int[] cur = qu.poll();
//            v[cur[0]] = true;
//            if(cur[1] == 4){
//                System.out.println(1);
//                return;
//            }
//            for (int i = 0; i < M; i++) {
//                if(map[i][0] == cur[0]){
//                    qu.add(new int[] {map[i][1], cur[1]+1});
//                }
//            }
//        }
//
//        //i로 시작하고 방문안한 곳 큐에 넣기
//        System.out.println(0);
    }
}
