import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> kinds = new HashMap<>();
        
        for(String[] cloth : clothes) {
            kinds.put(cloth[1], kinds.getOrDefault(cloth[1], 0) + 1);
        }
        
        for(Map.Entry<String, Integer> kind : kinds.entrySet()) {
            answer *= (kinds.get(kind.getKey()) + 1);
        }
        
        return answer - 1;
    }
}