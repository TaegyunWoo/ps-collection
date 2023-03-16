import java.io.*;
import java.util.*;

public class Q11723 {
  public static HashSet<Integer> set = new HashSet<>();
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    for (int i = 0; i < n; i++) {
      String[] order = br.readLine().split(" ");

      switch (order[0]) {
        case "add" :
          set.add(Integer.parseInt(order[1]));
          break;
        case "remove" :
          set.remove(Integer.valueOf(order[1]));
          break;
        case "check" :
          if ( set.contains(Integer.parseInt(order[1])) ) answer.append(1 + "\n");
          else answer.append(0 + "\n");
          break;
        case "toggle" :
          if ( !set.remove(Integer.parseInt(order[1])) ) set.add(Integer.parseInt(order[1]));
          break;
        case "all" :
          set = new HashSet<>();
          for (int u = 1; u <= 20; u++) {
            set.add(u);
          }
          break;
        case "empty" :
          set = new HashSet<>();
      }
    }

    br.close();

    System.out.println(answer.toString());
  }
}
