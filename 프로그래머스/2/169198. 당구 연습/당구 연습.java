class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int ballsSize = balls.length;
        for(int i=0;i<ballsSize;i++){
            int k=1000000;
            int a = balls[i][0];
            int b = balls[i][1];
            
            // System.out.println(balls[i][0]+balls[i][1]+'\n');
            if(startX!=a || b>startY){
                k = Math.min(k,(int)Math.pow((startX-a),2)+(int)Math.pow((startY+b),2));
                // System.out.println("1. "+k);
            }
            if(startY!=b || a>startX){
                k = Math.min(k,(int)Math.pow((startX+a),2)+(int)Math.pow((startY-b),2));
                // System.out.println("2. "+k);
            }
            if(startX!=a || b<startY){
                k  = Math.min(k,(int)Math.pow((startX-a),2)+(int)Math.pow((2*n-b-startY),2));
                // System.out.println("3. "+k);
            }
            if(startY!=b || a<startX){
                k = Math.min(k,(int)Math.pow((2*m-a-startX),2)+(int)Math.pow((startY-b),2));
                // System.out.println("4. "+k);
            }
            answer[i] = k;
        }
        return answer;
    }
}