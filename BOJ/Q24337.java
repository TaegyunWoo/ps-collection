import java.io.*;
import java.util.*;

public class Q24337 {
  static int n;
  static int a;
  static int b;
  static List<Integer> heights = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    a = Integer.parseInt(tmp[1]);
    b = Integer.parseInt(tmp[2]);

    //solution
    int maxHeight = 0;

    //add gahui
    for (int i = 1; i < a; i++) {
      heights.add(i);
      maxHeight = Math.max(maxHeight, i);
    }

    //add danbi
    for (int i = 1; i < b; i++) {
      heights.add(b - i);
      maxHeight = Math.max(maxHeight, i);
    }

    //가운데 건물 삽입
    heights.add(a - 1, maxHeight+1);

    //건물 갯수가 n을 넘으면 -1
    if (heights.size() > n) {
      System.out.println(-1);
      return;
    }

    //print answer
    StringBuilder answer = new StringBuilder();
    answer.append(heights.get(0) + " ");
    //부족한 높이 개수만큼 설정
    for (int i = 0; i < n - heights.size(); i++) {
      answer.append(1 + " ");
    }
    //도출한 높이 설정
    for (int i = 1; i < heights.size(); i++) {
      answer.append(heights.get(i) + " ");
    }
    System.out.print(answer.toString());
  }
}