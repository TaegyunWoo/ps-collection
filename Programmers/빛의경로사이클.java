import java.util.*;

class Solution {
  int rLength;
  int cLength;
  boolean[][][] visited;
  List<Integer> answerList = new ArrayList<>();

  public int[] solution(String[] grid) {
    //Init
    rLength = grid.length;
    cLength = grid[0].length();
    visited = new boolean[grid.length][grid[0].length()][4];

    //Solution
    for (int r = 0; r < rLength; r++) {
      for (int c = 0; c < cLength; c++) {
        for (int dir = 0; dir < 4; dir++) {
          if (visited[r][c][dir]) continue;

          int cycleLength = getCycleLength(grid, new Info(r, c, dir));
          answerList.add(cycleLength);
        }
      }
    }

    //Sort
    answerList.sort(Comparator.naturalOrder());

    return answerList.stream().mapToInt(i->i).toArray();
  }

  private int getCycleLength(String[] grid, Info startInfo) {
    int cycleLength = 1;
    int currentR = startInfo.r;
    int currentC = startInfo.c;
    int currentDir = startInfo.dir;

    visited[currentR][currentC][currentDir] = true;

    while (true) {
      Info nextInfo = getNextInfo(grid, currentR, currentC, currentDir);

      if (visited[nextInfo.r][nextInfo.c][nextInfo.dir]) return cycleLength;


      visited[nextInfo.r][nextInfo.c][nextInfo.dir] = true;
      cycleLength++;

      currentR = nextInfo.r;
      currentC = nextInfo.c;
      currentDir = nextInfo.dir;
    }
  }

  private Info getNextInfo(String[] grid, int currentR, int currentC, int currentDir) {
    int nextR = currentR;
    int nextC = currentC;
    int nextDir = currentDir;
    char c = grid[currentR].charAt(currentC);
    if (c == 'S') {
      if (currentDir == 0) nextR = (nextR - 1 < 0) ? rLength - 1 : nextR - 1;
      if (currentDir == 1) nextR = (nextR + 1 > rLength - 1) ? 0 : nextR + 1;
      if (currentDir == 2) nextC = (nextC - 1 < 0) ? cLength - 1 : nextC - 1;
      if (currentDir == 3) nextC = (nextC + 1 > cLength - 1) ? 0 : nextC + 1;
    } else if (c == 'L') {
      if (currentDir == 0) {
        nextC = (nextC - 1 < 0) ? cLength - 1 : nextC - 1;
        nextDir = 2;
      }
      if (currentDir == 1) {
        nextC = (nextC + 1 > cLength - 1) ? 0 : nextC + 1;
        nextDir = 3;
      }
      if (currentDir == 2) {
        nextR = (nextR + 1 > rLength - 1) ? 0 : nextR + 1;
        nextDir = 1;
      }
      if (currentDir == 3) {
        nextR = (nextR - 1 < 0) ? rLength - 1 : nextR - 1;
        nextDir = 0;
      }
    } else if (c == 'R') {
      if (currentDir == 0) {
        nextC = (nextC + 1 > cLength - 1) ? 0 : nextC + 1;
        nextDir = 3;
      }
      if (currentDir == 1) {
        nextC = (nextC - 1 < 0) ? cLength - 1 : nextC - 1;
        nextDir = 2;
      }
      if (currentDir == 2) {
        nextR = (nextR - 1 < 0) ? rLength - 1 : nextR - 1;
        nextDir = 0;
      }
      if (currentDir == 3) {
        nextR = (nextR + 1 > rLength - 1) ? 0 : nextR + 1;
        nextDir = 1;
      }
    }

    return new Info(nextR, nextC, nextDir);
  }

  class Info {
    public int r; //행번호
    public int c; //열번호
    public int dir; //방향

    public Info(int r, int c, int dir) {
      this.r = r;
      this.c = c;
      this.dir = dir;
    }
  }
}