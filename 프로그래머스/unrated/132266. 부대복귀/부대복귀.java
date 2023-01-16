import java.util.*;

class Solution {
    //dst에서 src까지 구하는데 최단거리 구한다.
    public static int N;
    public static int Destination;
    public static int[][] Roads;
    public static int[] Sources;
    public static List<Integer>[] listArr;
    public static Queue<Integer> qu;
    public static int[] Result;
    
    public static void makeListArr(){ //양방향 인접 리스트 만들기
        for(int i=0;i<=N;i++){
            listArr[i] = new LinkedList<Integer>();
        }
        for(int[] x:Roads){
            // System.out.println(x[0]+" "+x[1]);
            //양방향 그래프
            listArr[x[0]].add(x[1]);
            listArr[x[1]].add(x[0]);
        }
    }
    
    public static void bfs(){
        qu = new LinkedList<>();
        qu.add(Destination);
        int dest = 0;
        Result = new int[N+1];
        Arrays.fill(Result, -1); //-1로 채우기
        Result[Destination] = 0; //dest는 거리 0으로 초기화
        while(!qu.isEmpty()){
            int cur = qu.poll();
            for(int i=0;i<listArr[cur].size();i++){
                int x = listArr[cur].get(i); //listArr에 listArr<Integer>[]로 선언하기 아니면 에러난다.
                if(Result[x] != -1) continue; //거리 측정한 곳이면 진행안한다.
                Result[x] = Result[cur]+1; //다음 칸 거리는 현재 칸+1
                qu.add(x); //큐에 넣어준다.
            }
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        N = n;
        Destination = destination;
        Roads = roads;
        Sources = sources;
        listArr = new List[N+1];
        makeListArr();
        bfs();
        int[] answer = new int[Sources.length];
        for(int i=0;i<Sources.length;i++){
            answer[i] = Result[Sources[i]];
        }
        return answer;
    }
}
//1시간