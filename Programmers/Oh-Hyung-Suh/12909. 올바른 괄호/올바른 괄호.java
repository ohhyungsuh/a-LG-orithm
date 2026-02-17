import java.util.*;

class Solution {
    boolean solution(String s) {
        List<Character> buckets = new ArrayList<>();
        
        for(char c : s.toCharArray()) {
            if(buckets.size() == 0 || c == '(') {
                buckets.add(c);
                continue;
            }
            
            if(buckets.get(buckets.size() - 1) == '(') {
                buckets.remove(buckets.size() - 1);
            } 
        }

        return buckets.size() == 0 ? true : false;
    }
}