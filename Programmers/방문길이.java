class Solution {
  boolean[][][][] visited = new boolean[11][11][11][11];

  public int solution(String dirs) {
    int fromR = 5;
    int fromC = 5;
    int answer = 0;

    for (int i = 0; i < dirs.length(); i++) {
      char order = dirs.charAt(i);
      int toR = fromR;
      int toC = fromC;

      if (order == 'U') {
        toR -= 1;
      } else if (order == 'D') {
        toR += 1;
      } else if (order == 'L') {
        toC -= 1;
      } else if (order == 'R') {
        toC += 1;
      }

      if (toR < 0 || toR >= visited.length) continue;
      if (toC < 0 || toC >= visited[0].length) continue;

      if (!visited[fromR][fromC][toR][toC]) answer++;

      //양방향으로 처리
      visited[fromR][fromC][toR][toC] = true;
      visited[toR][toC][fromR][fromC] = true;

      fromR = toR;
      fromC = toC;
    }

    return answer;
  }
}