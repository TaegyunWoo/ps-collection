import java.io.*;
import java.util.*;

public class Q9935 {
  static String s;
  static String blow;
  static Deque<Character> deque = new ArrayDeque<>();
  static Deque<Character> subDeque = new ArrayDeque<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    s = br.readLine();
    blow = br.readLine();
    if (blow.length() > s.length()) {
      System.out.println(s);
      return;
    }

    //solution
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      deque.offerLast(c);

      if (c == blow.charAt(blow.length()-1)) {
        int blowIdx = blow.length()-1;

        for (int u = 0; u < blow.length(); u++) {
          if (deque.isEmpty()) break;
          subDeque.offerFirst(deque.pollLast());
          if (subDeque.peekFirst() == blow.charAt(blowIdx)) blowIdx--;
        }

        if (blowIdx != -1) {
          while (!subDeque.isEmpty()) {
            deque.offerLast(subDeque.pollFirst());
          }
        } else {
          subDeque.clear();
        }
      }

    }

    //print answer
    if (deque.isEmpty()) {
      System.out.println("FRULA");
      return;
    }
    StringBuilder answer = new StringBuilder();
    while (!deque.isEmpty()) {
      answer.append(deque.pollFirst());
    }
    System.out.println(answer.toString());
  }
}