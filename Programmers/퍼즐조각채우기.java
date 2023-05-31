import java.util.*;

class Solution {
  int[] dr = {-1, 1, 0, 0};
  int[] dc = {0, 0, -1, 1};
  List<List<Position>> puzzleList;

  public int solution(int[][] game_board, int[][] table) {
    initPuzzleList(table);

    int answer = 0;
    for (int i = 0; i <= 3; i++) {
      answer += matchPuzzle(game_board);
      game_board = spinBoard(game_board);
    }

    return answer;
  }

  private void initPuzzleList(int[][] table) {
    puzzleList = new ArrayList<>();
    boolean[][] visited = new boolean[table.length][table.length];

    for (int r = 0; r < table.length; r++) {
      for (int c = 0; c < table.length; c++) {
        if (table[r][c] == 0 || visited[r][c]) continue;
        List<Position> result = bfs(new Position(r, c), table, visited);
        puzzleList.add(result);
      }
    }
  }

  private List<Position> bfs(Position start, int[][] graph, boolean[][] visited) {
    List<Position> positionList = new ArrayList<>();
    Deque<Position> deque = new ArrayDeque<>();
    deque.offerLast(start);
    visited[start.r][start.c] = true;
    positionList.add(start);

    while (!deque.isEmpty()) {
      Position current = deque.pollFirst();
      for (int i = 0; i < 4; i++) {
        int nextR = current.r + dr[i];
        int nextC = current.c + dc[i];

        if (nextR < 0 || nextR >= graph.length) continue;
        if (nextC < 0 || nextC >= graph.length) continue;
        if (graph[nextR][nextC] != graph[start.r][start.c]) continue;
        if (visited[nextR][nextC]) continue;

        Position next = new Position(nextR, nextC);
        deque.offerLast(next);
        visited[nextR][nextC] = true;
        positionList.add(next);
      }
    }

    transPosition(positionList);

    return positionList;
  }

  //(0,0)을 기준으로 좌표 수정
  private void transPosition(List<Position> list) {
    int minR = 100;
    int minC = 100;

    for (int i = 0; i < list.size(); i++) {
      Position position = list.get(i);
      minR = Math.min(minR, position.r);
      minC = Math.min(minC, position.c);
    }

    for (int i = 0; i < list.size(); i++) {
      Position position = list.get(i);
      position.r -= minR;
      position.c -= minC;
    }
  }

  //퍼즐을 맞춰보고, 채워진 공백 개수 반환
  private int matchPuzzle(int[][] board) {
    int matchCount = 0;
    boolean[][] visited = new boolean[board.length][board.length];

    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board.length; c++) {
        if (board[r][c] == 1 || visited[r][c]) continue;
        List<Position> result = bfs(new Position(r, c), board, visited);

        int foundPuzzleIndex = getMathedPuzzle(result);
        if (foundPuzzleIndex != -1) {
          matchCount += result.size();
          puzzleList.remove(foundPuzzleIndex);
          fillBoardToOne(new Position(r, c), board);
        }
      }
    }

    return matchCount;
  }

  //해당 빈 자리 (blankPositionList) 와 맞는 퍼즐의 index 반환
  private int getMathedPuzzle(List<Position> blankPositionList) {
    for (int i = 0; i < puzzleList.size(); i++) {
      List<Position> puzzlePositionList = puzzleList.get(i);

      if (blankPositionList.size() != puzzlePositionList.size()) continue;

      int blankListIdx = 0;
      for (int u = 0; u < puzzlePositionList.size(); u++) {
        if (blankListIdx == blankPositionList.size()) return i;

        Position blankPosition = blankPositionList.get(blankListIdx);
        Position puzzlePosition = puzzlePositionList.get(u);
        if (blankPosition.r == puzzlePosition.r && blankPosition.c == puzzlePosition.c) {
          blankListIdx++;
          u = -1;
        }
      }
    }

    return -1;
  }

  //start 좌표에서부터 0인 칸들을 BFS로 1로 채우는 메서드
  private void fillBoardToOne(Position start, int[][] board) {
    Deque<Position> deque = new ArrayDeque<>();
    deque.offerLast(start);
    board[start.r][start.c] = 1;

    while (!deque.isEmpty()) {
      Position current = deque.pollFirst();
      for (int i = 0; i < 4; i++) {
        int nextR = current.r + dr[i];
        int nextC = current.c + dc[i];

        if (nextR < 0 || nextR >= board.length) continue;
        if (nextC < 0 || nextC >= board.length) continue;
        if (board[nextR][nextC] == 1) continue;

        Position next = new Position(nextR, nextC);
        deque.offerLast(next);
        board[nextR][nextC] = 1;
      }
    }
  }

  //보드판을 90도 반시계 방향으로 회전시키는 메서드
  private int[][] spinBoard(int[][] board) {
    int[][] result = new int[board.length][board[0].length];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board.length; c++) {
        int newR = (board.length - 1) - c;
        int newC = r;
        result[newR][newC] = board[r][c];
      }
    }

    return result;
  }

  class Position {
    public int r;
    public int c;
    public Position(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
}