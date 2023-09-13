import java.io.*;
import java.util.*;

public class Q16234 {
  static final int[] dr = {-1, 1, 0, 0};
  static final int[] dc = {0, 0, -1, 1};
  static int n;
  static int l;
  static int r;
  static int[][] beforeA;
  static int[][] A;
  static boolean[][] visitedOpen; //국경 확인용
  static boolean[][] visitedCal; //인구 계산용
  static boolean[][] open; //국경 기록

  public static void main(String[] args) throws IOException {
    //init
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split(" ");
    n = Integer.parseInt(tmp[0]);
    l = Integer.parseInt(tmp[1]);
    r = Integer.parseInt(tmp[2]);
    beforeA = new int[n][n];
    A = new int[n][n];
    for (int i = 0; i < n; i++) {
      tmp = br.readLine().split(" ");
      for (int u = 0; u < n; u++) {
        int p = Integer.parseInt(tmp[u]);
        A[i][u] = p;
        beforeA[i][u] = p;
      }
    }
    visitedOpen = new boolean[n][n];
    visitedCal = new boolean[n][n];
    open = new boolean[n*n][n*n];

    //solution
    int answer = 0;
    while (true) {
      //국경 계산
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
          if (visitedOpen[r][c]) continue;
          getOpenByBFS(r, c);
        }
      }

      //인구 이동 결과를 A에 적용
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
          if (visitedCal[r][c]) continue;
          calPopulationByBFS(r, c);
        }
      }

      //변동사항 확인
      boolean isChanged = false;
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
          if (A[r][c] != beforeA[r][c]) {
            isChanged = true;
            break;
          }
        }
      }

      //변동사항이 없다면
      if (!isChanged) break;

      answer++; //날짜 count

      //초기화
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
          beforeA[r][c] = A[r][c];
        }
      }
      visitedOpen = new boolean[n][n];
      visitedCal = new boolean[n][n];
      open = new boolean[n*n][n*n];
    }

    //print answer
    System.out.println(answer);
  }

  private static void getOpenByBFS(int startR, int startC) {
    Deque<int[]> deque = new ArrayDeque<>();
    visitedOpen[startR][startC] = true;
    deque.offerLast(new int[] {startR, startC});

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int currentR = current[0];
      int currentC = current[1];
      int currentPopul = A[currentR][currentC];

      for (int i = 0; i < 4; i++) {
        int nextR = currentR + dr[i];
        int nextC = currentC + dc[i];

        if (nextR < 0 || nextR >= n) continue;
        if (nextC < 0 || nextC >= n) continue;
        if (visitedOpen[nextR][nextC]) continue;

        int nextPopul = A[nextR][nextC];
        int cal = Math.abs(currentPopul - nextPopul);
        if (cal < l || cal > r) continue;

        deque.offerLast(new int[] {nextR, nextC});
        visitedOpen[nextR][nextC] = true;
        open[nextR * n + nextC][currentR * n + currentC] = true;
        open[currentR * n + currentC][nextR * n + nextC] = true;
      }
    }
  }

  private static void calPopulationByBFS(int startR, int startC) {
    Deque<int[]> deque = new ArrayDeque<>();
    HashSet<int[]> unitedSet = new HashSet<>();
    int population = A[startR][startC];
    int country = 1;
    int[] start = {startR, startC};
    unitedSet.add(start);
    visitedCal[startR][startC] = true;
    deque.offerLast(start);

    while (!deque.isEmpty()) {
      int[] current = deque.pollFirst();
      int currentR = current[0];
      int currentC = current[1];

      for (int i = 0; i < 4; i++) {
        int nextR = currentR + dr[i];
        int nextC = currentC + dc[i];

        if (nextR < 0 || nextR >= n) continue;
        if (nextC < 0 || nextC >= n) continue;
        if (visitedCal[nextR][nextC]) continue;

        if (!open[currentR * n + currentC][nextR * n + nextC]) continue;

        population += A[nextR][nextC];
        country++;
        int[] next = new int[] {nextR, nextC};
        unitedSet.add(next);
        deque.offerLast(next);
        visitedCal[nextR][nextC] = true;
      }
    }

    //set average
    int average = population / country;
    for (int[] item : unitedSet) {
      int r = item[0];
      int c = item[1];
      A[r][c] = average;
    }
  }
}