import java.util.*;

class Solution {
    public int solution(String numbers) {
        Set<Integer> primes = new HashSet<>();
        boolean[] visited = new boolean[numbers.length()];
        
        dfs(numbers, primes, visited, "0");
        
        return primes.size();
    }
    
    private void dfs(String numbers, Set<Integer> primes, boolean[] visited, String num) {
        int tmp = Integer.parseInt(num);
        if(isPrime(tmp) && !primes.contains(tmp)) {
            primes.add(tmp);
        }
        
        for(int i = 0; i < numbers.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(numbers, primes, visited, num + numbers.charAt(i));
                visited[i] = false;
            }
        }
    }
    
    private boolean isPrime(int num) {
        if(num < 2) {
            return false;
        }
        
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}