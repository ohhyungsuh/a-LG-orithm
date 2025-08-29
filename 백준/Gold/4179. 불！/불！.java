import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    private static int R;
    private static int C;
    private static boolean[][] fires;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[][] map = new String[R][C];
        fires = new boolean[R][C];
        visited = new boolean[R][C];

        Queue<Position> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                String s = inputs[j];

                if(s.equals("J")) {
                    visited[i][j] = true;
                }

                if(s.equals("F")) {
                    fires[i][j] = true;
                    q.add(new Position(i, j, true));
                }

                map[i][j] = s;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(visited[i][j]) {
                    q.add(new Position(i, j, false, 0));
                }
            }
        }

        int answer = bfs(map, q);
        if(answer == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    private static int bfs(String[][] map, Queue<Position> q) {
        int answer = -1;

        while(!q.isEmpty()) {
            Position p = q.poll();

            // 지훈이 가장자리에 도달한 경우 1분 추가 후 break
            if(!p.isFire && (p.x == 0 || p.x == R - 1 || p.y == 0 || p.y == C - 1)) {
                answer = p.movement + 1;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                // 공통 조건(내부가 아니거나 벽이 있을 때)
                if(!isInside(nx, ny) || isWall(nx, ny, map)) {
                    continue;
                }

                // 불이 안 퍼진 곳이며, 지훈이가 아직 방문하지 않은 곳일 때
                if(!p.isFire && !fires[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Position(nx, ny, false, p.movement + 1));
                }

                // 아직 퍼지지 않은 곳에 불이 퍼질 때
                else if(p.isFire && !fires[nx][ny]) {
                    fires[nx][ny] = true;
                    q.add(new Position(nx, ny, true));
                }
            }
        }

        return answer;
    }

    private static boolean isInside(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    private static boolean isWall(int x, int y, String[][] map) {
        return map[x][y].equals("#");
    }

    static class Position {
        int x;
        int y;
        boolean isFire;
        int movement;

        public Position(int x, int y, boolean isFire) {
            this.x = x;
            this.y = y;
            this.isFire = isFire;
            this.movement = 0;
        }

        public Position(int x, int y, boolean isFire, int movement) {
            this.x = x;
            this.y = y;
            this.isFire = isFire;
            this.movement = movement;
        }
    }
}