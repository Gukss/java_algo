package _20221008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_17472_bridge2 {
    static int N, M;
    static int map[][];
    static boolean visit[][];
    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };
    static int land[][];
    static ArrayList<Info> list[];

    static class Info implements Comparable<Info> {
        int node, w;

        public Info(int node, int w) {
            // TODO Auto-generated constructor stub
            this.node = node;
            this.w = w;
        }

        @Override
        public int compareTo(Info o) {
            // TODO Auto-generated method stub
            return this.w - o.w;
        }
    }

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            // TODO Auto-generated constructor stub
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        land = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());
            }
        }
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] == 1) {
                    land[i][j] = idx;
                    findLand(i, j, idx);
                    idx++;
                }
            }
        }
        list = new ArrayList[idx];
        for (int i = 0; i < idx; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] != 0) {
                    goRight(i, j);
                    goDown(i, j);
                }
            }
        }
        mst(idx);
    }

    static void mst(int num) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1, 0));
        boolean check[] = new boolean[num];
        int sum = 0;
        int minDist[] = new int[num];
        for (int i = 1; i < num; i++) {
            minDist[i] = Integer.MAX_VALUE;
        }
        minDist[1] = 0;

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (check[cur.node])
                continue;
            check[cur.node] = true;
            sum += cur.w;
            for (Info next : list[cur.node]) {
                if (!check[next.node] && minDist[next.node] > next.w) {
                    minDist[next.node] = next.w;
                    pq.add(new Info(next.node, minDist[next.node]));
                }
            }
        }
        boolean flag = true;
        for(int i=2; i<num; i++) {
            if(minDist[i]==Integer.MAX_VALUE) {
                flag = false;
                break;
            }
        }
        if(!flag) {
            System.out.println(-1);
        }else {
            System.out.println(sum);
        }
    }

    static void goRight(int r, int c) {
        int dist = 0;
        int cur = land[r][c];
        for (int j = c; j < M; j++) {
            int nC = j + 1;
            if (nC >= M || land[r][nC] == cur)// 현재값 또는 범위 밖을 나갈경우
                break;
            int next = land[r][nC];
            if (next != 0 && next != cur) {// 현재값이 아니고, 0이 아닌경우 즉 다른 섬인경우
                if (dist <= 1)
                    break;
                list[cur].add(new Info(next, dist));
                list[next].add(new Info(cur, dist));
                break;
            }
            dist++;
        }
    }

    static void goDown(int r, int c) {
        int dist = 0;
        int cur = land[r][c];
        for (int i = r; i < N; i++) {
            int nR = i + 1;
            if (nR >= N || land[nR][c] == cur)// 현재값 또는 범위 밖을 나갈경우
                break;
            int next = land[nR][c];
            if (next != 0 && next != cur) {// 현재값이 아니고, 0이 아닌경우 즉 다른 섬인경우
                if (dist <= 1)
                    break;
                list[cur].add(new Info(next, dist));
                list[next].add(new Info(cur, dist));
                break;
            }
            dist++;
        }
    }

    static void findLand(int r, int c, int idx) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(r, c));
        visit[r][c] = true;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nR = cur.r + dr[k];
                int nC = cur.c + dc[k];
                if (nR < 0 || nR >= N || nC < 0 || nC >= M || visit[nR][nC])
                    continue;
                if (map[nR][nC] == 0)
                    continue;
                land[nR][nC] = idx;
                visit[nR][nC] = true;
                queue.add(new Pair(nR, nC));
            }
        }
    }
}