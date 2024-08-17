class Solution {
    public int solution(int n, int m, int[] section) {
        //i=0부터 시작해서, section[i]+m-1를 end 저장해놓고
        //다음 section[i]가 end보다 작으면 continue
        //continue 안하면 result++
        int s = section[0];
        int e = section[0]+m-1;
        int answer = 1;
        for(int x: section){
            if(x>=s && x<=e){
                continue;
            }else{
                s = x;
                e = x+m-1;
                answer += 1;
            }
        }
        return answer;
    }
}