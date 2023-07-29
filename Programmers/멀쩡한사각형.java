import java.util.*;

class Solution {
  public long solution(int w, int h) {
    int exceptionCount = 0;

    int leftTopX = 0; //대각선에 포함되는지 확인할 박스의 x 값
    for (int y = h; y > 0; y--) {

      while (true) {
        double bottomXOfDiagonal = getXofDiagonal(y-1, w, h);
        double rightYOfDiagonal = getYofDiagonal(leftTopX+1, w, h);

        if (leftTopX < bottomXOfDiagonal && leftTopX+1 >= bottomXOfDiagonal)
          exceptionCount++;
        else if (y-1 <= rightYOfDiagonal && y > rightYOfDiagonal)
          exceptionCount++;
        else {
          if (leftTopX != bottomXOfDiagonal)
            leftTopX--;
          break;
        }
        leftTopX++;
      }

    }

    return (long) w*h - exceptionCount;
  }

  //x에 대해, 대각선 위에 있는 y 값 구하기
  private double getYofDiagonal(int x, double w, double h) {
    return -h / w * x + h;
  }

  //y에 대해, 대각선 위에 있는 x 값 구하기
  private double getXofDiagonal(int y, double w, double h) {
    return (h-y) * w / h;
  }
}