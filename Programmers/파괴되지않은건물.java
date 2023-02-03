
/**
 * 누적합 문제
 *
 *
 * [변화 구간 구하기]
 * {0, 0, 0, 0} 에서 0~2번까지 2를 뺀다면,
 * {-2, 0, 0, 2} 로 표현할 수 있음. (시작지점에 -2, 끝지점+1에 +2)
 * 그리고 최종 변화 값을 구할 땐, 누적합을 하면 됨.
 * {-2, 0, 0, 2} -> {-2, -2, 0, 2} -> {-2, -2, -2, 2} -> {-2, -2, -2, 0}
 * {-2, -2, -2, 0} 이 최종적인 변화값임
 *
 * [2차원 배열에서 변화 구간 구하기]
 * {
 *  {0, 0, 0, 0},
 *  {0, 0, 0, 0},
 *  {0, 0, 0, 0},
 *  {0, 0, 0, 0}
 * }
 *
 * -n을 (0,0) ~ (2,2) 의 직사각형 구간을 표현해야 한다면
 * 아래와 같이 표현할 수 있음.
 *
 * {
 *  {-n, 0, 0, n},
 *  {0, 0, 0, 0},
 *  {0, 0, 0, 0},
 *  {n, 0, 0, -n}
 * }
 *
 * 최종 변화 값을 구할 땐, 행마다 누적합, 열마다 누적합을 수행하면 됨.
 * 1. 행별 누적합 수행
 * {
 *  {-n, -n, -n, 0},
 *  {0, 0, 0, 0},
 *  {0, 0, 0, 0},
 *  {n, n, n, 0}
 * }
 *
 * 2. 열별 누적합 수행
 * {
 *  {-n, -n, -n, 0},
 *  {-n, -n, -n, 0},
 *  {-n, -n, -n, 0},
 *  {0, 0, 0, 0}
 * }
 *
 * [시간복잡도]
 * 1. 모든 변화 구간을 기록한다. 시간복잡도는 "O(k)" (k는 skill 개수)
 *    - '시작점'과 '끝점+1'에 표시하기만 하면 되기 때문에
 * 2. 최종적인 변화 값을 구한다. 시간복잡도는 "O(n*m)"
 *    - 누적합 연산 수행
 * 3. board 에 적용한다. 시간복잡도는 "O(n*m)"
 *    - 원소별 덧셈
 * 따라서 최종 시간복잡도는 "O(k + n*m)" 이다.
 * 완전탐색의 경우 "O(k*n*m)" 이다.
 */
class Solution {
  int[][] changeAry;

  public int solution(int[][] board, int[][] skill) {
    changeAry = new int[board.length+1][board[0].length+1];

    //변화 구간 기록
    saveChangeSectors(skill);
    //변화 값 계산 (누적합)
    prefixSum();
    //파괴되지 않은 건물 개수 count
    int answer = countAnswer(board);

    return answer;
  }

  //change Ary에 변화 구간 기록
  private void saveChangeSectors(int[][] skill) {
    int maxRow = changeAry.length - 1;
    int maxCol = changeAry[0].length - 1;

    for (int i = 0; i < skill.length; i++) {
      int type = skill[i][0];
      int startRow = skill[i][1];
      int startCol = skill[i][2];
      int endRow = skill[i][3];
      int endCol = skill[i][4];
      int degree = (type == 1) ? -skill[i][5] : skill[i][5];

      //시작점에 값 기록
      changeAry[startRow][startCol] += degree;
      changeAry[endRow+1][endCol+1] += degree;
      //끝점+1에 값 기록
      changeAry[startRow][endCol+1] += -degree;
      changeAry[endRow+1][startCol] += -degree;
    }
  }

  //최종 변화 값 계산 (누적합)
  private void prefixSum() {
    //행 누적합
    for (int i = 0; i < changeAry.length; i++) {
      for (int u = 1; u < changeAry[i].length; u++) {
        changeAry[i][u] += changeAry[i][u-1];
      }
    }
    //열 누적합
    for (int i = 0; i < changeAry[0].length; i++) {
      for (int u = 1; u < changeAry.length; u++) {
        changeAry[u][i] += changeAry[u-1][i];
      }
    }
  }

  //정답 구하기
  private int countAnswer(int[][] board) {
    int answer = 0;
    for (int i = 0; i < board.length; i++) {
      for (int u = 0; u < board[i].length; u++) {
        if (board[i][u] + changeAry[i][u] > 0) answer++;
      }
    }
    return answer;
  }
}