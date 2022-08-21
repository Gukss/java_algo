package _20220820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_1697_hide {
    static boolean[] visited;
    static int N, K;
    static Queue<int[]> qu;

    public static void bfs(){
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            int p = cur[0];
            if(p == K){
                System.out.println(cur[1]);
                return;
            }
            if(p-1>0 && !visited[p-1]){
                qu.add(new int[] {p-1, cur[1]+1});
                visited[p-1] = true;
            }
            if(p+1<100001 && !visited[p+1]){
                qu.add(new int[] {p+1, cur[1]+1});
                visited[p+1] = true;
            }
            if(p*2<100001 && !visited[p*2]){
                qu.add(new int[] {p*2, cur[1]+1});
                visited[p*2] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        visited[N] = true;
        qu = new LinkedList<>();
        qu.add(new int[] {N, 0});
        bfs();
    }
}
