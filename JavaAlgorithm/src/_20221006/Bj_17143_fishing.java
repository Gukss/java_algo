package _20221006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj_17143_fishing {
    static int R,C,M;
    static Sh[][] map;
    static List<Sh> list;
    static int[] dr = {-1, 1, 0, 0}; //위, 밑, 오, 왼
    static int[] dc = {0, 0, 1, -1}; //위, 밑, 오, 왼

    public static void getshark(int col){
        for (int i = 0; i < R; i++) {
            if(map[i][col] != null){ //땅에서 가까운 물고기 잡는다.
                list.remove(map[i][col]); //list에서 삭제
                map[i][col] = null; //map에서 삭제
                break;
            }
        }
    }

    public static void moveshark(){
        Sh[][] tempmap = new Sh[R][C];
        List<Sh> removelist = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Sh cur = list.get(i);
            int sr = cur.r;
            int sc = cur.c;
            int dir = cur.d;
            int speed = cur.s;
            if(dir==0||dir==1){
                speed = speed % ((R-1)*2);
            }else{
                speed = speed % ((C-1)*2);
            }
            int nr = sr;
            int nc = sc;
            for (int j = 0; j < speed; j++) {
                nr += dr[dir];
                nc += dc[dir];
                if(!(nr>=0&&nr<R&&nc>=0&&nc<C)){ //경계 벗어나면
                    nr -= dr[dir];
                    nc -= dc[dir]; //돌려놓고
                }
                j -= 1;
            }
            if(tempmap[nr][nc] != null){ //tempmap에 상어가 있으면 크기비교
                if(tempmap[nr][nc].z > cur.z){ //tempmap이 크면 cur가 먹힌다. => list에서 삭제
                    removelist.add(cur);
                }else{ //cur가 그면 tempmap 갱신, tempmap에 들어있던 상어 list에서 지운다.
                    Sh temp = new Sh(tempmap[nr][nc].r,tempmap[nr][nc].c,tempmap[nr][nc].s,tempmap[nr][nc].d,tempmap[nr][nc].z);
                    removelist.add(temp); //틀리면 여기서 틀리지 않았을까?
                    cur.r = nr;
                    cur.c = nc;
                    cur.d = dir;
                    tempmap[nr][nc] = cur;
                }
            }else{ //상어가 없으면 tempmap에 넣는다.
                tempmap[nr][nc] = cur;
            }
        }
    }

    public static void start(){
        for (int fishercol = 0; fishercol < C; fishercol++) {
            getshark(fishercol);
            moveshark();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Sh[R][C];
        list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int r = Integer.parseInt(st.nextToken())-1;
                int c = Integer.parseInt(st.nextToken())-1;
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken())-1;
                int z = Integer.parseInt(st.nextToken());
                Sh newshark = new Sh(r,c,s,d,z);
                map[r][c] = newshark;
                list.add(newshark);
            }
        }

        start();

    }

    public static class Sh{
        int r,c,s,d,z;

        public Sh(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
