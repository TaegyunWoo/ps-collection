import java.io.*;
import java.util.*;

public class Q22251 {
  static final boolean[][] num = {
      {true, true, true, false, true, true, true}, //0
      {false, false, true, false, false, true, false}, //1
      {true, false, true, true, true, false, true}, //2
      {true, false, true, true, false, true, true}, //3
      {false, true, true, true, false, true, false}, //4
      {true, true, false, true, false, true, true}, //5
      {true, true, false, true, true, true, true}, //6
      {true, false, true, false, false, true, false}, //7
      {true, true, true, true, true, true, true}, //8
      {true, true, true, true, false, true, true}, //9
  };
  static int n; //층의 총수, 반전된 k개의 수가 만드는 층수의 최대수
  static int k; //자리수
  static int p; //반전시킬 최대 LED 수
  static int[] x; //현재 엘베의 층수
  static Set<Integer> answerSet = new HashSet<>(); //반전된 결과

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    k = Integer.parseInt(tmp[1]);
    p = Integer.parseInt(tmp[2]);
    int xNum = Integer.parseInt(tmp[3]);
    x = new int[k];
    for (int i = k-1; i >= 0; i--) {
      if (xNum == 0) break;
      x[i] = xNum % 10;
      xNum /= 10;
    }
    xNum = Integer.parseInt(tmp[3]);


    //solution
    int[] ary = new int[k];
    brute(0, 0, ary);

    //print answer
    answerSet.remove(xNum);
    System.out.println(answerSet.size());
  }

  private static void brute(int depth, int changedCount, int[] result) {
    if (changedCount > p) return; //p보다 많은 수의 LED를 반전시켰다면

    if (depth == k) { //끝 자리까지 살펴봤다면
      if (changedCount == 0) return; //아예 반전시키지 않았다면

      int resultNum = 0;
      int tmp = 1;
      for (int i = k-1; i >= 0; i--) {
        resultNum += result[i] * tmp;
        tmp *= 10;
      }
      if (resultNum >= 1 && resultNum <= n) { //유효한 층수라면
        answerSet.add(resultNum); //정답에 추가
      }

      return;
    }

    //현재 층수의 depth 자리수의 숫자
    int currentIntNum = x[depth];
    //복사
    boolean[] currentNum = new boolean[7];
    for (int i = 0; i < 7; i++) {
      currentNum[i] = num[currentIntNum][i];
    }

    //재귀
    for (int i = 0; i <= 7; i++) {
      //currentNum의 i개 LED를 반전시켰을 때, 나오는 숫자 구하기
      List<Integer> reversedNumList = new ArrayList<>();
      getReversedNumList(currentNum, i, reversedNumList, 0);

      //반전된 숫자 적용 후, 다음 자리수 살펴보기
      for (int reversedNum : reversedNumList) {
        result[depth] = reversedNum;
        brute(depth + 1, changedCount + i, result); //재귀
      }
    }
  }

  private static void getReversedNumList(boolean[] currentNum, int changeCount, List<Integer> result, int idxStart) {
    if (changeCount == 0) {
      //유효한 숫자인지 확인 후, result에 add
      for (int i = 0; i < num.length; i++) {
        boolean valid = true;
        for (int u = 0; u < 7; u++) {
          if (currentNum[u] != num[i][u]) {
            valid = false;
            break;
          }
        }
        if (valid) {
          result.add(i);
          break;
        }
      }

      return;
    }

    for (int i = idxStart; i < 7; i++) {
      currentNum[i] = currentNum[i] ? false : true;
      getReversedNumList(currentNum, changeCount-1, result, i + 1);
      currentNum[i] = currentNum[i] ? false : true;
    }
  }
}