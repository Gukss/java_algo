package _20220918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Bj_2026_picnic {
    static int K,N,F;
    static ArrayList<Integer>[] list;
    static boolean[] v;

//    public static boolean dfs(int idx, int depth){
//        if(depth == 0){
//            return true;
//        }
//        int size = list[idx].size();
//        int cnt = 0;
//        for (int i = 0; i < size; i++) {
//            if(v[list[idx].get(i)]){
//                cnt += 1;
//            }else{
//
//            }
//        }
//        if(cnt < K){
//            v[idx] = false;
//            return false;
//        }
//        for (int i = 0; i < size; i++) {
//            if(!dfs(list[idx].get(i), depth-1)){
//                return false;
//            }else{
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean check(int num){
        for (int i = 1; i < N+1; i++) {
            if(v[i] && list[num].indexOf(i) == -1){
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(int idx, int depth){
        if(depth == 0){
            return true;
        }
        boolean temp=false;
        for(int x:list[idx]){ //방문할 노드랑 현재까지 방문한 노드가 친구인지 확인
            if(check(x)){
                v[x] = true;
                if(dfs(x, depth-1  )){
                    return true;
                }
                v[x] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); //K명을 소풍 보낸다.
        N = Integer.parseInt(st.nextToken()); //N명 학생중
        F = Integer.parseInt(st.nextToken()); //친구관계 개수
        list = new ArrayList[N+1]; //학생 번호는 1부터 시작

        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < F; i++) { //양방향이다.
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
            list[e].add(s);
        }
        for (int i = 0; i < N+1; i++) {
            list[i].sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            });
        }

        v = new boolean[N+1];
        for (int i = 1; i < N+1; i++) {
            if(list[i].size() >= K-1){
                v[i] = true;
                if(dfs(i, K-1)){
                    break;
                }
                v[i] = false;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 1; i < N+1; i++) {
            if(v[i]) {
                flag = true;
                sb.append(i);
                sb.append('\n');
            }
        }
        if(!flag){
            System.out.println(-1);
        }else{
            System.out.println(sb);
        }

    }
}
//3시간 -> 못품
/*
[input]
4 8 21
5 3
1 4
3 6
6 2
4 6
2 8
8 5
3 5
8 4
3 4
1 2
1 3
6 4
2 6
3 6
1 6
3 6
3 2
8 7
3 8
6 7
[output]
1
2
3
6

[input]
4 5 23
1 4
4 3
1 4
2 4
3 5
1 2
2 1
1 4
3 4
4 2
1 4
2 3
2 1
1 4
3 5
5 1
5 3
2 1
4 3
3 2
2 4
3 4
5 3
[output]
-1

[input]
4 7 19
4 2
6 1
5 1
6 1
4 5
5 7
7 1
1 7
3 4
4 1
2 7
4 6
2 6
1 4
6 5
6 4
7 4
3 7
1 3
[output]
1
3
4
7

[input]
3 5 17
4 5
1 5
5 3
1 4
1 3
3 5
1 4
4 1
4 5
2 5
4 5
5 4
4 1
3 2
3 2
4 5
1 5
[output]
1
3
5
 */