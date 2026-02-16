// 1. 마라톤에 참여한 선수들 이름 -> participant 배열
// 2. 완주한 선수들 이름 -> completion 배열
// 3. 완주하지 못한 선수들 이름 반환
// 4. 선수의 수는 100,000명 이하, completion 길이 = participant 길이 - 1
// 5. 동명이인 가능성 존재
// 6. Set은 동명이인 처리가 불가능하므로, Map 사용

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> runners = new HashMap<>();
        
        for (String p : participant) {
            runners.put(p, runners.getOrDefault(p, 0) + 1);
        }
        
        for (String c : completion) {
            int v = runners.get(c) - 1;
            
            if (v == 0) {
                runners.remove(c);
                continue;
            }
            
            runners.put(c, v);
        }
        
        return runners.keySet().iterator().next();
    }
}