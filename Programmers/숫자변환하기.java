import java.util.*;

/**
 * [점화식 정의]
 * d[a][0] = n을 더해서 a로 만드는 최소 연산수
 * d[a][1] = 2를 곱해서 a로 만드는 최소 연산수
 * d[a][2] = 3을 곱해서 a로 만드는 최소 연산수
 *
 * [점화식]
 * d[a][0] = min(d[a-n][0], d[a-n][1], d[a-n][2]) + 1
 * d[a][1] = min(d[a/2][0], d[a/2][1], d[a/2][2]) + 1 (단, 나누어떨어지지 않으면 -1)
 * d[a][1] = min(d[a/3][0], d[a/3][1], d[a/3][2]) + 1 (단, 나누어떨어지지 않으면 -1)
 */
class Solution {
  public static final int INF = (int) 1e9;
  int[][] d;

  public int solution(int x, int y, int n) {
    //배열 초기화
    d = new int[y+1][3];
    for (int i = 0; i < d.length; i++) {
      Arrays.fill(d[i], INF);
    }
    d[x][0] = 0;
    d[x][1] = 0;
    d[x][2] = 0;

    //DP
    for (int i = x; i <= y; i++) {
      if (i - n >= x) {
        d[i][0] = Math.min(d[i-n][0], d[i-n][1]);
        d[i][0] = Math.min(d[i][0], d[i-n][2]);
        d[i][0]++;
      }
      if (i % 2 == 0) {
        d[i][1] = Math.min(d[i/2][0], d[i/2][1]);
        d[i][1] = Math.min(d[i][1], d[i/2][2]);
        d[i][1]++;
      }
      if (i % 3 == 0) {
        d[i][2] = Math.min(d[i/3][0], d[i/3][1]);
        d[i][2] = Math.min(d[i][2], d[i/3][2]);
        d[i][2]++;
      }
    }

    //정답 도출
    int answer = Math.min(d[y][0], d[y][1]);
    answer = Math.min(answer, d[y][2]);
    return (answer == INF) ? -1 : answer;
  }
}