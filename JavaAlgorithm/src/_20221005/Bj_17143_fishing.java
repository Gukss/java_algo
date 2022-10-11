package _20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_17143_fishing {
    static int R,C,M;
    static int[] dr = {-1, 1, 0, 0}; //위, 밑, 오, 왼
    static int[] dc = {0, 0, 1, -1}; //위, 밑, 오, 왼
    static List<Sh> list;
    static Sh[][] map;
    static int result;

    public static void getshark(int col){
        for (int i = 0; i < R; i++) {
            if(map[i][col] != null){ //가장 아래부터 상어 잡기 => 땅이 가장 위다!!
                result += map[i][col].z;
                list.remove(map[i][col]); //null로 만들기 전에 삭제 먼저
                map[i][col] = null;
                break;
            }
        }
    }

    public static void moveshark(){
        Sh[][] tempmap = new Sh[R][C];
        List<Sh> removeindexlist = new ArrayList<>();

        for(int i = 0;i<list.size();i++){
            Sh cur = list.get(i);
            int d = cur.d;
            int speed = cur.s;
            int sr = cur.r;
            int sc = cur.c;
            int nr = sr;
            int nc = sc;
            if(d==0||d==1){
                speed = speed % (2*(R-1));
            }else if(d==2||d==3){
                speed = speed % (2*(C-1));
            }
            for (int j = 0; j < speed; j++) {
                nr += dr[d];
                nc += dc[d];
                //경계선 방향으로 방향을 가지고 있는 input이 있다.
                if(!(nr>=0&&nr<R&&nc>=0&&nc<C)){ //경계확인 => 경계 벗어났을 때
                    //값 돌려주고
                    nr -= dr[d];
                    nc -= dc[d];
                    if(d==0||d==2){ //0,2 =>+1하면 반대방향
                        d += 1;
                    }else if(d==1||d==3){ //1,3=>-1하면 반대방향
                        d -= 1;
                    }
                    j--;
                }
            }
            //이동 완료 => 같은 자리에 있는 상어가 있다면 => 크기비교
            if(tempmap[nr][nc] != null){
                if(tempmap[nr][nc].z > cur.z){ //먼저 들어가 있던 상어가 크다면 tempmap에 넣지 않고, list에서 삭제
                    removeindexlist.add(cur);
                }else{ //나중에 들어가는 상어가 크다면 cur 갱신하고, tempmap에 덮어쓰고,
                    removeindexlist.add(tempmap[nr][nc]);
                    cur.r = nr;
                    cur.c = nc;
                    cur.d = d;
                    tempmap[nr][nc] = cur;
                }
            }else{ //같은 자리에 상어가 없다면 => 상어 좌표, 방향 갱신하고 map에 넣기
                cur.r = nr;
                cur.c = nc;
                cur.d = d;
                tempmap[nr][nc] = cur;
            }
        }

        for (int i = 0; i < removeindexlist.size(); i++) { //리스트는 한 번에 삭제 => for문에서 인덱스 오류 생긴다.
            Sh remove = removeindexlist.get(i);
            list.remove(remove);
        }
        //모든 상어 이동 완료
        map = tempmap;
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
        map = new Sh[R][C]; //인덱스 0부터 시작
        list = new ArrayList<>();
        result = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            Sh newshark = new Sh(r,c,s,d,z); //방향은 1~4 => 0~3으로
            map[r][c] = newshark;
            list.add(newshark);
        }
        start();
        System.out.println(result);
    }

    public static class Sh{
        int r,c,s,d,z;

        public Sh(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s; //속력
            this.d = d; //방향
            this.z = z; //크기
        }
    }
}
//3시간