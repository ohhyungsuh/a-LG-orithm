import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int M;
    private static int N;
    private static int H;

    private static final int[] dx = {-1, 0, 1, 0, 0, 0};
    private static final int[] dy = {0, -1, 0, 1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new LinkedList<>();
        int ripenTomato = 0;
        int notRipenTomato = 0;

        int[][][] tomatoes = new int[H][N][M];
        for(int z = 0; z < H; z++) {
            for(int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < M; x++) {
                    int tomato = Integer.parseInt(st.nextToken());

                    if(tomato == 0) {
                        notRipenTomato++;
                    } else if(tomato == 1) {
                        ripenTomato++;
                        q.add(new int[]{z, y, x, 0});
                    }

                    tomatoes[z][y][x] = tomato;
                }
            }
        }

        if (notRipenTomato == 0) {
            System.out.println(0);
        } else if(ripenTomato == 0) {
            System.out.println(-1);
        } else {
            int takeDays = bfs(tomatoes, q, notRipenTomato);
            if(takeDays == -1) {
                System.out.println(-1);
            } else {
                System.out.println(takeDays);
            }
        }
    }

    private static int bfs(int[][][] tomatoes, Queue<int[]> q, int notRipenTomato) {
        int days = 0;
        int ripenTomato = 0;

        while(!q.isEmpty()) {
            int[] info = q.poll();

            int z = info[0];
            int y = info[1];
            int x = info[2];
            int day = info[3];

            for(int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int ny = y + dy[i];
                int nx = x + dx[i];
                int newDay = day + 1;

                if(isInside(nz, ny, nx) && tomatoes[nz][ny][nx] == 0) {
                    tomatoes[nz][ny][nx] = 1;
                    ripenTomato++;
                    days = newDay;
                    q.add(new int[]{nz, ny, nx, newDay});
                }
            }
        }

        if(ripenTomato != notRipenTomato) {
            return -1;
        }

        return days;
    }

    private static boolean isInside(int z, int y, int x) {
        return 0 <= z && z < H && 0 <= y && y < N && 0 <= x && x < M;
    }
}