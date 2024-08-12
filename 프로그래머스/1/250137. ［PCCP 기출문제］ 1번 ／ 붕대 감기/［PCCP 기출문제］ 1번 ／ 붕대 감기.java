import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int len = attacks.length;
        int conti = 0;
        int attackIndex = 0;
        for(int i=1;i<=attacks[len-1][0];i++){
            if(i == attacks[attackIndex][0]){ //몬스터공격
                conti = 0;
                health -= attacks[attackIndex][1];
                attackIndex += 1;
                if(health <= 0){
                    break;
                }
            }else{ //회복
                health += bandage[1]; //항상 회복해주고, 최대체력 벗어나면 
                //가장 아래에서 최대체력으로 초기화해주기
                conti += 1;
                if(conti == bandage[0]){
                    health += bandage[2];
                    conti = 0;
                }
                if(health > maxHealth){
                    health = maxHealth;
                }
            }
            // System.out.println(i+" helath: "+health);
        }
        
        if(health<=0){
            return -1;
        }else{
            return health;
        }
    }
}