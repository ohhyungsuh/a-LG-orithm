import java.util.*;

class Solution {
    private static int answer = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        dfs(0, 0, picks, minerals);
        
        return answer;
    }
    
    private void dfs(int idx, int accumTired, int[] picks, String[] minerals) {
        // 사용할 곡괭이 또는 캐낼 광물이 없으면 종료
        if(!isLeftPicks(picks) || !isLeftMinerals(idx, minerals)) {
            answer = Math.min(answer, accumTired);
            return;
        }
        
        // 로직
        for(int j = 0; j < 3; j++) {
            if(picks[j] != 0) {
                int tired = calTired(j, idx, minerals);
                picks[j] -= 1;
                dfs(idx + 5, accumTired + tired, picks, minerals); 
                picks[j] += 1;
            }
        }
    }
    
    private boolean isLeftPicks(int[] picks) {
        for(int pick : picks) {
            if(pick > 0) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isLeftMinerals(int idx, String[] minerals) {
        if(minerals.length <= idx) {
            return false;
        }
        
        return true;
    }
    
    private int calTired(int pick, int start, String[] minerals) {
        int end = Math.min(start + 5, minerals.length);
        int tired = 0;
        
        for(int i = start; i < end; i++) {
            tired += combTired(pick, minerals[i]);
        }
        
        return tired;
    }
    
    private int combTired(int pick, String mineral) {
        if(mineral.equals("diamond")) {
            if(pick == 0) {
                return 1;
            } else if(pick == 1) {
                return 5;
            } else {
                return 25;
            }
        } else if(mineral.equals("iron")) {
            if(pick == 0) {
                return 1;
            } else if(pick == 1) {
                return 1;
            } else {
                return 5;
            }
        } else {
            return 1;
        }
    }
}