import java.util.*;

// wires 하나를 떼고, bfs 진행?

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        int[][] networks = new int[n][n];
        
        for(int[] wire : wires) {
            int v1 = wire[0] - 1;
            int v2 = wire[1] - 1;
            
            networks[v1][v2] = 1;
            networks[v2][v1] = 1;
        }
        
        for(int[] wire : wires) {
            int v1 = wire[0] - 1;
            int v2 = wire[1] - 1;
            
            networks[v1][v2] = 0;
            networks[v2][v1] = 0;
            answer = Math.min(answer, calDiff(networks, n));
            networks[v1][v2] = 1;
            networks[v2][v1] = 1;
        }
        
        return answer;
    }
    
    private int calDiff(int[][] networks, int n) {
        int oneSide = 0;
        boolean[] visited = new boolean[n];
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        
        while(!q.isEmpty()) {
            int network = q.poll();
            visited[network] = true;
            oneSide++;
            
            for(int i = 0; i < n; i++) {
                if(network != i && !visited[i] && networks[network][i] == 1) {
                    q.add(i);
                }
            }
        }
        
        int otherSide = n - oneSide;
        
        return Math.max(oneSide, otherSide) - Math.min(oneSide, otherSide);
    }
}