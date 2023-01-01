import java.util.*;

class Solution {
  int[] scores = new int[3];

  public int solution(String dartResult) {
    calculate(dartResult);

    return Arrays.stream(scores).sum();
  }

  private void calculate(String dartResult) {
    StringBuilder num = new StringBuilder();

    int index = -1;
    boolean wasNum = false;
    for (int i = 0; i < dartResult.length(); i++) {
      char c = dartResult.charAt(i);

      if (c >= '0' && c <= '9') {
        if (!wasNum) {
          index++;
          num = new StringBuilder();
        }
        num.append(c);
        wasNum = true;
      } else {
        if (scores[index] == 0) {
          scores[index] = Integer.parseInt(num.toString());
        }
        wasNum = false;

        if (c == 'D') {
          scores[index] = (int) Math.pow(scores[index], 2);
        } else if (c == 'T') {
          scores[index] = (int) Math.pow(scores[index], 3);
        } else if (c == '*') {
          if (index != 0) {
            scores[index-1] *= 2;
          }
          scores[index] *= 2;
        } else if (c == '#') {
          scores[index] *= -1;
        }
      }
    }
  }


}