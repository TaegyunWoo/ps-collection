import java.io.*;
import java.util.*;

public class Q25757 {
  public static int n;
  public static String type;
  public static HashSet<String> userSet = new HashSet<>();
  public static int answer = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    type = st.nextToken();

    for (int i = 0; i < n; i++) {
      String user = br.readLine();
      userSet.add(user);
    }

    br.close();

    if (type.equals("Y")) System.out.println(userSet.size());
    else if (type.equals("F")) System.out.println(userSet.size() / 2);
    else System.out.println(userSet.size() / 3);
  }
}
