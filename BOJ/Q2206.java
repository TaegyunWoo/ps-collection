import java.io.*;
import java.util.*;

/**
 * [해결방법]
 * BFS와 3차원 배열을 사용하여 해결해야 한다.
 *
 * < int[][][] count = 해당 위치의 최단거리를 저장하는 배열 >
 * - "count[a][b][0] = count[A][B][0] + 1" :
 *      (A,B)에서 (a,b) 위치로 벽을 부수지 않고 이동할 때
 *
 * - "count[a][b][0] = -1" :
 *      (A,B)에서 (a,b) 위치로 벽을 부수지 않고 이동할 수 없을 때
 *
 * - "count[a][b][1] = count[A][B][0] + 1" :
 *      (A,B)에서 (a,b) 위치로 이동하며, 해당 위치의 벽을 부술 때
 *
 * - "count[a][b][1] = count[a][b][1] + 1" :
 *      벽을 부순 경험이 있고, (A,B)에서 (a,b) 위치로 이동하는데 (a,b)에 벽이 없을 때
 *
 * - "count[a][b][1] = -1" :
 *      벽을 부순 경험이 있고, (A,B)에서 (a,b) 위치로 이동하는데 (a,b)에 벽이 있을 때
 */
public class Q2206 {

  static int n;
  static int m;
  static int[][] map;

  public static void main(String[] args)throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new int[n+1][m+1];

    for (int i = 1; i <= n; i++) {
      String[] tmp = br.readLine().split("");
      for (int u = 0; u < tmp.length; u++) {
        map[i][u+1] = Integer.parseInt(tmp[u]);
      }
    }

    solution();
  }

  private static void solution() {
    int[][][] visited = bfs();
    int resultA = visited[n][m][0];
    int resultB = visited[n][m][1];

    if (resultA <= 0 && resultB <= 0) {
      System.out.println(-1);
    } else if (resultA <= 0) {
      System.out.println(resultB);
    } else if (resultB <= 0) {
      System.out.println(resultA);
    } else {
      System.out.println(Math.min(resultA, resultB));
    }
  }

  private static int[][][] bfs() {
    //(a, b)까지 도달하는데 벽을 부순적이 있다면, visited[a][b][1] = 움직인 칸 수
    //(a, b)까지 도달하는데 벽을 부순적이 없다면, visited[a][b][0] = 움직인 칸 수
    int[][][] count = new int[n+1][m+1][2];
    boolean[][][] visited = new boolean[n+1][m+1][2];
    Deque<Position> deque = new ArrayDeque<>();
    deque.addLast(new Position(1, 1));
    count[1][1][0] = 1;
    count[1][1][1] = 1;

    while (!deque.isEmpty()) {
      Position current = deque.pollFirst();
      List<Position> nearList = new ArrayList<>();
      nearList.add(new Position(current.getRow()-1, current.getCol()));
      nearList.add(new Position(current.getRow()+1, current.getCol()));
      nearList.add(new Position(current.getRow(), current.getCol()-1));
      nearList.add(new Position(current.getRow(), current.getCol()+1));

      for (Position near : nearList) {
        boolean flag = false;
        if (near.getRow() < 1 || near.getCol() < 1) continue;
        if (near.getRow() > n || near.getCol() > m) continue;

        //------------벽을 한번도 안부수고 near칸에 도착하는 경우------------

        if (!visited[near.getRow()][near.getCol()][0]) { //near 칸을 방문하지 않았고
          if (map[near.getRow()][near.getCol()] == 0 && count[current.getRow()][current.getCol()][0] != -1) { //이동이 가능하다면
            count[near.getRow()][near.getCol()][0] = count[current.getRow()][current.getCol()][0] + 1;
            flag = true;
            visited[near.getRow()][near.getCol()][0] = true;
          } else { //이동이 불가능하다면
            count[near.getRow()][near.getCol()][0] = -1;
          }
        }

        //------------벽을 한번 부수고 near칸에 도착하는 경우------------

        if (!visited[near.getRow()][near.getCol()][1]) { //near 칸을 방문하지 않았고
          if (map[near.getRow()][near.getCol()] == 1) { //벽으로 막혀있는 경우
            if (count[current.getRow()][current.getCol()][0] != -1) { //이동이 가능한 경우
              count[near.getRow()][near.getCol()][1] = count[current.getRow()][current.getCol()][0] + 1;
              flag = true;
              visited[near.getRow()][near.getCol()][1] = true;
            } else { //이동이 불가능한 경우
              count[near.getRow()][near.getCol()][1] = -1;
            }
          } else { //벽으로 막혀있지 않은 경우
            count[near.getRow()][near.getCol()][1] = count[current.getRow()][current.getCol()][1] + 1;
            flag = true;
            visited[near.getRow()][near.getCol()][1] = true;
          }
        }

        if (flag) {
          deque.addLast(near);
        }

      }
    }

    return count;
  }


  private static class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public int getRow() {
      return row;
    }

    public int getCol() {
      return col;
    }
  }

}