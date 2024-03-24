
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Stack<int[]> tower = new Stack<>();
        StringBuilder sb = new StringBuilder();
//        tower.add(new int[] {sc.nextInt(), 0});
//        rc[0] = 0;
//        for (int i = 1; i < N; i++) {
//            int[] top = tower.peek();
//            int next = sc.nextInt();
//            if(tower.peek()[0] < next) {
//                tower.pop();
//            }
//
//
//            if(tower.isEmpty()) {
//                rc[i] = 0;
//            }else{
//                rc[i] = tower.peek()[1]+1;
//            }
//            tower.add(new int[] {next, i});
//        }
        st = new StringTokenizer(br.readLine());

        tower.add(new int[] {Integer.parseInt(st.nextToken()), 0});
        System.out.printf("%d ", 0);
        for (int i = 0; i < N-1; i++) {
            int next = Integer.parseInt(st.nextToken());
            while(!tower.isEmpty() && tower.peek()[0] < next){ //if 가 아니고 여러개를 빼줘야 한다.
                tower.pop();
            }
            if(tower.isEmpty()){
//                System.out.printf("%d ", 0);
                sb.append(0).append(" ");
            }else{
//                System.out.printf("%d ", tower.peek()[1]+1);
                sb.append(tower.peek()[1]+1).append(" ");
            }
            tower.add(new int[] {next, i+1});
        }
        System.out.println(sb);
    }
}
