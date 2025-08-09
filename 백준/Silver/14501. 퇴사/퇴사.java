import java.io.*;
import java.util.*;

public class Main {

    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] costs = new int[N][2];

        for(int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");

            costs[i][0] = Integer.parseInt(input[0]);
            costs[i][1] = Integer.parseInt(input[1]);
        }

        dfs(0, 0, N, costs);
        System.out.println(answer);
    }

    private static void dfs(int idx, int sum, int N, int[][] costs) {
        if(idx > N) {
            return;
        }

        answer = Math.max(answer, sum);

        for(int i = idx; i < N; i++) {
            dfs(i + costs[i][0], sum + costs[i][1], N, costs);
        }
    }
}
