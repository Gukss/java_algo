class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int c = w; //1부터
        int r = (n/c); //1부터
        if (n%c!=0) {
            r+=1;
        }
        int[][] arr = new int[r][c];
        
        int idx = 1;
        int nr = 0;
        int nc = 0;
        boolean stop = false;
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (i%2==0) {
                    arr[i][j] = idx;
                    if (idx == num) {
                        nr = i; //0부터
                        nc = j; //0부터
                    }
                    idx += 1;
                } else {
                    arr[i][c-1-j] = idx;
                    if (idx == num) {
                        nr = i; 
                        nc = c-1-j;
                    }
                    idx += 1;
                }
                if (idx > n) {
                    break;
                }
            }
        }
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        for (int i=nr;i<r;i++){
            if (arr[i][nc] == 0) {
                break;
            }
            answer += 1;
        }
        System.out.println("r: "+r+", nr: "+nr+", c: "+c+", nc: "+nc);
        return answer;
    }
}