class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[] includeFirst = new int[n];
        int[] excludeFirst = new int[n];
        
        includeFirst[0] = includeFirst[1] = money[0];
        excludeFirst[0] = 0; excludeFirst[1] = money[1];
        
        for(int i=2; i<n; i++){
            includeFirst[i] = Math.max(includeFirst[i-1], includeFirst[i-2] + money[i]);
            excludeFirst[i] = Math.max(excludeFirst[i-1], excludeFirst[i-2] + money[i]);
        }
        
        
        return Math.max(includeFirst[n-2], excludeFirst[n-1]);
    }
}