import java.util.*;

class Solution {
  String allNumber = "";

  public String solution(int n, int t, int m, int p) {
    tansfer(n);
    return getAnswer(t, p, m);
  }

  private void tansfer(int n) {
    StringBuilder result = new StringBuilder("0");
    int num = 1;
    for (int i = 0; i < 1000001; i++) {
      StringBuilder resultUnit = new StringBuilder();
      int tmp = num;
      while (tmp > 0) {
        int lastNum = tmp%n;
        switch (lastNum) {
          case 10:
            resultUnit.insert(0, 'A');
            break;
          case 11:
            resultUnit.insert(0, 'B');
            break;
          case 12:
            resultUnit.insert(0, 'C');
            break;
          case 13:
            resultUnit.insert(0, 'D');
            break;
          case 14:
            resultUnit.insert(0, 'E');
            break;
          case 15:
            resultUnit.insert(0, 'F');
            break;
          default:
            resultUnit.insert(0, lastNum);
        }
        tmp /= n;
      }
      num++;
      result.append(resultUnit);
    }

    allNumber = result.toString();
  }

  private String getAnswer(int t, int p, int m) {
    StringBuilder answer = new StringBuilder();
    int personIndex = 0;
    int numberIndex = 0;

    while (answer.length() < t) {
      if (personIndex == p - 1)
        answer.append(allNumber.charAt(numberIndex));
      personIndex++;
      personIndex %= m;
      numberIndex++;
    }

    return answer.toString();
  }
}