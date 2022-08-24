package _20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_16236_bbShark {

    static int[] dr = {-1, 0, 0, 1}; //위, 왼, 오, 아
    static int[] dc = {0, -1, 1, 0};
    static PriorityQueue<int[]> qu;
    static int[][] map;
    static int[] pos;
    static int N;
    static boolean[][] v;
    static int result;
    static int tempr;

    public static boolean check(int lv) { //먹을 수 있는 물고기가 남아있나 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0 && map[i][j] < lv) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int bfs() {
        while(!qu.isEmpty()) {

            int[] cur = qu.poll();
            int sr = cur[0];
            int sc = cur[1];
            int lv = cur[2];
            int eat = cur[3];
            int dist = cur[4];

            v[sr][sc] = true; //큐에서 poll하고 이동처리
            if(map[sr][sc] != 0 && map[sr][sc] < lv) { //상어보다 작은 물고기면
                map[sr][sc] = 0;
                eat += 1; //먹은 물고기 수 증가
                tempr = dist; //물고기 먹으면 거리 tempr에 저장
                if(eat == lv) { //lv이랑 eat 같으면 lv 증가
                    lv += 1;
                    eat = 0; //eat초기화 안해줬어!!
                }
                v = new boolean[N][N]; //물고기 먹었으면 v초기화
                v[sr][sc] = true; //시작점 방문

//                System.out.println("------------------------"); //시뮬레이션 디버깅할 때 모두 찍어보자
//                for (int[] x: map) {
//                    System.out.println(Arrays.toString(x));
//                }
//                System.out.println(dist+1);
//                System.out.println(lv);

                qu = new PriorityQueue<int[]>(new Comparator<int[]>() { //물고기 먹었으면 큐도 초기화
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if(o1[4] == o2[4]){ //거리
                            if(o1[0] == o2[0]){ //행
                                return o1[1] - o2[1];
                            }else{
                                return o1[0] - o2[0];
                            }
                        }else{
                            return o1[4] - o2[4];
                        }
                    }
                });
            }

            if(!check(lv)) { //먹이가 없으면
                return dist;
            }
            //지금은 nr에서 바로 이동한다. nr해서 큐에 넣어주고 poll할 때 이동을 해야한다.
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0 && nc>=0 && nr<N && nc<N && !v[nr][nc] && map[nr][nc]<=lv) { //크기가 같은 물고기는 먹을수는 없고 지나갈 수 있다.
                    v[nr][nc] = true;
                    //이동할 자리에서 바로 0으로 만들고 이동해 버리니까 이동 순서에 오류가 있었다. 이동할 자리 모두 큐에 넣어서 정렬해주고 이동해야 한다.
                    qu.add(new int[] {nr, nc, lv, eat, dist+1}); //크기가 같은 물고기는 이동만
                }
            }
        }
        if(tempr == 0){ //0이면 먹은 물고기가 없다.
            return -1;
        }else{
            return tempr; //먹은 물고기는 있지만 이동할 수 없어서 마지막까지 왔다.
        }
    }

    public static void main(String[] args) throws IOException {
        //시작1035점심1230~1330
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        pos = new int[5]; //r, c, lv, eat, dist
        qu = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[4] == o2[4]){ //거리
                    if(o1[0] == o2[0]){ //행
                        return o1[1] - o2[1];
                    }else{
                        return o1[0] - o2[0];
                    }
                }else{
                    return o1[4] - o2[4];
                }
            }
        });
        v = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    pos[0] = i;
                    pos[1] = j;
                    pos[2] = 2;
                    pos[3] = 0;
                    pos[4] = 0;
                    v[i][j] = true;
                    map[i][j] = 0;
                    qu.add(pos);
                }
            }
        }
        tempr = 0; //먹이는 남았지만 이동할 수 있을 때 먹이 먹고나서 거리 저장할 변수
        result = 0;
        result = bfs();
        System.out.println(result == -1 ? 0 : result); //-1 반환하면 먹을 수 있는 물고기가 없다.
    }

}

