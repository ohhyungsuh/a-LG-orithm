import java.util.*;

class Solution {
    
    private static int[] parents;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        for(int[] cost : costs) {
            if(find(cost[0]) != find(cost[1])) {
                union(cost[0], cost[1]);
                answer += cost[2];
            }
        }
        
        return answer;
    }
    
    private void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);
        
        if(node1 != node2) {
            parents[node2] = node1;
        }
    }
    
    private int find(int node) {
        if(parents[node] == node) {
            return node;
        }
        
        return parents[node] = find(parents[node]);
    }
}