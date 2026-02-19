import java.util.*;

class Solution {
    
    private static final String[] combs = {"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        List<String> dict = new ArrayList<>();
        dfs(dict, 0, new StringBuilder());
        return dict.indexOf(word) + 1;
    }
    
    private void dfs(List<String> dict, int depth, StringBuilder sb) {
        if(depth >= 5) {
            return;
        }
        
        for(int i = 0; i < 5; i++) {
            sb.append(combs[i]);
            dict.add(sb.toString());
            dfs(dict, depth + 1, sb);
            sb.delete(sb.length() - 1, sb.length());
        }
    }
}