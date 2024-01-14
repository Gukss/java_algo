import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 바이러스 번호 순서대로 전염이 되어야하기 때문에 priority queue에 담아야한다.
// 바이러스의 좌표와 번호를 저장해야하기 때문에 클래스를 만든다.
// map은 해당 위치에 바이러스가 들어있는지만 검사만 하면 되기때문에 boolean으로 만들어도 된다.

public class Main {
	public static int N, K, S, X, Y;
	public static int[][] map;
	public static PriorityQueue<Virus> pq;
	public static int[] dr = {0,1,0,-1}; //위오아왼
	public static int[] dc = {-1,0,1,0};
	public static boolean[][] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		pq = new PriorityQueue<Virus>(new Comparator<Virus>(){
			public int compare(Virus o1, Virus o2){
				if(o1.n == o2.n){
					return o1.r-o2.r;
				}else{
					return o1.n-o2.n;
				}
			}
		});

		N = Integer.parseInt(st.nextToken()); //map 크기
		K = Integer.parseInt(st.nextToken()); //k번 까지의 바이러스 종류
		map = new int[N][N];
		v = new boolean[N][N];

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				int insertNum = Integer.parseInt(st.nextToken());
				if(insertNum != 0){
					map[i][j] = insertNum;
					pq.add(new Virus(i,j,insertNum));
					v[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); //s초
		X = Integer.parseInt(st.nextToken())-1; //행
		Y = Integer.parseInt(st.nextToken())-1; //열 => 1,1이 왼쪽위기 때문에 보정
		//가장 왼쪽 위가 1,1

		for(int i=0;i<S;i++){ //S초 동안 진행된다.
			//pq가 한 번 다 비위지고, 다시 채워져야한다.
			//N이 200, 200*200은 40,000
			//비우면서 추가하면 숫자가 더 작은거는 앞쪽에서 빠져버리는 수가 있어서, 
			//map을 사용해서 반복문을 돌면서 pq를 비우고, 새로 채워야한다.

			//qp를 비우면서 경계검사를 하고, 0이면 map에 번호를 추가한다.
			if(map[X][Y] != 0){
				break;
			}
			while(!pq.isEmpty()){
				Virus cur = pq.poll();
				for(int j=0;j<4;j++){
					int nr = cur.r+dr[j];
					int nc = cur.c+dc[j];
					if(nr>=0&&nc>=0&&nr<N&&nc<N&& !v[nr][nc] && map[nr][nc]==0){ //다른 바이러스가 있으면 못들어간다.
						map[nr][nc] = cur.n;
						
					}
				}
			}
			//pq를 다 비우고, 다시 map을 돌면서 pq를 채운다.
			for(int j=0;j<N;j++){
				for(int k=0;k<N;k++){
					if(!v[j][k] && map[j][k]!=0){ //0이 아닐 때
						pq.add(new Virus(j,k,map[j][k]));
					}
				}
			}
		}
		//출력
		System.out.println(map[X][Y]);
		// System.out.println(0);
	}

	public static class Virus{
		int r,c,n;

		public Virus(int r, int c, int n){
			this.r = r;
			this.c = c;
			this.n = n;
		}
	}
}