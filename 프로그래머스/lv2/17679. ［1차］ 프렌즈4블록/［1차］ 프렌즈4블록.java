import java.util.Arrays;

class Solution {
    //dfs와 반복문 고민, but 굳이 dfs안해도 되겠다는생각으로 반복문으로 접근했다.
    
    public static int M,N;
    public static String[][] map;
    public static int[][] checkMap;
    public static int[] dr = {0, 0, 1, 1};
    public static int[] dc = {0, 1, 1, 0}; //오, 오밑, 밑
    public static int result;
    public static boolean isContinue;
    
    public static void checkSame(){ //4같이 같은 모양인지 판단한다.
        isContinue = false;
        for(int i=0;i<M-1;i++){ //한 칸씩 마진 필요하다.
            for(int j=0;j<N-1;j++){
                boolean isSame = true;
                for(int k=0;k<4;k++){
                    if(!map[i][j].equals(map[i+dr[k]][j+dc[k]]) || map[i][j].equals("-")){ //기준과 다르면 탈출한다. => 문자열이기때문에 equals사용한다. 빈칸"-"일 때도 탈출한다.
                        isSame = false;
                        break;
                    }
                }
                if(isSame){ //반복문 안의 if문 안들어가면 모두 같은 모양이다.
                    for(int k=0;k<4;k++){
                        checkMap[i+dr[k]][j+dc[k]] = 1; //모양이 같은 칸 1로 표시
                        isContinue = true; //2x2모양이 있으면 main의 while문 계속 진행
                    }
                }
            }
        }
    }
    
    // public static boolean isContinue(){ //checkMap에 1이 있으면 계속 진행한다. => boolean형 변수로 체크할 수 있다.
    //     for(int i=0;i<M;i++){
    //         for(int j=0;j<N;j++){
    //             if(checkMap[i][j] == 1){
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }
    
    public static void erase(){
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(checkMap[i][j]==1){
                    map[i][j] = "-";
                    result += 1;
                }
            }
        }
    }
    
    public static void fall(){
        for(int j=0;j<N;j++){ //열을 고정해 놓고
            int fallNum = 0;
            for(int i=M-1;i>=0;i--){ //행따라 올라간다.
                if(checkMap[i][j] == 1){
                    fallNum += 1;
                }
                //밑에 checkMap과 map 둘중 아무거나 사용해도 된다.
                if(checkMap[i][j]==0 && fallNum != 0){ //지워지지 않는 블록이고, 밑에 빈칸이 있으면
                    map[i+fallNum][j] = map[i][j]; //블록을 밑으로 내리고
                    map[i][j] = "-"; //원래 칸은 빈칸으로 만든다.
                }
            }
        }
    }
    
    public static void print(String[][] map){
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public int solution(int m, int n, String[] board) {
        
        M=m;
        N=n;
        map = new String[M][N];
        for(int i=0;i<M;i++){
            map[i] = board[i].split("");
        }
        result = 0;
        isContinue = false;
        do{
            checkMap = new int[M][N];
            checkSame();
            erase();
            fall();
            // print(map);
            // System.out.println("==============");
        }while(isContinue);
        return result;
    }
}