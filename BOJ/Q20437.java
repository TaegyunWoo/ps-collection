import java.io.*;
import java.util.*;

public class Q20437 {
  static final int MAX = (int) 1e9;
  static int t;
  static String w;
  static int k;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());

    //solution
    StringBuilder answer = new StringBuilder();
    while (t-- > 0) {
      int minLength = MAX;
      int maxLength = 0;
      w = br.readLine();
      k = Integer.parseInt(br.readLine());

      for (char c = 'a'; c <= 'z'; c++) {
        minLength = Math.min(minLength, getMinLength(c)); //3번 조건의 결과 찾기
        maxLength = Math.max(maxLength, getMaxLength(c)); //4번 조건의 결과 찾기
      }

      if (minLength == MAX)
        answer.append(-1 + "\n");
      else
        answer.append(minLength + " " + maxLength + "\n");
    }

    System.out.println(answer);
  }

  private static int getMinLength(char c) {
    int start = 0;
    int end = 0;
    int count = 0;
    int minLength = MAX;

    while (end < w.length()) {
      if (w.charAt(end) == c) count++; //c 문자 개수 세기
      if (count == k) { //k개의 c 문자를 start ~ end 범위에서 찾았다면
        while (w.charAt(start) != c) { //start 줄이기
          start++;
        }
        minLength = Math.min(minLength, end - start + 1); //갱신

        start++;
        count--;
      }
      end++;
    }

    return minLength;
  }

  private static int getMaxLength(char c) {
    List<Integer> cIdxList = new ArrayList<>();
    for (int i = 0; i < w.length(); i++) {
      if (w.charAt(i) == c) cIdxList.add(i); //c의 위치 기록
    }

    int maxLength = 0;
    for (int start = 0; start < cIdxList.size(); start++) {
      int end = start + k - 1;
      if (end >= cIdxList.size()) break;
      maxLength = Math.max(maxLength, cIdxList.get(end) - cIdxList.get(start) + 1);
    }
    return maxLength;
  }
}