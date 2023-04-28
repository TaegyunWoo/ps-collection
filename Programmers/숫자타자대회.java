import java.util.*;

/**
 * [점화식 정의]
 * d[a][left][right] = a번째 숫자를 눌러서 왼손이 left, 오른손이 right에 위치했을 때 총 최소 가중치 합
 *
 * [점화식]
 * d[a][left][right] = min({d[a-1][0][0], d[a-1][0][1], ..., d[a-1][9][9]})
 * 단, left와 right가 같은 경우는 생략
 */
class Solution {
  public static final int INF = (int) 1e9;
  int[][] weight = {
      {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
      {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
      {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
      {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
      {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
      {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
      {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
      {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
      {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
      {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
  };
  int[][][] d;

  public int solution(String numbers) {
    //d 배열 초기화
    d = new int[numbers.length()][10][10];
    for (int i = 0; i < d.length; i++) {
      for (int u = 0; u < d[i].length; u++) {
        Arrays.fill(d[i][u], INF);
      }
    }
    int firstNum = numbers.charAt(0) - '0';
    d[0][firstNum][6] = weight[4][firstNum];
    d[0][4][firstNum] = weight[6][firstNum];

    //계산
    for (int i = 1; i < numbers.length(); i++) {
      int num = numbers.charAt(i) - '0';

      //가능한 모든 손 위치에 대해 반복
      for (int left = 0; left < 10; left++) {
        for (int right = 0; right < 10; right++) {
          if (left == right || d[i-1][left][right] == INF) continue;

          //왼손을 num으로 이동
          d[i][num][right] = Math.min(d[i][num][right], d[i-1][left][right] + weight[left][num]);
          //오른손을 num으로 이동
          d[i][left][num] = Math.min(d[i][left][num], d[i-1][left][right] + weight[right][num]);
        }
      }

    }

    int answer = INF;
    for (int left = 0; left < 10; left++) {
      for (int right = 0; right < 10; right++)
        answer = Math.min(answer, d[numbers.length()-1][left][right]);
    }
    return answer;
  }
}