import java.io.*;
import java.util.*;

public class Q7682 {
  static final int[][][] madeLocation = {
      {{0, 0}, {0, 1}, {0, 2}},
      {{1, 0}, {1, 1}, {1, 2}},
      {{2, 0}, {2, 1}, {2, 2}},
      {{0, 0}, {1, 0}, {2, 0}},
      {{0, 1}, {1, 1}, {2, 1}},
      {{0, 2}, {1, 2}, {2, 2}},
      {{0, 0}, {1, 1}, {2, 2}},
      {{0, 2}, {1, 1}, {2, 0}},
  };
  static int totalCount;
  static List<int[]> xList;
  static List<int[]> oList;
  static char[][] board;
  static StringBuilder answer = new StringBuilder();
  static boolean isValid = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();

    while (!input.equals("end")) {
      //X, O 개수 확인
      if (!isValidCount(input)) {
        answer.append("invalid\n");
        input = br.readLine();
        continue;
      }

      //totalCount 구하기
      totalCount = getTotalCount(input);

      //X, O 좌표 저장
      xList = new ArrayList<>();
      oList = new ArrayList<>();
      addXOLocation(input);

      //brute force
      board = new char[3][3];
      for (int i = 0; i < 3; i++) Arrays.fill(board[i], '.');
      isValid = false;
      brute(0, 0, true);
      if (isValid) answer.append("valid\n");
      else answer.append("invalid\n");

      input = br.readLine();
    }

    //print answer
    System.out.println(answer.toString());
  }

  private static boolean isValidCount(String input) {
    int countX = 0;
    int countO = 0;
    for (int i = 0; i < 9; i++) {
      if (input.charAt(i) == 'X') countX++;
      else if (input.charAt(i) == 'O') countO++;
    }
    return countX - countO == 0 || countX - countO == 1;
  }

  private static int getTotalCount(String input) {
    int count = 0;
    for (int i = 0; i < 9; i++) {
      if (input.charAt(i) != '.') count++;
    }
    return count;
  }

  private static void addXOLocation(String input) {
    for (int i = 0; i < 9; i++) {
      char c = input.charAt(i);
      if (c == 'X') {
        xList.add(new int[] {i/3, i%3});
      } else if (c == 'O') {
        oList.add(new int[] {i/3, i%3});
      }
    }
  }

  private static void brute(int xCount, int oCount, boolean isX) {
    boolean isMade = isMade();

    if (xCount + oCount == totalCount) {
      if (isMade) isValid = true; //모든 X,O을 다 두었을 때, 승패가 결정됐다면
      if (xCount + oCount == 9) isValid = true; //모든 X,O을 다 두었을 때, 보드가 꽉찼다면
      return;
    }
    if (isMade) return; //아직 모든 X,O을 두지 못했을 때, 이미 승패가 결정됐다면

    //X를 둘 차례라면
    if (isX) {
      for (int i = 0; i < xList.size(); i++) {
        int xr = xList.get(i)[0];
        int xc = xList.get(i)[1];
        if (board[xr][xc] == 'X') continue;
        board[xr][xc] = 'X';
        brute(xCount + 1, oCount, false);
        board[xr][xc] = '.';
      }

    } else { //O를 둘 차례라면
      for (int u = 0; u < oList.size(); u++) {
        int or = oList.get(u)[0];
        int oc = oList.get(u)[1];
        if (board[or][oc] == 'O') continue;
        board[or][oc] = 'O';
        brute(xCount, oCount + 1, true);
        board[or][oc] = '.';
      }
    }
  }

  private static boolean isMade() {
    for (int i = 0; i < madeLocation.length; i++) {
      int[][] location = madeLocation[i];
      char character = board[location[0][0]][location[0][1]];
      boolean made = true;
      if (character == '.') continue;

      for (int u = 1; u < location.length; u++) {
        int r = location[u][0];
        int c = location[u][1];
        if (character != board[r][c]) made = false;
      }

      if (made) return true;
    }
    return false;
  }
}