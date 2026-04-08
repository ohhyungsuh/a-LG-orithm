import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String tree : skill_trees) {
            boolean isValid = true;
            int idx = 0;
            
            for(char c : tree.toCharArray()) {
                int pos = skill.indexOf(c);
                if(pos == -1) continue;
                
                if(pos != idx) {
                    isValid = false;
                    break;
                } else {
                    idx++;
                }
            }
            
            if(isValid) {
                answer++;
            }
        }
        
        return answer;
    }
}