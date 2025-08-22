import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] switches = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if(gender == 1) {
                changeByMale(idx, switches, N);
            } else {
                changeByFemale(idx, switches, N);
            }
        }

        print(switches, N);
    }

    private static void changeByMale(int idx, int[] switches, int N) {
        for(int i = idx; i <= N; i += idx) {
            switches[i] = (switches[i] + 1) % 2;
        }
    }

    private static void changeByFemale(int idx, int[] switches, int N) {
        switches[idx] = (switches[idx] + 1) % 2;
        for(int i = 1; i <= N / 2; i++) {
            int left = idx - i;
            int right = idx + i;

            if(left <= 0 || right > N) {
                break;
            }

            if(switches[left] != switches[right]) {
                break;
            }

            switches[left] = (switches[left] + 1) % 2;
            switches[right] = (switches[right] + 1) % 2;
        }
    }

    private static void print(int[] switches, int N) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            if(i % 20 == 0) {
                sb.append(switches[i]).append("\n");
            } else {
                sb.append(switches[i]).append(" ");
            }
        }

        System.out.println(sb);
    }
}