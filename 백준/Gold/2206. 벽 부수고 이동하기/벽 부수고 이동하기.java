import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            String[] rows = br.readLine().split("");
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(rows[j - 1]);
            }
        }

        System.out.println(bfs(N, M, map));
    }

    private static int bfs(int N, int M, int[][] map) {
        int[][][] visited = new int[N + 1][M + 1][2];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1, 0});
        visited[1][1][0] = 1;

        while(!q.isEmpty()) {
            int[] point = q.poll();
            int x = point[0];
            int y = point[1];
            int chk = point[2];

            if(x == N && y == M) {
                return visited[x][y][chk];
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 1 || N < nx || ny < 1 || M < ny) {
                    continue;
                }

                if(map[nx][ny] == 0 && visited[nx][ny][chk] == 0) {
                    visited[nx][ny][chk] = visited[x][y][chk] + 1;
                    q.add(new int[]{nx, ny, chk});
                    continue;
                }

                if(map[nx][ny] == 1 && chk == 0) {
                    visited[nx][ny][1] = visited[x][y][chk] + 1;
                    q.add(new int[]{nx, ny, 1});
                }
            }
        }

        return -1;
    }
}
