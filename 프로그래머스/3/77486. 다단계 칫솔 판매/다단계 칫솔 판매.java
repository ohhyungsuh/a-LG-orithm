import java.util.*;

class Solution {
    private static int[] answer;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int M = seller.length;
        answer = new int[N];
        Map<String, Integer> employeeIdx = new HashMap<>();
        
        for(int i = 0; i < N; i++) {
            employeeIdx.put(enroll[i], i);
        }
        
        for(int i = 0; i < M; i++) {
            calculateAmount(referral, seller[i], amount[i] * 100, employeeIdx);
        }
        
        return answer;
    }
    
    private void calculateAmount(String[] referral, String seller, int amount, Map<String, Integer> employeeIdx) {
        int idx = employeeIdx.get(seller);
        
        if(amount < 10) {
            answer[idx] += amount;
            return;
        }
        
        answer[idx] += amount - amount / 10;
        
        if(referral[idx].equals("-")) {
            return;
        }
        
        calculateAmount(referral, referral[idx], amount / 10, employeeIdx);
    }
}