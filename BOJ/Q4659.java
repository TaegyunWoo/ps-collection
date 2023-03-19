import java.io.*;

public class Q4659 {
  public static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      String password = br.readLine();
      if (password.equals("end")) break;

      if (!checkFirst(password)) {
        fail(password);
        continue;
      }
      if (!checkSecond(password)) {
        fail(password);
        continue;
      }
      if (!checkThird(password)) {
        fail(password);
        continue;
      }

      succeed(password);
    }

    System.out.println(answer.toString());
  }

  private static boolean checkFirst(String password) {
    if (password.contains("a")
        || password.contains("e")
        || password.contains("i")
        || password.contains("o")
        || password.contains("u")) {
      return true;
    }
    return false;
  }

  private static boolean checkSecond(String password) {
    char beforeChar = password.charAt(0);
    int count = 1;

    for (int i = 1; i < password.length(); i++) {
      char currentChar = password.charAt(i);
      if (isAEIOU(beforeChar) == isAEIOU(currentChar)) count++;
      else count = 1;
      if (count == 3) return false;
      beforeChar = currentChar;
    }
    return true;
  }

  private static boolean isAEIOU(char c) {
    if (c == 'a'
        || c == 'e'
        || c == 'i'
        || c == 'o'
        || c == 'u') {
      return true;
    }
    return false;
  }

  private static boolean checkThird(String password) {
    char beforeChar = password.charAt(0);
    for (int i = 1; i < password.length(); i++) {
      char currentChar = password.charAt(i);
      if (currentChar == beforeChar) {
        if (currentChar != 'e' && currentChar != 'o') return false;
      }
      beforeChar = currentChar;
    }
    return true;
  }

  private static void fail(String password) {
    answer.append("<" + password + "> is not acceptable.\n");
  }

  private static void succeed(String password) {
    answer.append("<" + password + "> is acceptable.\n");
  }
}
