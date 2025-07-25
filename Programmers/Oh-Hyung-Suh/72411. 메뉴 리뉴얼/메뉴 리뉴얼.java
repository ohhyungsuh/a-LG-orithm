import java.util.*;
import java.lang.*;

class Solution {
    
    private static Map<String, Integer> combinations;
    
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        
        for(int c : course) {
            combinations = new HashMap<>();
            for(String order : orders) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars);
                dfs(chars, 0, c, new StringBuilder());
            }
            
            List<Map.Entry<String, Integer>> entryList = new LinkedList<>(combinations.entrySet());
            entryList.sort((o1, o2) -> o2.getValue() - o1.getValue());
            
            int max = Integer.MIN_VALUE;
            for(Map.Entry<String, Integer> entry : entryList) {
                if(entry.getValue() >= 2 && entry.getValue() >= max) {
                    max = entry.getValue();
                    result.add(entry.getKey());
                }
            } 
        }
        
        Collections.sort(result);
            
        return result.toArray(new String[result.size()]);
    }
    
    private void dfs(char[] order, int idx, int c, StringBuilder sb) {
        if(sb.length() == c) {
            String key = sb.toString();
            
            combinations.put(key, combinations.getOrDefault(key, 0) + 1);
            return;
        }
        
        for(int i = idx; i < order.length; i++) {
            dfs(order, i + 1, c, sb.append(order[i]));
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}