import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> bridge = new ArrayDeque<>();
        int time = 0;
        int totalWeight = 0;
        int idx = 0;

        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while (idx < truck_weights.length) {

            totalWeight -= bridge.poll();
            time++;

            if (totalWeight + truck_weights[idx] <= weight) {
                bridge.offer(truck_weights[idx]);
                totalWeight += truck_weights[idx];
                idx++;
            } else {
                bridge.offer(0);
            }
        }

        return time + bridge_length;
    }
}