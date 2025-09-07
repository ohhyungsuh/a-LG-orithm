import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int M;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        int totalCheese = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) {
                    totalCheese++;
                }
                board[i][j] = num;
            }
        }

        List<Integer> leftCheese = bfs(0, 0, totalCheese);

        System.out.println(leftCheese.size());
        System.out.println(leftCheese.get(leftCheese.size() - 1));

    }

    private static List<Integer> bfs(int x, int y, int totalCheese) {
        List<Integer> leftCheese = new ArrayList<>();

        Queue<Point> nowCheese = new LinkedList<>();
        nowCheese.add(new Point(x, y));
        visited[x][y] = true;

        Queue<Point> nextCheese = new LinkedList<>();

        while(!nowCheese.isEmpty()) {
            Point p = nowCheese.poll();

            for(int i = 0; i < 4; i ++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(!isInside(nx, ny)) {
                    continue;
                }

                if(visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;

                if(board[nx][ny] == 0) {
                    nowCheese.add(new Point(nx, ny));
                    continue;
                }

                if(board[nx][ny] == 1) {
                    board[nx][ny] = 0;
                    nextCheese.add(new Point(nx, ny));
                }
            }

            if(nowCheese.isEmpty()) {
                if(!nextCheese.isEmpty()) {
                    leftCheese.add(nextCheese.size());
                    nowCheese.addAll(nextCheese);
                    nextCheese.clear();
                    continue;
                }
                break;
            }
        }

        return leftCheese;
    }

    private static boolean isInside(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}