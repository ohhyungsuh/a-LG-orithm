import java.util.*;

class Solution {
    
    private final static int MAX_DISTANCE = Integer.MAX_VALUE;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        int[] distances = new int[n + 1];
        
        List<List<Integer>> maps = initMaps(n, roads);
        Arrays.fill(distances, MAX_DISTANCE);
        
        findMinDistance(maps, distances, destination);
        
        for(int i = 0; i < sources.length; i++) {
            if(distances[sources[i]] == MAX_DISTANCE) {
                answer[i] = -1;
            } else {
                answer[i] = distances[sources[i]];
            }
        }
            
        return answer;
    }
    
    private List<List<Integer>> initMaps(int n, int[][] roads) {
        List<List<Integer>> maps = new ArrayList<>(n + 1);
        
        for(int i = 0; i <= n; i++) {
            maps.add(new ArrayList<>());
        }
        
        for(int[] road : roads) {
            int from = road[0];
            int to = road[1];
            
            maps.get(from).add(to);
            maps.get(to).add(from);
        }
        
        return maps;
    }
    
    private void findMinDistance(List<List<Integer>> maps, int[] distances, int destination) {
        // 출발점은 0으로 설정
        Queue<Integer> q = new LinkedList<>();
        q.add(destination);
        distances[destination] = 0;
        
        while(!q.isEmpty()) {
            int tmp = q.poll();
            
            List<Integer> nearRoads = maps.get(tmp);
            for(int nearRoad : nearRoads) {
                if(distances[nearRoad] == MAX_DISTANCE) {
                    distances[nearRoad] = distances[tmp] + 1;
                    q.add(nearRoad);
                }
            }
        }
    }
}