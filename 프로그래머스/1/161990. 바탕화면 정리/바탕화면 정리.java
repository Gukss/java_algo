import java.util.*;

class Solution {
    public int[] solution(String[] wallpaper) {
        int rM = -1;
        int rm = 102;
        int cM = -1;
        int cm = 102;
        
        int[] hr = new int[2];
        int[] hc = new int[2];
        int[] lr = new int[2];
        int[] lc = new int[2];
        
        for (int i=0;i<wallpaper.length;i++) {
            for (int j=0;j<wallpaper[i].length();j++) {
                char c = wallpaper[i].charAt(j);
                if ('#'== c) {
                    if (i < rm) {
                        rm = i;
                        // lr[0] = i;
                        // lr[1] = j;
                    }
                    if (i > rM) {
                        rM = i;
                        // hr[0] = i;
                        // hr[1] = j;
                    }
                    if (j < cm) {
                        cm = j;
                        // lc[0] = i;
                        // lc[1] = j;
                    }
                    if (j > cM) {
                        cM = j;
                        // hc[0] = i;
                        // hr[1] = j;
                    }
                    rM = Math.max(rM, i);
                    rm = Math.min(rm, i);
                    cM = Math.max(cM, j);
                    cm = Math.min(cm, j);
                }
            }
        }
        // System.out.println("rm: "+rm+", cm: "+cm+", rM: "+rM+", cM: "+cM);
        int[] answer = new int[4];
        answer[0] = rm;
        answer[1] = cm;
        answer[2] = rM+1;
        answer[3] = cM+1;
        
        return answer;
    }
}