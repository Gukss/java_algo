package day003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj_5427_fire {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			char[][] room = new char[h][w];
			
			int[] pos = new int[2];
			LinkedList<int[]> fire_pos = new LinkedList<>();
			
			for (int j = 0; j < h; j++) {
				String temp = br.readLine();
				for (int k = 0; k < w; k++) {
					room[j][k] = temp.charAt(k);
					if(room[j][k] == '@') {
						pos = new int[]{j, k};
					} else if(room[j][k] == '*'){
						fire_pos.add(new int[] {j,k}); 
					}
				}
			}
			
			LinkedList<int[]> qu = new LinkedList<>();
			boolean[][] visited = new boolean[h][w];
			
			qu.add(pos);
			visited[pos[0]][pos[1]] = true;
			
			int[] dr = {-1,0,1,0};
			int[] dc = {0,1,0,-1};
			int result = 0;
			boolean flag = false;
			
			while(!qu.isEmpty()) {
				result += 1;
				
				int f_pos_size = fire_pos.size();
				for(int k=0;k<f_pos_size;k++) {
					int[] f_pos = fire_pos.poll();
					for (int j = 0; j < 4; j++) {
						int fire_nr = f_pos[0] + dr[j];
						int fire_nc = f_pos[1] + dc[j];

						if(fire_nr<0 || fire_nr>=h || fire_nc<0 || fire_nc >= w) {
							continue;
						}
						if(room[fire_nr][fire_nc] != '#' && room[fire_nr][fire_nc] != '*') {
							fire_pos.add(new int[] {fire_nr, fire_nc});
						}
					}
				}
				for(int[] f_pos: fire_pos) {
					room[f_pos[0]][f_pos[1]] = '*';
				}
				
				
				int pos_size = qu.size();
				for (int k = 0; k < pos_size; k++) {
					int[] cur = qu.poll();
					for (int j = 0; j < 4; j++) {
						int nr = cur[0] + dr[j];
						int nc = cur[1] + dc[j];
						if(nr<0 || nr >= h || nc<0 || nc >= w) {
							//완료조건이다.
							flag = true;
							break;
							
						}
						if(room[nr][nc] != '#' && room[nr][nc] != '*' && !visited[nr][nc]) {						
							qu.add(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
					if(flag) break;
				}
				
				
			}
			if(!flag) {				
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(result);
			}
			while(!qu.isEmpty()) qu.poll();
			while(!fire_pos.isEmpty()) fire_pos.poll();
			
		}
	}

}