/*
7
3 5 0 6 4 5 5
1 6 3 3 0 2 2
6 2 1 3 1 5 1
9 2 2 3 4 2 3
2 1 6 2 0 0 4
4 5 0 6 1 1 0
5 4 3 2 1 4 0
answer: 67

7
2 0 6 1 2 4 1
2 0 0 0 1 6 1
3 5 1 0 2 5 0
3 0 0 9 1 0 4
6 1 1 0 2 1 6
0 0 4 0 4 1 2
5 0 0 0 4 0 2
answer: 59

7
0 0 4 9 0 0 0
0 0 0 0 0 0 0
0 0 1 0 1 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
answer: 5

6
2 9 6 2 5 6
5 1 0 4 0 0
5 3 1 4 0 5
2 0 6 4 1 0
0 2 1 5 1 5
1 2 2 6 4 6

answer: 55

6
0 4 4 3 0 6
0 0 3 5 4 2
1 4 1 3 6 2
0 0 2 1 9 0
1 3 5 4 6 0
2 5 6 2 1 0

answer: 48

6
3 5 5 1 6 3
0 6 0 6 5 1
9 5 0 0 0 4
2 1 0 3 0 4
6 0 1 1 1 4
1 6 0 1 0 1

answer: 58

6
1 1 1 4 3 0
3 0 0 6 1 0
2 9 6 2 5 5
6 2 1 0 6 5
5 5 0 1 6 0
3 1 2 0 4 1

answer: 55

6
3 6 6 1 5 3
1 6 1 5 2 4
6 1 2 2 1 5
4 0 0 1 6 3
2 0 5 4 0 0
1 0 9 0 0 3

answer: 63

6
1 2 0 3 1 6
1 0 5 0 0 0
1 0 2 0 2 0
0 1 4 0 2 5
6 6 3 0 3 3
4 9 6 0 2 2

answer:0

10
0 1 4 6 1 4 5 4 0 3
2 0 0 9 0 0 6 0 0 0
4 3 2 4 6 3 0 2 1 6
0 0 0 0 1 0 0 1 0 6
0 0 0 6 0 4 1 4 0 1
0 3 0 1 6 0 3 1 0 4
6 5 0 2 0 0 5 1 0 1
0 4 0 4 1 1 2 5 6 6
1 0 5 6 5 1 0 1 2 0
5 6 3 0 6 0 1 1 6 0

answer:103

10
5 6 3 0 5 5 4 4 3 0
2 4 0 4 0 1 0 1 0 6
0 3 4 1 0 0 3 1 1 0
0 5 1 0 1 6 1 3 5 1
0 3 0 1 1 0 4 0 1 0
0 5 1 5 6 0 3 4 0 9
0 5 5 3 0 0 4 5 0 3
2 5 0 3 3 2 0 0 3 2
2 6 5 0 0 4 1 1 6 3
1 3 1 3 0 1 0 0 0 5

answer:105

10
5 3 2 0 0 1 2 0 4 0
3 0 3 1 0 0 3 0 6 1
0 3 0 5 0 5 4 4 2 5
3 0 0 3 0 6 1 5 4 2
1 0 2 0 2 0 3 0 0 6
1 1 1 1 1 1 1 1 0 0
2 0 2 1 9 6 0 0 4 3
1 6 1 0 6 0 5 0 1 0
6 5 4 0 1 2 1 3 5 0
0 1 6 6 1 4 3 0 1 1

answer:102

10
2 0 2 0 1 1 1 0 1 0
0 4 4 0 4 0 0 0 3 0
4 3 5 0 1 0 2 6 0 0
0 0 5 5 3 1 3 1 3 4
6 0 5 1 4 2 4 0 5 0
0 0 5 0 2 1 1 2 1 0
2 0 5 2 4 0 9 1 6 2
4 1 2 0 3 0 3 2 4 6
3 0 1 0 4 0 0 5 0 1
0 4 1 1 6 6 1 6 0 0

answer:87

3
9 2 2
2 2 3
1 3 1
답: 2
 */