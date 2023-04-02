import java.io.*;
import java.util.*;

public class Q1406 {
  public static int m;
  public static List<Character> answerString = new LinkedList<>();
  public static String[] orderAry;
  public static ListIterator<Character> iter;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    for (int i = 0; i < s.length(); i++) {
      answerString.add(s.charAt(i));
    }
    m = Integer.parseInt(br.readLine());
    orderAry = new String[m];
    for (int i = 0; i < m; i++) {
      orderAry[i] = br.readLine();
    }

    //Solution
    solution();

    //정답 출력
    StringBuilder answer = new StringBuilder();
    for (char c : answerString) {
      answer.append(c);
    }
    System.out.println(answer.toString());
  }

  private static void solution() {
    iter = answerString.listIterator();

    while (iter.hasNext()) {
      iter.next();
    }

    for (String order : orderAry) {
      char type = order.charAt(0);

      if (type == 'L') moveLeft();
      else if (type == 'D') moveRight();
      else if (type == 'B') remove();
      else insert(order.charAt(2));
    }
  }

  private static void moveLeft() {
    if (iter.hasPrevious()) iter.previous();
  }

  private static void moveRight() {
    if (iter.hasNext()) iter.next();
  }

  private static void remove() {
    if (!iter.hasPrevious()) return;
    iter.previous();
    iter.remove();
  }

  private static void insert(char c) {
    iter.add(c);
  }
}
