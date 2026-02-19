import java.util.*;

class Solution {
    
    private static int answer = -1;
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(dungeons, visited, k, 0);
        return answer;
    }
    
    private void dfs(int[][] dungeons, boolean[] visited, int k, int cnt) {
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                dfs(dungeons, visited, k - dungeons[i][1], cnt + 1);
                visited[i] = false;
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}