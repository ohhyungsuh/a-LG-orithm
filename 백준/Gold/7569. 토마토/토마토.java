import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] tomatoes;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomatoes = new int[H][N][M];
        Queue<int[]> q = new LinkedList<>();
        int notRipenCount = 0;

        // 입력 처리
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    int t = Integer.parseInt(st.nextToken());
                    tomatoes[h][n][m] = t;

                    if (t == 1) {
                        q.add(new int[]{m, n, h, 0}); // 익은 토마토만 큐에 추가
                    } else if (t == 0) {
                        notRipenCount++; // 안 익은 토마토 개수 카운트
                    }
                }
            }
        }

        // 예외 처리: 처음부터 다 익어 있음
        if (notRipenCount == 0) {
            System.out.println(0);
            return;
        }

        // BFS 실행
        int result = bfs(q, notRipenCount);
        System.out.println(result);
    }

    static int bfs(Queue<int[]> q, int notRipenCount) {
        int maxDay = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], z = cur[2], day = cur[3];
            maxDay = Math.max(maxDay, day);

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                // 배열 범위 체크 + 안 익은 토마토만 처리
                if (0 <= nx && nx < M && 0 <= ny && ny < N && 0 <= nz && nz < H && tomatoes[nz][ny][nx] == 0) {
                    tomatoes[nz][ny][nx] = 1; // 익었다고 표시
                    notRipenCount--;          // 남은 안 익은 토마토 감소
                    q.add(new int[]{nx, ny, nz, day + 1});

                    // 최적화: 모두 익으면 바로 종료
                    if (notRipenCount == 0) return day + 1;
                }
            }
        }

        // 안 익은 토마토가 남아 있으면 -1
        return -1;
    }
}
