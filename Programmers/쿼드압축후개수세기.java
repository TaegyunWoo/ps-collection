import java.util.*;

class Solution {
  int[][] totalMapArr;
  Deque<int[][]> areaDeque = new ArrayDeque<>();

  public int[] solution(int[][] arr) {
    totalMapArr = arr;

    int countZero = 0;
    int countOne = 0;

    //확인할 영역의 왼쪽 상단 좌표, 오른쪽 하단 좌표 추가
    areaDeque.offerLast(new int[][] {{0, 0}, {arr.length-1, arr.length-1}});

    //압축
    while (!areaDeque.isEmpty()) {
      int[][] area = areaDeque.pollFirst();
      int[] leftUp = area[0];
      int[] rightDown = area[1];

      boolean canArchive = checkArchive(leftUp, rightDown); //압축이 가능한지 확인

      if (canArchive) { //압축이 가능하다면
        if (totalMapArr[leftUp[0]][leftUp[1]] == 0) countZero++;
        else countOne++;

        archive(leftUp, rightDown);
        continue;
      }

      int[][][] splitedAreas = getSplitedArea(leftUp, rightDown); //4분할

      for (int i = 0; i < 4; i++) {
        areaDeque.offerLast(splitedAreas[i]);
      }
    }

    //압축되지 않은 부분 계산
    for (int r = 0; r < arr.length; r++) {
      for (int c = 0; c < arr.length; c++) {
        if (totalMapArr[r][c] == -1) continue;
        if (totalMapArr[r][c] == 0) countZero++;
        else countOne++;
      }
    }

    return new int[] {countZero, countOne};
  }

  private int[][][] getSplitedArea(int[] leftUp, int[] rightDown) {
    int size = rightDown[0] - leftUp[0] + 1;
    int[][] firstArea = new int[2][2];
    int[][] secondArea = new int[2][2];
    int[][] thirdArea = new int[2][2];
    int[][] fourthArea = new int[2][2];

    firstArea = new int[][] {
        {leftUp[0], leftUp[1]},
        {rightDown[0] - size/2, rightDown[1] - size/2}
    };
    secondArea = new int[][] {
        {leftUp[0], leftUp[1] + size/2},
        {rightDown[0] - size/2, rightDown[1]}
    };
    thirdArea = new int[][] {
        {leftUp[0] + size/2, leftUp[1]},
        {rightDown[0], rightDown[1] - size/2}
    };
    fourthArea = new int[][] {
        {leftUp[0] + size/2, leftUp[1] + size/2},
        {rightDown[0], rightDown[1]}
    };

    return new int[][][] {firstArea, secondArea, thirdArea, fourthArea};
  }

  private boolean checkArchive(int[] leftUp, int[] rightDown) {
    int num = totalMapArr[leftUp[0]][leftUp[1]];

    for (int r = leftUp[0]; r <= rightDown[0]; r++) {
      for (int c = leftUp[1]; c <= rightDown[1]; c++) {
        if (totalMapArr[r][c] != num) return false;
      }
    }

    return true;
  }

  private void archive(int[] leftUp, int[] rightDown) {
    for (int r = leftUp[0]; r <= rightDown[0]; r++) {
      for (int c = leftUp[1]; c <= rightDown[1]; c++) {
        totalMapArr[r][c] = -1;
      }
    }
  }
}