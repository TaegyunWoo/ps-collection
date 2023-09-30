import java.io.*;
import java.util.*;

public class Q2668 {
  static int n;
  static int[] ary;
  static Set<Integer>[][] setAry;

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    ary = new int[n+1];
    for (int i = 1; i <= n; i++) {
      ary[i] = Integer.parseInt(br.readLine());
    }
    setAry = new Set[n+1][2]; //setAry[i][0] : i를 포함하는 윗쪽 집합, setAry[i][1] : ary[i]를 포함하는 아래쪽 집합,
    for (int i = 1; i <= n; i++) {
      setAry[i][0] = new HashSet<>();
      setAry[i][1] = new HashSet<>();
    }

    //solution
    Set answerSet = solution();

    //print answer
    StringBuilder answer = new StringBuilder(answerSet.size() + "\n");
    List<Integer> answerList = new ArrayList<>(answerSet);
    answerList.sort(Comparator.naturalOrder());
    for (int num : answerList) {
      answer.append(num + "\n");
    }
    System.out.print(answer.toString());
  }

  private static Set<Integer> solution() {
    for (int i = 1; i < ary.length; i++) {
      int a = i;
      int b = ary[i];

      while (!setAry[i][0].contains(a)) {
        setAry[i][0].add(a);
        setAry[i][1].add(b);
        a = b;
        b = ary[b];
      }
    }

    Set<Integer> answerSet = new HashSet<>();

    //윗쪽 집합과 아랫쪽 집합이 같은지 확인
    for (int i = 1; i <= n; i++) {
      Set<Integer> setA = setAry[i][0];
      Set<Integer> setB = setAry[i][1];
      boolean flag = true;

      if (setA.size() != setB.size()) continue;
      for (int a : setA) {
        if (!setB.contains(a)) {
          flag = false;
          break;
        }
      }

      if (!flag) continue;

      //윗쪽 집합과 아랫쪽 집합이 같다면
      answerSet.addAll(setA);
    }

    return answerSet;
  }
}