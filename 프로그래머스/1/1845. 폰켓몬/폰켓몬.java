// 1. N 마리 중 N/2 가져도 좋음
// 2. 종류에 따라 번호 붙임
// 3. N/2 마리 가져갈 때 최대한 많은 종류를 가져갈 수 있도록
// 4. N은 항상 짝수로 주어짐

import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Map<Integer, Integer> monsters = new HashMap<>();
        
        for(int n : nums) {
            monsters.put(n, monsters.getOrDefault(n, 0) + 1);
        }
        
        int N = monsters.keySet().size();
        
        if(nums.length / 2 >= N) {
            return N;
        }
        
        return nums.length / 2;
    }
}