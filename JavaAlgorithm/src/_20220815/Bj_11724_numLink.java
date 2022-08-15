package _20220815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_11724_numLink {

    static boolean[][] map;
    static boolean[] v;
    static int N, M;

    public static void dfs(int i){ //연결된 노드 탐색하기
        if(!v[i]){ //방문한 노드는 방문하지 않기
            v[i] = true;
            for (int j = 1; j <= N; j++) { //인접행렬에서 연결되어있다고 표시된 노드 따라 탐색하기
                if(map[i][j]){
                    dfs(j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][N+1];
        v = new boolean[N+1];
        int result = 0;
        for (int i = 0; i < M; i++) { //인접행렬 만들기
            st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());
            map[temp1][temp2] = true;
            map[temp2][temp1] = true;
        }

        for (int i = 1; i <= N; i++) { //노드와 연결된 노드 dfs로 탐색
            if(!v[i]){ //방문했던 노드는 방문하지 않기
                dfs(i);
                result += 1;
            }
        }
        System.out.println(result);
    }
}

//40분