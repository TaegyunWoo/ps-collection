import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q2448 {
  static int n;
  static StringBuilder[] result;
  static int colLength = 0;
  static List<List<Integer>> topPointList = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    result = new StringBuilder[n];
    StringBuilder tmp = new StringBuilder();
    colLength = 5*(n/3) + (n/3+1);
    for (int i = 0; i < colLength; i++) {
      tmp.append(" ");
    }
    for (int i = 0; i < n; i++) {
      result[i] = new StringBuilder(tmp);
    }
    for (int i = 0; i < n; i++) {
      topPointList.add(new ArrayList<>());
    }

    //solution
    List<Integer> bottomCList = new ArrayList<>();
    for (int i = 0; i < colLength-5; i+=6) {
      bottomCList.add(i);
    }
    makeTri(bottomCList, n-1);

    //print answer
    for (int i = 0; i < n; i++) {
      System.out.println(result[i].toString());
    }
  }

  private static void makeTri(List<Integer> bottomCList, int depth) {
    if (depth <= 0) return;


    for (int bottomC : bottomCList) {
      //3번째 row 채우기
      for (int i = 0; i < 5; i++) {
        result[depth].setCharAt(bottomC+i, '*');
      }

      //2번째 row 채우기
      for (int i = 1; i < 4; i+=2) {
        result[depth-1].setCharAt(bottomC+i, '*');
      }

      //1번째 row 채우기
      result[depth-2].setCharAt(bottomC+2, '*');
    }

    //다음 Bottom 값
    List<Integer> nextBottomCList = new ArrayList<>();
    for (int i = 0; i < bottomCList.size()-1; i+=2) {
      int topC1 = bottomCList.get(i) + 2;
      int topC2 = bottomCList.get(i+1) + 2;
      for (int u = topC1+1; u < topC2; u+=6) {
        nextBottomCList.add(u);
      }
    }

    //재귀
    makeTri(nextBottomCList, depth - 3);
  }
}