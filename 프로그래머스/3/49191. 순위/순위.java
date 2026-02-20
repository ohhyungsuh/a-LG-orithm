class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        boolean[][] wins = new boolean[n + 1][n + 1];
        
        for(int[] result : results) {
            wins[result[0]][result[1]] = true;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(wins[i][k] && wins[k][j]) {
                        wins[i][j] = true;
                    }
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                
                if(wins[i][j] || wins[j][i]) {
                    cnt++;
                }
            }
            
            if(cnt == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
}