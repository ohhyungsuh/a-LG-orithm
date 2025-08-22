import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            lists.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lists.get(a).add(b);
            lists.get(b).add(a);
        }

        boolean[] visited = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                dfs(i, visited, lists);
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int now, boolean[] visited, List<List<Integer>> lists) {
        if(visited[now]) {
            return;
        }

        visited[now] = true;
        for(int i : lists.get(now)) {
            dfs(i, visited, lists);
        }
    }

}