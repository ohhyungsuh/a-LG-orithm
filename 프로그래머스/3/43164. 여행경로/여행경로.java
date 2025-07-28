// 1. 공항은 무조건 3글자, 공항 수는 3 ~ 10,000개
// 2. 항공권 모두 사용..
// 3. 가능한 경로가 2개 이상이면 알파벳 순
// 4. 뭐부터 시작할진 랜덤이네,, 그래도 정렬 해놔야 편하겠다. 나중에 머리 안 아플라면
// 5. 

import java.util.*;

class Solution {
    
    private static List<String[]> answer = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        List<String> path = new ArrayList<>();
        int n = tickets.length;
        System.out.println(n);
        Arrays.sort(tickets, (o1, o2) -> {
            if(o1[0].compareTo(o2[0]) == 0) {
                return o1[1].compareTo(o2[1]);
            }
            
            return o1[0].compareTo(o2[0]);
        });
        
        path.add("ICN");
        dfs(n, tickets, new boolean[n], path);
        
        return answer.get(0);
    }
    
    private void dfs(int n, String[][] tickets, boolean[] visited, List<String> path) {
        if(answer.size() == 1) {
            return;
        }
        
        if(n == 0) {
            answer.add(path.toArray(new String[path.size()]));
            return;
        }
        
        String from = path.get(path.size() - 1);
        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && tickets[i][0].equals(from)) {
                visited[i] = true;
                path.add(tickets[i][1]);
                dfs(n - 1, tickets, visited, path);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }
}