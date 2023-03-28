import java.io.*;

public class Q20310 {
  public static String s;
  public static int count0;
  public static int count1;
  public static String answer;

  public static void main(String[] args) throws IOException {
    //Input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    s = br.readLine();

    //Solution
    solution();

    //정답 출력
    System.out.println(answer);
  }

  private static void solution() {
    StringBuilder result = new StringBuilder(s);

    //count0, count1
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') count0++;
      else count1++;
    }

    count0 /= 2;
    count1 /= 2;

    //Except First 1
    for (int i = 0; i < result.length(); i++) {
      char c = result.charAt(i);
      if (c == '1') {
        if (count1 > 0) {
          result.deleteCharAt(i);
          i--;
          count1--;
        }
      }
    }

    //Except Last 0
    for (int i = result.length()-1; i >= 0; i--) {
      char c = result.charAt(i);
      if (c == '0') {
        if (count0 > 0) {
          result.deleteCharAt(i);
          count0--;
        }
      }
    }

    answer = result.toString();
  }
}
