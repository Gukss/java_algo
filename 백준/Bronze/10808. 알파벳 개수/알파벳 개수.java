import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10808번 알파벳 개수
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];

        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            count[(int) input.charAt(i) - 97] += 1;
        }

        for (int i = 0; i < count.length; i++) {
            sb.append(count[i]);
            sb.append(" ");
        }

        System.out.println(sb);
    }
}