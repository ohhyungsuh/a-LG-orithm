import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int round = 1;
        
        Set<String> usedWords = new HashSet<>();
        usedWords.add(words[0]);
        
        for(int i = 1; i < words.length; i++) {
            if(i % n == 0) {
                round++;
            }
            
            if(usedWords.contains(words[i]) || 
               words[i-1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                return new int[]{i % n + 1, round};
            }
            
            usedWords.add(words[i]);
        }

        return new int[]{0, 0};
    }
}