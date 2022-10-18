package _20221016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_16235_tree {
    static int N,M,K;
    static int[][] map, feed;
    static Deque<Tree> dq;
    static ArrayList<Tree> deadlist;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //위 부터 시계방향
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1}; //위 부터 시계방향
    public static void spring(){
        deadlist = new ArrayList<>();
        for(Tree cur: dq){
            int r = cur.r;
            int c = cur.c;
            if(map[r][c] - cur.age >= 0){ //나이만큼 양분을 먹을수 있을 때
                map[r][c] -= cur.age; //양분 먹고
                cur.age += 1; //나이 +1
                if(cur.age % 5 == 0){
                    cur.sun = 1;
                }
            }else{ //양분은 먹을 수 없을 때
                deadlist.add(cur);
            }
        }
    }

    public static void summer(){
        int size = deadlist.size();
        for (Tree cur: deadlist){
            dq.remove(cur); //죽은 나무 list에서 빼기
            int r = cur.r;
            int c = cur.c;
            int age = cur.age;
            int addval = age/2;
            map[r][c] += addval;
        }
    }

    public static void autumn(){
        List<Tree> templist = new ArrayList<>();
        for(Tree cur: dq){
            if(cur.age % 5==0){ //나무의 나이가 5의 배수일 때
                if(cur.sun == 1){
                    int sr = cur.r;
                    int sc = cur.c;
                    cur.sun = 0;
                    for (int i = 0; i < 8; i++) {
                        int nr = sr + dr[i];
                        int nc = sc + dc[i];
                        if(nr>=1&&nr<=N&&nc>=1&&nc<=N){ //경계
                            templist.add(new Tree(nr, nc, 1,0)); //나이가 1인 나무 생성
                        }
                    }
                }
            }
        }
        for(Tree cur: templist){
            dq.addFirst(cur);
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
            for (int j = 1; j <= N; j++) { //처음 양분은 모두 5다. => 기본 양분 5에 += 5가 아니다.
                feed[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] += 5; //기본 양분이 5있다.
            }
        }

        dq = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            int x,y,z;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            dq.add(new Tree(x,y,z,0));
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(dq.size());
    }

    public static class Tree{
        int r,c,age,sun;

        public Tree(int r, int c, int age, int sun) {
            this.r = r;
            this.c = c;
            this.age = age;
            this.sun = sun;
        }
    }
}
