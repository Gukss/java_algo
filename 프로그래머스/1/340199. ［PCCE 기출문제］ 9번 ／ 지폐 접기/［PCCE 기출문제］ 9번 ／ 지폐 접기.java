class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int bMax = Math.max(bill[0], bill[1]);
        int bMin = Math.min(bill[0], bill[1]);        
        int wMax = Math.max(wallet[0], wallet[1]);
        int wMin = Math.min(wallet[0], wallet[1]);
        while((bMin > wMin) || (bMax > wMax)) {
            bMax = bMax / 2;
            bill[0] = Math.max(bMax, bMin);
            bill[1] = Math.min(bMax, bMin);
            bMax = Math.max(bill[0], bill[1]);
            bMin = Math.min(bill[0], bill[1]);     
            // System.out.println("bMax: "+bMax+", bMin: "+bMin);
            answer += 1;
        }
        return answer;
    }
}