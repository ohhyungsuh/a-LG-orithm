import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 1;
        List<List<Integer>> graphs = init(n, edge);
        
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = true;
        dist[1] = 0;
        
        while(!q.isEmpty()) {
            int v = q.poll();
            
            for(int node : graphs.get(v)) {
                if(visited[node]) continue;
                
                visited[node] = true;
                dist[node] = dist[v] + 1;
                q.add(node);
                
            }
        }
        
        Arrays.sort(dist);
        
        int maxValue = dist[n];
        for(int i = n - 1; i >= 1; i--) {
            if(maxValue > dist[i]) {
                break;
            }
            
            answer++;
        }
        
        return answer;
    }
    
    private List<List<Integer>> init(int n, int[][] edge) {
        List<List<Integer>> graphs = new ArrayList<>();
        
        for(int i = 0; i < n + 1; i++) {
            graphs.add(new ArrayList<>());
        }
        
        for(int[] e : edge) {
            graphs.get(e[0]).add(e[1]);
            graphs.get(e[1]).add(e[0]);
        }
        
        return graphs;
    }
}