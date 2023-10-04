import java.io.*;
import java.util.*;

public class Q7490 {
  static final char[] oper = {'+', '-', ' '};
  static int t;
  static int n;
  static List<String> answerList;
  static StringBuilder answer = new StringBuilder();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());

    while (t-- > 0) {
      n = Integer.parseInt(br.readLine());
      answerList = new ArrayList<>();

      brute(1, new StringBuilder());

      //sort
      answerList.sort(Comparator.naturalOrder());
      for (String s : answerList) {
        answer.append(s);
        answer.append("\n");
      }
      answer.append("\n");
    }

    //print answer
    System.out.println(answer.toString());
  }

  private static void brute(int num, StringBuilder result) {
    if (num == n) {
      //result 뒤에 n 붙이기
      result.append(n);

      //result 수식 계산, 만약 결과가 0이면 answerList에 수식 저장
      String s = result.toString();
      if (is0(s)) answerList.add(s);

      //result 뒤에 n 떼기
      result.deleteCharAt(result.length()-1);

      return;
    }

    result.append(num);

    for (int i = 0; i < oper.length; i++) {
      result.append(oper[i]);
      brute(num+1, result);
      result.deleteCharAt(result.length()-1);
    }

    result.deleteCharAt(result.length()-1);
  }

  private static boolean is0(String s) {
    s = s.replaceAll(" ", "");

    int result = 0;
    int start = 0;
    int end = 1;
    while (start < s.length()) {
      while (end < s.length()) {
        if (s.charAt(end) != '+' && s.charAt(end) != '-') {
          end++;
        } else {
          break;
        }
      }
      int num = Integer.parseInt(s.substring(start, end));
      if (start == 0 || s.charAt(start-1) == '+') result += num;
      else result -= num;

      start = end + 1;
      end = start + 1;
    }

    return result == 0;
  }
}