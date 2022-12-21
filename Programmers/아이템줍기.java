import java.util.*;

class Solution {
  /**
   * map[x][y] = 0 : (x,y) 좌표는 빈 공간
   * map[x][y] = 1 : (x,y) 좌표는 직사각형의 외곽 (겹친것 고려)
   * map[x][y] = 2 : (x,y) 좌표는 직사각형의 내부 (겹친것 고려)
   */
  int[][] map;
  int[][] countAry;
  boolean[][] visited;
  int[] dx = {-1, 1, 0, 0};
  int[] dy = {0, 0, -1, 1};
  final int INF = (int) 1e9;
  int answer = INF;

  public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
    //map에 직사각형 기록
    initMap(rectangle);

    //countAry 초기화
    countAry = new int[101][101];
    for (int i = 0; i < countAry.length; i++) {
      Arrays.fill(countAry[i], INF);
    }
    countAry[characterX*2][characterY*2] = 0;

    //dfs
    visited = new boolean[101][101];
    dfs(characterX*2, characterY*2, itemX*2, itemY*2);

    //정답 출력
    return answer / 2;
  }

  private void dfs(int startX, int startY, int itemX, int itemY) {
    if (startX == itemX && startY == itemY) {
      answer = Math.min(answer, countAry[startX][startY]);
      return;
    } else {
      visited[startX][startY] = true;
    }

    for (int i = 0; i < 4; i++) {
      int nearX = startX + dx[i];
      int nearY = startY + dy[i];

      if (nearX < 2 || nearX > 100) continue; //맵 밖으로 나간 경우
      if (nearY < 2 || nearY > 100) continue; //맵 밖으로 나간 경우
      if (map[nearX][nearY] != 1) continue; //외곽이 아닌 경우
      if (visited[nearX][nearY]) continue;

      countAry[nearX][nearY] = countAry[startX][startY] + 1;
      dfs(nearX, nearY, itemX, itemY);
    }
  }

  //전체 map을 2배로 확대해야함
  private void initMap(int[][] rectangle) {
    map = new int[101][101];

    //직사각형마다 반복
    for (int i = 0; i < rectangle.length; i++) {
      int leftX = rectangle[i][0];
      int leftY = rectangle[i][1];
      int rightX = rectangle[i][2];
      int rightY = rectangle[i][3];

      //해당 좌표가 직사각형의 외곽인지, 내부인지 기록
      for (int x = leftX * 2; x <= rightX * 2; x++) {
        for (int y = leftY * 2; y <= rightY * 2; y++) {
          if (x == leftX * 2 || x == rightX * 2) {
            map[x][y] = Math.max(map[x][y], 1);
          } else if (y == leftY * 2 || y == rightY * 2) {
            map[x][y] = Math.max(map[x][y], 1);
          } else {
            map[x][y] = Math.max(map[x][y], 2);
          }
        }
      }
    }
  }
}