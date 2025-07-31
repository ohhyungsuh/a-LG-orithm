// 1. 그래픈가...... 많이 안 풀어봐서 약한 유형 중 하나네..
// 2. 지역 간 이동 시간은 1이고, 각 부대원들이 최단 시간에 부대 복귀. 복귀 불가능할 수도 있음
// 3. 총 지역 수 n, 길 정보 2차원 배열 roads, 부대원 위치한 지역 sources, 부대 위치 destination
// 4. 원하는 값은 복귀할 수 있는 최단 시간 담은 배열 return
// 5. 생각보다 배열 크기가 좀 크네
// 6. 왕복 가능
// 7. 이런 거 어떻게 풀더라. 그냥 List 만들어도 될라나. 최대 10만개 나오는데. 어쩔 수 없지. 최단이 bfs가 유리했던거같고.
// 8. 아 근데 10만개 다 돌면 무조건 시간 초과일 거 같은데, 알고리즘 기억이 안 난다.
// 9. 엇 근데 부대 복귀 못하는 사람도 찾으려면 초기 값 알아야 할 거 같은데
// 10. 어차피 q 돌릴거니까 초기 배열 필요할 듯
// 11. sources 에서 뻗어나가지 말고, 그냥 destination에서 뻗어나가는게 훨씬 빠르겠다. 그게 맞는 듯
// 12. 아 근데 얘가 처음 간 곳이 무조건 빠른 건 아닌건가? 무지성으로 다 돌면 무조건 시간 초과 삘인데 이거

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
        List<List<Integer>> maps = new ArrayList<>(n);
        
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
                if(distances[nearRoad] == MAX_DISTANCE ||
                  distances[nearRoad] > distances[tmp] + 1) {
                    distances[nearRoad] = distances[tmp] + 1;
                    q.add(nearRoad);
                }
            }
        }
    }
}