package _20221016;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_16235_tree {
    static int N,M,K;
    static int[][] map, feed;
    static List<Tree> list;
    static Queue<Tree> deadqu;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //위 부터 시계방향
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1}; //위 부터 시계방향
    public static void spring(){
        list.sort(new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                return o1.age - o2.age;
            }
        });
        for(Tree cur:list){
            int r = cur.r;
            int c = cur.c;
            if(map[r][c] - cur.age >= 0){ //나이만큼 양분을 먹을수 있을 때
                map[r][c] -= cur.age; //양분 먹고
                cur.age += 1; //나이 +1
            }else{ //양분은 먹을 수 없을 때
                deadqu.add(cur);
            }
        }
    }

    public static void summer(){
        while(!deadqu.isEmpty()){
            Tree cur = deadqu.poll();
            list.remove(cur); //죽은 나무 list에서 빼기
            int r = cur.r;
            int c = cur.c;
            int age = cur.age;
            int addval = age/2;
            map[r][c] += addval;
        }
    }

    public static void autumn(){
        for(Tree cur: list){
            if(cur.age % 5 == 0){ //나무의 나이가 5의 배수일 때
                int sr = cur.r;
                int sc = cur.c;
                for (int i = 0; i < 8; i++) {
                    int nr = sr + dr[i];
                    int nc = sc + dc[i];
                    if(nr>=1&&nr<=N&&nc>=1&&nc<=N){ //경계
                        list.add(new Tree(nr, nc, 1)); //나이가 1인 나무 생성
                    }
                }
            }
        }
    }

    public static void winter(){ //양분 추가하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += feed[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][N+1]; //r,c는 1부터 시작한다.
        feed = new int[N+1][N+1]; //r,c는 1부터 시작한다.
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                feed[i][j] = map[i][j];
            }
        }

        list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int x,y,z;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            list.add(new Tree(x,y,z));
        }

        deadqu = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(list.size());
    }

    public static class Tree{
        int r,c,age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }
}
