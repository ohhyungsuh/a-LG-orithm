import java.util.*;

class Solution {

    static int[] dx = {0,1,1,1,0,-1,-1,-1};
    static int[] dy = {1,1,0,-1,-1,-1,0,1};

    public int solution(int[] arrows) {

        Set<String> nodes = new HashSet<>();
        Set<String> edges = new HashSet<>();

        int x = 0, y = 0;
        int answer = 0;

        nodes.add("0,0");

        for (int dir : arrows) {

            // ⭐ 핵심: 2번 이동
            for (int i = 0; i < 2; i++) {

                int nx = x + dx[dir];
                int ny = y + dy[dir];

                String node = nx + "," + ny;
                String edge = x + "," + y + "-" + nx + "," + ny;
                String reverse = nx + "," + ny + "-" + x + "," + y;

                // ⭐ 방 생성 조건
                if (nodes.contains(node) && !edges.contains(edge)) {
                    answer++;
                }

                nodes.add(node);
                edges.add(edge);
                edges.add(reverse);

                x = nx;
                y = ny;
            }
        }

        return answer;
    }
}