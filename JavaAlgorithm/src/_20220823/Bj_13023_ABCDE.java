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
        if(depth == 4){ //깊이가 4가 되면 5명 연결돼있다.
            result = true;
            return;
        }
        v[i] = true; //시작점 방문처리
        for (int j = 0; j < list[i].size(); j++) { //i번 리스트의 사이즈 만큼 반복
            int cur = (int) list[i].get(j); //j번째 숫자 꺼내기
            if(!v[cur]){ //방문안했으면
                v[cur] = true; //방문처리하고
                dfs(cur, depth+1); //재귀호출
                v[cur] = false; //for문 진행을 위해 원상복귀
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N]; //리스트배열

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<Integer>(); //리스트배열 초기화
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list[from].add(to); //간선리스트 만들기
            list[to].add(from);

        }

        for (int i = 0; i < N; i++) { //사람 수 만큼 반복
            v = new boolean[N]; //시작하는 사람이 바귈 때 마다 방문배열 초기화
            result = false; //결과값 초기화 -> 안해줘도 될것 같다.
            dfs(i, 0); //함수 호출
            if(result) break; //함수를 빠져나왔을 때 depth가 4가 되었다. 반복문도 탈출
        }
        if(result) System.out.println(1); //출력
        else System.out.println(0);
    }
}
/*
5 4
0 1
1 2
2 3
3 0
답: 0
 */
