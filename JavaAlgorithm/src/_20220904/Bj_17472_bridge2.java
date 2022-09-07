package _20220904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_17472_bridge2 {
    static int N,M;
    static int[][] map;
    static Queue<Pos> qu;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1};
    static ArrayList[] list;
    static int islandNum;
    static int[][] cycle;
    static boolean[] v;

    public static int countIsland(){
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == -1){
                    qu.add(new Pos(i, j, count));
                    map[i][j] = count;
                    while(!qu.isEmpty()){
                        Pos cur = qu.poll();
                        int sr = cur.r;
                        int sc = cur.c;
                        for (int k = 0; k < 4; k++) {
                            int nr = sr + dr[k];
                            int nc = sc + dc[k];
                            if(nr>=0&&nc>=0&&nr<N&&nc<M && map[nr][nc] == -1){
                                qu.add(new Pos(nr, nc, count));
                                map[nr][nc] = count;
                            }
                        }
                    }
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void savePos(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] != 0){
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(nr>=0&&nc>=0&&nr<N&&nc<M && map[nr][nc] == 0){ //바다면 방향이랑 좌표 저장
                            list[map[i][j]].add(new Pos(i,j,k)); //몇 번째 섬에 가장자리 좌표와 바다로 향하는 방향 저장, 0번 섬은 없다.
                        }
                    }

                }
            }
        }
    }

    public static void checkBridge(){
        for (int i = 0; i < islandNum; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                Pos cur = (Pos)list[i].get(j);
                int bridgeLength=0;
                int sr = cur.r;
                int sc = cur.c;
                int nr = sr + dr[cur.countdir];
                int nc = sc + dc[cur.countdir];
                while(nr>=0&&nc>=0&&nr<N&&nc<M && map[nr][nc] != map[sr][sc]){
                    bridgeLength += 1; //다리 길이는 다른 섬 확인하고 -1해줘야 한다.

                    if(map[nr][nc] != 0){ //다른 섬에 도착
                        if(bridgeLength-1<2){ //다리 길이가 2가 안되면 다리 놓을수 없다.
//                            cycle[map[sr][sc]][1] = -1; //다리길이 -1로 만들기
                            break;
                        }
                        if(cycle[map[sr][sc]][1] > bridgeLength-1){ //새로 만든 다리가 더 짧을 때
                            cycle[map[sr][sc]][1] = bridgeLength-1; //다리길이 갱신
                            cycle[map[sr][sc]][0] = map[nr][nc];
                            break;
                        }
                    }
                    nr = nr + dr[cur.countdir];
                    nc = nc + dc[cur.countdir];
                }
            }
        }
    }

    public static int countBridgeLength(){
        int total=0;
        for (int i = 1; i < cycle.length; i++) {
            if(cycle[i][0] == Integer.MAX_VALUE){
                continue;
            }
            if(i == cycle[cycle[i][0]][0]){ //서로 연결돼있을 때
                cycle[cycle[i][0]][1] = 0; //똑같은 다리는 0으로 만들어주기
            }
            v[i] = true;
            total += cycle[i][1];
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        qu = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = -1*Integer.parseInt(st.nextToken()); //처음 값은 -1로
            }
        }

        islandNum = countIsland(); //섬 개수는 -1해야하지만 배열 생성을 위해 한 개 많게 설정? 배열이 필요한가? -> 배열로 사이클 확인하면 모두 연결됐는지 확인가능
        list = new ArrayList[islandNum]; //섬 개수만큼
        for (int i = 0; i < islandNum; i++) {
            list[i] = new ArrayList<Pos>();
        }
        savePos();
        //list islandNum만큼 돌면서 list[i].size()만큼 반복하면서 2보다 큰 다리, 섬이랑 연결되는 다리
        //한 개 씩 가장 짧은 다리 선택하고 반대편 섬과 연결돼있었는지 확인하기 -> 이미 연결돼있으면 같은 다리다. => count할 필요 없다.
        cycle = new int[islandNum][2];
        for (int i = 0; i < islandNum; i++) {
            cycle[i][1] = Integer.MAX_VALUE;
            cycle[i][0] = Integer.MAX_VALUE;
        }
        v = new boolean[islandNum];
        checkBridge();
        int result = countBridgeLength();
        for (int i = 1; i < islandNum; i++) {
            if(!v[i]){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(result);

    }

    public static class Pos{
        int r, c, countdir;

        public Pos(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.countdir = count;
        }
    }
}
/*
6 6
1 1 1 1 1 1
0 0 0 0 0 0
1 1 1 0 1 0
0 1 0 1 0 1
0 0 0 0 0 0
1 1 1 1 1 1
ans: 8?

6 6
1 1 1 1 1 1
0 0 0 0 0 0
1 1 1 0 1 0
0 1 0 1 0 1
0 0 0 0 0 0
1 1 1 1 1 1
ans: -1

10 10
0 0 0 1 1 0 0 0 0 0
0 0 0 1 0 0 0 0 0 1
0 0 0 1 1 0 0 0 0 0
0 0 0 1 1 0 0 0 0 0
1 0 0 1 0 0 0 0 0 1
0 0 0 1 1 0 0 0 0 0
0 0 0 1 1 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 1
0 0 1 1 1 1 0 0 1 1
ans: 11

8 6
0 1 0 0 1 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 1 0 0 1 0
0 0 0 0 0 0
0 0 0 0 0 0
0 1 1 1 1 0
ans: 9
 */
